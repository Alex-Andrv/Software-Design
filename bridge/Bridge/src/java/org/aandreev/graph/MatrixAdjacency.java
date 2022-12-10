package java.org.aandreev.graph;

import java.org.aandreev.draw.DrawingApi;
import java.org.aandreev.util.Edge;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class MatrixAdjacency extends Graph {
    private final List<List<Integer>> graph;
    public MatrixAdjacency(int n, List<List<Integer>> graph, DrawingApi drawingApi) {
        super(drawingApi, n);
        this.graph = graph;
    }

    @Override
    public Iterator<Edge> iterator() {
        return new MatrixAdjacencyIterator();
    }

    private class MatrixAdjacencyIterator implements Iterator<Edge> {
        private int out = 0;
        private int in = 0;

        public MatrixAdjacencyIterator() {
            findNextEdge();
        }

        private void findNextEdge() {
            for (; out < getCntVertex(); out++) {
                for (;in < getCntVertex(); in++) {
                    if (graph.get(out).get(in) == 1) {
                        return;
                    }
                }
                in = 0;
            }
        }

        // Checks if the next element exists
        public boolean hasNext() {
            return out < getCntVertex();
        }

        // moves the cursor/iterator to next element
        public Edge next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Edge edge = new Edge(out, in);
            in++;
            findNextEdge();
            return edge;
        }
    }
}
