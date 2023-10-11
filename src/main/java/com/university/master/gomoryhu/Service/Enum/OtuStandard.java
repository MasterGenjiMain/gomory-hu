package com.university.master.gomoryhu.Service.Enum;

public enum OtuStandard {
    OTU1(2.666),
    OTU2(10.709),
    OTU3(43.018),
    OTU4(111.8);

    public final Double bandwidth;

    OtuStandard(Double bandwidth) {
        this.bandwidth = bandwidth;
    }
}
