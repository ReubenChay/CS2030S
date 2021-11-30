
class TFQ extends MCQ {

    TFQ(String question, String expectedAnswer) {
        super(question, new String[] { "True", "False" }, 
                expectedAnswer.length() - 3);
    }

    TFQ(String question, int expectedAnswer, int response) {
        super(question, new String[] { "True", "False" }, expectedAnswer, response);
    }
    @Override 
    Question answer(int response) {
        return new TFQ(super.getQuestion(), super.getExpectedAnswer(), response);
    }

    TFQ answer(String response) {
        return new TFQ(super.getQuestion(), super.getExpectedAnswer(),response.length() - 3);
    }

    @Override 
    LockedQuestion lock() {
        LockedQuestion lq = new TFQ(super.getQuestion(), super.getExpectedAnswer(), super.getAnswer());
        return lq;
    }

    @Override
    public String toString() {
        if (super.getAnswer() == 0) {
            return String.format("%s [1:%s][2:%s]; Your answer: [ ? ]", super.getQuestion(), this.getOptions()[0], this.getOptions()[1]);
        } else {
            return String.format("%s [1:%s][2:%s]; Your answer: [ %d:%s ]", super.getQuestion(), this.getOptions()[0], this.getOptions()[1],super.getAnswer(), this.getOptions()[super.getAnswer() - 1]);
        }
    }

}









