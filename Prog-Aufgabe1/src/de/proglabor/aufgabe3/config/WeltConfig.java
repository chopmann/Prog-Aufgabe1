package de.proglabor.aufgabe3.config;

public enum WeltConfig {
	DAYS("days"), WIDHT("Width"), HEIGHT("Height"), JUNGLE_WIDTH("Jungle Width"), JUNGLE_HEIGHT(
			"Jungle Height"), PLANTENERGY("Plant Energy"), INITIALTENERGY(
			"Initial Energy"), REPRODUCTIONENERGY("Reproduction Energy");
	private String displayname;

	private WeltConfig(String displayname) {
		this.displayname = displayname;
	}

	@Override
	public String toString() {
		return displayname;
	}
}
