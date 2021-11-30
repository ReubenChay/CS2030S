
class RubikUp extends RubikFront {
    
    RubikUp(Rubik rubik) {
        super(rubik);
    }

    @Override 
    public RubikFront right() {
        return this.clone().upView().right().downView();
    }

    @Override 
    public RubikFront left() {
        return this.clone().upView().left().downView();
    }

    @Override 
    public RubikFront half() {
        return this.clone().upView().half().downView();
    }
}
