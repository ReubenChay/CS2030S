
abstract class Rubik implements Cloneable {

    private static final int NUM_OF_FACES = 6;
    private static final int ARRAY_LEN = 3;
    private static final int PRINT_FUNCT2 = 5; 
    private static final int PRINT_FUNCT = 4;

    private final int[][][] grid = new int[NUM_OF_FACES][ARRAY_LEN][ARRAY_LEN];

    Rubik(int[][][] grid) {
        for (int i = 0; i < NUM_OF_FACES; i++) {
            for (int j = 0; j < ARRAY_LEN; j++) {
                for (int k = 0; k < ARRAY_LEN; k++) {
                    this.grid[i][j][k] = grid[i][j][k];
                }
            }
        }
    }


    abstract Rubik right();

    abstract Rubik left();

    abstract Rubik half();

    public abstract Rubik clone();

    abstract Rubik rightView();

    abstract Rubik leftView();

    abstract Rubik upView();

    abstract Rubik downView(); 

    abstract Rubik backView();

    abstract Rubik frontView();


    int getGrid(int i, int j, int k) {
        return this.grid[i][j][k];
    }

    int[][] getFace(int i) {
        Face face = new Face(this.grid[i]);
        return face.clone().toIntArray();
    }

    int[][][] get3DGrid() {
        int[][][] grid = new int[NUM_OF_FACES][ARRAY_LEN][ARRAY_LEN]; 
        grid = this.grid;
        return grid; 
    }


    @Override
    public String toString() {
        String out = "";
        String dots = "......";
        out += "\n";

        for (int i = 0; i < ARRAY_LEN; i++) {
            out += dots;
            for (int j = 0; j < ARRAY_LEN; j++) {
                out += String.format("%02d",this.grid[0][i][j]);
            }
            out += dots;
            out += "\n";
        }
        // prints top

        for (int mid = 0; mid < ARRAY_LEN; mid++) {
            for (int first = 1; first < PRINT_FUNCT; first++) {
                for (int last = 0; last < ARRAY_LEN; last++) {
                    out += String.format("%02d", this.grid[first][mid][last]);
                }
            }
            out += "\n";
        }
        // prints left, front, right

        for (int i = 0; i < ARRAY_LEN; i++) {
            out += dots;
            for (int j = 0; j < ARRAY_LEN; j++) {
                out += String.format("%02d", this.grid[PRINT_FUNCT][i][j]);
            }
            out += dots;
            out += "\n";
        }
        // prints down face

        for (int i = 0; i < ARRAY_LEN; i++) {
            out += dots;
            for (int j = 0; j < ARRAY_LEN; j++) {
                out += String.format("%02d", this.grid[PRINT_FUNCT2][i][j]);
            }
            out += dots;
            out += "\n";
        }
        // prints back face

        return out;
    }
}






