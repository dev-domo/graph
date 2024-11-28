import java.util.ArrayList;
import java.util.List;

public class PathGraphParser {

    private static final String SEPARATOR = " ";

    public static ShortestPath parse(List<List<String>> components) {
        List<Graph> graphs = new ArrayList<>();

        for (List<String> component : components) {
            int totalNode = toInt(component.get(0));
            int[][] matrix = createGraph(component, totalNode);

            graphs.add(new Graph(totalNode, matrix));
        }

        return new ShortestPath(graphs);
    }

    private static int[][] createGraph(List<String> component, int totalNode) {
        int[][] graph = new int[totalNode][totalNode];
        initGraph(totalNode, graph);

        for (int node = 1; node < component.size(); node++) {
            addGraph(component, graph, node);
        }

        return graph;
    }

    private static void initGraph(int totalNode, int[][] graph) {
        for (int i = 0; i < totalNode; i++) {
            for (int j = 0; j < totalNode; j++) {
                if (i != j) {
                    graph[i][j] = Integer.MAX_VALUE;
                }
            }
        }
    }

    private static void addGraph(List<String> component, int[][] graph, int node) {
        String[] result = splitComponent(component, node);

        for (int i = 1; i < result.length - 1; i += 2) {
            int adjacency = toInt(result[i]);
            int weight = toInt(result[i + 1]);
            graph[node - 1][adjacency - 1] = weight;
        }
    }

    private static String[] splitComponent(List<String> component, int node) {
        return component.get(node).split(SEPARATOR);
    }

    private static int toInt(String text) {
        return Integer.parseInt(text);
    }
}
