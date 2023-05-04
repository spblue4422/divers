package com.spblue4422.divers.dto.spots;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpotResponseDto {
	private Long spotId;
	private String name;
	private String location;
	private String explanation;

	//국가 정보
	private Long nationId;
	private String nationName;
	private String nationCode;
}
