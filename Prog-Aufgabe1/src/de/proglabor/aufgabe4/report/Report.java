package de.proglabor.aufgabe4.report;

import java.util.Calendar;
import java.util.List;

public abstract class Report {
	Header header;
	
	public Calendar getCalendar() {
		return Calendar.getInstance();
	}
	
	public abstract void reportHeader();
	
	public abstract void reportContent(Object obj);
	
	public abstract void reportFooter(Object obj);
	
	public void reportWriter(List<String> list, String filename) {
		
	}
}
