package project.domain;

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
        // 인접 행렬을 확인하고 싶을 때 주석 풀기
        System.out.println("총 노드 개수 : " + totalNode);
        printGraph(matrix);

        System.out.println("그래프 [" + number + "]");
        System.out.println("-".repeat(28));

        System.out.println(depthFirstSearch(totalNode, matrix));
        System.out.println(breadthFirstSearch(totalNode, matrix));

        System.out.println("=".repeat(28) + "\n");
    }

    private String depthFirstSearch(int totalNode, int[][] matrix) {
        return null;
    }

    private String breadthFirstSearch(int totalNode, int[][] matrix) {
        boolean[] visitedNode = new boolean[totalNode];
        StringBuilder result = new StringBuilder();

        // 큐 선언. Node 1부터 탐색하므로 초기화.
        Queue<Integer> bfsQueue = new LinkedList<>();
        bfsQueue.add(1);
        visitedNode[0] = true;

        // 큐의 맨 앞 노드 꺼내고, 여기에 연결된 방문하지 않은 모든 Node 큐에 집어넣기 (행 기준으로 탐색)
        while (true) {
            Integer searchNode = bfsQueue.poll();

            // 큐가 비었고 && 모든 노드에 방문했다면 결과 출력
            if (bfsQueue.isEmpty() && isAllVisited(visitedNode, totalNode)) {
                result.append(searchNode);
                break;
            }
            result.append(searchNode).append(" - ");

            for (int i = 0; i < totalNode; i++) {
                if (matrix[searchNode - 1][i] == 1 && !visitedNode[i]) {
                    bfsQueue.add(i + 1);
                    visitedNode[i] = true;
                }
            }

            // 큐가 비었을 때 방문하지 않은 노드들이 남아있다면, 숫자가 가장 작은 노드 부터 또 탐색 시작
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
                result.replace(result.length()-3, result.length(), "   ");
            }
            // 각 단계 별 result 테스트 출력용
            //System.out.println(result.toString());
        }

        return result.toString();
    }

    private boolean isAllVisited(boolean[] visited, int length) {
        for (int i = 0; i < length; i++) {
            if (!visited[i])
                return false;
        }
        return true;
    }

    private void printGraph(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.printf("%2d", ints[j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
