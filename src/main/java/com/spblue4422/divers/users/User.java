package com.spblue4422.divers.users;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Builder
@Entity(name="TB_User")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "userId")
    @NotNull()
    private String userId;

    @Column(name = "password")
    @NotNull()
    private String password;

    @Column(name = "firstName")
    @NotNull()
    private String firstName;

    @Column(name = "lastName")
    @NotNull()
    private String lastName;

    @Column(name = "nickName")
    @NotNull()
    private String nickName;

    /*
    @Column(name = "nation")
    private String nation;

    @Column(name = "birth")
    @Nullable()
    private Date birth;

    @Column(name = "phoneNum")
    @Nullable()
    private String phoneNum;
    */

    @Column(name = "createdAt")
    @NotNull()
    private Date createdAt;

    @Column(name = "deletedAt")
    @Nullable()
    private Date deletedAt;

    public User(String userId, String password, String fName, String lName, String nickName) {
        this.userId = userId;
        this.password = password;
        this.firstName = fName;
        this.lastName = lName;
        this.nickName = nickName;
        this.createdAt = new Date();
        this.deletedAt = null;
    }
}
