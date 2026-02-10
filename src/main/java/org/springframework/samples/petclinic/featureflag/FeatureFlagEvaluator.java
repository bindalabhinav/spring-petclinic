package org.springframework.samples.petclinic.featureflag;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FeatureFlagEvaluator {

	private final FeatureFlagRepository repository;

	public FeatureFlagEvaluator(FeatureFlagRepository repository) {
		this.repository = repository;
	}

	public boolean isEnabled(String flagName, String userId) {
		return repository.findByName(flagName).map(flag -> evaluate(flag, userId)).orElse(false);
	}

	private boolean evaluate(FeatureFlag flag, String userId) {

		// 1. Global switch
		if (!flag.isEnabled()) {
			return false;
		}

		// 2. Blacklist
		if (parse(flag.getBlacklistUsers()).contains(userId)) {
			return false;
		}

		// 3. Whitelist
		if (parse(flag.getWhitelistUsers()).contains(userId)) {
			return true;
		}

		// 4. Percentage rollout
		Integer rollout = flag.getRolloutPercentage();
		if (rollout == null || rollout >= 100) {
			return true;
		}
		if (rollout <= 0) {
			return false;
		}

		int hash = Math.abs(userId.hashCode() % 100);
		return hash < rollout;
	}

	private Set<String> parse(String value) {
		if (value == null || value.isBlank()) {
			return Collections.emptySet();
		}
		return Arrays.stream(value.split(",")).map(String::trim).collect(Collectors.toSet());
	}

}
