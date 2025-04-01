import java.util.ArrayList;
import java.util.List;

public class Grid {
    private Node[][] grid;
    private int width;
    private int height;
    private Node startNode;
    private Node endNode;

    // Default Constructor
    public Grid(){
        this.width = 20;
        this.height = 20;
        initializeGrid();
    }

    // Custom Constructor
    public Grid(int width, int height){
        this.width = width;
        this.height = height;
        initializeGrid();
    }

    // Grid initialization

    public void initializeGrid(){
        this.grid = new Node[width][height];
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                grid[x][y] = new Node(x,y);
            }
        }
    }

    // Get Neighbors excluding Obstacles && grid borders
    // https://stackoverflow.com/questions/33854605/find-node-neighbors-in-2d-array
    // https://stackoverflow.com/questions/19961786/how-to-increment-neighbor-cells-from-a-point-in-a-2d-array/19963523
    //   0 1 2
    // 0 a b c
    // 1 d N e
    // 2 f g h
    //

    public List<Node> getNeighbors(Node node){
        List<Node> neighbors = new ArrayList<>();
        int x = node.getX();
        int y = node.getY();

        // Check all (8) directions
        for (int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){
             //Skip current Node node
             if (i == 0 && j == 0){
                 continue;
             }

             int neighborX = x + i;
             int neighborY = y + j;

             // Check if neighbor coordinates are inside grid
             if (neighborX >= 0 && neighborX < width && neighborY >= 0 && neighborY < height){

                 Node neighbor = this.grid[neighborX][neighborY];
                 //Add Node to return List if not an Obstacle
                 if (!neighbor.isObstacle()){
                     neighbors.add(neighbor);
                 }
             }
            }
        }
        return neighbors;
    }


    // Set Start/End Node
    public void setStartNode(int x, int y, boolean force) {
        if (startNode == null || force) {
            if (startNode != null) {
                startNode.setObstacle(false);
            }
            startNode = grid[x][y];
            startNode.setObstacle(false);
        }
    }

    public void setEndNode(int x, int y, boolean force) {
        if (endNode == null || force) {
            if (endNode != null) {
                endNode.setObstacle(false);
            }
            endNode = grid[x][y];
            endNode.setObstacle(false);
        }
    }


    // Reset grid (keep size)
    public void resetGrid() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Node node = grid[x][y];
                node.setObstacle(false);
                node.setgCost(Double.POSITIVE_INFINITY);
                node.sethCost(0.0);
                node.setParentNode(null);
            }
        }
        startNode = null;
        endNode = null;
    }


    // Hard reset Method
    public void hardResetGrid(){
        initializeGrid();
        startNode = null;
        endNode = null;
    }

    public Node[][] getGrid() {
        return grid;
    }

    public void setGrid(Node[][] grid) {
        this.grid = grid;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public void setEndNode(Node endNode) {
        this.endNode = endNode;
    }

    public Node getNode(int x, int y){
        if(x >= 0 && x < width && y >= 0 && y < height){
            return grid[x][y];
        }
        return null;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Node node = grid[x][y];
                if (node == startNode) {
                    sb.append("S ");
                } else if (node == endNode) {
                    sb.append("E ");
                } else if (node.isObstacle()) {
                    sb.append("X ");
                } else {
                    sb.append(". ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
