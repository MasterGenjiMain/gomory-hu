package com.university.master.gomoryhu.Controller;

import com.university.master.gomoryhu.Service.GomoryHuAlgorithm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    private GomoryHuAlgorithm gomoryHuAlgorithm;

    @GetMapping
     public String mainPage() {
        return "Looks fine";
    }
}
