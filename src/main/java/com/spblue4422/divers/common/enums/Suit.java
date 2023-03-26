package com.spblue4422.divers.common.enums;

import lombok.*;

@Getter()
public enum Suit {
    SKIN("SKIN"),
    SEMIDRY("SEMIDRY"),
    DRY("DRY"),
    WET("WET");

    private String suitCode;
    Suit(String sCode) {
        this.suitCode = sCode;
    }
}
