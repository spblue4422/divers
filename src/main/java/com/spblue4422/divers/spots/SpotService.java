package com.spblue4422.divers.spots;

import com.spblue4422.divers.common.errors.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpotService {
	private final SpotRepository spotRepository;

	public SpotService(SpotRepository spotRepository) {
		this.spotRepository = spotRepository;
	}

	public Spot getSpotInfo(Long id) {
		return spotRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(()-> new BadRequestException(400, "잘못된 ID입니다."));
	}

	public List<Spot> getSpotInfoList() {
		return spotRepository.findAllByDeletedAtIsNull().orElseThrow(()-> new BadRequestException(400, "너는 null이냐 빈 배열이냐"));
	}

	/*
	public Spot insertSpot() {

	}

	public Spot updateUser() {

	}

	public int deleteUser() {

	}
	*/
}
