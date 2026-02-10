package org.springframework.samples.petclinic.featureflag.aop;

import org.springframework.samples.petclinic.featureflag.FeatureFlagEvaluator;
import org.springframework.samples.petclinic.featureflag.annotation.FeatureToggle;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Aspect
@Component
public class FeatureToggleAspect {

	private final FeatureFlagEvaluator evaluator;

	public FeatureToggleAspect(FeatureFlagEvaluator evaluator) {
		this.evaluator = evaluator;
	}

	@Before("@annotation(featureToggle)")
	public void checkFeature(FeatureToggle featureToggle) {

		String flagName = featureToggle.value();

		String userId = resolveUser();

		boolean enabled = evaluator.isEnabled(flagName, userId);

		if (!enabled) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Feature '" + flagName + "' is disabled");
		}
	}

	private String resolveUser() {

		return "anonymous-user";
	}

}
