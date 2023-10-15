package com.university.master.gomoryhu.Service;

import com.university.master.gomoryhu.Service.Entity.Edge;
import com.university.master.gomoryhu.Service.Entity.Graph;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@NoArgsConstructor
public class UniversalAlgorithmResolver {

    private static final BigDecimal TWO = new BigDecimal(2);
    private static final int SCALE_TO_FOUR = 4;

    Graph buildGraphByEdges(List<Edge> edges) {
        int numberOfVertexes = 0;

        for (Edge edge : edges) {
            numberOfVertexes = Math.max(numberOfVertexes, Math.max(edge.getFrom(), edge.getTo()));
        }

        Graph graph = new Graph(numberOfVertexes + 1);  // + 1 because count starts from 0

        for (Edge edge : edges) {
            graph.addEdge(edge.getFrom(), edge.getTo(), edge.getValue());
        }

        return graph;
    }

    int getCurrentNumberOfEdges(double[][] adjMatrix) {
        return (int) Arrays.stream(adjMatrix)
                .flatMapToDouble(Arrays::stream)
                .filter(value -> value > 0.0)
                .count();
    }

    int[] getExistedVertexes(double[][] adjMatrix) {
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

    double getMinGraphEdgeValue(double[][] adjMatrix) {
        return Arrays.stream(adjMatrix)
                .flatMapToDouble(Arrays::stream)
                .filter(value -> value > 0)
                .min()
                .orElseThrow(() -> new RuntimeException("Empty array"));
    }

    BigDecimal divideByTwoBigDecimalValue(BigDecimal inputBigDecimal) {
        return inputBigDecimal.divide(TWO, SCALE_TO_FOUR, RoundingMode.CEILING);
    }

    BigDecimal sumMatrixElements(double[][] inputMatrix) {
        BigDecimal result = new BigDecimal(0);

        for (int i = 0; i < inputMatrix.length; i++) {
            for (int j = 0; j < inputMatrix[0].length; j++) {
                result = result.add(BigDecimal.valueOf(inputMatrix[i][j]));
            }
        }

        return result;
    }
}
