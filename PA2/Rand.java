import java.util.function.Supplier; 
import java.util.Random; 
import java.util.stream.Stream; 
import java.util.function.Function; 
import java.util.Optional; 

class Rand<T> { 

    private final Random random; 
    private final int seed; 
    private final Function<Integer, T> func; 

    private Rand(Integer seed, Function<Integer, T> func) {
        this.random = new Random(seed); 
        this.seed = seed; 
        this.func = func; 
    }


    static Rand<Integer> of(int seed) {
        return new Rand<>(seed, x -> x); 
    }

    T get() {
        return func.apply(seed);
    }

    Rand<T> next() {
        return new Rand<>(new Random(this.seed).nextInt(Integer.MAX_VALUE), func);
    }

    Stream<T> stream() {
        return Stream.iterate(this.seed, x -> Rand.of(x).next().get()).map(func); 
    }

    static <T> Stream<T> randRange(int seed, Function<Integer, T> func) {
        return Rand.of(seed).stream().map(func);
    }

    <R> Rand<R> map(Function<T, ? extends R> mapper) {
        Supplier<? extends R> newSupplier; 
        return new Rand<>(seed, mapper.compose(this.func));
    }

    <R> Rand<R> flatMap(Function<T, ? extends Rand<R>> flatMapper) {
        Supplier<? extends R> newSupplier; 
        return new Rand<>(seed, func.andThen(flatMapper).andThen(x -> x.get()));
    }


    @Override 
    public String toString() {
        return "Rand"; 
    }

}

