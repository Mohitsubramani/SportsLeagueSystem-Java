package league;

import java.time.LocalDate;
import java.util.*;

public class LeagueService {
    private Map<String, Team> teams = new LinkedHashMap<>();
    private Map<String, Match> fixtures = new LinkedHashMap<>();

    
    public void registerTeam(Team t) { 
        teams.put(t.getTeamId(), t); 
    }

    public void registerPlayerToTeam(String teamId, Player p) {
        Team t = teams.get(teamId);
        if (t != null) t.addPlayer(p);
    }

    
    public void generateFixtures(LocalDate startDate) {
        List<Team> list = new ArrayList<>(teams.values());
        int matchCounter = fixtures.size() + 1;

        System.out.println("--- Upcoming Fixtures "); 
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                String id = "M" + String.format("%03d", matchCounter++);
                Match match = new Match(id, list.get(i), list.get(j), startDate.plusDays(matchCounter));
                fixtures.put(id, match);

               
                System.out.println(match.getMatchId() + ": "
                                   + match.getHomeTeam().getName() + " vs "
                                   + match.getAwayTeam().getName()
                                   + " on " + match.getDate());
            }
        }
    }


    
    public void recordResult(String matchId, String scoreline) {
        Match m = fixtures.get(matchId);
        if (m == null) return;

        m.setScorecard(scoreline);
        String[] parts = scoreline.split("-");
        int home = Integer.parseInt(parts[0].trim());
        int away = Integer.parseInt(parts[1].trim());

        if (home > away) {
            m.setResult("Home Win");
            updatePoints(m.getHomeTeam(), 3);
        } else if (away > home) {
            m.setResult("Away Win");
            updatePoints(m.getAwayTeam(), 3);
        } else {
            m.setResult("Draw");
            updatePoints(m.getHomeTeam(), 1);
            updatePoints(m.getAwayTeam(), 1);
        }
    }

    public void recordResult(String matchId, String scoreline,
                             Map<String, Map<String,Integer>> playerStats) {
        recordResult(matchId, scoreline);
        for (var e : playerStats.entrySet()) {
            Player p = findPlayerById(e.getKey());
            if (p != null) p.ratingUpdate(e.getValue());
        }
    }

    private void updatePoints(Team team, int delta) { 
        team.setPoints(team.getPoints() + delta); 
    }

    private Player findPlayerById(String pid) {
        for (Team t : teams.values()) {
            for (Player p : t.getPlayers()) {
                if (p.getPlayerId().equals(pid)) return p;
            }
        }
        return null;
    }

   
    public void standings() {
        if (teams.isEmpty()) {
            System.out.println("No teams registered yet!");
            return;
        }

        System.out.println("\n LEAGUE STANDINGS ");

        List<Team> sortedTeams = new ArrayList<>(teams.values());
        sortedTeams.sort((t1, t2) -> Integer.compare(t2.getPoints(), t1.getPoints()));

        System.out.printf("%-5s %-25s %-15s %-10s%n", "Rank", "Team Name", "City", "Points");

        int rank = 1;
        for (Team t : sortedTeams) {
            System.out.printf("%-5d %-25s %-15s %-10d%n",
                              rank++, t.getName(), t.getCity(), t.getPoints());
        }
    }

    
    public void printLeagueTable() {
        if (teams.isEmpty()) {
            System.out.println("No teams registered yet!");
            return;
        }

        System.out.println("League Table ");

        List<Team> sortedTeams = new ArrayList<>(teams.values());
        sortedTeams.sort((t1, t2) -> Integer.compare(t2.getPoints(), t1.getPoints()));

        int pos = 1;
        for (Team t : sortedTeams) {
            System.out.printf("%d. %-20s %d pts%n", pos++, t.getName(), t.getPoints());
        }
    }

    
    public void showResults() {
        System.out.println("Completed Matches ");
        for (Match m : fixtures.values()) {
            if (m.getResult() != null) {
                System.out.println(m.getMatchId() + ": "
                                   + m.getHomeTeam().getName() + " vs "
                                   + m.getAwayTeam().getName()
                                   + " → " + m.getResult());
            }
        }
    }

    public void showFixtures() {
        System.out.println("Upcoming Fixtures");
        for (Match m : fixtures.values()) {
            if (m.getResult() == null) {
                System.out.println(m.getMatchId() + ": "
                                   + m.getHomeTeam().getName() + " vs "
                                   + m.getAwayTeam().getName()
                                   + " on " + m.getDate());
            }
        }
    }

    
    public void computeAwards() {
        System.out.println("\n Awards ");

        Player bestBatsman = null;
        Player bestBowler = null;

        for (Team t : teams.values()) {
            for (Player p : t.getPlayers()) {
                if (p.getRole().equalsIgnoreCase("Batsman")) {
                    if (bestBatsman == null || p.getRating() > bestBatsman.getRating()) {
                        bestBatsman = p;
                    }
                } else if (p.getRole().equalsIgnoreCase("Bowler")) {
                    if (bestBowler == null || p.getRating() > bestBowler.getRating()) {
                        bestBowler = p;
                    }
                }
            }
        }

        if (bestBatsman != null) {
            System.out.println("Best Batsman: " + bestBatsman.getName() 
                               + " - Rating: " + bestBatsman.getRating());
        } else {
            System.out.println("No Batsman available for awards.");
        }

        if (bestBowler != null) {
            System.out.println("Best Bowler: " + bestBowler.getName() 
                               + " - Rating: " + bestBowler.getRating());
        } else {
            System.out.println("No Bowler available for awards.");
        }
    }

    public void showTeams() {
        System.out.println("Registered Teams");
        for (Team t : teams.values()) {
            System.out.println(t.getTeamId() + " | " + t.getName() + " | " + t.getCity());
        }
    }

}
