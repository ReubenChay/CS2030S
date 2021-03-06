
class MCQ extends Question implements LockedQuestion {

    private final String[] options; 

    MCQ(String question, String[] options, int expectedAnswer) {
        super(question, expectedAnswer);
        this.options = new String[options.length];
        int i = 0;
        for (String s : options) {
            this.options[i++] = s;
        }
    }

    MCQ(String question, String[] options, int expectedAnswer, int answer) {
        super(question, expectedAnswer, answer);
        this.options = options; 
    }

    String[] getOptions() {
        return this.options; 
    }

    @Override 
    Question answer(int response) {
        return new MCQ(this.getQuestion(), this.options, this.getExpectedAnswer(), response);
    }

    @Override 
    public String toString() {
        String output = this.getQuestion() + " ";
        int i = 1;
        for (String s : options) {
                output += String.format("[%d:%s]", i, options[i-1]);
                i++;
            }
        if (this.getAnswer() == 0) {
            output += String.format("; Your answer: [ ? ]");
        } else {
            output += String.format("; Your answer: [ %d:%s ]", this.getAnswer(), this.getOptions()[this.getAnswer() - 1]);
        }
        return output;
    }

    @Override 
    LockedQuestion lock() {
        LockedQuestion lq = new MCQ(this.getQuestion(), this.options, this.getExpectedAnswer(), this.getAnswer());
        return lq; 
    }


    @Override 
    public int mark() {
        if(this.getExpectedAnswer() == this.getAnswer()) {
            return 1; 
        } 
        return 0;
    }
}



