package com.spblue4422.divers.Dto.auth;

import com.spblue4422.divers.users.User;
import lombok.Getter;

import java.util.Date;

@Getter
public class RegisterRequestDto extends LoginRequestDto {

    protected String firstName;

    protected String lastName;

    protected String nickName;

    public RegisterRequestDto(String id, String pw, String fName, String lName, String nName) {
        super(id, pw);
        this.firstName = fName;
        this.lastName = lName;
        this.nickName = nName;
    }

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .nickName(nickName)
                .createdAt(new Date())
                .deletedAt(null).build();
    }
}
