package com.spblue4422.divers.records;

import com.spblue4422.divers.common.entities.EntityDate;
import com.spblue4422.divers.spots.Spot;
import com.spblue4422.divers.users.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="TB_Record")
public class Record extends EntityDate {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name="record_user")
    private User user;

    @ManyToOne()
    @JoinColumn(name="record_spot")
    private Spot spot;

    @Column(name="logNo")
    @NotNull()
    private int logNo;

    //후에 친구 기능이 추가된다면? 친구목록에서 buddy 찾기도 가능
    @Column(name="buddy")
    @NotNull()
    private String buddy;

    @Column(name="diveAt")
    @NotNull()
    private Date diveAt;

    @Column(name="diveTime")
    @NotNull()
    private int diveTime;

    @Column(name = "maxDepth")
    @NotNull()
    private int maxDepth;

    @Column(name="avgDepth")
    @NotNull()
    private int avgDepth;

    //날씨 - enum type으로 가면 될듯?
    //@OneToOne
    //@Column(name="weather")

    //수면온도
    @Column(name="sTemperature")
    private float sTemperature;

    //수온
    @Column(name="wTemperature")
    private float wTemperature;

    @Column(name="airDiveIn")
    private int airDiveIn;

    @Column(name="usedAir")
    private int usedAir;

    @Column(name = "visibility")
    private float visibility;

    //외에도 wave, tide, surge 등이 잇음
    //enum type
    //@Column(name="suit")

    @Column(name = "rating")
    @NotNull()
    private float rating;

    @Column(name="memo")
    @NotNull()
    private String memo;

    @Column(name="opened")
    private Boolean opened;

    @OneToMany(mappedBy = "RecordPhoto", cascade = CascadeType.ALL)
    private List<RecordPhoto> recordPhotoList;


//    public Record(Date at, int time, int maxD, int avgD, float sTemp, float wTemp, int airDiveIn, int usedAir, float vision, int rating, String memo) {
//        super();
//        this.diveAt = at;
//        this.diveTime = time;
//        this.maxDepth = maxD;
//        this.avgDepth = avgD;
//        this.sTemperature = sTemp;
//        this.wTemperature = wTemp;
//        this.airDiveIn = airDiveIn;
//        this.usedAir = usedAir;
//        this.visibility = vision;
//        this.rating = rating;
//        this.memo = memo;
//        this.isOpened = false;
//    }
}
