import java.util.Scanner; 
import java.util.List; 
import java.util.ArrayList; 
import cs2030.simulator.Simulator;

class Main1 { 
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
