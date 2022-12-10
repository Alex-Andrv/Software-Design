package java.org.aandreev.graph;

import java.org.aandreev.draw.DrawingApi;
import java.org.aandreev.util.Edge;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ListAdjacency extends Graph {
    private final List<List<Integer>> graph;

    public ListAdjacency(int n, List<List<Integer>> graph, DrawingApi drawingApi) {
        super(drawingApi, n);
        this.graph = graph;
    }

    @Override
    public Iterator<Edge> iterator() {
        return new ListAdjacencyIterator();
    }

    public class ListAdjacencyIterator implements Iterator<Edge> {

        private int out = 0;
        private int j = 0;

        public ListAdjacencyIterator() {
            findNextEdge();
        }

        private void findNextEdge() {
            for (; out < getCntVertex(); out++) {
                for (;j < graph.get(out).size(); j++) {
                    return;
                }
                j = 0;
            }
        }

        // Checks if the next element exists
        public boolean hasNext() {
            return out > getCntVertex();
        }

        // moves the cursor/iterator to next element
        public Edge next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Edge edge = new Edge(out, graph.get(out).get(j));
            j++;
            findNextEdge();
            return edge;
        }
    }
}
