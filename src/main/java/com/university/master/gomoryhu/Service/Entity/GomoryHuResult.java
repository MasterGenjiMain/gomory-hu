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

    BigDecimal defaultResult;
    BigDecimal adaptedToOtnResult;

    @Override
    public String toString() {
        return "GomoryHuResult{" +
                "defaultResult=" + defaultResult +
                ", adaptedToOtnResult=" + adaptedToOtnResult +
                '}';
    }
}
