package java.org.aandreev.draw;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.org.aandreev.util.Cycle;
import java.org.aandreev.util.Line;

import java.util.LinkedList;

import static javafx.application.Application.launch;

public class JavaFxDrawingApi extends Application implements DrawingApi {

    public final int WIDTH = 600;
    public final int HEIGHT = 400;

    private volatile GraphicsContext context = null;
    private final Canvas CANVAS = new Canvas();

    private final LinkedList<Line> lines = new LinkedList<Line>();
    private final LinkedList<Cycle> cycles = new LinkedList<Cycle>();

    public JavaFxDrawingApi() {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        StackPane pane = new StackPane();
        pane.getChildren().add(CANVAS);
        CANVAS.setHeight(HEIGHT);
        CANVAS.setWidth(WIDTH);
        pane.setMinSize(100, 100);
        Scene scene = new Scene(pane);
        stage.setTitle("Graph plot");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
        context = CANVAS.getGraphicsContext2D();
    }

    @Override
    public int getDrawingAreaWidth() {
        return WIDTH;
    }

    @Override
    public int getDrawingAreaHeight() {
        return HEIGHT;
    }

    public void addCircle(Cycle cycle) {
        cycles.add(cycle);
    }

    @Override
    public void addLine(Line line) {
        lines.add(line);
    }

    @Override
    public void draw() {
        for (Cycle cycle : cycles) {
            context.fillOval(cycle.point.x - cycle.r, cycle.point.y - cycle.r, 2 * cycle.r, 2 * cycle.r);
        }
        for (Line line : lines) {
            context.strokeLine(line.start.x, line.start.y, line.end.x, line.end.y);
        }
    }
}
