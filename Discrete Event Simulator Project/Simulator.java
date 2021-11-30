package cs2030.simulator;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList; 
import java.util.PriorityQueue;
import java.util.function.Supplier; 

/** 
 * A discrete event simulator which runs the entire simulation of event.s
 */
public class Simulator {

    protected final List<Double> arrivalTimes;
    protected final int numServer; 
    protected final List<Double> serviceTimes;
    protected final int maxQueue;
    protected final List<Double> restTimes; 
    protected final int numSelfCheckout; 

    protected final int numCustomers; 
    protected final double restingProb; 
    protected final double greedyProb; 
    protected final RandomGenerator rng; 

    private static final int SERVER_LIST_FUNCTION = 1;
    private static final  double REST_TIME = 0.00;
    private static final double SERVICE_TIME = 1.000;

    /**
     * Constructs the simulator object specifically for use by Main1. 
     * @param arrivalTimes   a list of customer Arrival Times.
     * @param numServer      the number of servers available.
     */
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
        this.numCustomers = arrivalTimes.size(); 
        this.restingProb = 1.0; 
        this.greedyProb = 1.0; 
        this.rng = new RandomGenerator(0, -1, -1, -1); 
    }


    /** 
     * Constructs the simulator object specifically for use by Main2. 
     * @param arrivalTimes       a list of customer arrival times. 
     * @param serviceTimes       a list of times taken to serve each customer. 
     * @param numServer          the number of servers available. 
     * @param maxQueue           the maximum queue allowed at each server. 
     */
    public Simulator(List<Double> arrivalTimes, List<Double> serviceTimes, 
            int numServer, int maxQueue) {
        this.arrivalTimes = arrivalTimes; 
        this.serviceTimes = serviceTimes; 
        this.numServer = numServer;  
        this.maxQueue = maxQueue; 
        this.restTimes = new ArrayList<Double>();
        for (int i = 0; i < arrivalTimes.size(); i++) {
            restTimes.add(REST_TIME);
        }
        this.numSelfCheckout = 0;
        this.numCustomers = arrivalTimes.size();
        this.restingProb = 1.0;
        this.greedyProb = 1.0;
        this.rng = new RandomGenerator(0, -1, -1, -1);

    }

    /**
     * Constructs the simulator object specifically for use by Main3.
     * @param arrivalTimes       a list of customer arrival times.
     * @param serviceTimes       a list of times taken to serve each customer.
     * @param numServer          the number of servers available.
     * @param maxQueue           the maximum queue allowed at each server.
     * @param restTimes          a list of times the servers will rest after serving each customer. 
     */
    public Simulator(List<Double> arrivalTimes, List<Double> serviceTimes, 
            int numServer, int maxQueue, List<Double> restTimes) {
        this.arrivalTimes = arrivalTimes; 
        this.serviceTimes = serviceTimes;
        this.numServer = numServer; 
        this.maxQueue = maxQueue; 
        this.restTimes = restTimes;
        this.numSelfCheckout = 0;
        this.numCustomers = arrivalTimes.size();
        this.restingProb = 1.0;
        this.greedyProb = 1.0;
        this.rng = new RandomGenerator(0, -1, -1, -1);

    }

    /**
     * Constructs the simulator object specifically for use by Main3.
     * @param arrivalTimes       a list of customer arrival times.
     * @param serviceTimes       a list of times taken to serve each customer.
     * @param numServer          the number of servers available.
     * @param maxQueue           the maximum queue allowed at each server.
     * @param restTimes          a list of times the servers will rest after serving each customer.
     * @param numSelfCheckout    the number of self-checkout counters available.
     */
    public Simulator(List<Double> arrivalTimes, List<Double> serviceTimes, int numServer,
            int maxQueue, List<Double> restTimes, int numSelfCheckout) {
        this.arrivalTimes = arrivalTimes;
        this.serviceTimes = serviceTimes;
        this.numServer = numServer;
        this.maxQueue = maxQueue;
        this.restTimes = restTimes;
        this.numSelfCheckout = numSelfCheckout; 
        this.numCustomers = arrivalTimes.size();
        this.restingProb = 1.0;
        this.greedyProb = 1.0;
        this.rng = new RandomGenerator(0, -1, -1, -1);

    }

    /**
     * Constructs the simulator object specifically for use by Main3.
     * @param arrivalTimes       a list of customer arrival times.
     * @param serviceTimes       a list of times taken to serve each customer.
     * @param numServer          the number of servers available.
     * @param maxQueue           the maximum queue allowed at each server.
     * @param restTimes          a list of times the servers will rest after serving each customer.
     * @param numSelfCheckout    the number of self-checkout counters available.
     * @param numCustomers       the total number of customers arriving.
     * @param restingProb        probability of a server resting 
     * @param greedyProb         probability of a customer being greedy
     * @param seed               seed value provided to the RandomGenerator. 
     * @param arrivalRate        rate of arrival of customers provided to the RandomGenerator. 
     * @param serviceRate        service rate provided to the RandomGenerator. 
     * @param restingRate        resting rate provided to the RandomGenerator.
     */
    public Simulator(List<Double> arrivalTimes, List<Double> serviceTimes, int numServer, 
            int maxQueue, List<Double> restTimes, int numSelfCheckout, int numCustomers, 
            double restingProb, double greedyProb, int seed, 
            double arrivalRate, double serviceRate, double restingRate) {
        this.arrivalTimes = arrivalTimes;
        this.serviceTimes = serviceTimes;
        this.numServer = numServer;
        this.maxQueue = maxQueue;
        this.restTimes = restTimes;
        this.numSelfCheckout = numSelfCheckout;
        this.numCustomers = numCustomers; 
        this.restingProb = restingProb; 
        this.greedyProb = greedyProb; 
        this.rng = new RandomGenerator(seed, arrivalRate, serviceRate, restingRate); 
    }

    /** 
     * Manages the delays involved when a server rests. 
     * @param oldPq          the PriorityQueue containing the events. 
     * @param delayTime      the time which the events should be delayed by. 
     * @param serverIdentity the identity of the server which is taking a rest. 
     */ 
    PriorityQueue<Event> queueUpdate(PriorityQueue<Event> oldPQ, 
            double delayTime, int serverIdentity) {
        List<Event> pqList = new ArrayList<Event>(oldPQ);
        for (Event e : pqList) {
            if (e.getServerIdentity() == serverIdentity) {
                oldPQ.add(e.eventDelayer(delayTime));
                oldPQ.remove(e);
            }
        }
        return oldPQ; 
    }

    /**
     * Manages the shared queue amongst the self-checkout counters.
     * @param serverList       the list of Servers. 
     * @param serverIndex      the index of this self-checkout counter affected 
     */
    List<Server> updateSelfCheckQueue(List<Server> serverList, int serverIndex) {
        List<Customer> updatedQ = serverList.get(serverIndex).getQueueList();
        List<Server> newList = serverList; 
        for (int i = numServer; i < numServer + numSelfCheckout; i++) {
            Server oldServer = serverList.get(i);
            newList.set(i, new SelfCheckout(oldServer.getIdentity(), 
                        oldServer.getIdleStatus(), oldServer.getMaxQueue(),
                        updatedQ, oldServer.getDoneServingTime(), oldServer.getDefaultIdentity()));
        }
        return newList;
    }

    /**
     * Pushes then next customer in the shared self-checkout queue to be served 
     * whenever a self-checkout counter is idle. 
     * @param oldPQ          the PriorityQueue containing all the events. 
     * @param c              the customer to be served.
     * @param server         the server which will be serving the customer c. 
     * @param serveTime      the service time of the customer c. 
     */
    PriorityQueue<Event> selfCheckoutQUpdater(PriorityQueue<Event> oldPQ, Customer c, 
            Server server, double serveTime) {
        List<Event> pqList = new ArrayList<>(oldPQ);
        for (Event e : pqList) {
            if (e.getCustomer().equals(c) && e.isServe()) {
                oldPQ.add(new Serve(e.getArrivalTime(), e.getCustomer(), server, serveTime));
                oldPQ.remove(e);
            }
        }
        return oldPQ; 
    }

    /**
     * Manages the serve events whenever a delay is expected. 
     * @param oldPQ       the PriorityQueue containing all the events. 
     * @param server      the server which is affected. 
     */
    PriorityQueue<Event> serveEventUpdater(PriorityQueue<Event> oldPQ, Server server) {
        List<Event> pqList = new ArrayList<>(oldPQ); 
        for (Event e : pqList) {
            if (e.isServe() && (e.getServerIdentity() == server.getIdentity())) {
                oldPQ.add(new Serve(e.getArrivalTime(), e.getCustomer(), 
                            server, server.getDoneServingTime())); 
                oldPQ.remove(e); 
            }
        }
        return oldPQ; 
    }


    /**
     * Serves as a supplier to obtain rest times for non-random generated timings. 
     */
    public double getRestTime() {
        return this.restTimes.remove(0);
    }

    /** 
     * Serves as a supplier to obtain arrival times for non-random generated timings. 
     */
    public double getArrivalTimes() {
        return this.arrivalTimes.remove(0);
    }

    /**
     * Serves as a supplier to obtain service times for non-random generated timings. 
     */
    public double getServiceTimes(int n) {
        return this.serviceTimes.get(n);
    }

    /**
     * Begins and runs the entire event simulation for Main1 to Main4. 
     */
    public void simulate() {

        PriorityQueue<Event> pq = new PriorityQueue<>();
        List<Server> serverList = new ArrayList<Server>();
        Statistics stats = new Statistics();

        for (int i = 0; i < numServer; i++) {
            serverList.add(new Server(i + 1, true, this.maxQueue,
                        new ArrayList<Customer>(), 0, restingProb, 
                () -> getRestTime(), () -> 0.0));
        } // add normal Servers to list 
        for (int i = numServer; i < numServer + numSelfCheckout; i++) {
            serverList.add(new SelfCheckout(i + 1, true, this.maxQueue, 
                        new ArrayList<Customer>(), 0, numServer + 1));
        } // add self check-out servers 

        for (int i = 0; i < arrivalTimes.size(); i++) {
            int x = i;
            Customer c = new Customer(arrivalTimes.get(i), i + SERVER_LIST_FUNCTION, 
                () -> getServiceTimes(x), Arrays.asList(-1.0));
            pq.add(new Arrive(arrivalTimes.get(i), c, serverList));
        }

        while (!pq.isEmpty()) { 
            Event event = pq.poll();
            stats = event.updateStatistics(stats);

            if (event.getServerIdentity() != 0) {
                int serverIndex = event.getServerIdentity() - SERVER_LIST_FUNCTION;
                if (event.getServerIdentity() > numServer) {
                    serverList.set(serverIndex, 
                            event.updateSelfCheckout(serverList.get(serverIndex)));
                    serverList = updateSelfCheckQueue(serverList, serverIndex);
                    // method to update all self check-out counters needed 
                } else { 
                    serverList.set(serverIndex, event.updateServer(serverList.get(serverIndex)));
                }
                // update server according to whether normal server or self check-out 
            }

            if (event.isServe()) {
                Server s = serverList.get(event.getServerIdentity() - 1);
                pq = serveEventUpdater(pq, s);
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
        System.out.println(String.format("[%.3f %d %d]", avgWaitingTime, 
                    numCustomersServed, numCustomersLeft));
    }

    /** 
     * Begins and runs the entire event simulation for Main5. 
     */
    public void simulate5() {

        PriorityQueue<Event> pq = new PriorityQueue<>();
        List<Server> serverList = new ArrayList<Server>();
        Statistics stats = new Statistics();

        for (int i = 0; i < numServer; i++) {
            serverList.add(new Server(i + 1, true, this.maxQueue,
                        new ArrayList<Customer>(), 0, restingProb, 
                () -> rng.genRestPeriod(), () -> rng.genRandomRest()));
        } // add normal Servers to list
        for (int i = numServer; i < numServer + numSelfCheckout; i++) {
            serverList.add(new SelfCheckout(i + 1, true, this.maxQueue,
                        new ArrayList<Customer>(), 0, numServer + 1));
        } // add self check-out servers

        // kick off first customer and arrival
        int customerIdentity = 1;
        double originalTime = 0;
        double arrivalTime = originalTime;
        Customer c; 
        if (rng.genCustomerType() < greedyProb) {
            c = new GreedyCustomer(arrivalTime, customerIdentity, 
                () -> rng.genServiceTime(), Arrays.asList(-1.0));
        } else {
            c = new Customer(arrivalTime, customerIdentity, 
                () -> rng.genServiceTime(), Arrays.asList(-1.0));
        }
        originalTime = arrivalTime;
        pq.add(new Arrive(arrivalTime, c, serverList));


        while (!pq.isEmpty()) {
            Event event = pq.poll();
            stats = event.updateStatistics(stats);

            if (event.isArrive() && customerIdentity < numCustomers) {
                customerIdentity += 1;
                arrivalTime = rng.genInterArrivalTime() + originalTime;
                originalTime = arrivalTime;
                Customer d; 
                if (rng.genCustomerType() < greedyProb) {
                    d = new GreedyCustomer(arrivalTime, customerIdentity, 
                        () -> rng.genServiceTime(), Arrays.asList(-1.0));
                } else {
                    d = new Customer(arrivalTime, customerIdentity, 
                        () -> rng.genServiceTime(), Arrays.asList(-1.0));
                }
                pq.add(new Arrive(arrivalTime, d, serverList));
            }
            // add more arrive events

            if (event.getServerIdentity() != 0) {
                int serverIndex = event.getServerIdentity() - SERVER_LIST_FUNCTION;
                if (event.getServerIdentity() > numServer) {
                    serverList.set(serverIndex, 
                            event.updateSelfCheckout(serverList.get(serverIndex)));
                    serverList = updateSelfCheckQueue(serverList, serverIndex);
                    // method to update all self check-out counters needed
                } else {
                    serverList.set(serverIndex, event.updateServer(serverList.get(serverIndex)));
                }
                // update server according to whether normal server or self check-out
            }

            if (event.isServe()) {
                Server s = serverList.get(event.getServerIdentity() - 1);
                pq = serveEventUpdater(pq, s); 
            }



            if (event.isDone() && event.getServerIdentity() > numServer) {
                Server s = serverList.get(event.getServerIdentity() - 1);
                Customer c1 = s.nextCustomer();
                double serveTime = event.getEventTime();
                pq = selfCheckoutQUpdater(pq, c1, s, serveTime);
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
        System.out.println(String.format("[%.3f %d %d]", avgWaitingTime, 
                    numCustomersServed, numCustomersLeft));
    }
}




