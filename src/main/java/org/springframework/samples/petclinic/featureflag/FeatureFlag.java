package org.springframework.samples.petclinic.featureflag;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "feature_flags")
public class FeatureFlag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String name;

	private boolean enabled;

	@Column(length = 1000)
	private String whitelistUsers;

	@Column(length = 1000)
	private String blacklistUsers;

	private Integer rolloutPercentage;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	@PrePersist
	void onCreate() {
		createdAt = LocalDateTime.now();
		updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	void onUpdate() {
		updatedAt = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getWhitelistUsers() {
		return whitelistUsers;
	}

	public void setWhitelistUsers(String whitelistUsers) {
		this.whitelistUsers = whitelistUsers;
	}

	public String getBlacklistUsers() {
		return blacklistUsers;
	}

	public void setBlacklistUsers(String blacklistUsers) {
		this.blacklistUsers = blacklistUsers;
	}

	public Integer getRolloutPercentage() {
		return rolloutPercentage;
	}

	public void setRolloutPercentage(Integer rolloutPercentage) {
		this.rolloutPercentage = rolloutPercentage;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	// Getters & setters
}
