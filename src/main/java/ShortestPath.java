package project.domain;

import java.util.Arrays;
import java.util.List;

public class ShortestPath {

    private final List<Graph> graphs;

    public ShortestPath(List<Graph> graphs) {
        this.graphs = graphs;
    }

    public void doDijkstra() {
        int totalNode;
        int[][] matrix;
        int number = 0;

        System.out.println("2. 최단 경로 구하기 수행 결과\n");

        for (Graph graph : graphs) {
            totalNode = graph.getTotalNode();
            matrix = graph.getMatrix();

            printShortestPathResult(++number, totalNode, matrix);
        }
    }

    private void printShortestPathResult(int number, int totalNode, int[][] matrix) {
        System.out.println("그래프 [" + number + "]");
        System.out.println("-".repeat(28));

        System.out.println(dijkstra(totalNode, matrix));

        System.out.println("=".repeat(28) + "\n");
    }

    private String dijkstra(int totalNode, int[][] matrix) {
        int[] distance = new int[totalNode];
        boolean[] visited = new boolean[totalNode];
        int[] parent = new int[totalNode];

        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        distance[0] = 0;

        for (int i = 0; i <= totalNode; i++) {
            int u = findMinDistanceNode(distance, visited, totalNode);
            if (u == -1) {
                break;
            }
            visited[u] = true;
            updateMinDistance(totalNode, matrix, u, visited, distance, parent);
        }

        return formatResult(totalNode, distance, parent);
    }

    private void updateMinDistance(int totalNode, int[][] matrix, int u, boolean[] visited, int[] distance,
                                   int[] parent) {
        for (int v = 0; v < totalNode; v++) {
            if (matrix[u][v] != Integer.MAX_VALUE && matrix[u][v] > 0 && !visited[v]
                    && distance[u] + matrix[u][v] < distance[v]) {
                distance[v] = distance[u] + matrix[u][v];
                parent[v] = u;
            }
        }
    }

    private int findMinDistanceNode(int[] distance, boolean[] visited, int totalNode) {
        int minDistance = Integer.MAX_VALUE;
        int minNode = -1;

        for (int i = 0; i < totalNode; i++) {
            if (!visited[i] && distance[i] < minDistance) {
                minDistance = distance[i];
                minNode = i;
            }
        }

        return minNode;
    }

    private String formatResult(int totalNode, int[] distance, int[] parent) {
        StringBuilder result = new StringBuilder();
        result.append("시작점 : 1\n");

        for (int i = 1; i < totalNode; i++) {
            result.append("정점 [").append(i + 1).append("] : ");
            if (distance[i] == Integer.MAX_VALUE) {
                result.append("경로 없음, 길이 : INF\n");
            } else {
                result.append(formatPath(i, parent)).append(", 길이 : ").append(distance[i]).append("\n");
            }
        }

        return result.toString().trim();
    }

    private String formatPath(int node, int[] parent) {
        if (parent[node] == -1) {
            return String.valueOf(node + 1);
        }
        return formatPath(parent[node], parent) + " - " + (node + 1);
    }
}