import java.util.Objects;

public class Node {
    // Node coordinates
    private final int x;
    private final int y;

    // Obstacle flag
    private boolean isObstacle;

    //Cost
    private double gCost;
    private double hCost; // Heuristics - estimated cost

    // Parent reference
    private Node parentNode;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;

        // Default values
        this.isObstacle = false;
        this.gCost = Double.POSITIVE_INFINITY;
        this.hCost = 0.0;
        this.parentNode = null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isObstacle() {
        return isObstacle;
    }

    public void setObstacle(boolean obstacle) {
        isObstacle = obstacle;
    }

    public double getgCost() {
        return gCost;
    }

    public void setgCost(double gCost) {
        this.gCost = gCost;
    }

    public double gethCost() {
        return hCost;
    }

    public void sethCost(double hCost) {
        this.hCost = hCost;
    }

    public double getfCost() {
        return this.gCost + this.hCost;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node node = (Node) obj;
        return x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    
    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                ", isObstacle=" + isObstacle +
                ", gCost=" + gCost +
                ", hCost=" + hCost +
                ", fCost=" + getfCost() + // Dynamic calculation
                ", parentNode=" + (parentNode != null ? "(" + parentNode.x + "," + parentNode.y + ")" : "null") +
                '}';
    }

}
