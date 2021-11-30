import java.util.stream.Stream; 
import java.util.Optional; 
import java.util.Arrays; 
import java.util.stream.IntStream;
import java.util.List; 
import java.util.ArrayList; 

class Main {

    private static final int ZERO = 0;
    private static final int ONE = 1; 

    static boolean isPrime(int n) {
        return n > 1 && IntStream.range(2, n).noneMatch(x -> n % x == 0);
    }

    static long countTwinPrimes(int n) {
        return IntStream.rangeClosed(1, n)
            .filter(x -> isPrime(x) && (isPrime(x + 2) || isPrime(x - 2)))
            .count();
    }

    static String reverse(String str) {
        Stream<String> s = Stream.<String>of(str.split("")); // String::split(regex) splits each character into individual String arrays 
        return s.reduce("", (x,y) -> y + x); 
    }
    


    static long countRepeats(int... array) {
        int len = array.length; 
        IntStream stream = Arrays.stream(array);
        return IntStream.rangeClosed(0, len - 2)
            .filter(x -> array[x] == array[x + 1] && (x + 2 == len || 
                        array[x] != array[x+2]))
            .count();
    }

    static Optional<Helper> defaulter() {
        return Optional.<Helper>of(new Helper(2,1,1,1)); 
    }

    static double normalizedMean(Stream<Integer> stream) {
         Optional<Helper> o = stream.map(x -> new Helper(x, x, 1, x))
            .reduce( (x,y) -> new Helper(Math.max(x.getMax(), y.getMax()),
                        Math.min(x.getMin(), y.getMin()), 
                        x.getCount() + ONE, x.getSum() + y.getSum()))
            .or( () -> defaulter()); 

         Stream<Helper> s = Stream.<Helper>of(o.get());
         return s.filter(x -> x.getMax() != x.getMin()).reduce( (x,y) -> new Helper(x.getMax(), x.getMin(), x.getCount(), x.getSum())).
             or( () -> defaulter()).get().normalizedMean();

    }

}

