import edu.princeton.cs.algs4.StdRandom;

/**
 * Generates a random maze using recursion
 * based on the number of rows and columns
 * from user input.
 * Reference: Maze.java algs 4
 *
 * @author Jasmine Robbins
 */
public class GenerateMaze {
    private final int cols, rows;   // dimension of maze based on user input
    private boolean[][] north;      // is there a wall to north of cell (col, row)
    private boolean[][] east;
    private boolean[][] south;
    private boolean[][] west;
    private boolean[][] visited;

    public GenerateMaze(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;

        initializeBorderCells();
        generate();
    }

    /**
     * initializes the border cells as visited.
     */
    private void initializeBorderCells() {
        visited = new boolean[cols + 2][rows + 2]; // create array to track visited cells

        for (int col = 0; col < cols + 2; col++) {
            visited[col][0] = true;         // top border
            visited[col][rows + 1] = true; // bottom border
        }
        for (int row = 0; row < rows + 2; row++) {
            visited[0][row] = true;         //left border
            visited[cols + 1][row] = true; // right border
        }

        // initialize all walls as present
        north = new boolean[cols + 2][rows + 2];
        east = new boolean[cols + 2][rows + 2];
        south = new boolean[cols + 2][rows + 2];
        west = new boolean[cols + 2][rows + 2];
        for (int col = 0; col < cols + 2; col++) {
            for (int row = 0; row < rows + 2; row++) {
                north[col][row] = true;
                east[col][row] = true;
                south[col][row] = true;
                west[col][row] = true;
            }
        }
    }

    /**
     * Generates a random maze from a specified cell.
     * Recursively calls generate to create dimensions of
     * maze.
     * @param col in maze.
     * @param row in maze
     */
    private void generate(int col, int row) {
        visited[col][row] = true;

        // while there is an unvisited neighbor
        while (!visited[col][row + 1] || !visited[col + 1][row]
                || !visited[col][row - 1] || !visited[col - 1][row]) {

            // pick random neighbor
            while (true) {
                double r = StdRandom.uniformInt(4); //generates random number between 0 & 3.
                // If the random number is 0 and the cell north(above) is unvisited
                // Remove the wall between the current cell and the cell north.
                if (r == 0 && !visited[col][row + 1]) {
                    north[col][row] = false;
                    south[col][row + 1] = false;
                    // recursively generate the maze from the "north" or above cell
                    generate(col, row + 1);
                    break;
                }
                // If the random number is 1 and the cell to the east(right) is unvisited
                // Remove the wall between the current cell and the cell to the east.
                else if (r == 1 && !visited[col + 1][row]) {
                    east[col][row] = false;
                    west[col + 1][row] = false;
                    // Recursively generate the maze from the cell to the east
                    generate(col + 1, row);
                    break;
                }
                // If the random number is 2 and the cell south(below) is unvisited
                // Remove the wall between the current cell and the cell south
                else if (r == 2 && !visited[col][row - 1]) {
                    south[col][row] = false;
                    north[col][row - 1] = false;
                    // Recursively generate the maze from the cell to the south
                    generate(col, row - 1);
                    break;
                }
                // If the random number is 3 and the cell to the west(left) is unvisited
                // Remove the wall between the current cell and the cell to the west
                else if (r == 3 && !visited[col - 1][row]) {
                    west[col][row] = false;
                    east[col - 1][row] = false;
                    // Recursively generate the maze from the cell to the west
                    generate(col - 1, row);
                    break;
                }
            }
        }
    }
    /**
     * Recursive method to generate a maze by resetting
     * the start and end cells, and removes walls accordingly.
     * The start position is the bottom left corner.
     */
    private void generate() {
        generate(cols, rows); // start position at bottom left corner

        // Reset the start cell as unvisited and remove the north and south walls
        visited[1][1] = false;
        north[1][1] = false;
        south[1][2] = false;

        // Reset the end cell as unvisited and remove the north and south walls
        visited[cols][rows] = false;
        north[cols][rows - 1] = false;
        south[cols][rows] = false;
    }

    public boolean[][] getNorth() {
        return north;
    }

    public boolean[][] getEast() {
        return east;
    }

    public boolean[][] getSouth() {
        return south;
    }

    public boolean[][] getWest() {
        return west;
    }

    public boolean[][] getVisited() {
        return visited;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }
}

