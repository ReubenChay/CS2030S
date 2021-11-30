
class Helper { 

    private final int max; 
    private final int min; 
    private final int count; 
    private final int sum; 

    Helper(int max, int min, int count, int sum) {
        this.max = max; 
        this.min = min; 
        this.sum = sum; 
        this.count = count; 
    }

    double normalizedMean() {
        return ((((double)sum / count) - (double)min) / ((double)max - min));
    }

    int getMax() {
        return this.max; 
    }

    int getMin() {
        return this.min;
    }


    int getCount() {
        return this.count; 
    }

    int getSum() {
        return this.sum;
    }

}
