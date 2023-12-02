package com.university.master.gomoryhu.Service.Entity;

import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
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
