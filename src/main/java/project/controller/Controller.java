package project.controller;

import java.util.List;
import project.domain.Search;
import project.domain.ShortestPath;
import project.fileio.PathGraphParser;
import project.fileio.ResourceReader;
import project.fileio.SearchGraphParser;

public class Controller {

    private static final String INPUT1_FILE_PATH = "src/main/resources/input1.txt";
    private static final String INPUT2_FILE_PATH = "src/main/resources/input2.txt";

    private final ResourceReader reader = new ResourceReader();

    public void startSearch() {
        List<List<String>> components = reader.readGraphs(INPUT1_FILE_PATH);
        Search search = SearchGraphParser.parse(components);

        search.doSearch();
    }

    public void findShortestPath() {
        List<List<String>> components = reader.readGraphs(INPUT2_FILE_PATH);
        ShortestPath shortestPath = PathGraphParser.parse(components);

        shortestPath.doDijkstra();
    }
}
