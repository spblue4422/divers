package com.spblue4422.divers.dto.spots;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpotDataResponseDto {
	private Long spotId;
	private String name;
	private String location;
	private String nation;
	private String explanation;
}
