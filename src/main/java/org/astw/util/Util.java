/*******************************************************************************
 *     Specification-Driven-Predictive-Process-Monitoring
 *     Copyright (C) 2018 Ario Santoso (santoso.ario@gmail.com)
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package org.astw.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

/**
 * 
 * @author Ario Santoso (santoso.ario@gmail.com)
 *
 */
public class Util {

	
	public static String getCurrentTimeStamp(){
		
		return new Timestamp(System.currentTimeMillis()).toString();
	}
	
	////////////////////////////////////////////////////////////
	//Time related methods
	////////////////////////////////////////////////////////////	
	
	public static long timeDiff(String ts1, String ts2) {
		DateTime dt1 = DateTime.parse(ts1);
		DateTime dt2 = DateTime.parse(ts2);
		return Math.abs(dt1.getMillis() - dt2.getMillis());
	}
	
	public static long timeDiffSinceMidnight(String ts1) {
		DateTime dt = DateTime.parse(ts1);
		DateTime dtMidnight = dt.withTime(0, 0, 0, 0);
		return dt.getMillis() - dtMidnight.getMillis();
	}
	
	public static long timeDiffSinceFirstDayOfTheWeek(String ts1) {
		Locale usersLocale = Locale.getDefault();
		DateTime dt = DateTime.parse(ts1);
		LocalDate localDate = dt.toLocalDate();
		int firstDoW = Calendar.getInstance(usersLocale).getFirstDayOfWeek();
		
		// because the constant in Calendar starts from Sunday = 1, 
		// and the constant in Joda starts from Monday = 1
		if (firstDoW == Calendar.SUNDAY)
			firstDoW = 7;
		else
			firstDoW = firstDoW - 1;
		
		// get the day of week from our timestamp
		int ourDoW = dt.dayOfWeek().get();
		
		// get the difference of the day
		int diffToDoW = ourDoW - firstDoW;
		if (ourDoW < firstDoW) {
			diffToDoW += 7;
		}
		
		// now construct the date from the info we have
		DateTime temp = dt.minusDays(diffToDoW);
		DateTime dow = temp.withTime(0, 0, 0, 0);
		
		return dt.getMillis() - dow.getMillis();
	}

	////////////////////////////////////////////////////////////
	//END OF Time related methods
	////////////////////////////////////////////////////////////	

}
