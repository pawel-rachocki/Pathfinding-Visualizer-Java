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

    public List<Node> getNeighbors(Node node){
        List<Node> neighbors = new ArrayList<>();
        return neighbors;
    }


}
