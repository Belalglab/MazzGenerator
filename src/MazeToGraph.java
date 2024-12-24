import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdDraw;

/**
 * This class converts our boolean maze from
 * GenerateMaze to a Graph structure and displays
 * it as a GUI using Std Draw from algs4.
 *
 * @author Jasmine Robbins
 */
public class MazeToGraph {
    private final int cols, rows;
    private final boolean[][] north;
    private final boolean[][] east;
    private final boolean[][] south;
    private final boolean[][] west;
    private final boolean[][] visited;
    private final int startPositionX;
    private final int startPositionY;
    private final int endPositionX;
    private final int endPositionY;
    private Graph graph;

    public MazeToGraph(GenerateMaze maze) {
        this.north = maze.getNorth();
        this.east = maze.getEast();
        this.south = maze.getSouth();
        this.west = maze.getWest();
        this.cols = maze.getCols();
        this.rows = maze.getRows();

        // updates the start and end positions
        this.startPositionX = 1; // Bottom left corner X coordinate
        this.startPositionY = 1; // Bottom left corner Y coordinate
        this.endPositionX = cols; // Top right corner X coordinate
        this.endPositionY = rows; // Top right corner Y coordinate

        visited = new boolean[cols + 2][rows + 2];

        int height = 800;
        int width = (int) Math.round(1.0 * height * cols / rows);
        StdDraw.setCanvasSize(width, height);

        StdDraw.setXscale(0, cols + 2);
        StdDraw.setYscale(0, rows + 2);

        createGraph();
    }

    /**
     * Creates a graph representation of the maze by
     * iterating over each cell in the maze, and connects
     * neighboring cells based on the presence of walls.
     * The edges in the graph represent the connection or
     * path between cells. Each cell corresponds to a vertex
     * in the graph.
     */
    private void createGraph() {
        graph = new Graph(cols * rows);

        for (int v = 0; v < graph.V(); v++) {
            int col = (v % cols) + 1;
            int row = (v / cols) + 1;

            // check for neighboring vertices, add edges where walls are not present
            if (row < rows && !north[col][row]) {
                int w = v + cols; // neighbor to the north
                graph.addEdge(v, w);
            }
            if (col < cols && !east[col][row]) {
                int w = v + 1; // neighbor to the east
                graph.addEdge(v, w);
            }
            if (row > 1 && !south[col][row - 1]) {
                int w = v - cols; // neighbor to the south
                graph.addEdge(v, w);
            }
            if (col > 1 && !west[col - 1][row]) {
                int w = v - 1; // neighbor to the west
                graph.addEdge(v, w);
            }
        }
    }


    /**
     * Draws the Maze with Std Draw.
     */
    public void draw() {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius(.01);
        // draws the walls based on boolean arrays
        for (int col = 1; col <= cols; col++) {
            for (int row = 1; row <= rows; row++) {
                if (south[col][row]) StdDraw.line(col, row, col + 1, row);
                if (north[col][row]) StdDraw.line(col, row + 1, col + 1, row + 1);
                if (west[col][row]) StdDraw.line(col, row, col, row + 1);
                if (east[col][row]) StdDraw.line(col + 1, row, col + 1, row + 1);
            }
        }
        // changes the border wall color at the START position
        StdDraw.setPenRadius(.015);
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        StdDraw.line(1, 1, 1, 2);

        // changes the border wall color at the END position
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.line(cols, rows + 1, cols + 1, rows + 1);

        StdDraw.show();
    }

    /**
     * Checks if the cell at the specified column & row has been visited.
     * This will be used for solving the path.
     *
     * @param col column index of the cell.
     * @param row index of the cell.
     * @return true if the cell has been visited, false otherwise.
     */
    public boolean isVisited(int col, int row) {
        return visited[col][row];
    }

    /**
     * Gets the number of rows and columns in maze
     */
    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    /**
     * Calculates the x-coordinate on the maze for the given Column.
     *
     * @param col column index.
     * @return The x-coordinate value.
     */
    public double getXCoordinate(int col) {
        return col + 0.5;
    }

    /**
     * Calculates the y-coordinate on the graph maze for the given Row.
     *
     * @param row index.
     * @return The y-coordinate value.
     */
    public double getYCoordinate(int row) {
        return row + 0.5;
    }

    /**
     * Gets the start and end coordinates
     */
    public int getStartPositionX() {
        return startPositionX;
    }

    public int getStartPositionY() {
        return startPositionY;
    }

    public int getEndPositionX() {
        return endPositionX;
    }

    public int getEndPositionY() {
        return endPositionY;
    }

    /**
     * Gets the neighboring cells: up down left right
     */
    public boolean[][] getNorth() {
        return north;
    }

    public boolean[][] getSouth() {
        return south;
    }

    public boolean[][] getWest() {
        return west;
    }

    public boolean[][] getEast() {
        return east;
    }

}