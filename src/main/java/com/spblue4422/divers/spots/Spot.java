package com.spblue4422.divers.spots;

import com.spblue4422.divers.common.entities.EntityDate;
import com.spblue4422.divers.common.entities.Nation;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;

import java.util.Date;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql="UPDATE tb_spot SET deletedAt = now() where spotId = ?")
@Entity(name="TB_Spot")
public class Spot extends EntityDate {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="spotId")
    private Long spotId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="spot_nation")
    private Nation nation;

    @Column(name="name")
    private String name;

    @Column(name="location")
    private String location;

    @Column(name="explanation")
    private String explanation;

    public Spot(String name, String location, String explanation) {
        this.name = name;
        this.location = location;
        this.explanation = explanation;
        this.createdAt = new Date();
        this.deletedAt = null;
    }
}
