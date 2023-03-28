package com.spblue4422.divers.spots;

import com.spblue4422.divers.common.entities.EntityDate;
import com.spblue4422.divers.common.entities.Nation;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="TB_Spot")
public class Spot extends EntityDate {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne()
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
