import java.util.*;

//https://www.baeldung.com/java-a-star-pathfinding
enum HeuristicType {
    MANHATTAN, EUCLIDEAN
}

public class AStarPathfinder {
    private Grid grid;
    private HeuristicType heuristicType;

    public AStarPathfinder(Grid grid, HeuristicType heuristicType) {
        this.grid = grid;
        this.heuristicType = heuristicType;
    }

    public List<Node> findPath() {
        Node startNode = grid.getStartNode();
        Node endNode = grid.getEndNode();

        if (startNode == null || endNode == null) {
            throw new IllegalStateException("Start or End node is not set.");
        }

        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingDouble(Node::getfCost));
        Set<Node> closedSet = new HashSet<>();

        startNode.setgCost(0);
        startNode.sethCost(calculateHeuristic(startNode, endNode));
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.equals(endNode)) {
                return reconstructPath(endNode);
            }

            closedSet.add(current);

            for (Node neighbor : grid.getNeighbors(current)) {
                if (closedSet.contains(neighbor) || neighbor.isObstacle()) continue;

                double movementCost = (current.getX() != neighbor.getX() && current.getY() != neighbor.getY()) ? 1.41 : 1;
                double tentativeGCost = current.getgCost() + movementCost;

                if (tentativeGCost < neighbor.getgCost()) {
                    neighbor.setgCost(tentativeGCost);
                    neighbor.sethCost(calculateHeuristic(neighbor, endNode));
                    neighbor.setParentNode(current);

                    // Update queue
                    openSet.remove(neighbor);
                    openSet.add(neighbor);
                }
            }
        }

        return Collections.emptyList(); // No path found
    }

    private double calculateHeuristic(Node node, Node target) {
        int dx = Math.abs(node.getX() - target.getX());
        int dy = Math.abs(node.getY() - target.getY());

        switch (heuristicType) {
            case MANHATTAN:
                return dx + dy;
            case EUCLIDEAN:
                return Math.sqrt(dx * dx + dy * dy);
            default:
                return 0;
        }
    }

    private List<Node> reconstructPath(Node endNode) {
        List<Node> path = new ArrayList<>();
        Node current = endNode;

        while (current != null) {
            path.add(current);
            current = current.getParentNode();
        }

        Collections.reverse(path);
        return path;
    }
}

