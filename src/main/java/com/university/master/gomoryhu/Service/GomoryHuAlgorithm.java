package com.university.master.gomoryhu.Service;

import com.university.master.gomoryhu.Service.Entity.Graph;

import java.math.BigDecimal;
import java.util.*;

public class GomoryHuAlgorithm {

    private Graph calculateRingGraph(Graph inputGraph) {
        double[][] inputAdjMatrix = inputGraph.getAdjMatrix();
        int[] existedVertexes = getExistedVertexes(inputAdjMatrix);
        int existedVertexesLength = existedVertexes.length;

        Graph ringGraph = new Graph(inputGraph.getVertexCount());
        double ringGraphEdgeValue = getMinGraphEdgeValue(inputAdjMatrix) / 2;  //move to params later

        for (int i = 0, j = 1; i < existedVertexesLength; i++, j++) {
            if (j == existedVertexesLength) {
                ringGraph.addEdge(existedVertexes[i],existedVertexes[0], ringGraphEdgeValue);
                break;
            }
            ringGraph.addEdge(existedVertexes[i], existedVertexes[j], ringGraphEdgeValue);
        }
        return ringGraph;
    }

    private Graph calculateSubnetwork(Graph inputGraph) {
        double[][] snAdjMatrix = inputGraph.getAdjMatrix();
        double igMinEdgeValue = getMinGraphEdgeValue(snAdjMatrix);

        for (int i = 0; i < snAdjMatrix.length; i++) {
            for (int j = 0; j < snAdjMatrix[i].length; j++) {
                if (snAdjMatrix[i][j] > 0)  {
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
                if (adjMatrix[i][j] > 0)  {
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
    public void showGraph() {
        Graph g = new Graph(4);
        g.addEdge(0,1, 22);
        g.addEdge(1,2, 15.8);
        g.addEdge(2,3, 15.5);
        g.addEdge(3,0, 40.2);
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
    }
}
