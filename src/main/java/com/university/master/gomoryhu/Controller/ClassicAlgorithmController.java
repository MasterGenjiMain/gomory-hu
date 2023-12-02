package com.university.master.gomoryhu.Controller;

import com.university.master.gomoryhu.Service.Entity.Edge;
import com.university.master.gomoryhu.Service.Entity.GomoryHuResult;
import com.university.master.gomoryhu.Service.GomoryHuAlgorithm;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/classic-algorithm")
@AllArgsConstructor
public class ClassicAlgorithmController {
    private static final Logger logger = LogManager.getLogger(ClassicAlgorithmController.class);
    private GomoryHuAlgorithm gomoryHuAlgorithm;

    @PostMapping ("/calculate")
    public GomoryHuResult calculateByClassicMethod(@RequestBody List<Edge> edges) {
        logger.info("Classic algorithm called");
        return gomoryHuAlgorithm.showResultByClassicMethod(edges);
    }

    @PostMapping("/adapt-input-calculation")
    public GomoryHuResult calculateByClassicMethodAndAdaptingInput(@RequestBody List<Edge> edges) {
        logger.info("Adapted input algorithm called");
        return gomoryHuAlgorithm.showResultWithAdaptingToOTN(edges);
    }

    private List<Edge> setupTestEdges() {
        // Later here will be implemented connection with UI.
        // UI planned to be some space where we can draw a graph that represent a network between datahubs with
        // entered network capacity
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 22));
        edges.add(new Edge(1, 2, 15.8));
        edges.add(new Edge(2, 3, 15.5));
        edges.add(new Edge(3, 0, 40.2));
        return edges;
    }
}
