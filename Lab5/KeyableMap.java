import java.util.Map;
import java.util.HashMap;
import java.util.Optional; 

class KeyableMap<V extends Keyable> implements Keyable {

    private final Map<String, V> map; 
    private final String key; 

    KeyableMap(String key) {
        this.map = new HashMap<>();
        this.key = key; 
    }

    KeyableMap(String key, Map<String, V> map) {
        this.map = map; 
        this.key = key;
    }

    Map<String, V> getMap() {
        return this.map;
    }

    @Override 
    public String getKey() {
        return this.key; 
    }


    // put method 
    KeyableMap<V> put(V item) {
        this.map.put(item.getKey(), item);
        return new KeyableMap<V>(this.key, map);
    }

    Optional<V> get(String identifier) {
        return Optional.<V>ofNullable(this.map.get(identifier));
    }

    /**
    V get(String identifier) {
        return this.map.get(identifier);
    }
    */

}
