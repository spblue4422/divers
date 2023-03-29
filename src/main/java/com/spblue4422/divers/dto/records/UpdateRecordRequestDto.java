package com.spblue4422.divers.dto.records;

import com.spblue4422.divers.records.Record;
import com.spblue4422.divers.records.RecordPhoto;
import com.spblue4422.divers.spots.Spot;
import com.spblue4422.divers.users.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRecordRequestDto extends AddRecordRequestDto {
	private Long recordId;

	public Record toEntity(User userData, Spot spotData, int log, List<RecordPhoto> list) {
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
				.build();
	}
}
