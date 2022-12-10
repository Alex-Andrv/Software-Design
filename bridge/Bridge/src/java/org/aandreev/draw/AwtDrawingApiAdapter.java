package java.org.aandreev.draw;

import java.org.aandreev.util.Cycle;
import java.org.aandreev.util.Line;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

final public class AwtDrawingApiAdapter implements DrawingApi {

    public final int WIDTH = 600;
    public final int HEIGHT = 400;

    private final AwtDraw frame;

    public AwtDrawingApiAdapter() throws HeadlessException {
        this.frame = new AwtDraw();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        frame.setSize(600, 400);
        frame.setVisible(true);
    }

    private class AwtDraw extends Frame {

        private final LinkedList<Line> lines = new LinkedList<Line>();
        private final LinkedList<Cycle> cycles = new LinkedList<Cycle>();

        @Override
        public void paint(Graphics g) {
            Graphics2D ga = (Graphics2D)g;
            ga.setPaint(Color.green);
            for (Line line : lines) {
                ga.drawLine(line.start.x, line.start.y, line.end.x, line.end.y);
            }

            for (Cycle cycle : cycles) {
                ga.fillOval(cycle.point.x - cycle.r, cycle.point.y - cycle.r, 2 * cycle.r, 2 * cycle.r);
            }
        }

        public void drawCircle(Cycle cycle) {
            cycles.add(cycle);
        }

        public void drawLine(Line line) {
            lines.add(line);
        }
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
        frame.drawCircle(cycle);
    }

    @Override
    public void addLine(Line line) {
        frame.drawLine(line);
    }

    public void draw() {
        frame.repaint();
    }
}
