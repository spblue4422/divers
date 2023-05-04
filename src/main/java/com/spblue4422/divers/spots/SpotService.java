package com.spblue4422.divers.spots;

import com.spblue4422.divers.nations.Nation;
import com.spblue4422.divers.common.errors.BadRequestException;
import com.spblue4422.divers.dto.spots.SaveSpotRequestDto;
import com.spblue4422.divers.dto.spots.SpotResponseDto;
import com.spblue4422.divers.nations.NationRepository;
import com.spblue4422.divers.users.User;
import com.spblue4422.divers.users.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpotService {
	private final SpotRepository spotRepository;
	private final NationRepository nationRepository;
	private final UserRepository userRepository;

	public SpotService(SpotRepository spotRepository, NationRepository nationRepository, UserRepository userRepository) {
		this.spotRepository = spotRepository;
		this.nationRepository = nationRepository;
		this.userRepository =userRepository;
	}

	public Spot getSpotInfo(Long spotId) {
		return spotRepository.findBySpotIdAndDeletedAtIsNull(spotId	).orElseThrow(()-> new BadRequestException(400, "잘못된 ID입니다."));
	}

	public List<Spot> getSpotInfoList() {
		return spotRepository.findAllByDeletedAtIsNull().orElseThrow(()-> new BadRequestException(400, "너는 null이냐 빈 배열이냐"));
	}


	public SpotResponseDto insertSpot(SaveSpotRequestDto req, String userId) {
		Spot spotData = spotRepository.findByNameAndLocationAndDeletedAtIsNull(req.getSpotName(), req.getSpotLocation()).orElse(null);

		if(spotData != null) {
			throw new BadRequestException(400, "이미 존재하는 스팟입니다.");
		}

		User userData = userRepository.findUserByLoginIdAndDeletedAtIsNull(userId).orElseThrow(()-> new BadRequestException(400, "user없음"));
		Nation nationData = nationRepository.findById(req.getNationId()).orElseThrow(() -> new BadRequestException(400, "국가 없음"));

		Spot newSpot = spotRepository.save(req.toInsertEntity(userData, nationData));

		return SpotResponseDto.builder()
				.spotId(newSpot.getSpotId())
				.name(newSpot.getName())
				.location(newSpot.getLocation())
				.nation(newSpot.getNation().getName())
				.explanation(newSpot.getExplanation())
				.build();
	}

	/*
	public Spot updateSpot() {

	}

	public int deleteSpot() {

	}
	*/
}
