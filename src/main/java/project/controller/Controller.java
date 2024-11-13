package project.controller;

import project.fileio.ResourceReader;
import project.fileio.PathGraphParser;
import project.fileio.SearchGraphParser;
import project.domain.Search;
import project.domain.ShortestPath;
import java.util.List;

public class Controller {

    private static final String INPUT1_FILE_PATH = "src/main/resources/input1.txt";
    private static final String INPUT2_FILE_PATH = "src/main/resources/input2.txt";

    private final ResourceReader reader = new ResourceReader();

    public void startSearch() {
        List<List<String>> components = reader.readGraphs(INPUT1_FILE_PATH);
        Search search = SearchGraphParser.parse(components);

        search.doSearch();
    }

    public void startDijkstra() {
        List<List<String>> components = reader.readGraphs(INPUT2_FILE_PATH);
        ShortestPath shortestPath = PathGraphParser.parse(components);

        shortestPath.doDijkstra();
    }
}
