import java.util.List;

public class Main {
    public static void main(String[] args) {

        //Just for testing
        Grid grid = new Grid(5, 5);

        grid.setStartNode(0, 0, true);
        grid.setEndNode(4, 4, true);

        grid.getNode(2, 2).setObstacle(true);
        grid.getNode(3, 2).setObstacle(true);

        AStarPathfinder pathfinder = new AStarPathfinder(grid,HeuristicType.MANHATTAN);
        List<Node> path = pathfinder.findPath();
        if (!path.isEmpty()) {
            System.out.println("Znaleziono ścieżkę:");
            for (Node node : path) {
                System.out.println(" -> (" + node.getX() + ", " + node.getY() + ")");
            }
        } else {
            System.out.println("Nie znaleziono ścieżki.");
        }
        System.out.println(grid);

    }
}

