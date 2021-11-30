
class NormalCab extends Driver {

    NormalCab(String identifier, int waitTime) {
        super(identifier, waitTime, new CabService[] {new JustRide(), new TakeACab()} );
    }

    @Override 
    public String toString() {
        return String.format("%s (%d mins away) NormalCab", 
                this.getIdentifier(), this.getWaitTime());
    }

}
