package project.domain;

public class Graph {

    private final int totalNode;
    private final int[][] matrix;

    public Graph(final int totalNode, final int[][] matrix) {
        this.totalNode = totalNode;
        this.matrix = matrix;
    }

    public int getTotalNode() {
        return totalNode;
    }

    public int[][] getMatrix() {
        return matrix;
    }
}
