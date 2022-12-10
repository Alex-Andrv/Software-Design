package java.org.aandreev.draw;

import java.org.aandreev.util.Cycle;
import java.org.aandreev.util.Line;

public interface DrawingApi {
    int getDrawingAreaWidth();
    int getDrawingAreaHeight();
    void addCircle(Cycle cycle);
    void addLine(Line line);

    void draw();
}
