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
        // 인접 행렬을 확인하고 싶을 때 주석 풀기
//        System.out.println("총 노드 개수 : " + totalNode);
//        printGraph(matrix);

        System.out.println("그래프 [" + number + "]");
        System.out.println("-".repeat(28));

        System.out.println(dijkstra(totalNode, matrix));

        System.out.println("=".repeat(28) + "\n");
    }

    private String dijkstra(int totalNode, int[][] matrix) {
        // 최단 거리, 방문 여부, 경로 추적을 위한 배열 초기화
        int[] distance = new int[totalNode]; //각 정점까지 최단거리. 정점 번호 그대로 배열의 인덱스
        boolean[] visited = new boolean[totalNode]; // 방문 여부. 이미 최단 경로가 확정된 정점을 표시
        int[] parent = new int[totalNode]; // 최단 경로 추적을 위해 이전에 방문한 정점을 저장

        Arrays.fill(distance, Integer.MAX_VALUE); // 처음 최단거리는 모두 무한대로 설정.
        Arrays.fill(parent, -1);
        distance[0] = 0; // 시작노드는 0으로 설정

        // 최단 거리 계산
        for (int i = 0; i <= totalNode; i++) {
            int u = findMinDistanceNode(distance, visited, totalNode); // 가장 짧은 거리의 정점
            if (u == -1) {
                break; // 더 이상 처리할 노드가 없는 경우 (방문할 정점이 없는 경우) 중단
            }
            visited[u] = true; // 정점 방문 처리. -> 다시 방문하지 않도록 함.

            // 인접 노드들의 거리 업데이트
            for (int v = 0; v < totalNode; v++) {
                if (matrix[u][v] != Integer.MAX_VALUE && matrix[u][v] > 0 && !visited[v]
                        && distance[u] + matrix[u][v] < distance[v]) {
                    distance[v] = distance[u] + matrix[u][v]; // 최단 거리 갱신
                    parent[v] = u; // 경로 추적을 위해 이전에 방문한 정점 저장
                }
            }
        }

        return formatResult(totalNode, distance, parent);
    }

    //방문하지 않은 노드 중 최단 거리 노드 찾기
    private int findMinDistanceNode(int[] distance, boolean[] visited, int totalNode) {
        int minDistance = Integer.MAX_VALUE; // 최단 거리를 무한대로 초기화
        int minNode = -1; // 최단 거리를 지닌 정점의 번호 -1로 초기화

        for (int i = 0; i < totalNode; i++) { // 1번 정점부터 totalNode까지 반복
            if (!visited[i] && distance[i] < minDistance) {
                minDistance = distance[i]; // 최소 거리 업데이트
                minNode = i; // 최소 거리 정점 저장
            }
        }

        return minNode; // 최단 거리의 정점 반환
    }

    // 최단 경로 결과 및 경로 정보 출력
    private String formatResult(int totalNode, int[] distance, int[] parent) {
        StringBuilder result = new StringBuilder(); // 여러 문자열로 구성된 result를 반복하여 연결
        result.append("시작점 : 1\n");

        for (int i = 1; i < totalNode; i++) { // 시작점을 제외한 정점 경로 출력
            result.append("정점 [").append(i + 1).append("] : ");
            if (distance[i] == Integer.MAX_VALUE) {
                result.append("경로 없음, 길이 : INF\n"); // 경로가 없는 경우 출력값
            } else {
                result.append(formatPath(i, parent)).append(", 길이 : ").append(distance[i]).append("\n");
            }
        }

        return result.toString();
    }

    // 특정 정점에서 시작점까지의 경로 추적
    private String formatPath(int node, int[] parent) {
        if (parent[node] == -1) {
            return String.valueOf(node + 1); // 현재 노드가 시작점이면 해당 노드를 반환.
        }
        return formatPath(parent[node], parent) + " - " + (node + 1); // 재귀 호출
    }

    private void printGraph(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.printf("%10d", ints[j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}