package com.spblue4422.divers.dto.auth;

import com.spblue4422.divers.users.User;

import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.Date;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto extends LoginRequestDto {

    private String firstName;

    private String lastName;

    private String nickName;

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .nickName(nickName)
                .createdAt(new Date())
                .deletedAt(null)
                .build();
    }
}
