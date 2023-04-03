package com.spblue4422.divers.nations;

import jakarta.persistence.*;
import lombok.*;


@Getter()
@Builder()
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_Nation")
public class Nation {
    @Id()
    @GeneratedValue()
    @Column(name = "nationId")
    private Long nationId;

    @Column(name="name")
    private String name;

    @Column(name="nationCode")
    private String nationCode;
}
