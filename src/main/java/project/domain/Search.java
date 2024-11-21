package project.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Search {

    private final List<Graph> graphs;

    public Search(final List<Graph> graphs) {
        this.graphs = graphs;
    }

    public void doSearch() {
        int totalNode;
        int[][] matrix;
        int number = 0;

        System.out.println("1. 그래프 탐방 수행 결과\n");

        for (Graph graph : graphs) {
            totalNode = graph.getTotalNode();
            matrix = graph.getMatrix();
            printSearchResult(++number, totalNode, matrix);
        }
    }

    private void printSearchResult(int number, int totalNode, int[][] matrix) {
        System.out.println("그래프 [" + number + "]");
        System.out.println("-".repeat(28));

        System.out.println(depthFirstSearch(totalNode, matrix));
        System.out.println(breadthFirstSearch(totalNode, matrix));

        System.out.println("=".repeat(28) + "\n");
    }

    private String depthFirstSearch(int totalNode, int[][] matrix) {
        boolean[] visited = new boolean[totalNode + 1];
        List<String> result = new ArrayList<>();

        recursiveDfs(1, matrix, visited, result);

        return formatResult(result);
    }

    private void recursiveDfs(int node, int[][] matrix, boolean[] visited, List<String> result) {
        if (visited[node]) {
            return;
        }
        visited[node] = true;
        result.add(String.valueOf(node));

        for (int neighbor = 0; neighbor < matrix[node - 1].length; neighbor++) {
            if (matrix[node - 1][neighbor] == 1 && !visited[neighbor + 1]) {
                recursiveDfs(neighbor + 1, matrix, visited, result);
            }
        }
    }

    private String formatResult(List<String> result) {
        return "깊이 우선 탐색\n" + String.join(" - ", result);
    }

    private String breadthFirstSearch(int totalNode, int[][] matrix) {
        boolean[] visitedNode = new boolean[totalNode];
        StringBuilder result = new StringBuilder();

        Queue<Integer> bfsQueue = new LinkedList<>();
        bfsQueue.add(1);
        visitedNode[0] = true;

        while (true) {
            Integer searchNode = bfsQueue.poll();

            if (isEndedSearch(totalNode, bfsQueue, visitedNode, result, searchNode)) {
                break;
            }

            result.append(searchNode).append(" - ");
            exploreNodes(totalNode, matrix, searchNode, visitedNode, bfsQueue);
            checkNotConnectedGraph(totalNode, bfsQueue, visitedNode, result);
        }

        return "너비 우선 탐색\n" + result;
    }

    private boolean isEndedSearch(int totalNode, Queue<Integer> bfsQueue, boolean[] visitedNode, StringBuilder result,
                                  Integer searchNode) {
        if (bfsQueue.isEmpty() && isAllVisited(visitedNode, totalNode)) {
            result.append(searchNode);
            return true;
        }
        return false;
    }

    private void exploreNodes(int totalNode, int[][] matrix, Integer searchNode, boolean[] visitedNode,
                              Queue<Integer> bfsQueue) {
        for (int i = 0; i < totalNode; i++) {
            if (matrix[searchNode - 1][i] == 1 && !visitedNode[i]) {
                bfsQueue.add(i + 1);
                visitedNode[i] = true;
            }
        }
    }

    private void checkNotConnectedGraph(int totalNode, Queue<Integer> bfsQueue, boolean[] visitedNode,
                                        StringBuilder result) {
        if (bfsQueue.isEmpty() && !isAllVisited(visitedNode, totalNode)) {
            int minNumNode = 1;
            for (int i = 0; i < totalNode; i++) {
                if (!visitedNode[i]) {
                    minNumNode = i + 1;
                    break;
                }
            }
            bfsQueue.add(minNumNode);
            visitedNode[minNumNode - 1] = true;
            result.replace(result.length() - 3, result.length(), "   ");
        }
    }

    private boolean isAllVisited(boolean[] visited, int length) {
        for (int i = 0; i < length; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }
}
