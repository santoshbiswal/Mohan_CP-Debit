package com.dtdc.cd.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AppUtilCurrDtTime {

	public static String getCurrentDateTimeAsStringFormat(String format) {
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(format);//"dd/MM/yyyy HH:mm:ss.SS"
	    String strDate = sdf.format(cal.getTime());
	  
	    return strDate;
	}
}
