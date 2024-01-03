import java.util.*;

public class GBFS extends Search {

    public String solve(int[][] maze, int[] start, int[][] goals) {
        StringBuilder result = new StringBuilder();
        goals = sortGoalsByDistance(goals, start);

        int[][] mazeCopy = copyMaze(maze);
        int[] init = start;
        int[][] finish = goals;

        for (int[] goal : goals) {
            PriorityQueue<InformedNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.getCost()));
            priorityQueue.add(new InformedNode(start, "", manhattanDistance(start, goal)));

            Set<String> visited = new HashSet<>();

            while (!priorityQueue.isEmpty()) {
                InformedNode node = priorityQueue.poll();
                numberOfNodes++;

                if (Arrays.equals(node.position, goal)) {
                    result.append(node.path);
                    start = node.position;
                    goals = removeGoal(goals, goal);
                }

                visited.add(Arrays.toString(node.position));

                addGBFSNeighbors(maze, node, goal, priorityQueue, visited);
            }
        }

        updatePathInMaze(mazeCopy, init, finish, result.toString());
        newMaze = mazeCopy;

        return result.length() > 0 ? result.toString() : "No solution found.";
    }

    void addGBFSNeighbors(int[][] maze, InformedNode node, int[] goal, PriorityQueue<InformedNode> priorityQueue, Set<String> visited) {
        int[] moves = {0, -1, 0, 1, 0};

        int x = node.position[0];
        int y = node.position[1];

        for (int i = 0; i < 4; i++) {
            int nextX = x + moves[i];
            int nextY = y + moves[i + 1];

            if (isValid(maze, nextX, nextY, visited)) {
                String positionKey = Arrays.toString(new int[]{nextX, nextY});

                if (!visited.contains(positionKey)) {
                    int heuristic = manhattanDistance(new int[]{nextX, nextY}, goal);
                    priorityQueue.add(new InformedNode(new int[]{nextX, nextY}, node.path + getDirection(i), heuristic));
                }
            }
        }
    }
}