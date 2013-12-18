package de.proglabor.aufgabe4.report;

import java.util.Date;

public class Header {
	String reportName;
	Date timestamp;
	int numberOfDays;
	String[] spalten;
	public Header(String reportName, Date timestamp, int numberOfDays,
			String[] spalten) {
		super();
		this.reportName = reportName;
		this.timestamp = timestamp;
		this.numberOfDays = numberOfDays;
		this.spalten = spalten;
	}
	
	
}
