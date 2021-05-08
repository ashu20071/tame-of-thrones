import java.util.HashMap;
import java.util.Map;

/**
 * Lookup service to get instance of specified kingdom for processing.
 */

public class KingdomLookup {
    private final Map<String, KingdomService> lookup;

    public KingdomLookup() {
        this.lookup = new HashMap<>();
        lookup.put("AIR", new Air());
        lookup.put("SPACE", new Space());
        lookup.put("ICE", new Ice());
        lookup.put("LAND", new Land());
        lookup.put("WATER", new Water());
        lookup.put("FIRE", new Fire());
    }

    public KingdomService getKingdom(String kingdom) {
        if (lookup.containsKey(kingdom))
            return lookup.get(kingdom);
        else
            throw new IllegalArgumentException("Incorrect kingdom name");
    }
}
