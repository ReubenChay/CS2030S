
class PrivateCar extends Driver { 

    PrivateCar(String identifier, int waitTime) {
        super(identifier, waitTime, new CabService[] {new JustRide(), new ShareARide()});
    }

    @Override 
    public String toString() {
        return String.format("%s (%d mins away) PrivateCar", this.getIdentifier(),
                this.getWaitTime());
    }
}


