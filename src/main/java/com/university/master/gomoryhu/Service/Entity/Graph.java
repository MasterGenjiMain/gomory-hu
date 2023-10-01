package com.university.master.gomoryhu.Service.Entity;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class Graph {

    private int vertexCount; //vertexes
    private int edgeCount; //edges
    private double[][] adjMatrix;

    public Graph(int nodes) {
        this.vertexCount = nodes;
        this.edgeCount = 0;
        this.adjMatrix = new double[nodes][nodes];
    }

    public void addEdge(int u, int v, double value) {
        adjMatrix[u][v] = value;
        adjMatrix[v][u] = value;
        edgeCount++;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(vertexCount).append(" vertices, ").append(edgeCount).append(" edges ").append("\n");
        for (int v = 0; v < vertexCount; v++) {
            stringBuilder.append(v).append(": ");
            for (double w : adjMatrix[v]) {
                stringBuilder.append(w).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public double[][] getAdjMatrix() {
        return adjMatrix;
    }

    public void setAdjMatrix(double[][] adjMatrix) {
        this.adjMatrix = adjMatrix;
    }

    public void setEdgeCount(int edgeCount) {
        this.edgeCount = edgeCount;
    }

    public int getVertexCount() {
        return vertexCount;
    }
}
