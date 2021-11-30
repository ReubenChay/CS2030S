
class RubikLeft extends RubikFront { 

    RubikLeft(Rubik rubik) {
        super(rubik); 
    }

    @Override 
    public RubikFront right() {
        return this.clone().leftView().right().rightView();
    }

    @Override 
    public RubikFront left() {
        return this.clone().leftView().left().rightView();
    }

    @Override 
    public RubikFront half() {
        return this.clone().leftView().half().rightView();
    }

} 


