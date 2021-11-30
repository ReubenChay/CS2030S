import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

class Roster extends KeyableMap<Student> {

    Roster(String id) {
        super(id); 
    }

    Roster(String id, Map<String, Student> map) {
        super(id, map);
    }

    @Override 
    Roster put(Student student) {
        this.getMap().put(student.getKey(), student);
        return new Roster(super.getKey(), this.getMap()); 
    }

    String getGrade(String id, String module, String assessment) {
        return this.get(id)
            .flatMap(x -> x.get(module))
            .flatMap(x -> x.get(assessment))
            .map(x -> x.getGrade())
            .orElse(String.format("No such record: %s %s %s", id, module, assessment));
    }


    @Override 
    public String toString() {
        String output = super.getKey() + ": {";
        int numMaps = this.getMap().size();
        int i = 1;
        for (Map.Entry<String, Student> e : this.getMap().entrySet()) {
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

