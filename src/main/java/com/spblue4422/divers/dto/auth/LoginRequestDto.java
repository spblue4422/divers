package com.spblue4422.divers.dto.auth;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {
    protected String userId;
    protected String password;
}
