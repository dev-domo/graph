package project.fileio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceReader {

    public List<List<String>> readGraphs(String path) {
        List<List<String>> graphs = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            createGraphs(bufferedReader, graphs);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return graphs;
    }

    private void createGraphs(BufferedReader bufferedReader, List<List<String>> graphs) throws IOException {
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            List<String> input = new ArrayList<>();
            input.add(line);
            int totalNode = toInt(line);

            addGraphComponent(totalNode, input, bufferedReader);

            graphs.add(input);
        }
    }

    private void addGraphComponent(int totalNode, List<String> input, BufferedReader bufferedReader) throws IOException {
        while (totalNode > 0) {
            input.add(bufferedReader.readLine());
            totalNode--;
        }
    }

    private int toInt(String text) {
        return Integer.parseInt(text);
    }

}
