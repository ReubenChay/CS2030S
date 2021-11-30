
class Grader {
    private final int answer; 
    
    Grader(int answer) {
        this.answer = answer;
    }

    int getAnswer() {
        return this.answer;
    }

    int mark(int response) {
        if (response == this.answer) {
            return 1;
        }
        return 0;
    }

}
