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
                rect.setOnMouseClicked(mouseEvent -> );
                nodes[y][x] = rect;
                gridPane.add(rect,x,y);
            }
        }

        // Generate random grid button
        Button generateButton = new Button("Generate Grid");
        // TODO - Add action




    }
}
