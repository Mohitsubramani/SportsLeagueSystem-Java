package league;

import java.util.*;

public abstract class Player {
    protected final String playerId;
    protected String name;
    protected String role;
    protected double rating;
    protected Map<String, Integer> stats;

    public Player(String playerId, String name, String role, double rating) {
        this.playerId = playerId;
        this.name = name;
        this.role = role;
        this.rating = rating;
        this.stats = new HashMap<>();
    }

    public String getPlayerId() { return playerId; }
    public String getName() { return name; }
    public String getRole() { return role; }
    public double getRating() { return rating; }
    public Map<String, Integer> getStats() { return stats; }

    public void addStat(String key, int value) {
        stats.put(key, stats.getOrDefault(key, 0) + value);
    }

    public abstract void ratingUpdate(Map<String, Integer> matchStats);

    @Override
    public String toString() {
        return String.format("%s (%s) - rating: %.2f", name, role, rating);
    }
}
