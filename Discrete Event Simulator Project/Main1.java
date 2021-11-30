import java.util.Scanner; 
import java.util.List; 
import java.util.ArrayList; 
import cs2030.simulator.Simulator;

/**
 * The main class drives the program for level 1.
 */
class Main1 { 

    /**
     * The main logic of the program.
     * @param args the command line arguments supplied upon execution.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        List<Double> arrivalTimes = new ArrayList<Double>();

        int numServer = sc.nextInt(); 

        while (sc.hasNextDouble()) {
            arrivalTimes.add(sc.nextDouble());
        }

        Simulator s = new Simulator(arrivalTimes, numServer); 
        s.simulate();
    }
}

