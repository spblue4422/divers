package com.spblue4422.divers.Dto.auth;

import lombok.Getter;

@Getter
public class LoginRequestDto {
    protected String userId;

    protected String password;

    public LoginRequestDto (String id, String pw) {
        this.userId = id;
        this.password = pw;
    }
}
