package com.spblue4422.divers.common.errors;

import lombok.*;

@Getter
public enum ErrorType {
    INTERNAL_SERVER_ERROR(500, "서버 에러"),
    UNAUTHORIZED(401, "인증 실패"),
    NOT_FOUND(404, "잘못된 접근"),
    FORBIDDEN(403, "접근 불가");

    private final int status;
    private final String description;

    ErrorType(int status, String description) {
        this.status = status;
        this.description = description;
    }
}
