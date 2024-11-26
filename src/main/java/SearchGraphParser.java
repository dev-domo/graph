package project.fileio;

import project.domain.Graph;
import java.util.ArrayList;
import java.util.List;
import project.domain.Search;

public class SearchGraphParser {

    private static final String SEPARATOR = " ";

    public static Search parse(List<List<String>> components) {
        List<Graph> graphs = new ArrayList<>();

        for (List<String> component : components) {
            int totalNode = toInt(component.get(0));
            int[][] graph = createGraph(component, totalNode);

            graphs.add(new Graph(totalNode, graph));
        }

        return new Search(graphs);
    }

    private static int[][] createGraph(List<String> component, int totalNode) {
        int[][] graph = new int[totalNode][totalNode];

        for (int node = 1; node < component.size(); node++) {
            addGraph(component, node, graph);
        }

        return graph;
    }

    private static void addGraph(List<String> component, int node, int[][] graph) {
        String[] nodeConnections = splitInformation(component, node);

        for (int i = 1; i < nodeConnections.length; i++) {
            int adjacencyNode = toInt(nodeConnections[i]);
            graph[node - 1][adjacencyNode - 1] = 1;
        }
    }

    private static String[] splitInformation(List<String> component, int node) {
        return component.get(node).split(SEPARATOR);
    }

    private static int toInt(String text) {
        return Integer.parseInt(text);
    }
}
