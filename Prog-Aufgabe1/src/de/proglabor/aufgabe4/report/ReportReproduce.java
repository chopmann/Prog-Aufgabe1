package de.proglabor.aufgabe4.report;

import java.util.Date;

public class ReportReproduce extends Report {

	@Override
	public void reportHeader() {
		Date currentDate = getCalendar().getTime();
		//TODO Formating
//		this.header = new Header("Tierreproduktion", timestamp, numberOfDays, spalten)

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
