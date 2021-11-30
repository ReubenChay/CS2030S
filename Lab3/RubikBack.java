
class RubikBack extends RubikFront { 

    RubikBack(Rubik rubik) {
        super(rubik);
    }

    @Override 
    public RubikFront right() {
        return this.clone().backView().right().backView();
    }

    @Override 
    public RubikFront left() {
        return this.clone().backView().left().backView();
    }

    @Override 
    public RubikFront half() {
        return this.clone().backView().half().backView();
    }
}
