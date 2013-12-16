package de.proglabor.aufgabe3.config;

public enum StatusCode {
	OK("Ready to Rock!"), SIMULATING("SIM running!"), DONE("Done");
	private String displayname;

	private StatusCode(String displayname) {
		this.displayname = displayname;
	}

	@Override
	public String toString() {
		return displayname;
	}

}
