package com.spblue4422.divers.dto.auth;

import com.spblue4422.divers.users.User;

import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.Date;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SaveAuthRequestDto extends LoginRequestDto {

    private String firstName;

    private String lastName;

    private String nickName;

    public User toInsertEntity(String encodedPassword) {
        return User.builder()
                .loginId(loginId)
                .password(encodedPassword)
                .firstName(firstName)
                .lastName(lastName)
                .nickName(nickName)
                .createdAt(new Date())
                .deletedAt(null)
                .build();
    }

    public User toUpdateEntity(String encodedPassword, Date createdDate) {
        return User.builder()
                .loginId(loginId)
                .password(encodedPassword)
                .firstName(firstName)
                .lastName(lastName)
                .nickName(nickName)
                .createdAt(createdDate)
                .deletedAt(null)
                .build();
    }
}
