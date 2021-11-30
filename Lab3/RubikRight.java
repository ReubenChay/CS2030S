
class RubikRight extends RubikFront {

    RubikRight(Rubik rubik) {
        super(rubik); 
    }

    @Override 
    public RubikFront right() {
        return this.clone().rightView().right().leftView();
    }

    @Override 
    public RubikFront left() {
        return this.clone().rightView().left().leftView();
    }

    @Override 
    public RubikFront half() {
        return this.clone().rightView().half().leftView();
    }
}
