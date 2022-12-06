package org.example;

public abstract class Graph {

    /**
     * Bridge to drawing api
     */
    private DrawingApi drawingApi;

    public Graph(DrawingApi drawingApi) {
        this.drawingApi = drawingApi;
    }

    public abstract void drawGraph();
}
