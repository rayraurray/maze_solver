import java.util.*;
public abstract class Search {
    protected int numberOfNodes;
    protected int[][] newMaze;

    protected int[][] copyMaze(int[][] originalMaze) {

        int[][] mazeCopy = new int[originalMaze.length][];

        for (int i = 0; i < originalMaze.length; i++) {
            mazeCopy[i] = Arrays.copyOf(originalMaze[i], originalMaze[i].length);
        }

        return mazeCopy;
    }

    protected String getDirection(int i) {
        if(i == 0) return "up; ";
        if(i == 1) return "left; ";
        if(i == 2) return "down; ";
        return "right; ";
    }

    protected int[] getIntDirection(String direction) {
        switch (direction) {
            case "up":
                return new int[]{0, -1};
            case "down":
                return new int[]{0, 1};
            case "left":
                return new int[]{-1, 0};
            case "right":
                return new int[]{1, 0};
            default:
                return new int[]{0, 0};
        }

    }

    protected void updatePathInMaze(int[][] maze, int[] start, int[][] goals, String path) {
        int x = start[0];
        int y = start[1];

        String[] directions = path.split("; ");
        for (String direction : directions) {
            int[] moves = getIntDirection(direction);
            x += moves[0];
            y += moves[1];
            maze[y][x] = 2;
        }

        for (int[] goal : goals) {
            x = goal[0];
            y = goal[1];
            maze[y][x] = 9; // Mark as a goal (9)
        }
    }

    protected int manhattanDistance(int[] from, int[] to) {
        return Math.abs(from[0] - to[0]) + Math.abs(from[1] - to[1]);
    }
    protected int[][] sortGoalsByDistance(int[][] goals, int[] start) {
        Arrays.sort(goals, Comparator.comparingInt(goal -> manhattanDistance(start, goal)));
        return goals;
    }

    protected boolean isValid(int[][] maze, int x, int y, Set<String> visited) {
        if (x < 0 || x >= maze[0].length || y < 0 || y >= maze.length) {
            return false;
        }

        if (maze[y][x] == 1) return false;

        if (visited.contains(x + "," + y)) return false;
        return true;
    }

    protected int[][] removeGoal(int[][] goals, int[] goalToRemove) {
        return Arrays.stream(goals)
                .filter(goal -> !Arrays.equals(goal, goalToRemove))
                .toArray(int[][]::new);
    }

    protected int getNumberOfNodes(){
        return numberOfNodes;
    }

    protected int[][] getNewMaze(){ return newMaze; }

    protected abstract String solve(int[][] maze, int[] start, int[][] goals);
}
