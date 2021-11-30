import java.util.Map;
import java.util.HashMap; 

class Student extends KeyableMap<Module> {

    Student(String key) {
        super(key);
    }

    Student(String key, Map<String, Module> map) {
        super(key, map);
    }

    @Override 
    Student put(Module module) {
        this.getMap().put(module.getKey(), module);
        return new Student(this.getKey(), this.getMap());
    }

    @Override 
    public String toString() {
        String output = super.getKey() + ": {";
        int numMaps = this.getMap().size(); 
        int i = 1; 
        for (Map.Entry<String, Module> e : this.getMap().entrySet()) {
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


