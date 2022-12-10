package java.org.aandreev.graph;

import java.org.aandreev.draw.DrawingApi;
import java.org.aandreev.util.Cycle;
import java.org.aandreev.util.Edge;
import java.org.aandreev.util.Line;
import java.org.aandreev.util.Point;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.min;

public abstract class Graph implements Iterable<Edge> {

    private final int n;

    private final double SCALE = 1 / 6.;

    private Map<Integer, Point> points;

    /**
     * Bridge to drawing api
     */
    private final DrawingApi drawingApi;

    private final int mid_x;
    private final int mid_y;

    private final int max_r;

    public Graph(DrawingApi drawingApi, int n) {
        this.drawingApi = drawingApi;
        this.n = n;
        int height = drawingApi.getDrawingAreaHeight();
        int width = drawingApi.getDrawingAreaWidth();
        this.mid_x = width / 2;
        this.mid_y = height / 2;
        this.max_r = min(mid_y, mid_x);
        this.points = new HashMap<>();
    }

    public int getCntVertex() {
        return this.n;
    }

    public void drawGraph() {
        drawVertex();
        drawEdge();
        drawingApi.draw();
    }

    private void drawVertex() {

        int vertex_r = max_r / getCntVertex() / 2;
        int real_r = max_r - 2 * vertex_r;

        int vertexCount = getCntVertex();
        int cnt_segments = (vertexCount + 1) / 2;
        int dist = real_r / cnt_segments;
        int start = mid_x + real_r;
        for (int i = 0; i < vertexCount; i += 1) {
            int x = start - (i + 1) / 2 * dist;
            int y = 0;
            int sqrt = (int) Math.sqrt(real_r * real_r - (x - mid_x) * (x - mid_x));
            if (i % 2 == 0) {
                y = sqrt + mid_y;
            } else {
                y = -sqrt + mid_y;
            }
            Point point = new Point(x, y);
            points.put(i, point);
            Cycle cycle = new Cycle(point, vertex_r);
            drawingApi.addCircle(cycle);
        }
    }

    private void drawEdge() {
        for (Edge edge: this) {
            Point start = points.get(edge.start);
            Point end = points.get(edge.end);
            Line line = new Line(start, end);
            drawingApi.addLine(line);
        }
    }
}
