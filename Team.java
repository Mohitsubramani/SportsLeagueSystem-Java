package league;

import java.util.*;

public class Team {
    private final String teamId;
    private String name;
    private String coach;
    private String city;
    private int points;
    private List<Player> players;

    public Team(String teamId, String name, String coach, String city) {
        this.teamId = teamId;
        this.name = name;
        this.coach = coach;
        this.city = city;
        this.points = 0;
        this.players = new ArrayList<>();
    }

    public String getTeamId() { return teamId; }
    public String getName() { return name; }
    public String getCoach() { return coach; }
    public String getCity() { return city; }
    protected int getPoints() { return points; }
    protected void setPoints(int points) { this.points = points; }

    public void addPlayer(Player p) { players.add(p); }
    public List<Player> getPlayers() { return players; }

    @Override
    public String toString() {
        return String.format("%s (%s) - Coach: %s - Points: %d", name, teamId, coach, points);
    }
}
