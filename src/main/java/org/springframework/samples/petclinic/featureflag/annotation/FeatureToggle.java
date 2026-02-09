package org.springframework.samples.petclinic.featureflag.annotation;

import java.lang.annotation.*;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FeatureToggle {
	String value(); // Feature flag name
}
