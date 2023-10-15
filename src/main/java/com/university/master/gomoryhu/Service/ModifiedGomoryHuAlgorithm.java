package com.university.master.gomoryhu.Service;

import com.university.master.gomoryhu.Service.Entity.Edge;
import com.university.master.gomoryhu.Service.Entity.GomoryHuResult;
import com.university.master.gomoryhu.Service.Entity.Graph;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@NoArgsConstructor
public class ModifiedGomoryHuAlgorithm extends UniversalAlgorithmResolver {

    private GomoryHuResult calculateByModifiedMethod(Graph inputGraph) {
        Graph currentGraph = inputGraph;
        List<double[][]> ringGraphs = new ArrayList<>();
        double ringGraphEdgeValue;

        while (getCurrentNumberOfEdges(currentGraph.getAdjMatrix()) > 2) {
            ringGraphEdgeValue = getMinGraphEdgeValue(currentGraph.getAdjMatrix()) / 2;  //move to params later
            ringGraphEdgeValue = OtnAdapter.adapt(ringGraphEdgeValue);

            ringGraphs.add(calculateRingGraph(currentGraph, ringGraphEdgeValue).getAdjMatrix());

            ringGraphEdgeValue = ringGraphEdgeValue * 2; //due duplex channels

            currentGraph = calculateSubnetwork(currentGraph, ringGraphEdgeValue);
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
        OtnAdapter.adaptMatrix(currentAdjMatrix);
        castAllNegativeToZero(currentAdjMatrix);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                finalMatrix[i][j] = BigDecimal.valueOf(sumRingMatrix[i][j])
                        .add(BigDecimal.valueOf(currentAdjMatrix[i][j]))
                        .doubleValue();
            }
        }


        BigDecimal modifiedResult = sumMatrixElements(finalMatrix);
        modifiedResult = divideByTwoBigDecimalValue(modifiedResult);

        return GomoryHuResult.builder()
                .defaultResult(modifiedResult)
                .adaptedToOtnResult(new BigDecimal(-1))
                .build();
    }

    private Graph calculateRingGraph(Graph inputGraph, double ringGraphEdgeValue) {
        double[][] inputAdjMatrix = inputGraph.getAdjMatrix();
        int[] existedVertexes = getExistedVertexes(inputAdjMatrix);
        int existedVertexesLength = existedVertexes.length;

        Graph ringGraph = new Graph(inputGraph.getVertexCount());

        for (int i = 0, j = 1; i < existedVertexesLength; i++, j++) {
            if (j == existedVertexesLength) {
                ringGraph.addEdge(existedVertexes[i], existedVertexes[0], ringGraphEdgeValue);
                break;
            }
            ringGraph.addEdge(existedVertexes[i], existedVertexes[j], ringGraphEdgeValue);
        }
        return ringGraph;
    }

    private Graph calculateSubnetwork(Graph inputGraph, double ringGraphEdgeValue) {
        double[][] inputMatrix = inputGraph.getAdjMatrix();
        double[][] snAdjMatrix = new double[inputMatrix.length][inputMatrix[0].length];

        for (int i = 0; i < inputMatrix.length; i++) {
            snAdjMatrix[i] = Arrays.copyOf(inputMatrix[i], inputMatrix[i].length);
        }

        for (int i = 0; i < snAdjMatrix.length; i++) {
            for (int j = 0; j < snAdjMatrix[i].length; j++) {
                if (snAdjMatrix[i][j] > 0) {
                    snAdjMatrix[i][j] = BigDecimal.valueOf(snAdjMatrix[i][j])
                            .subtract(BigDecimal.valueOf(ringGraphEdgeValue))
                            .doubleValue();
                }
            }
        }

        Graph snGraph = new Graph(inputGraph.getVertexCount());
        snGraph.setAdjMatrix(snAdjMatrix);
        return snGraph;
    }

    private void castAllNegativeToZero(double[][] inputAdjMatrix) {
        for (int i = 0; i < inputAdjMatrix.length; i++) {
            for (int j = 0; j < inputAdjMatrix[i].length; j++) {
                if (inputAdjMatrix[i][j] < 0) {
                    inputAdjMatrix[i][j] = 0;
                }
            }
        }
    }

    public GomoryHuResult showResultByModifiedMethod(List<Edge> edges) {
        Graph g = buildGraphByEdges(edges);
        return calculateByModifiedMethod(g);
    }
}
