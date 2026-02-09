package org.springframework.samples.petclinic.featureflag;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeatureFlagService {

	private final FeatureFlagRepository repository;

	public FeatureFlagService(FeatureFlagRepository repository) {
		this.repository = repository;
	}

	public FeatureFlag createOrUpdate(FeatureFlag flag) {
		return repository.save(flag);
	}

	public FeatureFlag getByName(String name) {
		return repository.findByName(name)
			.orElseThrow(() -> new RuntimeException("Feature flag not found: " + name));
	}

	public List<FeatureFlag> getAll() {
		return repository.findAll();
	}

	public void delete(String name) {
		FeatureFlag flag = getByName(name);
		repository.delete(flag);
	}
}
