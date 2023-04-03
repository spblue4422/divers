package com.spblue4422.divers.nations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NationRepository extends JpaRepository<Nation, Long> {
//	name Like option
//	Optional<Nation> findByName
}
