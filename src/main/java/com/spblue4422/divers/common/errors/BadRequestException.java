package com.spblue4422.divers.common.errors;

import lombok.*;

@Getter
public class BadRequestException extends RuntimeException{
    private final int errorCode;

    public BadRequestException(int eCode, String message) {
        super(message);
        this.errorCode = eCode;
    }
}
