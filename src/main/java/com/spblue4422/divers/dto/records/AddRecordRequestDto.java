package com.spblue4422.divers.dto.records;

import com.spblue4422.divers.records.Record;
import com.spblue4422.divers.records.RecordPhoto;
import com.spblue4422.divers.spots.Spot;
import com.spblue4422.divers.users.User;

import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class AddRecordRequestDto {
    protected String loginId;
    protected Long spotId;
    protected String buddy;
    protected Date diveAt;
    protected Integer diveTime;
    protected Integer maxDepth;
    protected Integer avgDepth;
    protected Float sTemperature;
    protected Float wTemperature;
    protected Integer airDiveIn;
    protected Integer usedAir;
    protected Float visibility;
    protected Float rating;
    protected String memo;
    protected Boolean opened;

    public Record toEntity(User userData, Spot spotData, int log) {
        return Record.builder()
                .user(userData)
                .spot(spotData)
                .logNo(log)
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
                .recordPhotoList(new ArrayList<>())
                .build();
    }

    public Record toUpdateEntity(Long recordId, User userData, Spot spotData, int log, List<RecordPhoto> list) {
        return Record.builder()
                .recordId(recordId)
                .user(userData)
                .spot(spotData)
                .logNo(log)
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
                .recordPhotoList(list)
                .createdAt(new Date())
                .deletedAt(null)
                .build();
    }
}
