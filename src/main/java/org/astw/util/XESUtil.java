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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.astw.util.Const.XESDataType;
import org.deckfour.xes.in.XesXmlParser;
import org.deckfour.xes.model.XAttribute;
import org.deckfour.xes.model.XAttributeBoolean;
import org.deckfour.xes.model.XAttributeContinuous;
import org.deckfour.xes.model.XAttributeDiscrete;
import org.deckfour.xes.model.XAttributeID;
import org.deckfour.xes.model.XAttributeLiteral;
import org.deckfour.xes.model.XAttributeMap;
import org.deckfour.xes.model.XAttributeTimestamp;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;
import org.deckfour.xes.out.XesXmlGZIPSerializer;
import org.deckfour.xes.out.XesXmlSerializer;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;


/**
 * 
 * @author Ario Santoso (santoso.ario@gmail.com)
 * @author Yasmin Khairina (yasminkhairina08@gmail.com)
 */
public class XESUtil {


	//////////////////////////////////////////////////////////////////////////////
	//XES RELATED UTILITY METHODS
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 * @param xlog
	 * @return a map from attribute names into the corresponding attribute types
	 * @throws Exception 
	 */
	public static HashMap<String, XESDataType> getAttributeNamesTypesMap(XLog xlog) throws Exception{
		
		System.out.println("");
		HashMap<String,XESDataType> result = new HashMap<String, XESDataType>();
		
		for(XTrace xtrace: xlog){
			
			XAttributeMap traceAtts = xtrace.getAttributes();
			
			//collecting trace attributes information
			for(String traceAttName: traceAtts.keySet()){
				
				XAttribute xatt = traceAtts.get(traceAttName);
				
				XAttributeMap xattmap = xatt.getAttributes();
				
				if(xattmap != null){
					if(xattmap.size() > 0)
						System.out.println("NOTE: probably the Trace attribute \""+traceAttName+"\" is a nested attribute");
				}
				
				if(result.containsKey(traceAttName)){
					XESDataType currXdt = getXESDataType(xatt);
					XESDataType existingXdt = result.get(traceAttName);
					
					if(currXdt != existingXdt)
						throw new Exception("Inconsistent Data Type for "+traceAttName+" ("+currXdt+" or "+existingXdt+"?)");
						
				}else{
					result.put(traceAttName, getXESDataType(xatt));
				}
			}
			
			for(XEvent xevent : xtrace){
				
				XAttributeMap eventAtts = xevent.getAttributes();
				
				//collecting trace attributes information
				for(String eventAttName: eventAtts.keySet()){
					
					XAttribute xatt = eventAtts.get(eventAttName);
					
					if(xatt != null){
						XAttributeMap xattmap = xatt.getAttributes();
						
						if(xattmap != null){
							if(xattmap.size() > 0)
								System.out.println("NOTE: probably the Event attribute "+eventAttName+" is a nested attribute");
						}

						if(result.containsKey(eventAttName)){
							XESDataType currXdt = getXESDataType(xatt);
							XESDataType existingXdt = result.get(eventAttName);
							
							if(currXdt != existingXdt)
								throw new Exception("Inconsistent Data Type for "+eventAttName+" ("+currXdt+" or "+existingXdt+"?)");
								
						}else{
							result.put(eventAttName, getXESDataType(xatt));
						}
					}
				}
			}
		}
		
		return result;
	}

	/**
	 * 
	 * @param xtrace
	 * @return a map from attribute names into the corresponding attribute types
	 * @throws Exception 
	 */
	public static HashMap<String, XESDataType> getAttributeNamesTypesMap(XTrace xtrace) throws Exception{
		
		System.out.println("");
		HashMap<String,XESDataType> result = new HashMap<String, XESDataType>();
		
		XAttributeMap traceAtts = xtrace.getAttributes();
		
		//collecting trace attributes information
		for(String traceAttName: traceAtts.keySet()){
			
			XAttribute xatt = traceAtts.get(traceAttName);
			
			if(xatt.getAttributes().size() > 0)
				System.out.println("NOTE: probably the Trace attribute \""+traceAttName+"\" is a nested attribute");
			
			if(result.containsKey(traceAttName)){
				XESDataType currXdt = getXESDataType(xatt);
				XESDataType existingXdt = result.get(traceAttName);
				
				if(currXdt != existingXdt)
					throw new Exception("Inconsistent Data Type for "+traceAttName+" ("+currXdt+" or "+existingXdt+"?)");
					
			}else{
				result.put(traceAttName, getXESDataType(xatt));
			}
		}
		
		for(XEvent xevent : xtrace){
			
			XAttributeMap eventAtts = xevent.getAttributes();
			
			//collecting trace attributes information
			for(String eventAttName: eventAtts.keySet()){
				
				XAttribute xatt = eventAtts.get(eventAttName);
				
				if(xatt.getAttributes().size() > 0)
					System.out.println("NOTE: probably the Event attribute "+eventAttName+" is a nested attribute");

				if(result.containsKey(eventAttName)){
					XESDataType currXdt = getXESDataType(xatt);
					XESDataType existingXdt = result.get(eventAttName);
					
					if(currXdt != existingXdt)
						throw new Exception("Inconsistent Data Type for "+eventAttName+" ("+currXdt+" or "+existingXdt+"?)");
						
				}else{
					result.put(eventAttName, getXESDataType(xatt));
				}
			}
		}
		
		return result;
	}

	public static XESDataType getXESDataType(XAttribute xatt){

		if(xatt instanceof XAttributeBoolean)
			return XESDataType.XES_BOOLEAN;
		else if(xatt instanceof XAttributeTimestamp)
			return XESDataType.XES_DATE_TIME;
		else if(xatt instanceof XAttributeLiteral)
			return XESDataType.XES_STRING;
		else if(xatt instanceof XAttributeID)
			return XESDataType.XES_STRING;
		else if(xatt instanceof XAttributeDiscrete)
			return XESDataType.XES_LONG;
		else if(xatt instanceof XAttributeContinuous)
			return XESDataType.XES_DOUBLE;
		else 
			return XESDataType.XES_UNKNOWN;
	}

	public static double getAvgTraceLength(XLog xlog){
		
		int numOfTrace = 0;
		int lengthSum = 0;
		
		for(XTrace xtrace: xlog){
			
			lengthSum += xtrace.size();
			numOfTrace++;
		}
		
		return ((double) lengthSum / numOfTrace);
	}
	
	//////////////////////////////////////////////////////////////////////////////
	//END OF XES RELATED UTILITY METHODS
	//////////////////////////////////////////////////////////////////////////////


	//////////////////////////////////////////////////////////////////////////////
	// SOME METHODS FOR XES Log I/O
	//////////////////////////////////////////////////////////////////////////////	
	
	
	public static XLog readXESLog(String fileAbsolutePath) throws Exception{
		
		long startTime = System.currentTimeMillis();
		
		FileInputStream fis = new FileInputStream(fileAbsolutePath);
		XesXmlParser xxp = new XesXmlParser();	
		List<XLog> xlogs = xxp.parse(fis);
		System.out.println("Number of XLog: "+xlogs.size());
		
		xxp = null;
		fis.close();

		XLog xlog = null;
		
		if(xlogs.size() > 0){
			xlog = xlogs.get(0);
		}
	
		StringBuilder sb = new StringBuilder("");
		sb.append("Total running time of reading XES Log from \""+fileAbsolutePath+"\": "+(System.currentTimeMillis() - startTime)+" millisecond(s)");

		System.out.println(sb);
		
		return xlog;
	}

	public static void saveXESLog(XLog xlog, String filePath){

		//save XES Log
		try {
			new XesXmlSerializer().serialize(xlog, new FileOutputStream(new File(filePath)));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public static void saveXESLogIntoGZip(XLog xlog, String filePath){

		//save XES Log
		try {
			new XesXmlGZIPSerializer().serialize(xlog, new FileOutputStream(new File(filePath)));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	//////////////////////////////////////////////////////////////////////////////
	// END OF SOME METHODS FOR XES Log I/O
	//////////////////////////////////////////////////////////////////////////////	

	
	//////////////////////////////////////////////////////////////////////////////
	// Other XES Utility method from Yasmin
	//////////////////////////////////////////////////////////////////////////////	
	
	/**
	 * Collects all possible attribute values of the given 'attributeName'
	 * 
	 * 
	 * @param xlog
	 * @param attributeName
	 * @return
	 */
	public static ArrayList<String> getAllPossibleAttValues(XLog xlog, String attributeName){
	
		Set<String> setAttr = new HashSet<String>();
		
		for(XTrace xtrace: xlog){
			
			for(XEvent xe : xtrace){
				
				if(xe.getAttributes().containsKey(attributeName)){
					XAttribute attr = xe.getAttributes().get(attributeName);
					setAttr.add(attr.toString());
				}
			}
		}
		ArrayList<String> list = new ArrayList<String>();
		list.addAll(setAttr);
		list.sort(null);
		return list;
	}
	
	/**
	 * Obtain the maximum number of events of a trace in the log  
	 * @param xlog
	 * @return
	 */
	public static int getMaxTracesLength(XLog xlog) {
		int maxLength = 0;
		for(XTrace xtrace: xlog){
			
			if (xtrace.size() > maxLength) {
				maxLength = xtrace.size();
			}
		}
		//System.out.println("maxLength is " + maxLength);
		return maxLength;
	}
	
	public static int getMinTracesLength(XLog xlog) {
		int minLength = Integer.MAX_VALUE;
		for(XTrace xtrace: xlog){
			
			if (xtrace.size() < minLength) {
				minLength = xtrace.size();
			}
		}
		//System.out.println("minLength is " + minLength);
		return minLength;
	}
	
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
	
	//////////////////////////////////////////////////////////////////////////////
	// END OF Other XES Utility method from Yasmin
	//////////////////////////////////////////////////////////////////////////////	

	//////////////////////////////////////////////////////////////////////////////
	// OHTERS
	//////////////////////////////////////////////////////////////////////////////	

	public static XLog takeFirstNTraces(XLog xlog, int n){
		
		while(xlog.size() > n)
			xlog.remove(n);
		
		return xlog;
	}
	
	public static double computeEventDurationAverage(XLog xlog){
		
		long totalDuration = 0;
		long totalEvent = 0;
		
		for(XTrace xtrace: xlog){
			for(int ii = 0; ii < xtrace.size()-1; ii++){
				
				XEvent currE = xtrace.get(ii);
				XEvent nextE = xtrace.get(ii+1);
				
				totalDuration = totalDuration + XESUtil.timeDiff(
							nextE.getAttributes().get("time:timestamp").toString(), 
							currE.getAttributes().get("time:timestamp").toString());
				totalEvent++;
			}
		}
		
//		System.out.println(totalDuration);
//		System.out.println(totalEvent);
		
		return ((double) (totalDuration/totalEvent));
	}

	//////////////////////////////////////////////////////////////////////////////
	// END OF OHTERS
	//////////////////////////////////////////////////////////////////////////////	

}
