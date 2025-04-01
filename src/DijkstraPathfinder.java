import java.util.*;

//https://www.youtube.com/watch?v=BuvKtCh0SKk
//https://www.baeldung.com/java-dijkstra
public class DijkstraPathfinder {
    private Grid grid;

    public DijkstraPathfinder(Grid grid) {
        this.grid = grid;
    }

    public List<Node> findPath() {
        Node startNode = grid.getStartNode();
        Node endNode = grid.getEndNode();

        if (startNode == null || endNode == null) {
            throw new IllegalStateException("Start or End node is not set.");
        }

        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingDouble(Node::getgCost));
        Set<Node> closedSet = new HashSet<>();

        startNode.setgCost(0);
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.equals(endNode)) {
                return reconstructPath(endNode);
            }

            closedSet.add(current);

            for (Node neighbor : grid.getNeighbors(current)) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                double tentativeGCost = current.getgCost() + 1;

                if (tentativeGCost < neighbor.getgCost()) {
                    neighbor.setgCost(tentativeGCost);
                    neighbor.setParentNode(current);
                    openSet.add(neighbor);
                }
            }
        }

        return Collections.emptyList(); // No path found
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
