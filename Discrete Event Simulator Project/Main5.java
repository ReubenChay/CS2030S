import java.util.Scanner; 
import java.util.List; 
import java.util.ArrayList; 
import cs2030.simulator.Simulator;

/**
 * The main class drives the program for level 5.
 */
class Main5 { 

    /**
     * The main logic of the program.
     * @param args the command line arguments supplied upon execution.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int seed = sc.nextInt(); 

        int numServer = sc.nextInt();
        int numSelfCheck = sc.nextInt(); 
        int maxQueue = sc.nextInt(); 
        int numCustomers = sc.nextInt();

        double arrivalRate = sc.nextDouble(); 
        double serviceRate = sc.nextDouble(); 
        double restingRate = sc.nextDouble(); 

        double restingProb = sc.nextDouble(); 
        double greedyProb = sc.nextDouble(); 

        List<Double> arrivalTimes = new ArrayList<Double>();
        List<Double> serviceTimes = new ArrayList<Double>();
        List<Double> restTimes = new ArrayList<Double>();

        Simulator s = new Simulator(arrivalTimes, serviceTimes, numServer, maxQueue, 
                restTimes, numSelfCheck, numCustomers, restingProb, greedyProb, seed,
                arrivalRate, serviceRate, restingRate); 

        s.simulate5(); 

    }
}




