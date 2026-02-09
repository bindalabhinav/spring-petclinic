package org.springframework.samples.petclinic.featureflag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeatureFlagRepository extends JpaRepository<FeatureFlag, Long> {
	Optional<FeatureFlag> findByName(String name);
}
