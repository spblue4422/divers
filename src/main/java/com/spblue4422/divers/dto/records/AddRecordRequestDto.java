package com.spblue4422.divers.dto.records;
import com.spblue4422.divers.records.Record;

import com.spblue4422.divers.records.RecordPhoto;
import com.spblue4422.divers.spots.Spot;
import com.spblue4422.divers.users.User;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddRecordRequestDto {
    private String userId;
    private Long spotId;
    private String buddy;
    private Date diveAt;
    private int diveTime;
    private int maxDepth;
    private int avgDepth;
    private int sTemperature;
    private int wTemperature;
    private int airDiveIn;
    private int usedAir;
    private float visibility;
    private float rating;
    private String memo;
    private Boolean opened;
    private List<String> urls;

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
                .recordPhotoList(new ArrayList<RecordPhoto>())
                .build();
    }
}
