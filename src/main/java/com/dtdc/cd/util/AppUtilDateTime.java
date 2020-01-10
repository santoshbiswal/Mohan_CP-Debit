package com.dtdc.cd.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AppUtilDateTime {

	
	public static Date getCurrentDateTimeAsStringFormat() {
	  
	    Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
		String strDate = sdf.format(cal.getTime());
		Date dateTime = null;
		try {
			dateTime = sdf.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return dateTime;
	}
}
