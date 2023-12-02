package com.university.master.gomoryhu.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.university.master.gomoryhu.Service.Entity.Edge;
import com.university.master.gomoryhu.Service.Entity.GomoryHuResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ModifiedAlgorithmControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void modifiedAlgorithm1WithBigGraphTest() throws Exception {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 22));
        edges.add(new Edge(0, 3, 30));
        edges.add(new Edge(0, 4, 77));
        edges.add(new Edge(1, 2, 3));
        edges.add(new Edge(2, 3, 5));
        edges.add(new Edge(4, 5, 15));
        edges.add(new Edge(4, 7, 20));
        edges.add(new Edge(5, 7, 13));
        edges.add(new Edge(6, 7, 7));

        GomoryHuResult gomoryHuResult = new GomoryHuResult(BigDecimal.valueOf(-1), BigDecimal.valueOf(199.7780));
        prepareCallToModified1CalculateEndpoint(edges, gomoryHuResult);
    }

    @Test
    public void modifiedAlgorithm2WithBigGraphTest() throws Exception {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 22));
        edges.add(new Edge(0, 3, 30));
        edges.add(new Edge(0, 4, 77));
        edges.add(new Edge(1, 2, 3));
        edges.add(new Edge(2, 3, 5));
        edges.add(new Edge(4, 5, 15));
        edges.add(new Edge(4, 7, 20));
        edges.add(new Edge(5, 7, 13));
        edges.add(new Edge(6, 7, 7));

        GomoryHuResult gomoryHuResult = new GomoryHuResult(BigDecimal.valueOf(-1), BigDecimal.valueOf(291.5060));
        prepareCallToModified2CalculateEndpoint(edges, gomoryHuResult);
    }

    @Test
    public void modifiedAlgorithm1WithSmallGraphTest() throws Exception {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 100));
        edges.add(new Edge(1, 2, 2));
        edges.add(new Edge(1, 3, 10));
        edges.add(new Edge(1, 4, 10));
        edges.add(new Edge(3, 4, 10));

        GomoryHuResult gomoryHuResult = new GomoryHuResult(BigDecimal.valueOf(-1), BigDecimal.valueOf(135.7940));
        prepareCallToModified1CalculateEndpoint(edges, gomoryHuResult);
    }

    @Test
    public void modifiedAlgorithm2WithSmallGraphTest() throws Exception {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 100));
        edges.add(new Edge(1, 2, 2));
        edges.add(new Edge(1, 3, 10));
        edges.add(new Edge(1, 4, 10));
        edges.add(new Edge(3, 4, 10));

        GomoryHuResult gomoryHuResult = new GomoryHuResult(BigDecimal.valueOf(-1), BigDecimal.valueOf(151.9250));
        prepareCallToModified2CalculateEndpoint(edges, gomoryHuResult);
    }

    private void prepareCallToModified1CalculateEndpoint(List<Edge> edges, GomoryHuResult gomoryHuResult) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/modified-algorithm/calculate-by-first-method")
                        .content(objectMapper.writeValueAsString(edges))
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(gomoryHuResult)));
    }

    private void prepareCallToModified2CalculateEndpoint(List<Edge> edges, GomoryHuResult gomoryHuResult) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/modified-algorithm/calculate-by-second-method")
                        .content(objectMapper.writeValueAsString(edges))
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(gomoryHuResult)));
    }
}