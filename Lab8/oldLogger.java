import java.util.function.Function;
import java.util.Optional;
import java.util.function.Predicate;  

class Logger<T> { 

    private final T value; 
    String changes; 

    private Logger(T value, String changes) { 
        this.value = value;
        this.changes = changes;
    }


    static <U> Logger<U> of(U value) {
        if (value instanceof Logger) {
            throw new IllegalArgumentException("already a Logger");
        } else { 
            Optional<U> op = Optional.ofNullable(value);
            if (op.isPresent()) {
                return new Logger<U>(op.get(), "");
            } else {
                throw new IllegalArgumentException("argument cannot be null");
            }
        }
    }

    T getValue() {
        return this.value; 
    }

    String getChanges() {
        return this.changes; 
    }

    <U> Logger<U> flatMap(Function<? super T, ? extends Logger<? extends U>> flatMapper) {
        Logger<? extends U> log = flatMapper.apply(this.value);
        String change = this.changes + log.getChanges(); 
        return new Logger<U>(log.getValue(), change);
    }


    <U> Logger<U> map(Function<? super T, ? extends U> mapper) {
        T oldValue = this.value; 
        U newValue = mapper.apply(oldValue); 
        String output = this.changes + "\n" + oldValue + " -> " + newValue; 
        return new Logger<U>(mapper.apply(this.value), output);
    }

    Logger<T> test(Predicate<? super T> pred, Logger<T> trueLogger, Logger<T> falseLogger) {
        if (pred.test(this.value)) {
            return trueLogger; 
        } else {
            return falseLogger; 
        }
    }

    @Override 
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; 
        } else if (obj instanceof Logger) {
            Logger<T> log = (Logger<T>)obj; 
            return log.getValue().equals(this.value) && log.getChanges().equals(this.changes);
        } else {
            return false; 
        }
    }

    @Override 
    public String toString() {
        String output = "Logger" + "[" + this.value + "]"; 
        output += changes; 
        return output; 
    }

}
