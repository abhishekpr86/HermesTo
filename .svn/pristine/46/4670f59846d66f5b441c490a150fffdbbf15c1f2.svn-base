package com.capgemini.onboarding.testing;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import com.capgemini.onboarding.model.Country;

import static java.time.temporal.TemporalAdjusters.*;

public class Testmain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	/*	 Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
		 localCalendar.getInstance();
		 logger.info(localCalendar.getTime());*/

/*		 Calendar date = Calendar.getInstance();
         date.set(Calendar.DAY_OF_MONTH, 1);
        
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
     String startDate=df.format(date.getTime());


         logger.info("this is start date"+startDate);*/

 /*        LocalDate localDate = LocalDate.now();
 		localDate.getMonth();
         logger.info(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(localDate));*/
         

		LocalDate c_date = LocalDate.now();
		List<String> monthlst = new ArrayList<>();
		for(int month = 0; month <=6; month++){
			LocalDate currentupdateddate = c_date.plusMonths(month);
			String monthname = currentupdateddate.getMonth().name();
			monthlst.add(monthname);
		//	logger.info("The month name is" + monthname);
         
	}
	}
}


