package com.spblue4422.divers.dto.records;

import lombok.*;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordListResponseDto {
	private Long recordId;
	private Integer logNo;
	private Date diveAt;
	private Boolean opened;
	private Date createdAt;

	private Long userId;
	private String loginId;
	private String nickName;

	private Long spotId;
	private String spotName;
	private String location;
}
