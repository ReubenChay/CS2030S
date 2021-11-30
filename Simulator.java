package cs2030.simulator;
import java.util.List;
import java.util.ArrayList; 
import java.util.PriorityQueue;
import java.util.function.Supplier; 

public class Simulator {

    protected final List<Double> arrivalTimes;
    protected final int numServer; 
    protected final List<Double> serviceTimes;
    protected final int maxQueue;
    protected final List<Double> restTimes; 
    protected final int numSelfCheckout; 
    private static final int SERVER_LIST_FUNCTION = 1;
    private final static double REST_TIME = 0;
    private static final double SERVICE_TIME = 1.000;

    // constructor for Main1 
    public Simulator(List<Double> arrivalTimes, int numServer) {
        this.arrivalTimes = arrivalTimes; 
        this.numServer = numServer; 
        this.maxQueue = 1;
        this.serviceTimes = new ArrayList<Double>(); 
        this.restTimes = new ArrayList<Double>();
        for (int i = 0; i < arrivalTimes.size(); i++) {
            serviceTimes.add(SERVICE_TIME);
            restTimes.add(REST_TIME);
        }
        this.numSelfCheckout = 0;
    }


    //constructor for Main2 
    public Simulator(List<Double> arrivalTimes, List<Double> serviceTimes, int numServer, int maxQueue) {
        this.arrivalTimes = arrivalTimes; 
        this.serviceTimes = serviceTimes; 
        this.numServer = numServer;  
        this.maxQueue = maxQueue; 
        this.restTimes = new ArrayList<Double>();
        for (int i = 0; i < arrivalTimes.size(); i++) {
            restTimes.add(REST_TIME);
        }
        this.numSelfCheckout = 0;
    }

    //constructor for Main3 
    public Simulator(List<Double> arrivalTimes, List<Double> serviceTimes, int numServer, int maxQueue, List<Double> restTimes) {
        this.arrivalTimes = arrivalTimes; 
        this.serviceTimes = serviceTimes;
        this.numServer = numServer; 
        this.maxQueue = maxQueue; 
        this.restTimes = restTimes;
        this.numSelfCheckout = 0;
    }

    //constructor for Main4 
    public Simulator(List<Double> arrivalTimes, List<Double> serviceTimes, int numServer,
            int maxQueue, List<Double> restTimes, int numSelfCheckout) {
        this.arrivalTimes = arrivalTimes;
        this.serviceTimes = serviceTimes;
        this.numServer = numServer;
        this.maxQueue = maxQueue;
        this.restTimes = restTimes;
        this.numSelfCheckout = numSelfCheckout; 
    }

    public double getRestTime() {
        return this.restTimes.remove(0);
    }

    // this method pushes back the timings of Wait and Serve events 
    PriorityQueue<Event> queueUpdate(PriorityQueue<Event> oldPQ, double delayTime, int serverIdentity) {
        List<Event> pqList = new ArrayList<Event>(oldPQ);
        for (Event e : pqList) {
            if (e.getServerIdentity() == serverIdentity) {
                oldPQ.add(e.eventDelayer(delayTime));
                oldPQ.remove(e);
            }
        }
        return oldPQ; 
    }

    // this method ensures self-check counters share the same queue 
    List<Server> updateSelfCheckQueue(List<Server> serverList, int serverIndex) {
        List<Customer> updatedQ = serverList.get(serverIndex).getQueueList();
        List<Server> newList = serverList; 
        for (int i = numServer; i < numServer + numSelfCheckout; i++) {
            Server oldServer = serverList.get(i);
            newList.set(i, new SelfCheckout(oldServer.getIdentity(), oldServer.getIdleStatus(), oldServer.getMaxQueue(),
                        updatedQ, oldServer.getDoneServingTime(), oldServer.getDefaultIdentity()));
        }
        return newList;
    }

    // this method pushes the next person in self-check queue forward to be served 
    PriorityQueue<Event> selfCheckoutQUpdater(PriorityQueue<Event> oldPQ, Customer c, Server server, double serveTime) {
        List<Event> pqList = new ArrayList<>(oldPQ);
        for (Event e : pqList) {
            if (e.getCustomer().equals(c) && e.isServe()) {
                oldPQ.add(new Serve(e.getArrivalTime(), e.getCustomer(), server, serveTime));
                oldPQ.remove(e);
            }
        }
        return oldPQ; 
    }

    public void simulate() {

        PriorityQueue<Event> pq = new PriorityQueue<>();
        List<Server> serverList = new ArrayList<Server>();
        Statistics stats = new Statistics();

        for (int i = 0; i < numServer; i++) {
            serverList.add(new Server(i + 1, true, this.maxQueue,
                        new ArrayList<Customer>(), 0, () -> getRestTime()));
        } // add normal Servers to list 
        for (int i = numServer; i < numServer + numSelfCheckout; i++) {
            serverList.add(new SelfCheckout(i + 1, true, this.maxQueue, 
                        new ArrayList<Customer>(), 0, numServer + 1));
        } // add self check-out servers 

        for (int i = 0; i < arrivalTimes.size(); i++) {
            Customer c = new Customer(arrivalTimes.get(i), serviceTimes.get(i), i + SERVER_LIST_FUNCTION);
            pq.add(new Arrive(arrivalTimes.get(i), c, serverList));
        }

        while(!pq.isEmpty()) { 
            Event event = pq.poll();
            stats = event.updateStatistics(stats);

            if (event.getServerIdentity() != 0) {
                int serverIndex = event.getServerIdentity() - SERVER_LIST_FUNCTION;
                if (event.getServerIdentity() > numServer) {
                    serverList.set(serverIndex, event.updateSelfCheckout(serverList.get(serverIndex)));
                    serverList = updateSelfCheckQueue(serverList, serverIndex);
                    // method to update all self check-out counters needed 
                } else { 
                    serverList.set(serverIndex, event.updateServer(serverList.get(serverIndex)));
                }
                // update server according to whether normal server or self check-out 
            }

            if (event.isDone() && event.getServerIdentity() > numServer) {
                Server s = serverList.get(event.getServerIdentity() - 1);
                Customer c = s.nextCustomer();
                double serveTime = event.getEventTime();
                pq = selfCheckoutQUpdater(pq, c, s, serveTime);
            }


            if (event.isRest()) {
                pq = queueUpdate(pq, event.getRestTime(), event.getServerIdentity());
            }
            // rest event triggers delaying the affected events 

            Event newEvent = event.createNewEvent();
            if (!event.equals(newEvent)) {
                pq.add(newEvent);
            }
            // create newEvent 

            System.out.print(event);
        }
        double avgWaitingTime = stats.getTotalWaitTime() / stats.getNumCustomersServed();
        int numCustomersServed = stats.getNumCustomersServed();
        int numCustomersLeft = stats.getNumCustomersLeft();
        System.out.println(String.format("[%.3f %d %d]", avgWaitingTime, numCustomersServed, numCustomersLeft));
    }
}




