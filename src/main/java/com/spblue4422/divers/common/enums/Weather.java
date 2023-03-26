package com.spblue4422.divers.common.enums;

import lombok.*;

@Getter()
public enum Weather {
    SUNNY("SUNNY"),
    CLOUDY("CLOUDY"),
    RAINY("RAINY"),
    SNOWY("SNOWY"),
    NIGHT("NIGHT"),
    WINDY("WINDY"),
    MISTY("MISTY"),
    LIGHTNING("LIGHTNING");

    private String weatherCode;

    Weather(String wCode) {
        this.weatherCode = wCode;
    }
}
