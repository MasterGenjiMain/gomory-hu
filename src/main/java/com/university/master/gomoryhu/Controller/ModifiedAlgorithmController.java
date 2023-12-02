package com.university.master.gomoryhu.Controller;

import com.university.master.gomoryhu.Service.Entity.Edge;
import com.university.master.gomoryhu.Service.Entity.GomoryHuResult;
import com.university.master.gomoryhu.Service.ModifiedGomoryHuAlgorithm;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/modified-algorithm")
@AllArgsConstructor
public class ModifiedAlgorithmController {
    private static final Logger logger = LogManager.getLogger(ClassicAlgorithmController.class);
    private ModifiedGomoryHuAlgorithm modifiedGomoryHuAlgorithm;

    @PostMapping("/calculate-by-first-method")
    public GomoryHuResult calculateByModifiedMethod(@RequestBody List<Edge> edges) {
        logger.info("First modified algorithm called");
        return modifiedGomoryHuAlgorithm.showResultByModifiedMethod(edges);
    }

    @PostMapping("/calculate-by-second-method")
    public GomoryHuResult calculateBySecondModifiedMethod(@RequestBody List<Edge> edges) {
        logger.info("Second modified algorithm called");
        return modifiedGomoryHuAlgorithm.showResultByModifiedMethod2(edges);
    }
}
