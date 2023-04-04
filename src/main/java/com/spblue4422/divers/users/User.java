package com.spblue4422.divers.users;

import com.spblue4422.divers.common.entities.EntityDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;

@Getter
@SuperBuilder
//@Where(clause="deletedAt is null")
@SQLDelete(sql = "UPDATE tb_user SET deletedAt = now() where userId = ? and deletedAt is null")
@Entity(name="TB_User")
@AllArgsConstructor
@NoArgsConstructor
public class User extends EntityDate {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;

    @Column(name = "loginId")
    @NotNull()
    private String loginId;

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
}
