import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


enum InteractionMode {
    SET_START,
    SET_END,
    TOGGLE_OBSTACLE
}

public class PathFindApp extends Application {
    private static final int SIZE = 20; // Grid size
    private static final int NODE_SIZE = 30;
    private Grid grid = new Grid(SIZE, SIZE);
    private Rectangle[][] nodes = new Rectangle[SIZE][SIZE];

    private InteractionMode mode = InteractionMode.TOGGLE_OBSTACLE;
    private Node startNode = null;
    private Node endNode = null;

    @Override
    public void start(Stage stage) {
        // Grid
        GridPane gridPane = new GridPane();
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                Rectangle rect = new Rectangle(NODE_SIZE, NODE_SIZE);
                rect.setFill(Color.WHITE);
                rect.setStroke(Color.GRAY);
                int finalX = x;
                int finalY = y;
                rect.setOnMouseClicked(mouseEvent -> handleCellClick(finalX, finalY));
                nodes[y][x] = rect;
                gridPane.add(rect, x, y);
            }
        }

        // Generate random grid button
        Button generateButton = new Button("Generate Grid");
        generateButton.setOnAction(e -> {
            if (startNode == null || endNode == null) {
                System.out.println("Najpierw ustaw punkt startowy i końcowy!");
                return;
            }
            generateRandomGrid();
        });

        // Control panel
        VBox controlPanel = new VBox(10);
        controlPanel.setPadding(new Insets(10));

        Button setStartButton = new Button("Ustaw Start");
        setStartButton.setOnAction(e -> mode = InteractionMode.SET_START);

        Button setEndButton = new Button("Ustaw Koniec");
        setEndButton.setOnAction(e -> mode = InteractionMode.SET_END);

        Button obstacleButton = new Button("Dodaj/Usuń przeszkody");
        obstacleButton.setOnAction(e -> mode = InteractionMode.TOGGLE_OBSTACLE);

        controlPanel.getChildren().addAll(setStartButton, setEndButton, obstacleButton, generateButton);

        HBox root = new HBox(controlPanel, gridPane);
        Scene scene = new Scene(root);
        stage.setTitle("PathFinder Visualization");
        stage.setScene(scene);
        stage.show();
    }

    private void handleCellClick(int x, int y) {
        Node node = grid.getNode(x, y);

        switch (mode) {
            case SET_START:
                if (startNode != null) {
                    nodes[startNode.getY()][startNode.getX()].setFill(Color.WHITE);
                }
                startNode = node;
                grid.setStartNode(x, y, true);
                nodes[y][x].setFill(Color.LIGHTGREEN);
                break;

            case SET_END:
                if (endNode != null) {
                    nodes[endNode.getY()][endNode.getX()].setFill(Color.WHITE);
                }
                endNode = node;
                grid.setEndNode(x, y, true);
                nodes[y][x].setFill(Color.RED);
                break;

            case TOGGLE_OBSTACLE:
                if (!node.isObstacle()) {
                    node.setObstacle(true);
                    nodes[y][x].setFill(Color.BLACK);
                } else {
                    node.setObstacle(false);
                    nodes[y][x].setFill(Color.WHITE);
                }
                break;
        }
    }

    private void generateRandomGrid() {
        grid.generateRandomObstacles(0.3);
        updateGrid();
    }

    private void updateGrid() {
        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                Node node = grid.getNode(x, y);
                Rectangle rect = nodes[y][x];

                if (node.isObstacle()) {
                    rect.setFill(Color.BLACK);
                } else if (node.equals(grid.getStartNode())) {
                    rect.setFill(Color.LIGHTGREEN); // START
                } else if (node.equals(grid.getEndNode())) {
                    rect.setFill(Color.RED); // KONIEC
                } else {
                    rect.setFill(Color.WHITE);
                }
            }
        }
    }
}
