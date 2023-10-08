package com.university.master.gomoryhu.Service;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.university.master.gomoryhu.Service.Enum.OtuStandard.*;
import static com.university.master.gomoryhu.Service.Enum.OtuStandard.OTU4;

@Service
@NoArgsConstructor
public class OtnAdapter {

    public static void adapt(double[][] inputAdjMatrix) {
        double buffedVale = 0;

        for (int i = 0; i < inputAdjMatrix.length; i++) {
            for (int j = 0; j < inputAdjMatrix[i].length; j++) {
                if (inputAdjMatrix[i][j] > 0) {
                    if (inputAdjMatrix[i][j] > OTU4.bandwidth) {
                        buffedVale += OTU4.bandwidth;
                        inputAdjMatrix[i][j] = BigDecimal.valueOf(inputAdjMatrix[i][j])
                                .subtract(BigDecimal.valueOf(OTU4.bandwidth))
                                .doubleValue();
                        --j;
                        continue;
                    }

                    if (inputAdjMatrix[i][j] <= OTU1.bandwidth) {
                        inputAdjMatrix[i][j] = OTU1.bandwidth + buffedVale;
                        buffedVale = 0;
                    } else if (inputAdjMatrix[i][j] <= OTU2.bandwidth) {
                        inputAdjMatrix[i][j] = OTU2.bandwidth + buffedVale;
                        buffedVale = 0;
                    } else if (inputAdjMatrix[i][j] <= OTU3.bandwidth) {
                        inputAdjMatrix[i][j] = OTU3.bandwidth + buffedVale;
                        buffedVale = 0;
                    } else if (inputAdjMatrix[i][j] <= OTU4.bandwidth) {
                        inputAdjMatrix[i][j] = OTU4.bandwidth + buffedVale;
                        buffedVale = 0;
                    }
                }
            }
        }
    }
}
