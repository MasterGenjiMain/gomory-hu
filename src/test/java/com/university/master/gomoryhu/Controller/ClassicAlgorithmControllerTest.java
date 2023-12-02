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
class ClassicAlgorithmControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void classicAlgorithmWithBigGraphTest() throws Exception {
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

        GomoryHuResult gomoryHuResult = new GomoryHuResult(BigDecimal.valueOf(126.5000), BigDecimal.valueOf(280.7970));
        prepareCallToClassicCalculateEndpoint(edges, gomoryHuResult);
    }

    @Test
    public void classicAlgorithmWithAdaptedInputAndBigGraphTest() throws Exception {
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

        GomoryHuResult gomoryHuResult = new GomoryHuResult(BigDecimal.valueOf(208.5450), BigDecimal.valueOf(412.7440));
        prepareCallToAdaptedInputCalculateEndpoint(edges, gomoryHuResult);
    }

    @Test
    public void classicAlgorithmWithSmallGraphTest() throws Exception {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 100));
        edges.add(new Edge(1, 2, 2));
        edges.add(new Edge(1, 3, 10));
        edges.add(new Edge(1, 4, 10));
        edges.add(new Edge(3, 4, 10));

        GomoryHuResult gomoryHuResult = new GomoryHuResult(BigDecimal.valueOf(111.0000), BigDecimal.valueOf(149.2590));
        prepareCallToClassicCalculateEndpoint(edges, gomoryHuResult);

    }

    @Test
    public void classicAlgorithmWithAdaptedInputAndSmallGraphTest() throws Exception {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 100));
        edges.add(new Edge(1, 2, 2));
        edges.add(new Edge(1, 3, 10));
        edges.add(new Edge(1, 4, 10));
        edges.add(new Edge(3, 4, 10));

        GomoryHuResult gomoryHuResult = new GomoryHuResult(BigDecimal.valueOf(123.8420), BigDecimal.valueOf(149.2590));
        prepareCallToAdaptedInputCalculateEndpoint(edges, gomoryHuResult);

    }

    private void prepareCallToClassicCalculateEndpoint(List<Edge> edges, GomoryHuResult gomoryHuResult) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/classic-algorithm/calculate")
                        .content(objectMapper.writeValueAsString(edges))
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(gomoryHuResult)));
    }

    private void prepareCallToAdaptedInputCalculateEndpoint(List<Edge> edges, GomoryHuResult gomoryHuResult) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/classic-algorithm/adapt-input-calculation")
                        .content(objectMapper.writeValueAsString(edges))
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(gomoryHuResult)));
    }
}