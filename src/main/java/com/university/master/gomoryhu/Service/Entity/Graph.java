package com.university.master.gomoryhu.Service.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import static com.university.master.gomoryhu.Service.Enum.OtuStandard.*;

@Component
@NoArgsConstructor
public class Graph {

    @Getter
    private int vertexCount;
    private int edgeCount;
    @Getter
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

    public void adaptToOTN() {
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix[i].length; j++) {
                if (adjMatrix[i][j] > 0) {
                    if (adjMatrix[i][j] <= OTU1.bandwidth) {
                        adjMatrix[i][j] = OTU1.bandwidth;
                    } else if (adjMatrix[i][j] <= OTU2.bandwidth) {
                        adjMatrix[i][j] = OTU2.bandwidth;
                    } else if (adjMatrix[i][j] <= OTU3.bandwidth) {
                        adjMatrix[i][j] = OTU3.bandwidth;
                    } else if (adjMatrix[i][j] <= OTU4.bandwidth) {
                        adjMatrix[i][j] = OTU4.bandwidth;
                    }
                }
            }
        }
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

    public void setAdjMatrix(double[][] adjMatrix) {
        this.adjMatrix = adjMatrix;
    }

    public void setEdgeCount(int edgeCount) {
        this.edgeCount = edgeCount;
    }

}
