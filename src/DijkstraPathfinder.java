import java.util.*;

//https://www.youtube.com/watch?v=BuvKtCh0SKk
//https://www.baeldung.com/java-dijkstra
import java.util.*;

import java.util.*;
import java.util.function.Consumer;

public class DijkstraPathfinder {
    private Grid grid;
    private Consumer<Node> visitedCallback;

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


        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                grid.getNode(x, y).setgCost(Double.POSITIVE_INFINITY);
            }
        }
        startNode.setgCost(0);
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            if (visitedCallback != null) {
                visitedCallback.accept(current);
            }

            if (current.equals(endNode)) {
                return reconstructPath(endNode);
            }

            closedSet.add(current);

            for (Node neighbor : grid.getNeighbors(current)) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                double tentativeGCost = current.getgCost() + 1; // Koszt ruchu między węzłami

                if (tentativeGCost < neighbor.getgCost()) {
                    neighbor.setgCost(tentativeGCost);
                    neighbor.setParentNode(current);

                    openSet.remove(neighbor); // Usunięcie przed ponownym dodaniem
                    openSet.add(neighbor);
                }
            }
        }

        return Collections.emptyList(); // Brak ścieżki
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
    public void setVisitedCallback(Consumer<Node> callback) {
        this.visitedCallback = callback;
    }
}

