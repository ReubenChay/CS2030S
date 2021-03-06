import java.time.Instant;
import java.util.List; 
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.time.Duration;
import java.util.Scanner;

/**
 * This program finds different ways one can travel by bus (with a bit 
 * of walking) from one bus stop to another.
 *
 * @author: Ooi Wei Tsang
 * @version: CS2030 AY19/20 Semester 1, Lab 10
 */
public class Main {
    /**
     * The program read a sequence of (id, search string) from standard input.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Instant start = Instant.now();
        Scanner sc = new Scanner(System.in);

        List<CompletableFuture<String>> list = new ArrayList<>(); 
        while (sc.hasNext()) {
            BusStop srcId = new BusStop(sc.next());
            String searchString = sc.next();
            list.add(BusSg.findBusServicesBetween(srcId, searchString)
                    .thenCompose(x -> x.description()));
        }
        //System.out.println(BusSg.findBusServicesBetween(srcId, searchString).description());
        sc.close();

        CompletableFuture.allOf(list.toArray(new CompletableFuture<?>[1]));

        for (CompletableFuture<String> s : list) {
            System.out.println(s.join());
        }

        Instant stop = Instant.now();
        System.out.printf("Took %,dms\n", Duration.between(start, stop).toMillis());
    }
}
