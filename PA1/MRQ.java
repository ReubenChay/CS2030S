
class MRQ extends MCQ {

    private final int[] expectedAnswers; 
    private final int[] answers; 

    MRQ(String question, String[] options, int[] expectedAnswers) {
        super(question, options, 0);
        this.expectedAnswers = expectedAnswers;
        this.answers = new int[options.length];
    }

    MRQ(String question, String[] options, int[] expectedAnswers, int[] answers) {
        super(question, options, 0); 
        this.expectedAnswers = expectedAnswers; 
        this.answers = answers;
    }

    @Override 
    LockedQuestion lock() {
        LockedQuestion lq = new MRQ(super.getQuestion(), super.getOptions(), this.expectedAnswers, this.answers);
        return lq;
    }

    @Override 
    public int mark() {
        int[] rightAns = new int[super.getOptions().length];
        for (int i : this.expectedAnswers) {
            rightAns[i - 1] = i;
        }
        for (int j = 0; j < answers.length; j++) {
            if (rightAns[j] != answers[j]) {
                return 0;
            }
        }
        return 1;
    }


    @Override 
    MRQ answer(int response) {
        int[] temp = new int[this.answers.length];
        for(int i = 0; i < temp.length; i++) {
            temp[i] = this.answers[i];
        }
        if (temp[response - 1] == response) {
            temp[response - 1] = 0;
        } else {
            temp[response - 1] = response;
        }
        return new MRQ(super.getQuestion(), super.getOptions(), this.expectedAnswers, temp);
    }

    @Override 
    public String toString() {
        String out = super.getQuestion() + " ";
        int i = 1; 
        for (String s : this.getOptions()) {
            out += String.format("[%d:%s]", i, super.getOptions()[i-1]);
            i++;
        }
        out += String.format("; Your answer: [ ");
        for (int k : answers) {
            if (k != 0) {
                out += String.format("%d ", k);
            }
        }
        out += "]";
        return out;
    }




}

