package league;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Match {
    private final String matchId;
    private final Team homeTeam, awayTeam;
    private final LocalDate date;
    private String scorecard, result;

    public Match(String matchId, Team homeTeam, Team awayTeam, LocalDate date) {
        this.matchId = matchId;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.date = date;
        this.result = "Pending";
    }

    public String getMatchId() { return matchId; }
    public Team getHomeTeam() { return homeTeam; }
    public Team getAwayTeam() { return awayTeam; }
    public LocalDate getDate() { return date; }
    public String getResult() { return result; }
    public void setScorecard(String scorecard) { this.scorecard = scorecard; }
    public void setResult(String result) { this.result = result; }

    @Override
    public String toString() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return String.format("%s: %s vs %s on %s -> %s",
                matchId, homeTeam.getName(), awayTeam.getName(), date.format(df), result);
    }
}
