import java.util.List;

public class Main {
    public static void main(String[] args) {

        //Just for testing
        Grid grid = new Grid(10, 10);

        grid.setStartNode(0, 0, true);
        grid.setEndNode(9, 9, true);

        grid.getNode(1, 0).setObstacle(true);
        grid.getNode(1, 1).setObstacle(true);
        grid.getNode(1, 2).setObstacle(true);
        grid.getNode(2, 2).setObstacle(true);
        grid.getNode(3, 2).setObstacle(true);

        DijkstraPathfinder pathfinder = new DijkstraPathfinder(grid);
        List<Node> path = pathfinder.findPath();

        if (path != null) {
            System.out.println("Found Path:");
            for (Node node : path) {
                System.out.println("[" + node.getX() + ", " + node.getY() + "]");
            }
        } else {
            System.out.println("Path not found.");
        }

        System.out.println(grid);
    }
}
