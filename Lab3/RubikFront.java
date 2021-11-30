
class RubikFront extends Rubik implements Cloneable {
    private static final int FACE_NUM = 6;
    private static final int ARRAY_LEN = 3;
    private static final int TOP = 0;
    private static final int LEFT = 1;
    private static final int FRONT = 2;
    private static final int RIGHT = 3;
    private static final int DOWN = 4;
    private static final int BACK = 5;

    RubikFront(int[][][] grid) {
        super(grid);
    }

    RubikFront(Rubik rubik) {
        this(rubik.get3DGrid());
    }


    @Override 
    public RubikFront right() {
        int[][][] newGrid = new int[FACE_NUM][ARRAY_LEN][ARRAY_LEN];
        for (int i = 0; i < FACE_NUM; i++) {
            for (int j = 0; j < ARRAY_LEN; j++) {
                for (int k = 0; k < ARRAY_LEN; k++) {
                    newGrid[i][j][k] = this.getGrid(i,j,k);
                }
            }
        } // copy original grid into newGrid

        newGrid[TOP][FRONT][TOP] = this.getGrid(LEFT,FRONT,FRONT);
        newGrid[TOP][FRONT][LEFT] = this.getGrid(LEFT,LEFT,FRONT);
        newGrid[TOP][FRONT][FRONT] = this.getGrid(LEFT,TOP,FRONT);
        newGrid[RIGHT][TOP][TOP] = this.getGrid(TOP,FRONT,TOP);
        newGrid[RIGHT][LEFT][TOP] = this.getGrid(TOP,FRONT,LEFT);
        newGrid[RIGHT][FRONT][TOP] = this.getGrid(TOP,FRONT,FRONT);
        newGrid[DOWN][TOP][FRONT] = this.getGrid(RIGHT,TOP,TOP);
        newGrid[DOWN][TOP][LEFT] = this.getGrid(RIGHT,LEFT,TOP);
        newGrid[DOWN][TOP][TOP] = this.getGrid(RIGHT,FRONT,TOP);
        newGrid[LEFT][TOP][FRONT] = this.getGrid(DOWN,TOP,TOP);
        newGrid[LEFT][LEFT][FRONT] = this.getGrid(DOWN,TOP,LEFT);
        newGrid[LEFT][FRONT][FRONT] = this.getGrid(DOWN,TOP,FRONT); 

        newGrid[2] = new Face(this.getFace(2)).right().toIntArray();
        return new RubikFront(newGrid);

    }

    @Override 
    public RubikFront left() {
        return this.clone().right().right().right();
    }

    @Override 
    public RubikFront half() {
        return this.clone().right().right();
    }

    @Override
    public RubikFront clone() {
        int[][][] newGrid = new int[FACE_NUM][ARRAY_LEN][ARRAY_LEN];
        for (int i = 0; i < FACE_NUM; i++) {
            for (int j = 0; j < ARRAY_LEN; j++) {
                for (int k = 0; k < ARRAY_LEN; k++) {
                    newGrid[i][j][k] = this.getGrid(i,j,k);
                }
            }
        }
        return new RubikFront(newGrid);
    }

    @Override 
    public RubikFront rightView() {
        int[][][] newGrid = new int[FACE_NUM][ARRAY_LEN][ARRAY_LEN];
        newGrid[TOP] = new Face(this.getFace(TOP)).right().toIntArray();
        newGrid[LEFT] = this.getFace(FRONT);
        newGrid[FRONT] = this.getFace(RIGHT); 
        newGrid[RIGHT] = new Face(this.getFace(BACK)).half().toIntArray();
        newGrid[BACK] = new Face(this.getFace(LEFT)).half().toIntArray();
        newGrid[DOWN] = new Face(this.getFace(DOWN)).left().toIntArray();
        return new RubikFront(newGrid);
    }

    @Override 
    public RubikFront leftView() {
        int[][][] newGrid = new int[FACE_NUM][ARRAY_LEN][ARRAY_LEN]; 
        newGrid[TOP] = new Face(this.getFace(TOP)).left().toIntArray();
        newGrid[FRONT] = this.getFace(LEFT); 
        newGrid[RIGHT] = this.getFace(FRONT); 
        newGrid[LEFT] = new Face(this.getFace(BACK)).half().toIntArray();
        newGrid[BACK] = new Face(this.getFace(RIGHT)).half().toIntArray();
        newGrid[DOWN] = new Face(this.getFace(DOWN)).right().toIntArray();
        return new RubikFront(newGrid);
    }

    @Override 
    public RubikFront backView() {
        int[][][] newGrid = new int[FACE_NUM][ARRAY_LEN][ARRAY_LEN]; 
        newGrid[TOP] = new Face(this.getFace(TOP)).half().toIntArray(); 
        newGrid[FRONT] = new Face(this.getFace(BACK)).half().toIntArray(); 
        newGrid[LEFT] = this.getFace(RIGHT); 
        newGrid[RIGHT] = this.getFace(LEFT);
        newGrid[BACK] = new Face(this.getFace(FRONT)).half().toIntArray(); 
        newGrid[DOWN] = new Face(this.getFace(DOWN)).half().toIntArray(); 
        return new RubikFront(newGrid);
    }

    @Override 
    public RubikFront upView() {
        int[][][] newGrid = new int[FACE_NUM][ARRAY_LEN][ARRAY_LEN]; 
        newGrid[RIGHT] = new Face(this.getFace(RIGHT)).left().toIntArray(); 
        newGrid[LEFT] = new Face(this.getFace(LEFT)).right().toIntArray();
        newGrid[FRONT] = this.getFace(TOP); 
        newGrid[DOWN] = this.getFace(FRONT);
        newGrid[BACK] = this.getFace(DOWN); 
        newGrid[TOP] = this.getFace(BACK); 
        return new RubikFront(newGrid);
    }

    @Override 
    public RubikFront downView() {
        return this.clone().upView().upView().upView();
    }

    @Override 
    public RubikFront frontView() {
        return this; 
    }
}










