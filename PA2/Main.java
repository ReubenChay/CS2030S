import java.util.stream.Stream; 
import java.util.List; 

class Main { 

    static double simulate(int seed, int n) {
        Circle c = new Circle(new Point(0,0), 1.0); 
        long numPoints = Stream.iterate(Rand.of(seed), x -> x.next().next())
            .limit(n)
            .map(x -> x.flatMap(y -> Rand.of(y).map(z -> List.of(f(y), f(z)))
                        .next()).get())
            .filter(x -> c.contains(new Point(x.get(0), x.get(1))))
            .count(); 

        for (int i = 0; i < n; i++) {
            Rand.of(seed).next();
            // make calculations on how many there are
        }

        return 4.0 * numPoints / n;  
    }

    static double f(int v) { 
        return 2.0 * v / (Integer.MAX_VALUE - 1) - 1.0;

    }
}
