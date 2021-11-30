
class Face implements Cloneable { 

    private static final int ARRAY_LEN = 3; 

    private final int[][] grid = new int[ARRAY_LEN][ARRAY_LEN];


    Face(int[][] grid) {
        for (int i = 0; i < ARRAY_LEN; i++) {
            for (int j = 0; j < ARRAY_LEN; j++) {
                this.grid[i][j] = grid[i][j];
            }
        }
    }

    public Face right() {
        int[][] grid = {  
            {this.grid[2][0], this.grid[1][0], this.grid[0][0] },
            {this.grid[2][1], this.grid[1][1], this.grid[0][1] }, 
            {this.grid[2][2], this.grid[1][2], this.grid[0][2] }
        };

        return new Face(grid);
    }

    public Face left() {

        return new Face(new int[][] {
            { this.grid[0][2], this.grid[1][2], this.grid[2][2] }, 
                {  this.grid[0][1], this.grid[1][1], this.grid[2][1] }, 
                { this.grid[0][0], this.grid[1][0], this.grid[2][0] } 
        }
        );
    }

    public Face half() {
        return new Face(new int[][] {
            { this.grid[2][2], this.grid[2][1], this.grid[2][0] },
                { this.grid[1][2], this.grid[1][1], this.grid[1][0] },
                { this.grid[0][2], this.grid[0][1], this.grid[0][0] }
        }
        );
    }

    int[][] toIntArray() {
        return this.grid;
    }

    @Override 
    public Face clone() {
        int[][] newGrid = new int[ARRAY_LEN][ARRAY_LEN]; 
        for (int i = 0; i < ARRAY_LEN; i++) {
            for (int j = 0; j < ARRAY_LEN; j++) {
                newGrid[i][j] = this.grid[i][j];
            }
        }        
        return new Face(newGrid);
    }

    @Override 
    public String toString() {
        String temp = "";
        for (int i = 0; i < ARRAY_LEN; i++) {
            temp += "\n";
            for (int j = 0; j < ARRAY_LEN; j++) {
                temp += String.format("%02d", this.grid[i][j]);
            }
        }
        temp += "\n";
        return temp;
    }
}

