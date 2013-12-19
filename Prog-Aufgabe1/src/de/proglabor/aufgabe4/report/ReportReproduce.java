package de.proglabor.aufgabe4.report;

import java.util.Date;

public class ReportReproduce extends Report {

	public ReportReproduce(int days) {
		super(days);
	}

	@Override
	public void reportHeader() {
		Date currentDate = getCalendar().getTime();
		String timestamp = String.format("Simulationsdatum: %tc", currentDate);
		reportOutput.add("Simulation Tierreproduktion");
		reportOutput.add("");
		reportOutput.add(timestamp);
		reportOutput.add("Anzahl Durchläufe: " + this.days);
		reportOutput.add("");
		reportOutput.add("Tag	TierID	Energie	Reproductionen");

	}

	@Override
	public void reportContent(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reportFooter(Object obj) {
		// TODO Auto-generated method stub

	}

}
