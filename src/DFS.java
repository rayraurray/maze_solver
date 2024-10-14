import java.util.*;

public class DFS extends Search {

    public String solve(int[][] maze, int[] start, int[][] goals) {
        StringBuilder result = new StringBuilder();
        goals = sortGoalsByDistance(goals, start);

        int[][] mazeCopy = copyMaze(maze);
        int[] init = start;
        int[][] finish = goals;

        for (int[] goal : goals) {
            Stack<Node> stack = new Stack<>();
            stack.add(new Node(start, ""));
            Set<String> visited = new HashSet();

            while (!stack.isEmpty()) {
                Node node = stack.pop();
                numberOfNodes++;

                if (Arrays.equals(node.position, goal)) {
                    result.append(node.path);
                    start = node.position;
                    goals = removeGoal(goals, goal);
                }

                visited.add(Arrays.toString(node.position));
                addNeighbors(maze, node, stack, visited);
            }

        }

        updatePathInMaze(mazeCopy, init, finish, result.toString());
        newMaze = mazeCopy;

        return result.length() > 0 ? result.toString() : "No solution found.";
    }

    void addNeighbors(int[][] maze, Node node, Stack<Node> stack, Set<String> visited) {


        int[] moves = {0, -1, 0, 1}; // x/y offsets

        int x = node.position[0];
        int y = node.position[1];

        // Push neighbors in reverse order: Right, Down, Left, Up
        for (int i = 3; i >= 0; i--) {

            int nextX = x + moves[i];
            int nextY = y;

            if (i == 0) {
                nextY = y - 1;
            } else if (i == 2) {
                nextY = y + 1;
            }


            // Check if in bounds
            if(nextX >= 0 && nextX < maze[0].length &&
                    nextY >= 0 && nextY < maze.length) {

                // Check not visited
                if(!visited.contains(Arrays.toString(new int[]{nextX, nextY}))) {

                    // Check not wall
                    if(maze[nextY][nextX] != 1) {

                        // Add neighbor
                        stack.add(new Node(new int[]{nextX, nextY}, node.path + getDirection(i)));

                    }
                }
            }
        }
    }
}