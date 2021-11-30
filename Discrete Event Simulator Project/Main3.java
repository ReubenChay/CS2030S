import java.util.Scanner; 
import java.util.List; 
import java.util.ArrayList; 
import cs2030.simulator.Simulator;

/**
 * The main class drives the program for level 3. 
 */
class Main3 { 

    /**
     * The main logic of the program.
     * @param args the command line arguments supplied upon execution.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        List<Double> arrivalTimes = new ArrayList<Double>();
        List<Double> serviceTimes = new ArrayList<Double>();
        List<Double> restTimes = new ArrayList<Double>();

        int numServer = sc.nextInt(); 
        int maxQueue = sc.nextInt();
        int n = sc.nextInt(); 
        // N customers and N rest times 

        for (int i = 0; i < n; i++) {
            arrivalTimes.add(sc.nextDouble());
            serviceTimes.add(sc.nextDouble());
        }

        for (int i = 0; i < n; i++) {
            restTimes.add(sc.nextDouble());
        }

        Simulator s = new Simulator(arrivalTimes, serviceTimes, numServer, maxQueue, restTimes); 
        s.simulate();
    }
}

