package com.spblue4422.divers.common.enums;

import lombok.*;

@Getter()
public enum Degree {
    HIGH("HIGH"),
    MIDDLE("MIDDLE"),
    LOW("LOW"),
    NONE("NONE");

    private String degreeCode;

    Degree(String dCode) {
        this.degreeCode = dCode;
    }
}
