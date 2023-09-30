package com.university.master.gomoryhu.Service;

import com.university.master.gomoryhu.Service.Entity.Graph;

import java.math.BigDecimal;
import java.util.Arrays;

public class GomoryHuAlgorithm {

    private Graph calculateRingGraph(Graph inputGraph) {
        double[][] inputAdjMatrix = inputGraph.getAdjMatrix();
        int graphVertexCount = getCurrentNumberOfEdges(inputAdjMatrix) / 2; //ToDo: fir vertex count
        Graph ringGraph = new Graph(graphVertexCount);
        double ringGraphEdgeValue = getGraphEdgeValue(inputAdjMatrix) / 2;  //move to params later

        for (int i = 0, j = 1; i < graphVertexCount; i++, j++) {
            if (j == graphVertexCount) {
                ringGraph.addEdge(i,0, ringGraphEdgeValue);
                break;
            }
            ringGraph.addEdge(i, j, ringGraphEdgeValue);
        }
        return ringGraph;
    }

    private Graph calculateSubnetwork(Graph inputGraph) {
        double[][] snAdjMatrix = inputGraph.getAdjMatrix();
        double ringGraphEdgeValue = getGraphEdgeValue(snAdjMatrix);    //move to params later

        for (int i = 0; i < snAdjMatrix.length; i++) {
            for (int j = 0; j < snAdjMatrix[i].length; j++) {
                if (snAdjMatrix[i][j] > 0)  {
                    snAdjMatrix[i][j] = BigDecimal.valueOf(snAdjMatrix[i][j])
                            .subtract(BigDecimal.valueOf(ringGraphEdgeValue))
                            .doubleValue();
                }
            }
        }

        int snGraphVertexCount = getCurrentNumberOfEdges(snAdjMatrix) / 2;  //ToDo: fir vertex count
        Graph snGraph = new Graph(snGraphVertexCount);
        snGraph.setAdjMatrix(snAdjMatrix);
        return snGraph;
    }

    private double getGraphEdgeValue(double[][] adjMatrix) {
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
        System.out.println(g);
        System.out.println(getGraphEdgeValue(g.getAdjMatrix()));
        System.out.println("----------");
        System.out.println(calculateRingGraph(g));
        System.out.println("----------");
        System.out.println(calculateSubnetwork(g));
    }
}
