import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PathFindApp extends Application{
    private static final int SIZE = 20; // Grid size
    private static final int NODE_SIZE = 30;
    private Grid grid = new Grid(SIZE,SIZE);
    private Rectangle[][] nodes = new Rectangle[SIZE][SIZE];

    @Override
    public void start(Stage stage) throws Exception {

        // Grid
        GridPane gridPane = new GridPane();

        for (int y = 0; y < SIZE; y++){
            for (int x = 0; x < SIZE; x++){
                Rectangle rect = new Rectangle(NODE_SIZE, NODE_SIZE);
                rect.setFill(Color.RED);
                rect.setStroke(Color.GRAY);
                int finalX = x;
                int finalY = y;
                rect.setOnMouseClicked(mouseEvent -> handleCellClick(finalX,finalY));
                nodes[y][x] = rect;
                gridPane.add(rect,x,y);
            }
        }

        // Generate random grid button
        Button generateButton = new Button("Generate Grid");
        // TODO - Add action

        // Scene for Grid
        Scene scene = new Scene(gridPane, SIZE * NODE_SIZE, SIZE * NODE_SIZE);
        stage.setScene(scene);
        stage.setTitle("PathFinder Visualization");
        stage.show();

    }
    private void handleCellClick(int x, int y){
        Node node = grid.getNode(x,y);
        if (!node.isObstacle()){
            node.setObstacle(true);
            nodes[y][x].setFill(Color.BLACK);
        }else{
            node.setObstacle(false);
            nodes[y][x].setFill(Color.WHITE);
        }
    }

    private void generateRandomGrid(){
        grid.generateRandomObstacles(0.3);
        updateGrid();
    }

    private void updateGrid(){
        for (int y = 0; y < SIZE; y++){
            for (int x = 0; x < SIZE; x++){
                Node node = grid.getNode(x,y);
                if(node.isObstacle()){
                    nodes[y][x].setFill(Color.BLACK);
                }else{
                    nodes[y][x].setFill(Color.WHITE);
                }
            }
        }
    }


}
