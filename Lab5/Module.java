import java.util.Map;
import java.util.HashMap; 

class Module extends KeyableMap<Assessment> {

    Module(String key) {
        super(key);
    }

    Module(String key, Map<String, Assessment> map) {
        super(key, map);
    }

    @Override 
    Module put(Assessment item) {
        this.getMap().put(item.getKey(), item);
        return new Module(this.getKey(), this.getMap());
    }

    @Override 
    public String toString() {
        String output = super.getKey() + ": {";
        int numMaps = this.getMap().size(); 
        int i = 1; 
        for (Map.Entry<String, Assessment> e : this.getMap().entrySet()) {
            output += e.getValue().toString();
            if (i < numMaps) {
                output += ", ";
            }
            i++;
        }
        output += "}";
        return output; 
    }

}
    


