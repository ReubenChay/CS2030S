
class Pair<T> {

    private final T value; 
    private final String string; 
    private final String loggerString; 

    Pair(T value, String string, String loggerString) {
        this.value = value; 
        this.string = string; 
        this.loggerString = loggerString;
    }

    T getValue() {
        return this.value; 
    }

    String getHistory() { 
        return this.string; 
    }

    String getString() {
        String output = loggerString; 
        output += string;
        return output; 
    }



}
