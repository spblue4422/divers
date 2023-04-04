package com.spblue4422.divers.dto.records;

import com.spblue4422.divers.records.RecordPhoto;
import lombok.*;
import org.springframework.data.util.Pair;

import java.util.Date;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordResponseDto {
	//레코드 정보
	private Long recordId;
	private Integer logNo;
	private String buddy;
	private Date diveAt;
	private Integer diveTime;
	private Integer maxDepth;
	private Integer avgDepth;
	private Float sTemperature;
	private Float wTemperature;
	private Integer airDiveIn;
	private Integer usedAir;
	private Float visibility;
	private Float rating;
	private String memo;
	private Boolean opened;
	private List<RecordPhotoResponseDto> recordPhotoList;

	//유저 정보
	private Long userId;
	private String loginId;
	private String nickName;

	//스팟 정보
	private Long spotId;
	private String spotName;
	private String location;
}
