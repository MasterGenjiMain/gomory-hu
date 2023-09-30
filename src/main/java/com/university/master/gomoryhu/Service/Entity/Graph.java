package com.university.master.gomoryhu.Service.Entity;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class Graph {

    private int vertex; //vertexes
    private int edge; //edges
    private double[][] adjMatrix;

    public Graph(int nodes) {
        this.vertex = nodes;
        this.edge = 0;
        this.adjMatrix = new double[nodes][nodes];
    }

    public void addEdge(int u, int v, double value) {
        adjMatrix[u][v] = value;
        adjMatrix[v][u] = value;
        edge++;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(vertex).append(" vertices, ").append(edge).append(" edges ").append("\n");
        for (int v = 0; v < vertex; v++) {
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

    public void setEdge(int edge) {
        this.edge = edge;
    }
}
