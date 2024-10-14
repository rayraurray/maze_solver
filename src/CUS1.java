import java.util.*;

public class CUS1 extends Search {
    public String solve(int[][] maze, int[] start, int[][] goals) {
        StringBuilder result = new StringBuilder();
        goals = sortGoalsByDistance(goals, start);

        int[][] mazeCopy = copyMaze(maze);
        int[] init = start;
        int[][] finish = goals;

        for (int[] goal : goals) {
            PriorityQueue<InformedNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(InformedNode::getCost));
            priorityQueue.add(new InformedNode(start, "", 0));

            Set<String> visited = new HashSet<>();
            Map<String, Integer> costMap = new HashMap<>();

            while (!priorityQueue.isEmpty()) {
                InformedNode node = priorityQueue.poll();
                numberOfNodes++;

                if (Arrays.equals(node.position, goal)) {
                    result.append(node.path);
                    start = node.position;
                    goals = removeGoal(goals, goal);
                }

                visited.add(Arrays.toString(node.position));
                costMap.put(Arrays.toString(node.position), node.cost);

                addUCSNeighbors(maze, node, priorityQueue, visited, costMap);
            }
        }

        updatePathInMaze(mazeCopy, init, finish, result.toString());
        newMaze = mazeCopy;

        return result.length() > 0 ? result.toString() : "No solution found.";
    }

    void addUCSNeighbors(int[][] maze, InformedNode node, PriorityQueue<InformedNode> priorityQueue, Set<String> visited, Map<String, Integer> costMap) {
        int[] moves = {0, -1, 0, 1, 0};

        int x = node.position[0];
        int y = node.position[1];

        for (int i = 0; i < 4; i++) {
            int nextX = x + moves[i];
            int nextY = y + moves[i + 1];

            if (isValid(maze, nextX, nextY, visited)) {
                int newCost = node.cost + 1;
                String positionKey = Arrays.toString(new int[]{nextX, nextY});

                if (!costMap.containsKey(positionKey) || newCost < costMap.get(positionKey)) {
                    priorityQueue.add(new InformedNode(new int[]{nextX, nextY}, node.path + getDirection(i), newCost));
                    costMap.put(positionKey, newCost);
                }
            }
        }
    }
}