import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: search <filename> <method>");
            System.exit(1);
        }

        String filename = args[0];
        String method = args[1].toUpperCase();

        Environment env = new Environment(filename);

        int[][] maze = env.getMaze();
        int[] start = env.getStartLocation();
        int[][] goals = env.getGoalLocations();

        try {

            Search searchAlgorithm = null;

            if (method.equals("DFS")) {
                searchAlgorithm = new DFS();
            } else if (method.equals("BFS")) {
                searchAlgorithm = new BFS();
            } else if (method.equals("GBFS")) {
                searchAlgorithm = new GBFS();
            } else if (method.equals("AS")) {
                searchAlgorithm = new AS();
            } else if (method.equals("CUS1")) {
                searchAlgorithm = new CUS1();
            } else if (method.equals("CUS2")) {
                searchAlgorithm = new CUS2();
            } else {
                System.out.println("Invalid search method: " + method);
                return;
            }


            String result = searchAlgorithm.solve(maze, start, goals);
            int numberOfNodes = searchAlgorithm.getNumberOfNodes();

            System.out.println(filename + " " + method + " " + numberOfNodes);
            System.out.println(result);

            int[][] newMaze = searchAlgorithm.getNewMaze();

            for (int i = 0; i < newMaze.length; i++) {
                for (int j = 0; j < newMaze[i].length; j++) {
                    System.out.print(newMaze[i][j] + " ");
                }
                System.out.println();
            }

            // Create an instance of the View class
            View view = new View();

            // Set the maze in the View
            view.setMaze(newMaze);

            // Make the View window visible to display the maze
            view.setVisible(true);

        } catch (Exception e) {
            System.err.println("Error reading the maze file: " + e.getMessage());
        }

    }
}