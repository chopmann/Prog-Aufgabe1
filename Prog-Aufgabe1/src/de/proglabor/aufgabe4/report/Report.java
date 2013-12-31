package de.proglabor.aufgabe4.report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import de.proglabor.aufgabe4.utils.Logger;

/**
 * @author sirmonkey
 *
 */
public abstract class Report {
	/**
	 *  The Whole Report
	 */
	public List<String> reportOutput = new ArrayList<String>();
	protected int days;
	
	/**
	 * @param days to be simulated
	 */
	public Report(int days) {
		this.days = days;
	}
	
	/**
	 * @return a Calendar Instance
	 */
	public Calendar getCalendar() {
		return Calendar.getInstance();
	}
	
	/**
	 * Generates the Report Header
	 */
	public abstract void reportHeader();
	
	/**
	 * @param obj 
	 */
	public abstract void reportContent(Object obj);
	
	/**
	 * @param obj 
	 */
	public abstract void reportFooter(Object obj);
	
	/**
	 * @param list 
	 * @param filename 
	 */
	public void reportWriter(List<String> list, String filename) {
		BufferedWriter writer = null;
		try {
            //create a temporary file
            File outputCSV = new File(filename);

            // This will output the full path where the file will be written to...
            Logger.info("New Report " + outputCSV.getCanonicalPath());

            writer = new BufferedWriter(new FileWriter(outputCSV));
            for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				writer.write(string);
				if (iterator.hasNext()) {
					writer.newLine();	
				}
				
			}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
            	Logger.severe(e.getStackTrace().toString());
            }
        }
	}
}
