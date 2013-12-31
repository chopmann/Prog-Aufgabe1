package de.proglabor.aufgabe4.report;

import java.util.Date;
import java.util.Hashtable;

public class ReportTiere extends Report {

	public ReportTiere(int days) {
		super(days);
	}

	@Override
	public void reportHeader() {
		Date currentDate = getCalendar().getTime();
		String timestamp = String.format("Simulationsdatum: %tc", currentDate);
		reportOutput.add("Simulation Tiere");
		reportOutput.add("");
		reportOutput.add(timestamp);
		reportOutput.add("Anzahl Durchläufe: " + this.days);
		reportOutput.add("");
		reportOutput.add("Tag	World	Dschungel");
	}

	@Override
	public void reportContent(Object obj) {
		@SuppressWarnings("unchecked")
		Hashtable<String, Integer> reportTiereContent = (Hashtable<String, Integer> ) obj;
		int days = reportTiereContent.get("Tag");
		int welt = reportTiereContent.get("World");
		int jungle = reportTiereContent.get("Dschungel");
		String tmp = days + "	" + welt + "	" + jungle;
		reportOutput.add(tmp);

	}

	@Override
	public void reportFooter(Object obj) {
		reportOutput.add("");
		reportOutput.add("");
		Integer maxTiere = (Integer) obj;
		reportOutput.add("Maximale Anzahl von Tieren: " + maxTiere);

	}

}
