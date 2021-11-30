import java.util.Scanner; 
import java.util.List; 
import java.util.ArrayList; 
import cs2030.simulator.Simulator;

class Main4 { 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        List<Double> arrivalTimes = new ArrayList<Double>();
        List<Double> serviceTimes = new ArrayList<Double>();
        List<Double> restTimes = new ArrayList<Double>();

        int numServer = sc.nextInt(); 
        int numSelfCheckout = sc.nextInt();
        int maxQueue = sc.nextInt();
        int N = sc.nextInt(); 
        // N customers and N rest times 

        for (int i = 0; i < N; i++) {
            arrivalTimes.add(sc.nextDouble());
            serviceTimes.add(sc.nextDouble());
        }

        for (int i = 0; i < N; i++) {
            restTimes.add(sc.nextDouble());
        }

        Simulator s = new Simulator(arrivalTimes, serviceTimes, numServer,
                maxQueue, restTimes, numSelfCheckout); 
        s.simulate();
    }
}

