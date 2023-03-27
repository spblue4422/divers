package com.spblue4422.divers.users;

import com.spblue4422.divers.common.entities.EntityDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@SuperBuilder
@Entity(name="TB_User")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@AllArgsConstructor
@NoArgsConstructor
public class User extends EntityDate {
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

    @Column(name="refreshToken")
    private String refreshToken;

    public User(String userId, String password, String fName, String lName, String nickName) {
        super();
        this.userId = userId;
        this.password = password;
        this.firstName = fName;
        this.lastName = lName;
        this.nickName = nickName;
        this.refreshToken = null;
    }
}
