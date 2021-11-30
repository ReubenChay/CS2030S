import java.util.Optional; 
import java.util.function.Predicate; 
import java.util.function.Supplier;
import java.util.function.Function; 

class Lazy<T> {

    private Optional<T> cache; 
    private final Supplier<? extends T> supplier;
    private boolean nullFromStart; 
    private boolean cached; 

    private Lazy(T value, boolean nullFromStart) {
        this.cache = Optional.ofNullable(value); 
        this.supplier = () -> value; 
        this.nullFromStart = nullFromStart; 
        this.cached = false; 
    }

    private Lazy(T value) {
        this.cache = Optional.ofNullable(value); 
        this.supplier = () -> value;
        this.nullFromStart = false; 
        this.cached = true; 
    }
    // cache is Optional.empty if null value provided 

    private Lazy(Supplier<? extends T> supplier) {
        this.supplier = supplier; 
        this.nullFromStart = false; 
        this.cache = Optional.<T>empty();
    }

    static <T> Lazy<T> emptyLazy() {
        return Lazy.ofNullable(null);
    }

    static <T> Lazy<T> of(Supplier<? extends T> supplier) {
        return new Lazy<T>(supplier); 
    }

    static <T> Lazy<T> ofNullable(T v) { 
        if (v == null) {
            return new Lazy<T>(v, true); 
        } else {
            return new Lazy<T>(v); 
        }
    }

    Optional<T> get() {
        if (this.cached == true) { 
            return this.cache; 
        } else { 
        T v = this.cache.orElseGet(this.supplier);
        this.cache = Optional.<T>ofNullable(v);
        this.cached = true; 
        return Optional.<T>ofNullable(v); 
        }
    }

    /* helper method to directly get a non-wrapped in Optional value 
     * but potential bug here 
     */
    T getActualValue() {
        return this.get().orElseGet(() -> null); 
    }

    <R> Lazy<R> map(Function<? super T, ? extends R> mapper) { 
        Supplier<? extends R> newSupplier = () -> {
            T oldValue = this.get().orElseGet(() -> null);
            if (oldValue == null) {
                return null; 
            }
            R newValue = mapper.apply(oldValue);
            if (newValue == null) {
                return null; 
            } else {
                return newValue;
            }
        };
        return Lazy.<R>of(newSupplier); 
    }

    Lazy<T> filter(Predicate<? super T> predicate) { 
        Supplier<? extends T> newSupplier = () -> {
            T value = this.get().orElseGet(() -> null);  
            if (value == null) {
                return null; 
            }
            if (predicate.test(value)) {
                return value; 
            } else { 
                return null; 
            }
        }; 
        return Lazy.<T>of(newSupplier); 
    }

    @Override 
    public String toString() {
        if (nullFromStart || (cached && this.cache.equals(Optional.<T>empty()))) {
            return "Lazy[null]";
        } else if (this.cache.equals(Optional.<T>empty())) {
            return "Lazy[?]";
        } else { 
            return "Lazy" + "[" + this.cache.orElseGet(() -> null) + "]"; 
        }
    }


}



