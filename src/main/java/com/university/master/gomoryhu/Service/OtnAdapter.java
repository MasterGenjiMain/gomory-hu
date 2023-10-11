package com.university.master.gomoryhu.Service;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.university.master.gomoryhu.Service.Enum.OtuStandard.*;
import static com.university.master.gomoryhu.Service.Enum.OtuStandard.OTU4;

@Service
@NoArgsConstructor
public class OtnAdapter {

    public static void adaptMatrix(double[][] inputAdjMatrix) {
        double bufferedValue = 0;

        for (int i = 0; i < inputAdjMatrix.length; i++) {
            for (int j = 0; j < inputAdjMatrix[i].length; j++) {
                if (inputAdjMatrix[i][j] > 0) {
                    if (inputAdjMatrix[i][j] > OTU4.bandwidth) {
                        bufferedValue += OTU4.bandwidth;
                        inputAdjMatrix[i][j] = BigDecimal.valueOf(inputAdjMatrix[i][j])
                                .subtract(BigDecimal.valueOf(OTU4.bandwidth))
                                .doubleValue();
                        --j;
                        continue;
                    }

                    if (inputAdjMatrix[i][j] <= OTU1.bandwidth) {
                        inputAdjMatrix[i][j] = OTU1.bandwidth + bufferedValue;
                        bufferedValue = 0;
                    } else if (inputAdjMatrix[i][j] <= OTU2.bandwidth) {
                        inputAdjMatrix[i][j] = OTU2.bandwidth + bufferedValue;
                        bufferedValue = 0;
                    } else if (inputAdjMatrix[i][j] <= OTU3.bandwidth) {
                        inputAdjMatrix[i][j] = OTU3.bandwidth + bufferedValue;
                        bufferedValue = 0;
                    } else if (inputAdjMatrix[i][j] <= OTU4.bandwidth) {
                        inputAdjMatrix[i][j] = OTU4.bandwidth + bufferedValue;
                        bufferedValue = 0;
                    }
                }
            }
        }
    }

    public static double adapt(double inputValue) {
        double bufferedValue = 0;
        double adaptedValue = 0;

        if (inputValue > 0) {
            if (inputValue > OTU4.bandwidth) {
                while (inputValue > OTU4.bandwidth) {
                    bufferedValue += OTU4.bandwidth;
                    inputValue = BigDecimal.valueOf(inputValue)
                            .subtract(BigDecimal.valueOf(OTU4.bandwidth))
                            .doubleValue();
                }
            }

            if (inputValue <= OTU1.bandwidth) {
                adaptedValue = OTU1.bandwidth + bufferedValue;
            } else if (inputValue <= OTU2.bandwidth) {
                adaptedValue = OTU2.bandwidth + bufferedValue;
            } else if (inputValue <= OTU3.bandwidth) {
                adaptedValue = OTU3.bandwidth + bufferedValue;
            } else if (inputValue <= OTU4.bandwidth) {
                adaptedValue = OTU4.bandwidth + bufferedValue;
            }
        }

        return adaptedValue;
    }
}
