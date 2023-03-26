package com.spblue4422.divers.spots;

import com.spblue4422.divers.common.entities.EntityDate;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter()
@Builder()
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="TB_Spot")
public class Spot extends EntityDate {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="loation")
    private String location;

    @Column(name="explanation")
    private String explanation;

    @Column(name="rating")
    private float rating;

    public Spot(String name, String location, String explanation, float rating) {
        this.name = name;
        this.location = location;
        this.explanation = explanation;
        this.rating = rating;
        this.createdAt = new Date();
        this.deletedAt = null;
    }
}
