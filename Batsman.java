package league;

import java.util.Map;

public class Batsman extends Player {
    private int totalRuns, fours, sixes;

    public Batsman(String playerId, String name, String string, double rating, String string2) {
        super(playerId, name, "Batsman", rating);
    }

    @Override
    public void ratingUpdate(Map<String, Integer> matchStats) {
        int runs = matchStats.getOrDefault("runs", 0);
        int f = matchStats.getOrDefault("fours", 0);
        int s = matchStats.getOrDefault("sixes", 0);

        this.totalRuns += runs;
        this.fours += f;
        this.sixes += s;

        addStat("runs", runs);
        addStat("fours", f);
        addStat("sixes", s);

        this.rating += runs * 0.01 + s * 0.2;
    }
}
