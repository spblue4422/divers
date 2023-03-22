package com.spblue4422.divers.records;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter()
@Builder()
@AllArgsConstructor()
@NoArgsConstructor()
@Entity(name="TB_Record")
public class Record {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //@ManyToOne()

    @Column(name="place")
    @NotNull()
    private String placeName;

    @Column(name="address")
    private String address;

    @Column(name = "latitude")
    private int latitude;

    @Column(name = "longitude")
    private int longitude;

    @Column(name="diveAt")
    @NotNull()
    private Date diveAt;

    @Column(name="diveTime")
    @NotNull()
    private int diveTime;

    @Column(name = "depth")
    @NotNull()
    private int depth;

    @Column(name="temperature")
    private int temperature;

    @Column(name="waterTemperature")
    private int waterTemperature;

    @Column(name = "sight")
    private int sight;

    @Column(name = "rating")
    @NotNull()
    private float rating;

    /*
    @Column(name= "isPlaceRated")
    @NotNull()
    int isPlaceRated;
    */

    @Column(name = "createdAt")
    @NotNull()
    private Date createdAt;

    @Column(name = "deletedAt")
    @Nullable()
    private Date deletedAt;

    public Record(String place, String adrs, int ltt, int lnt, Date at, int time, int depth, int temp, int wTemp, int sight, int rating) {
        this.placeName = place;
        this.address = adrs;
        this.latitude = ltt;
        this.longitude = lnt;
        this.diveAt = at;
        this.diveTime = time;
        this.depth = depth;
        this.temperature = temp;
        this.waterTemperature = wTemp;
        this.sight = sight;
        this.rating = rating;
        this.createdAt = new Date();
        this.deletedAt = null;
    }
}
