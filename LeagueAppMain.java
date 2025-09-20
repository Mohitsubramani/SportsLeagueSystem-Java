package league;

import java.time.LocalDate;

public class LeagueAppMain {
    public static void main(String[] args) {
        LeagueService service = new LeagueService();

        System.out.println("CASE 1: REGISTRATION");
        Team csk = new Team("T01", "Chennai Super Kings", "Coach A", "Chennai");
        Team gt  = new Team("T02", "Gujarat Titans", "Coach B", "Gujarat");
        Team pk  = new Team("T03", "Punjab Kings", "Coach C", "Panjab");
        
        service.registerPlayerToTeam("T01", new Batsman("P01", "Ruturaj Gaikwad", "Batsman", 88, "120 runs"));
        service.registerPlayerToTeam("T02", new Batsman("P02", "Shreyas Iyer", "Batsman", 90, "95 runs"));
        service.registerPlayerToTeam("T03", new Batsman("P03", "Shubman Gill", "Batsman", 92, "110 runs"));

        service.registerPlayerToTeam("T01", new Bowler("P04", "Deepak Chahar", "Bowler", 80, "3 wickets"));
        service.registerPlayerToTeam("T02", new Bowler("P05", "Sunil Narine", "Bowler", 85, "2 wickets"));
        service.registerPlayerToTeam("T03", new Bowler("P06", "Mohammad Shami", "Bowler", 89, "4 wickets"));


        service.registerTeam(csk);
        service.registerTeam(gt);
        service.registerTeam(pk);

        service.showTeams();  

        System.out.println("\nCASE 2: FIXTURES");
        service.generateFixtures(LocalDate.now());
        service.showFixtures(); 

        System.out.println("\nCASE 3: RESULTS");
        service.recordResult("M001", "2-0");
        service.recordResult("M002", "1-1");
        service.showResults();

        System.out.println("\nCASE 4: STANDINGS");
        service.standings();

        System.out.println("\nCASE 5: AWARDS");
        service.computeAwards();
    }
}
