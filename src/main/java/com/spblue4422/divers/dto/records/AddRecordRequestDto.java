package com.spblue4422.divers.dto.records;
import com.spblue4422.divers.records.Record;

import lombok.*;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddRecordRequestDto {
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

    public Record toEntity() {
        return Record.builder()
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
                .build();
    }

}
