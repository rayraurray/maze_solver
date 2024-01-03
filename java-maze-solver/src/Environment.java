import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Environment {
    private int[][] maze;
    private int[][] goalLocations;
    private int[] startLocation;
    private String filename;

    public Environment(String filename) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        this.filename = filename;

        String line1 = reader.readLine();
        String line2 = reader.readLine();
        String line3 = reader.readLine();

        // Parse size from line1
        Pattern sizePattern = Pattern.compile("\\[(\\d+),(\\d+)]");
        Matcher sizeMatcher = sizePattern.matcher(line1);
        int rows = 0;
        int cols = 0;
        if(sizeMatcher.find()){
            rows = Integer.parseInt(sizeMatcher.group(1));
            cols = Integer.parseInt(sizeMatcher.group(2));
        }


        int[][] maze = new int[rows][cols];

        // Parse start from line2
        Pattern startPattern = Pattern.compile("\\((\\d+),(\\d+)\\)");
        Matcher startMatcher = startPattern.matcher(line2);
        if(startMatcher.find()){
            int x = Integer.parseInt(startMatcher.group(1));
            int y = Integer.parseInt(startMatcher.group(2));
            maze[y][x] = 8; // set start
            int [] startLocation = new int[2];
            startLocation[0] = x;
            startLocation[1] = y;
            this.startLocation = startLocation;
        }


        int[][] goals = new int[2][2]; // array to hold goals
        int goalIndex = 0;
        // Parse goals from line3
        Pattern goalPattern = Pattern.compile("\\((\\d+),(\\d+)\\)");
        Matcher goalMatcher = goalPattern.matcher(line3);
        while(goalMatcher.find()){
            int x = Integer.parseInt(goalMatcher.group(1));
            int y = Integer.parseInt(goalMatcher.group(2));
            maze[y][x] = 9; // set goal
            goals[goalIndex][0] = x;
            goals[goalIndex][1] = y;

            goalIndex++;
        }

        this.goalLocations = goals;

        String line;
        Pattern wallPattern = Pattern.compile("\\((\\d+),(\\d+),(\\d+),(\\d+)\\)");
        while((line = reader.readLine()) != null) {
            Matcher wallMatcher = wallPattern.matcher(line);
            if(wallMatcher.find()){
                int x = Integer.parseInt(wallMatcher.group(1));
                int y = Integer.parseInt(wallMatcher.group(2));
                int w = Integer.parseInt(wallMatcher.group(3));
                int h = Integer.parseInt(wallMatcher.group(4));

                for(int i=0; i<h; i++){
                    for(int j=0; j<w; j++){
                        maze[y+i][x+j] = 1;
                    }
                }
            }
        }

        this.maze = maze;

    }


    public int[][] getMaze() {
        return maze;
    }

    public int[] getStartLocation() {
        return startLocation;
    }

    public int[][] getGoalLocations(){
        return goalLocations;
    }

}