package com.university.master.gomoryhu.Controller;

import com.university.master.gomoryhu.Service.Entity.Edge;
import com.university.master.gomoryhu.Service.GomoryHuAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class WebController {

    private GomoryHuAlgorithm gomoryHuAlgorithm;

    @GetMapping("/")
    public String mainPage() {
        // Later here will be implemented connection with UI.
        // UI planned to be some space where we can draw a graph that represent a network between datahubs with
        // entered network capacity
        List<Edge> edges = new ArrayList<>();   //Temporary placeholder
        edges.add(new Edge(0,1,22));
        edges.add(new Edge(1,2,15.8));
        edges.add(new Edge(2,3,15.5));
        edges.add(new Edge(3,0,40.2));
        gomoryHuAlgorithm.showGraph(edges);
        return "Looks fine";
    }
}
