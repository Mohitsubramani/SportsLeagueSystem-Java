package league;

import java.util.Map;

public class Bowler extends Player {
    private int wickets, overs, runsConceded;

    public Bowler(String playerId, String name, String string, double rating, String string2) {
        super(playerId, name, "Bowler", rating);
    }

    @Override
    public void ratingUpdate(Map<String, Integer> matchStats) {
        int w = matchStats.getOrDefault("wickets", 0);
        int o = matchStats.getOrDefault("overs", 0);
        int r = matchStats.getOrDefault("runs", 0);

        this.wickets += w;
        this.overs += o;
        this.runsConceded += r;

        addStat("wickets", w);
        addStat("overs", o);
        addStat("runsConceded", r);

        this.rating += w * 0.5 - r * 0.01;
    }
}
