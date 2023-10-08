package com.university.master.gomoryhu.Service.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GomoryHuResult {

    BigDecimal classicResult;
    BigDecimal adaptedToOtnResult;

    @Override
    public String toString() {
        return "GomoryHuResult{" +
                "classicResult=" + classicResult +
                ", adaptedToOtnResult=" + adaptedToOtnResult +
                '}';
    }
}
