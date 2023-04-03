package com.spblue4422.divers.spots;

import com.spblue4422.divers.common.entities.EntityDate;
import com.spblue4422.divers.nations.Nation;
import com.spblue4422.divers.users.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql="UPDATE tb_spot SET deletedAt = now() where spotId = ? and deletedAt is null")
@Entity(name="TB_Spot")
public class Spot extends EntityDate {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="spotId")
    private Long spotId;

    @ManyToOne()
    @JoinColumn(name="spot_user")
    private User user;

    @ManyToOne()
    @JoinColumn(name="spot_nation")
    private Nation nation;

    @Column(name="name")
    private String name;

    @Column(name="location")
    private String location;

    @Column(name="explanation")
    private String explanation;
}
