package com.university.master.gomoryhu.Service.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@Component
@NoArgsConstructor
@AllArgsConstructor
public class Edge {

    private int from;
    private int to;
    private double value;
}
