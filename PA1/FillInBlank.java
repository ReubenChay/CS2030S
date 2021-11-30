
class FillInBlank extends Question implements LockedQuestion{

    private final Grader grader;

    FillInBlank(String question, int expectedAnswer) {
        super(question, expectedAnswer);
        this.grader = new Grader(expectedAnswer);
    }

    FillInBlank(String question, int expectedAnswer, int answer, Grader grader) {
        super(question, expectedAnswer, answer);
        this.grader = grader;
    }

    FillInBlank(String question, Grader grader) {
        super(question, grader.getAnswer());
        this.grader = grader; 
    }

    @Override 
    Question answer(int response) {
        return new FillInBlank(this.getQuestion(), this.getExpectedAnswer(), response, this.grader);
    }

    @Override 
    public String toString() {
        return String.format("%s; Your answer: %d", this.getQuestion(), this.getAnswer());
    }

    @Override 
    LockedQuestion lock() {
        LockedQuestion lq = new FillInBlank(this.getQuestion(), this.getExpectedAnswer(), this.getAnswer(), this.grader);
        return lq;
    }

    @Override 
    public int mark() {
        return this.grader.mark(this.getAnswer());
    }
}
