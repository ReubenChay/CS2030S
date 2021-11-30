
abstract class Question {

    private final String question;
    private final int expectedAnswer;
    private final int answer; 

    Question(String question, int expectedAnswer) {
        this.question = question; 
        this.expectedAnswer = expectedAnswer;
        this.answer = 0;
    }

    Question(String question, int expectedAnswer, int answer) {
        this.question = question; 
        this.expectedAnswer = expectedAnswer; 
        this.answer = answer;
    }

    String getQuestion() {
        return this.question;
    }

    int getAnswer() {
        return this.answer;
    }

    int getExpectedAnswer() {
        return this.expectedAnswer;
    }

    abstract Question answer(int response);

    abstract LockedQuestion lock();

}
