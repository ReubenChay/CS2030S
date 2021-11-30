import java.util.function.Function;
import java.util.Optional;
import java.util.function.Predicate;  
import java.util.function.Supplier; 

class Logger<T> { 

    private final Supplier<Pair<T>> supplier; 

    private Logger(Supplier<Pair<T>> supplier) { 
        this.supplier = supplier;
    }

    static <U> Logger<U> of(U value) {
        if (value instanceof Logger) {
            throw new IllegalArgumentException("already a Logger");
        } else { 
            Optional<U> op = Optional.ofNullable(value);
            if (op.isPresent()) {
                return new Logger<U>(() -> {
                    String s = "Logger" + "[" + value + "]";
                    return new Pair<>(value, "", s);
                });
            } else {
                throw new IllegalArgumentException("argument cannot be null");
            }
        }
    }

    Pair<T> get() {
        return this.supplier.get();
    }

    Supplier<Pair<T>> getSupplier() {
        return this.supplier;
    }

    <U> Logger<U> flatMap(Function<? super T, ? extends Logger<? extends U>> flatMapper) {
        Supplier<Pair<U>> newSupplier = () -> {
            Logger<? extends U> log = flatMapper.apply(this.get().getValue());
            String loggerString = "Logger" + "[" + log.get().getValue() + "]"; 
            String history = this.get().getHistory() + log.get().getHistory(); 
            return new Pair<U>(log.get().getValue(), history, loggerString);
        };
        return new Logger<U>(newSupplier);
    }


    <U> Logger<U> map(Function<? super T, ? extends U> mapper) {
        Supplier<Pair<U>> newSupplier = () -> {
            U u = mapper.apply(this.get().getValue());
            String loggerString = "Logger" + "[" + u + "]";
            String history = this.get().getHistory() + "\n" + this.get().getValue() + " -> " 
                + u;
            return new Pair<U>(u, history, loggerString);
        };
        return new Logger<U>(newSupplier);
    }

    Logger<T> test(Predicate<? super T> pred, Logger<T> trueLogger, Logger<T> falseLogger) {
        if (pred.test(this.get().getValue())) {
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
            Logger<?> log = (Logger<?>)obj; 
            return this.get().getValue().equals(log.get().getValue()) && 
                this.get().getHistory().equals(log.get().getHistory()); 
        } else {
            return false; 
        }
    }

    @Override 
    public String toString() {
        return this.get().getString();
    }

}
