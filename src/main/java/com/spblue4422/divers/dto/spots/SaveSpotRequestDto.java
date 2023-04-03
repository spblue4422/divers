package com.spblue4422.divers.dto.spots;

import com.spblue4422.divers.nations.Nation;
import com.spblue4422.divers.spots.Spot;
import com.spblue4422.divers.users.User;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveSpotRequestDto {
	private Long nationId;
	private String spotName;
	private String spotLocation;
	private String spotExplanation;

	public Spot toInsertEntity(User userData, Nation nationData) {
		return Spot.builder()
				.user(userData)
				.nation(nationData)
				.name(spotName)
				.location(spotLocation)
				.explanation(spotExplanation)
				.build();
	}
}
