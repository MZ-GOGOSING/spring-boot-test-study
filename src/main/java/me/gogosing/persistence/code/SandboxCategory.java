package me.gogosing.persistence.code;

public enum SandboxCategory {

	NORMAL("일반"),
	ADULT("성인용");

	private final String description;

	SandboxCategory(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
}
