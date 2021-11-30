
class RubikDown extends RubikFront {

    RubikDown(Rubik rubik) {
        super(rubik);
    }

    @Override 
    public RubikFront right() {
        return this.clone().downView().right().upView();
    }

    @Override 
    public RubikFront left() {
        return this.clone().downView().left().upView();
    }

    @Override 
    public RubikFront half() {
        return this.clone().downView().half().upView();
    }

}
