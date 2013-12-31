package de.proglabor.aufgabe4.test;

import java.util.Hashtable;

import org.junit.Test;

import de.proglabor.aufgabe4.report.ReportTiere;

public class ReportTiereTest {

	@Test
	public void test() {
		int days = 10;
		ReportTiere reportTiere = new ReportTiere(days);
		reportTiere.reportHeader();
		for (int i = 0; i < days; i++) {
			Hashtable<String, Integer> reportTiereContent = new Hashtable<String, Integer>();
			reportTiereContent.put("Tag", i);
			reportTiereContent.put("World", i+2);
			reportTiereContent.put("Dschungel", i+4);
			reportTiere.reportContent(reportTiereContent);
		}
		reportTiere.reportFooter(1337);
		reportTiere.reportWriter(reportTiere.reportOutput, "ReportTiere.csv");
	}

}
