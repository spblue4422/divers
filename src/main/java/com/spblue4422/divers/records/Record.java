package com.spblue4422.divers.records;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.spblue4422.divers.common.entities.EntityDate;
import com.spblue4422.divers.dto.records.RecordListResponseDto;
import com.spblue4422.divers.dto.records.RecordPhotoResponseDto;
import com.spblue4422.divers.dto.records.RecordResponseDto;
import com.spblue4422.divers.dto.records.SaveRecordRequestDto;
import com.spblue4422.divers.spots.Spot;
import com.spblue4422.divers.users.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;

import java.util.Date;
import java.util.List;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE tb_record SET deletedAt = now() where recordId = ? and deletedAt is null")
@Entity(name="TB_Record")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Record extends EntityDate {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recordId")
    private Long recordId;
    @ManyToOne()
    @JoinColumn(name="record_user")
    private User user;
    @ManyToOne()
    @JoinColumn(name="record_spot")
    private Spot spot;
    @Column(name="logNo")
    @NotNull()
    private Integer logNo;
    //후에 친구 기능이 추가된다면? 친구목록에서 buddy 찾기도 가능
    @Column(name="buddy")
    @NotNull()
    private String buddy;
    @Column(name="diveAt")
    @NotNull()
    private Date diveAt;
    @Column(name="diveTime")
    @NotNull()
    private Integer diveTime;
    @Column(name = "maxDepth")
    @NotNull()
    private Integer maxDepth;
    @Column(name="avgDepth")
    @NotNull()
    private Integer avgDepth;

    //날씨 - enum type으로 가면 될듯?
    //@OneToOne
    //@Column(name="weather")
    //수면온도
    @Column(name="sTemperature")
    private Float sTemperature;
    //수온
    @Column(name="wTemperature")
    private Float wTemperature;
    @Column(name="airDiveIn")
    private Integer airDiveIn;
    @Column(name="usedAir")
    private Integer usedAir;
    @Column(name = "visibility")
    private Float visibility;

    //외에도 wave, tide, surge 등이 잇음
    //enum type
    //@Column(name="suit")
    @Column(name = "rating")
    @NotNull()
    private Float rating;
    @Column(name="memo")
    @NotNull()
    private String memo;
    @Column(name="opened")
    private Boolean opened;
    @OneToMany(mappedBy = "record", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<RecordPhoto> recordPhotoList;

    public SaveRecordRequestDto toSaveRecordRequestDto() {
        return SaveRecordRequestDto.builder()
                .recordId(recordId)
                .spotId(spot.getSpotId())
                .buddy(buddy)
                .diveAt(diveAt)
                .diveTime(diveTime)
                .maxDepth(maxDepth)
                .avgDepth(avgDepth)
                .sTemperature(sTemperature)
                .wTemperature(wTemperature)
                .airDiveIn(airDiveIn)
                .usedAir(usedAir)
                .visibility(visibility)
                .rating(rating)
                .memo(memo)
                .opened(opened)
                .build();
    }

    public RecordResponseDto toRecordResponseDto(List<RecordPhotoResponseDto> list) {
        return RecordResponseDto.builder()
                .recordId(recordId)
                .logNo(logNo)
                .buddy(buddy)
                .diveAt(diveAt)
                .diveTime(diveTime)
                .maxDepth(maxDepth)
                .avgDepth(avgDepth)
                .sTemperature(sTemperature)
                .wTemperature(wTemperature)
                .airDiveIn(airDiveIn)
                .usedAir(usedAir)
                .visibility(visibility)
                .rating(rating)
                .memo(memo)
                .opened(opened)
                .userId(user.getUserId())
                .loginId(user.getLoginId())
                .nickName(user.getNickName())
                .spotId(spot.getSpotId())
                .spotName(spot.getName())
                .location(spot.getLocation())
                .recordPhotoList(list)
                .build();
    }

    public RecordListResponseDto toRecordListResponseDto() {
        return RecordListResponseDto.builder()
                .recordId(recordId)
                .logNo(logNo)
                .diveAt(diveAt)
                .opened(opened)
                .createdAt(createdAt)
                .userId(user.getUserId())
                .loginId(user.getLoginId())
                .nickName(user.getNickName())
                .spotId(spot.getSpotId())
                .spotName(spot.getName())
                .location(spot.getLocation())
                .build();
    }
}
