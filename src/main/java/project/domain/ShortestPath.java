package project.domain;

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
        // 인접 행렬을 확인하고 싶을 때 주석 풀기
//        System.out.println("총 노드 개수 : " + totalNode);
//        printGraph(matrix);

        System.out.println("그래프 [" + number + "]");
        System.out.println("-".repeat(28));

        System.out.println(dijkstra(totalNode, matrix));

        System.out.println("=".repeat(28) + "\n");
    }

    private String dijkstra(int totalNode, int[][] matrix) {
        return null;
    }

    private void printGraph(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.printf("%4d", ints[j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
