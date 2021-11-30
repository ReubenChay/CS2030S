
Logger<Integer> add(Logger<Integer> a, int b) {
     Logger<Integer> newLog = a.map(x -> x + b);
     return newLog;
}



Logger<Integer> sum(int n) {
    Logger log = Logger.<Integer>of(0);
    return recursiveSum(n, log, 1); 
}

Logger<Integer> recursiveSum(int n, Logger<Integer> log, int curr) {
    if (curr > n) {
        return log;
    } else { 
        return recursiveSum(n, add(log, curr), curr + 1);
    }
}

Logger<Integer> f(int n) {
    Logger log = Logger.<Integer>of(n); 
    return recursiveCollatz(log, n);
}

Logger<Integer> recursiveCollatz(Logger<Integer> log, int n) {
    if (n == 1) {
        return log; 
    } else if (n % 2 == 0) {
        return recursiveCollatz(log.map(x -> x / 2), n / 2);
    } else {
        return recursiveCollatz(log.map(x -> (x * 3)).map(y -> y + 1), (n * 3) + 1);
    }
}




