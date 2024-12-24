import edu.princeton.cs.algs4.*;

import java.util.Scanner;

/**
 * Starts the maze program and retrieves
 * the information from the other classes.
 *
 * @authors Jasmine Robbins + Belal Glab
 */
public class Main {
    private static int cols, rows;
    private static GenerateMaze gm;

    public static void main(String[] args) {
        // draw the homescreen. Once cols & rows are entered, the maze will appear and generate
        MazeGui.drawHomeScreen();// draws from mazegui instead
        StdDraw.show();

        // Display "how many columns or rows" at the start of the page
        System.out.println("Welcome to the Maze Generator!");

        // Create a Scanner object to read input from the user
        Scanner scanner = new Scanner(System.in);
        // Ask the user for the number of columns and rows
        System.out.println("Please enter the number of columns: ");
        cols = scanner.nextInt();
        System.out.println("Please enter the number of rows: ");
        rows = scanner.nextInt();

        // Create an instance of GenerateMaze to build a random maze
        gm = new GenerateMaze(cols, rows);

        // Create an instance of MazeToGraph to convert the maze into a graph structure and draw it
        MazeToGraph maze = new MazeToGraph(gm);
        maze.draw();

        // Solve the maze and get the path using depth first search
        StdDraw.pause(800); // pause for a moment before pacman solves
        MazeSolver solver = new MazeSolver(maze);
        solver.solve();

        // Draw the solved path on the maze
        Iterable<Integer> pathIndexes = solver.getSolution();
        MazeGui.drawPath(pathIndexes, maze); // draws path from MazeGui
    }
}

