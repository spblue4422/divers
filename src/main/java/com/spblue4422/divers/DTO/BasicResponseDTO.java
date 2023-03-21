package com.spblue4422.divers.common;

import lombok.*;

@Getter
@Builder
public class BasicResponseDTO {
    private Object data;

    private int statusCode;

    private String msg;

    public BasicResponseDTO(Object data, int statusCode, String msg) {
        this.data = data;
        this.statusCode = statusCode;
        this.msg = msg;
    }
}
