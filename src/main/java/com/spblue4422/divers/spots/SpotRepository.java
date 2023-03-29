package com.spblue4422.divers.spots;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpotRepository extends JpaRepository<Spot, Long> {
	Optional<Spot> findBySpotIdAndDeletedAtIsNull(Long spotId);

	Optional<List<Spot>> findAllByDeletedAtIsNull();
}
