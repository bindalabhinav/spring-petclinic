package org.springframework.samples.petclinic.featureflag;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flags")
public class FeatureFlagController {

	private final FeatureFlagService service;

	public FeatureFlagController(FeatureFlagService service) {
		this.service = service;
	}

	@PostMapping
	public FeatureFlag create(@RequestBody FeatureFlag flag) {
		return service.createOrUpdate(flag);
	}

	@GetMapping
	public List<FeatureFlag> getAll() {
		return service.getAll();
	}

	@GetMapping("/{name}")
	public FeatureFlag get(@PathVariable String name) {
		return service.getByName(name);
	}

	@PutMapping("/{name}")
	public FeatureFlag update(@PathVariable String name,
							  @RequestBody FeatureFlag flag) {
		flag.setName(name);
		return service.createOrUpdate(flag);
	}

	@DeleteMapping("/{name}")
	public void delete(@PathVariable String name) {
		service.delete(name);
	}
}
