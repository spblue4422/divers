package com.spblue4422.divers.spots;

import org.springframework.stereotype.Service;

@Service
public class SpotService {
	private final SpotRepository spotRepository;

	public SpotService(SpotRepository spotRepository) {
		this.spotRepository = spotRepository;
	}

	public int getAllSpots() {
		return 0;
	}
}
