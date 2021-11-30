
class OffByOneGrader extends Grader {
    
    OffByOneGrader(int answer) {
        super(answer);
    }

    @Override 
    int mark(int response) {
        if (response == this.getAnswer() + 1 || response == this.getAnswer() - 1) {
            return 1;
        } 
        if (response == this.getAnswer()) {
            return 2;
        }
        return 0;
    }
}


