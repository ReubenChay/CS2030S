
class FreeTenMarksGrader extends Grader {

    FreeTenMarksGrader() {
        super(0);
    }
   
    @Override 
    int mark(int response) {
        return 10;
    }
}
