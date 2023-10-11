package com.university.master.gomoryhu.Service;

import com.university.master.gomoryhu.Service.Entity.Edge;
import com.university.master.gomoryhu.Service.Entity.GomoryHuResult;
import com.university.master.gomoryhu.Service.Entity.Graph;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
@NoArgsConstructor
public class GomoryHuAlgorithm {

    private static final BigDecimal TWO = new BigDecimal(2);
    private static final int SCALE_TO_FOUR = 4;

    private GomoryHuResult calculateByClassicMethod(Graph inputGraph, boolean castResultToOtn) {
        Graph currentGraph = inputGraph;
        List<double[][]> ringGraphs = new ArrayList<>();

        while (getCurrentNumberOfEdges(currentGraph.getAdjMatrix()) > 2) {
            ringGraphs.add(calculateRingGraph(currentGraph).getAdjMatrix());
            currentGraph = calculateSubnetwork(currentGraph);
        }

        int rows = ringGraphs.get(0).length;
        int cols = ringGraphs.get(0)[0].length;

        double[][] sumRingMatrix = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (double[][] matrix : ringGraphs) {
                    sumRingMatrix[i][j] += matrix[i][j];
                }
            }
        }

        double[][] finalMatrix = new double[rows][cols];
        double[][] currentAdjMatrix = currentGraph.getAdjMatrix();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                finalMatrix[i][j] = BigDecimal.valueOf(sumRingMatrix[i][j])
                        .add(BigDecimal.valueOf(currentAdjMatrix[i][j]))
                        .doubleValue();
            }
        }


        BigDecimal classicResult = sumMatrixElements(finalMatrix);
        classicResult = divideByTwoBigDecimalValue(classicResult);

        BigDecimal adaptedToOtnResult = new BigDecimal(-1);

        if (castResultToOtn) {
            OtnAdapter.adaptMatrix(finalMatrix);  //Adapting final matrix to OTN standard

            adaptedToOtnResult = sumMatrixElements(finalMatrix);
            adaptedToOtnResult = divideByTwoBigDecimalValue(adaptedToOtnResult);
        }

        return GomoryHuResult.builder()
                .classicResult(classicResult)
                .adaptedToOtnResult(adaptedToOtnResult)
                .build();
    }

    private BigDecimal sumMatrixElements(double[][] inputMatrix) {
        BigDecimal result = new BigDecimal(0);

        for (int i = 0; i < inputMatrix.length; i++) {
            for (int j = 0; j < inputMatrix[0].length; j++) {
                result = result.add(BigDecimal.valueOf(inputMatrix[i][j]));
            }
        }

        return result;
    }

    private BigDecimal divideByTwoBigDecimalValue(BigDecimal inputBigDecimal) {
        return inputBigDecimal.divide(TWO, SCALE_TO_FOUR, RoundingMode.CEILING);
    }

    private Graph calculateRingGraph(Graph inputGraph) {
        double[][] inputAdjMatrix = inputGraph.getAdjMatrix();
        int[] existedVertexes = getExistedVertexes(inputAdjMatrix);
        int existedVertexesLength = existedVertexes.length;

        Graph ringGraph = new Graph(inputGraph.getVertexCount());
        double ringGraphEdgeValue = getMinGraphEdgeValue(inputAdjMatrix) / 2;  //move to params later

        for (int i = 0, j = 1; i < existedVertexesLength; i++, j++) {
            if (j == existedVertexesLength) {
                ringGraph.addEdge(existedVertexes[i], existedVertexes[0], ringGraphEdgeValue);
                break;
            }
            ringGraph.addEdge(existedVertexes[i], existedVertexes[j], ringGraphEdgeValue);
        }
        return ringGraph;
    }

    private Graph calculateSubnetwork(Graph inputGraph) {
        double[][] inputMatrix = inputGraph.getAdjMatrix();
        double[][] snAdjMatrix = new double[inputMatrix.length][inputMatrix[0].length];
        double igMinEdgeValue = getMinGraphEdgeValue(inputMatrix);

        for (int i = 0; i < inputMatrix.length; i++) {
            snAdjMatrix[i] = Arrays.copyOf(inputMatrix[i], inputMatrix[i].length);
        }

        for (int i = 0; i < snAdjMatrix.length; i++) {
            for (int j = 0; j < snAdjMatrix[i].length; j++) {
                if (snAdjMatrix[i][j] > 0) {
                    snAdjMatrix[i][j] = BigDecimal.valueOf(snAdjMatrix[i][j])
                            .subtract(BigDecimal.valueOf(igMinEdgeValue))
                            .doubleValue();
                }
            }
        }

        Graph snGraph = new Graph(inputGraph.getVertexCount());
        snGraph.setAdjMatrix(snAdjMatrix);
        return snGraph;
    }

    private int[] getExistedVertexes(double[][] adjMatrix) {
        Set<Integer> existedVertexes = new HashSet<>();

        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix[i].length; j++) {
                if (adjMatrix[i][j] > 0) {
                    existedVertexes.add(j);
                }
            }
        }

        return existedVertexes.stream().mapToInt(Integer::intValue).toArray();
    }

    private double getMinGraphEdgeValue(double[][] adjMatrix) {
        return Arrays.stream(adjMatrix)
                .flatMapToDouble(Arrays::stream)
                .filter(value -> value > 0)
                .min()
                .orElseThrow(() -> new RuntimeException("Empty array"));
    }

    private int getCurrentNumberOfEdges(double[][] adjMatrix) {
        return (int) Arrays.stream(adjMatrix)
                .flatMapToDouble(Arrays::stream)
                .filter(value -> value > 0.0)
                .count();
    }

    private Graph buildGraphByEdges(List<Edge> edges) {
        int numberOfVertexes = 0;

        for (Edge edge: edges) {
            numberOfVertexes = Math.max(numberOfVertexes, Math.max(edge.getFrom(), edge.getTo()));
        }

        Graph graph = new Graph(numberOfVertexes + 1);  // + 1 because count starts from 0

        for (Edge edge : edges) {
            graph.addEdge(edge.getFrom(), edge.getTo(), edge.getValue());
        }

        return graph;
    }

    public GomoryHuResult showResultByClassicMethod(List<Edge> edges) {
        Graph g = buildGraphByEdges(edges);

        System.out.println("Original graph");
        System.out.println(g);
        System.out.println("----------");
        System.out.println(getMinGraphEdgeValue(g.getAdjMatrix()) + " - min graph edge value");
        System.out.println("----------");
        System.out.println("Ring graph");
        System.out.println(calculateRingGraph(g));
        System.out.println("----------");
        System.out.println("Sn graph");
        Graph Sngraph = calculateSubnetwork(g);
        System.out.println(Sngraph);
        System.out.println("-------");
        System.out.println("Existed vertexes in original graph");
        System.out.println(Arrays.toString(getExistedVertexes(g.getAdjMatrix())));
        System.out.println("-------");
        System.out.println("Existed vertexes in Sn graph");
        System.out.println(Arrays.toString(getExistedVertexes(Sngraph.getAdjMatrix())));

        return calculateByClassicMethod(g, true);
    }

    public GomoryHuResult showResultWithAdaptingToOTN(List<Edge> edges) {
        Graph g = buildGraphByEdges(edges);
        OtnAdapter.adaptMatrix(g.getAdjMatrix());
        return calculateByClassicMethod(g, false);
    }
}
