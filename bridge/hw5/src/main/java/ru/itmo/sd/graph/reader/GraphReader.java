package ru.itmo.sd.graph.reader;

import java.io.IOException;

import ru.itmo.sd.graph.graph.Graph;

public interface GraphReader {

    Graph read(String path) throws IOException;

}
