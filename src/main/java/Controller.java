import java.util.List;

public class Controller {

    private static final String INPUT1_FILE_PATH = "src/main/java/input1.txt";
    private static final String INPUT2_FILE_PATH = "src/main/java/input2.txt";

    private final ResourceReader reader = new ResourceReader();

    public void start() {
        startSearch();
        findShortestPath();
    }

    private void startSearch() {
        List<List<String>> components = reader.readGraphs(INPUT1_FILE_PATH);
        Search search = SearchGraphParser.parse(components);

        search.doSearch();
    }

    private void findShortestPath() {
        List<List<String>> components = reader.readGraphs(INPUT2_FILE_PATH);
        ShortestPath shortestPath = PathGraphParser.parse(components);

        shortestPath.doDijkstra();
    }
}
