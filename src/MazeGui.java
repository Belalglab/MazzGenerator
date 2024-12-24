import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

/**
 * This class displays the homescreen, endscreen,
 * and pacman moving through the maze using
 * StdDraw from algs 4.
 *
 * @author Belal
 */
public class MazeGui {

    public MazeGui(int cols, int rows){
        // set canvas size and dimensions
        int height = 800;
        int width = (int) Math.round(1.0 * height * cols / rows);
        StdDraw.setCanvasSize(width, height);

        StdDraw.setXscale(0, cols + 2);
        StdDraw.setYscale(0, rows + 2);

        MazeToGraph maze = new MazeToGraph(new GenerateMaze(cols, rows));
    }

    /**
     * Draws a welcome screen
     */
    public static void drawHomeScreen() {
        // initial canvas size
        int height = 600;
        int width = 900;

        StdDraw.setCanvasSize(width, height);
        StdDraw.clear(StdDraw.BLACK); //background color
        StdDraw.setTitle("Pacman Labyrinth");

        // Draw the rectangle border around the text
        StdDraw.setPenRadius(0.022);
        double rectX = 0.5; // X-coordinate of the center of the rectangle
        double rectY = 0.5; // Y-coordinate of the center of the rectangle
        double rectWidth = .9; // Width of the rectangle
        double rectHeight = .9; // Height of the rectangle
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.rectangle(rectX, rectY, rectWidth / 2, rectHeight / 2);
        StdDraw.rectangle(0.5, 0.5, 0.9, 0.9);

        StdDraw.setPenColor(StdDraw.YELLOW);
        Font font = new Font("SansSerif", Font.BOLD, 35);
        StdDraw.setFont(font);
        StdDraw.text(0.5, 0.8, "Welcome to Pacman's Labyrinth!");

        StdDraw.setPenColor(Color.YELLOW);
        font = new Font("SansSerif", Font.PLAIN, 24);
        StdDraw.setFont(font);
        StdDraw.text(0.5, 0.2, "Enter number of columns & rows to begin: ");

        StdDraw.picture(0.5, 0.5, "src/pictures/pacman.png", 0.2, 0.3);

        StdDraw.show();
    }


    /**
     * Draws the solved path on the gui
     *
     * @author Jasmine
     * @param pathIndexes location of path
     */
    public static void drawPath(Iterable<Integer> pathIndexes, MazeToGraph maze) {
        int prevCol = 0;
        int prevRow = 0;

        for (int index : pathIndexes) {
            int col = (index % maze.getCols()) + 1;
            int row = (index / maze.getCols()) + 1;

            double x = maze.getXCoordinate(col);
            double y = maze.getYCoordinate(row);

            if (prevCol != 0 && prevRow != 0) {
                double prevX = maze.getXCoordinate(prevCol);
                double prevY = maze.getYCoordinate(prevRow);

                // draw pacman throughout maze
                StdDraw.picture(x, y, "src/pictures/pacman.png", 0.8, 0.9);
                StdDraw.setPenColor(StdDraw.GREEN);
                StdDraw.filledCircle(prevX, prevY, 0.45);
                StdDraw.show();
                StdDraw.pause(400);
            }

            prevCol = col;
            prevRow = row;

            // checks when pacman finishes the maze
            if (col == maze.getEndPositionX() && row == maze.getEndPositionY()) {
                StdDraw.pause(200);
                drawEndScreen();
                break;
            }
        }

    }

    /**
     * Draws a finish screen
     *
     * @author Jasmine
     */
    public static void drawEndScreen() {
        // main canvas
        int height = 600;
        int width = 900;
        StdDraw.setCanvasSize(width, height);
        StdDraw.clear(Color.BLACK);

        StdDraw.setPenRadius(0.02);
        StdDraw.setPenColor(StdDraw.RED);
        double rectX = 0.5; // X-coordinate of the center of the rectangle
        double rectY = 0.5; // Y-coordinate of the center of the rectangle
        double rectWidth = 0.4; // Width of the rectangle
        double rectHeight = 0.4; // Height of the rectangle
        StdDraw.rectangle(rectX, rectY, rectWidth / 2, rectHeight / 2);
        StdDraw.rectangle(0.5, 0.5, 0.9, 0.8);

        StdDraw.setPenColor(StdDraw.YELLOW);
        Font font = new Font("SansSerif", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(0.5, 0.85, "Pacman solved the maze and saved Ms. Pacman!");

        StdDraw.picture(0.5, 0.5, "src/pictures/pacman-end.jpeg", 0.4, 0.4); // draw pacman and ms pacman
        StdDraw.picture(0.5, 0.1, "src/pictures/ghosts.png", 0.5, 0.2); // draw ghosts

        StdDraw.show();
    }
}