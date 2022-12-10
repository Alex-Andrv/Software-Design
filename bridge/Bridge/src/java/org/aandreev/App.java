package java.org.aandreev;

import java.org.aandreev.draw.AwtDrawingApiAdapter;
import java.org.aandreev.draw.DrawingApi;
import java.org.aandreev.draw.JavaFxDrawingApi;
import java.org.aandreev.error.InvalidFileFormatException;
import java.org.aandreev.graph.Graph;
import java.org.aandreev.graph.ListAdjacency;
import java.org.aandreev.graph.MatrixAdjacency;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        if (args.length != 3 || !Arrays.stream(args).allMatch(Objects::nonNull)) {
            printUsage();
            return;
        }

        boolean isListAdjacency = false;

        switch (args[0]) {
            case "lists":
                isListAdjacency = true;
                break;
            case "matrix":
                isListAdjacency = false;
                break;
            default:
                throw new IllegalArgumentException("Invalid graph adjacency type: " + args[0]);
        }

        DrawingApi drawingApi = null;

        switch (args[1]) {
            case "Awt":
                drawingApi = new AwtDrawingApiAdapter();
                break;
            case "JavaFx":
                drawingApi = new JavaFxDrawingApi();
                break;
            default:
                throw new IllegalArgumentException("Invalid drawing type: " + args[1]);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(args[2]))) {
            Graph graph = isListAdjacency ? readAsListAdjacency(reader, drawingApi) : readAsMatrixAdjacency(reader, drawingApi);
            graph.drawGraph();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static Graph readAsMatrixAdjacency(BufferedReader reader, DrawingApi drawingApi) throws IOException {
        String[] head = reader.readLine().split(" ");

        if (head.length != 1) {
            throw new InvalidFileFormatException("Wrong line 0. Expected 1 value - size of matrix");
        }

        int n = 0;
        try {
            n = Integer.parseInt(head[0]);
        } catch (NumberFormatException e) {
            throw new InvalidFileFormatException("Wrong line 0. Expected 1 integer value - size of matrix");
        }

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            try {
                List<Integer> line = Arrays.stream(reader.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
                if (line.size() != n || !line.stream().allMatch(a -> a == 0 || a == 1)) {
                    throw new InvalidFileFormatException(
                            String.format("Wrong line %d. Expected %d 0 or 1 value", i, n));
                }
                graph.add(line);
            } catch (NumberFormatException e) {
                throw new InvalidFileFormatException(
                        String.format("Wrong line %d. Expected %d 0 or 1 value", i, n));
            }
        }

        return new MatrixAdjacency(n, graph, drawingApi);
    }

    private static Graph readAsListAdjacency(BufferedReader reader, DrawingApi drawingApi) throws IOException {
        String[] head = reader.readLine().split(" ");

        if (head.length != 1) {
            throw new InvalidFileFormatException("Wrong line 0. Expected 1 value - cnt row of matrix");
        }

        int n = 0;
        try {
            n = Integer.parseInt(head[0]);
        } catch (NumberFormatException e) {
            throw new InvalidFileFormatException("Wrong line 0. Expected 1 integer value - size of matrix");
        }

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            try {
                List<Integer> line = Arrays.stream(reader.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
                int j = line.get(0);
                if (line.size() == 0 || line.get(0) + 1 != line.size()) {
                    throw new InvalidFileFormatException(
                            String.format("Wrong line %d. Expected %d 0 or 1 value", i, n));
                }
                int finalN = n;
                if (!line.stream().allMatch(a -> a < 0 || a >= finalN)) {
                    throw new InvalidFileFormatException(
                            String.format("Wrong line %d. Expected %d 0 or 1 value", i, n));
                }
                graph.add(line.subList(1, line.size()));
            } catch (NumberFormatException e) {
                throw new InvalidFileFormatException(
                        String.format("Wrong line %d. Expected %d integer value - size of matrix", i, n));
            }
        }

        return new ListAdjacency(n, graph, drawingApi);
    }

    private static void printUsage() {
        System.out.println("Expected: <lists | matrix> <graphviz | Awt | JavaFx> <in.txt>");
        System.out.println("Where:");
        System.out.println("lists: graph format - adjacency list");
        System.out.println("matrix: graph format - adjacency matrix");
        System.out.println("Awt: draw with Awt");
        System.out.println("JavaFx: draw with JavaFx");
        System.out.println("in.txt: input file");
    }
}
