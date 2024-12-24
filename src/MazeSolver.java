import edu.princeton.cs.algs4.Stack;

/**
 * This class solves a path from a start point to
 * an end point in a maze created from MazeToGraph.
 * Once a path is found, a Stack is used to store
 * the cell locations for Pacman to follow.
 *
 * @authors Belal + Jasmine
 */
public class MazeSolver {

    private final MazeToGraph maze; //maze to solve
    private final int mazeWidth; //columns
    private final int mazeHeight; //rows
    private final boolean[][] visited; //marks the cells as visited when solving
    private final Stack<Integer> solution; //stores the solved path in the maze

    public MazeSolver(MazeToGraph mazeGraph) {
        this.maze = mazeGraph;
        this.mazeWidth = mazeGraph.getCols();
        this.mazeHeight = mazeGraph.getRows();
        this.visited = new boolean[mazeWidth + 2][mazeHeight + 2];
        this.solution = new Stack<>(); //creates new Stack to hold the solved path
    }

    /**
     * Solves a direct path in a maze using depth-first-
     * search, and a Stack to store the found path from
     * start(bottom left) to end(top right).
     * @param x current column
     * @param y current row
     * @param endX end position x
     * @param endY end position y
     * @return the direct path found in maze
     */
    private boolean solve(int x, int y, int endX, int endY) {
        // check if the end of the maze is reached
        if (x == endX && y == endY) {
            solution.push((y - 1) * mazeWidth + x - 1); //add end cell to the path
            return true;
        }

        // check if the cell is outside the maze or has been visited
        if (x == 0 || y == 0 || x == mazeWidth + 1 || y == mazeHeight + 1 || visited[x][y]) {
            return false;
        }

        // mark the current cell as visited
        visited[x][y] = true;

        // use DFS to find a path in each direction if there is no wall
        boolean directPath = false;
        if (!maze.getNorth()[x][y] && !directPath) directPath = solve(x, y + 1, endX, endY); //up
        if (!maze.getEast()[x][y] && !directPath) directPath = solve(x + 1, y, endX, endY); //right
        if (!maze.getSouth()[x][y] && !directPath) directPath = solve(x, y - 1, endX, endY); //down
        if (!maze.getWest()[x][y] && !directPath) directPath = solve(x - 1, y, endX, endY); //left

        // If a direct path to the end is found, add the current cell vertices to the stack
        if (directPath) {
            solution.push((y - 1) * mazeWidth + x - 1);
        } else {
            // If the cell is not part of the direct path, reset its visited state to false
            visited[x][y] = false;
        }

        // once direct path is found return true
        return directPath;
    }
    /**
     * Recursively call solve to find a direct path in the maze.
     */
    public void solve() {
        // start at the bottom left corner
        int startX = maze.getStartPositionX();
        int startY = maze.getStartPositionY();
        // end at the top right corner
        int endX = maze.getEndPositionX();
        int endY = maze.getEndPositionY();

        solve(startX, startY, endX, endY);

    }

    /**
     * @return the maze vertices(cells) that solve the direct path
     */
    public Iterable<Integer> getSolution() {
        return solution;
    }
}
