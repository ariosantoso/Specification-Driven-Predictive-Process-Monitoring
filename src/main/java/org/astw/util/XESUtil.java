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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.astw.util.Const.XESDataType;
import org.astw.util.encoder.Encoder;
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

import gnu.trove.map.hash.TIntIntHashMap;


/**
 * Various utility methods for XES event Logs
 * 
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

	//////////////////////////////////////////////////////////////////////////////
	//END OF XES RELATED UTILITY METHODS
	//////////////////////////////////////////////////////////////////////////////

	
	//////////////////////////////////////////////////////////////////////////////
	//XES RELATED UTILITY METHODS (mostly for statistical analysis)
	//////////////////////////////////////////////////////////////////////////////

	/**
	 * print the length of all traces - usefull for making histogram about the trace length
	 * @param xlog
	 * @throws Exception
	 */
	public static void printAllTraceLength(XLog xlog ) throws Exception{
		
		for(XTrace xtrace : xlog)
			System.out.println(xtrace.size());
	}

	/**
	 * Compute the average of the number of events in each trace (i.e., the average trace length)
	 * 
	 * @param log
	 * @return
	 */
	public static double getAvgTraceLength(XLog xlog){
		
		int numOfTrace = 0;
		int lengthSum = 0;
		
		for(XTrace xtrace: xlog){
			
			lengthSum += xtrace.size();
			numOfTrace++;
		}
		
		return ((double) lengthSum / numOfTrace);
	}
	
	/**
	 * Compute the average event duration within a log
	 * @param xlog
	 * @return
	 * @author Ario Santoso (santoso.ario@gmail.com)
	 */
	public static double computeEventDurationAverage(XLog xlog){
		
		long totalDuration = 0;
		long totalEvent = 0;
		
		for(XTrace xtrace: xlog){
			for(int ii = 0; ii < xtrace.size()-1; ii++){
				
				XEvent currE = xtrace.get(ii);
				XEvent nextE = xtrace.get(ii+1);
				
				totalDuration = totalDuration + Util.timeDiff(
							nextE.getAttributes().get("time:timestamp").toString(), 
							currE.getAttributes().get("time:timestamp").toString());
				totalEvent++;
			}
		}
		
//		System.out.println(totalDuration);
//		System.out.println(totalEvent);
		
		return ((double) (totalDuration/totalEvent));
	}

	/**
	 * Get an array of all trace lengths
	 * 
	 * @param xlog
	 * @return
	 */
	public static int[] getAllTraceLength(XLog xlog){
		
		int[] results = new int[xlog.size()];
		int idx = 0;
		
		for(XTrace xtrace: xlog)
			results[idx++] = xtrace.size();
		
		return results;
	}
	
	/**
	 * Get some statistical information w.r.t. trace length
	 * 
	 * @param xlog
	 * @throws Exception
	 */
	public static DescriptiveStatistics getTraceLengthStatistic(XLog xlog, PrintStream printStream){
		
		DescriptiveStatistics stats = new DescriptiveStatistics();
		
		for(XTrace xtrace : xlog)
			stats.addValue(xtrace.size());
		
		if(printStream != null){
			printStream.println("====================================================================================");
			printStream.println("Total Number of Traces: "+stats.getN());
			printStream.println("Mean of trace length: "+stats.getMean());
			printStream.println("Min of trace length: "+stats.getMin());
			printStream.println("Max of trace length: "+stats.getMax());
			printStream.println("Standard Deviation of trace length: "+stats.getStandardDeviation());
			printStream.println("Percentile 1 of trace length: "+stats.getPercentile(1));
			printStream.println("Percentile 25 of trace length: "+stats.getPercentile(25));
			printStream.println("Percentile 50 of trace length: "+stats.getPercentile(50));
			printStream.println("Percentile 75 of trace length: "+stats.getPercentile(75));
			printStream.println("Percentile 100 of trace length: "+stats.getPercentile(100));
			printStream.println("====================================================================================");
		}

		return stats;
		
		/*
		int[] traceLengths = new int[xlog.size()];
		
		int ii = 0;
		for(XTrace xtrace : xlog)
			traceLengths[ii++] = xtrace.size();
			
		Arrays.sort(traceLengths);
		System.out.println("Mean of trace length: "+StatisticFunctions.mean(traceLengths));
		System.out.println("Mode of trace length: "+StatisticFunctions.mode(traceLengths));
		System.out.println("Median of trace length: "+StatisticFunctions.median(traceLengths));
		System.out.println("Max trace length: "+StatisticFunctions.max(traceLengths));
		System.out.println("Min trace length: "+StatisticFunctions.min(traceLengths));
		*/
	}
	
	/**
	 * Print the frequency of each trace length
	 * 
	 * @param logPath
	 * @throws Exception
	 */
	public static void printFreqEachTraceLength(XLog xlog) throws Exception{

		TIntIntHashMap freq = new TIntIntHashMap();
		
		for(XTrace xtrace : xlog){
			
			int length = xtrace.size();
			
			if(freq.containsKey(length))
				freq.put(length, freq.get(length) + 1);
			else
				freq.put(length, 1);
		}
		
		int[] allPossibleTraceLength = freq.keys();

		Arrays.sort(allPossibleTraceLength);
		
		//print out
		int total = 0;
		System.out.println("length : Freq");
		for(int ii = 0; ii < allPossibleTraceLength.length; ii++){
			
			total += freq.get(allPossibleTraceLength[ii]);
			System.out.println(allPossibleTraceLength[ii]+" : "+freq.get(allPossibleTraceLength[ii]));
		}
		
		System.out.println("Number of distint trace length: "+allPossibleTraceLength.length);
		System.out.println("Total Number of Traces: "+total+" ; "+xlog.size());
	}

	//////////////////////////////////////////////////////////////////////////////
	//END OF XES RELATED UTILITY METHODS (mostly for statistical analysis)
	//////////////////////////////////////////////////////////////////////////////


	
	//////////////////////////////////////////////////////////////////////////////
	// SOME METHODS FOR XES Log I/O
	//////////////////////////////////////////////////////////////////////////////	
	
	public static XLog readXESLog(String fileAbsolutePath) throws Exception{
		
		long startTime = System.currentTimeMillis();
		
		FileInputStream fis = new FileInputStream(fileAbsolutePath);
		XesXmlParser xxp = new XesXmlParser();	
		List<XLog> xlogs = xxp.parse(fis);
		//System.out.println("Number of XLog: "+xlogs.size());
		
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
	 * @param xlog
	 * @param attributeName
	 * @return listOfAllPossibleValues - ArrayList<String>
	 * @author Yasmin Khairina (yasminkhairina08@gmail.com)
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
	 * Obtain the maximum number of events of a trace in the log  (i.e., the maximum trace length)
	 * @param xlog
	 * @return maxTraceLength
	 * @author Yasmin Khairina (yasminkhairina08@gmail.com)
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

	/**
	 * Obtain the minimum number of events of a trace in the log  (i.e., the minimum trace length)
	 * @param xlog
	 * @return minTraceLength
	 * @author Yasmin Khairina (yasminkhairina08@gmail.com)
	 */
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

	//////////////////////////////////////////////////////////////////////////////
	// END OF Other XES Utility method from Yasmin
	//////////////////////////////////////////////////////////////////////////////	

	
	
	//////////////////////////////////////////////////////////////////////////////
	// FILTERING
	//////////////////////////////////////////////////////////////////////////////	
	
	public static XLog standardFiltering(XLog log, boolean strongerFiltering) throws Exception{
		
		return standardFiltering(log, strongerFiltering, System.out);
	}

	public static XLog standardFiltering(XLog log, boolean strongerFiltering, PrintStream output) throws Exception{
		
		output.println("====================================================================================");
		output.println("APPLYING STANDARD FILTERING");
		output.println("====================================================================================");

		XLog xlog = (XLog) log.clone();
		
		XESUtil.getTraceLengthStatistic(xlog, output);

		XLog newXLog2 = xlog;
		
		if(strongerFiltering){
			XLog newXLog1 = XESUtil.removeTracesWithNoEvents(xlog, output);
			newXLog2 = XESUtil.removeTracesWithLengthLessThanOrEqualToTheThreshold(newXLog1, 1, output);
			XESUtil.getTraceLengthStatistic(newXLog2, output);
		}
		
		XLog newXLog3 = XESUtil.traceLengthFilterIQR(newXLog2, output);
		
		XESUtil.getTraceLengthStatistic(newXLog3, output);

		output.println("====================================================================================");
		output.println("END OF APPLYING STANDARD FILTERING");
		output.println("====================================================================================");

		return newXLog3;
	}

	public static XLog takeFirstNTraces(XLog xlog, int n){
		
		XLog log = (XLog) xlog.clone();
		
		while(log.size() > n)
			log.remove(n);		
		
		return log;
	}
	
	public static XLog removeTracesWithNoEvents(XLog xlog) {
		
		return removeTracesWithNoEvents(xlog, System.out);
	}

	public static XLog removeTracesWithNoEvents(XLog xlog, PrintStream ps) {
		
		ps.println("Removing traces with no events");

		XLog log = (XLog)xlog.clone();
		
		int beforeRemoval = xlog.size();
		
		log.clear();
		for(XTrace xtrace : xlog)
			if (xtrace.size() > 0) 
				log.add(xtrace);
		
		int afterRemoval = log.size();
		
		double percentage = (double)((beforeRemoval - afterRemoval) * 100)/ beforeRemoval;
		ps.println(percentage + "% has no events");
		
		return log;
	}
	
	/*
	 * note: the trace with the number of events equal to the threshold will be removed as well
	 * 
	 */
	public static XLog removeTracesWithLengthLessThanOrEqualToTheThreshold(XLog xlog, int threshold, PrintStream output) {

		output.println("Removing traces in which their length is less than or equal to " + threshold + " events");

		XLog log = (XLog) xlog.clone();
		
		int beforeRemoval = xlog.size();
		
		log.clear();
		for(XTrace trace: xlog)
			if (trace.size() > threshold)
				log.add(trace);
		
		int afterRemoval = log.size();
		
		double percentage = (double)((beforeRemoval - afterRemoval) * 100)/ beforeRemoval; 
		
		output.println( + percentage + "% has less than " + threshold + " events");
		
		return log;
	}

	
	/*
	 * note: the trace with the number of events equal to the threshold will be removed as well
	 * 
	 */
	public static XLog removeTracesWithLengthLessThanOrEqualToTheThreshold(XLog xlog, int threshold) {
		return removeTracesWithLengthLessThanOrEqualToTheThreshold(xlog, threshold, System.out);
	}

	//sort the traces in the log based on the timestamp of the first event. The sorting is ascending
	public static XLog sortXLogBasedOnTimeStamp(XLog xlog) {
		XLog log = (XLog) xlog.clone();
		log.sort(new XTraceComparatorTimeStampFirstEvent());
		return log;
	}
	
	//sort the traces in the log based on the length of the trace. The sorting is ascending
	public static XLog sortXLogBasedOnTraceLength(XLog xlog) {
		XLog log = (XLog) xlog.clone();
		log.sort(new XTraceComparatorTraceLength());
		return log;
	}

	//check if it is sorted based on the timestamp - ascending
	public static boolean isSortedBasedOnTimeStampFirstEvent(XLog log) {
		
		for (int i = 1; i < log.size()-1; i++) {
			
			XTrace t_before = log.get(i-1);
			XTrace t_after = log.get(i);
			
			String ts1 = t_before.get(0).getAttributes().get(Encoder.XES_TS_ATTR).toString();
			DateTime dt1 = DateTime.parse(ts1);
			String ts2 = t_after.get(0).getAttributes().get(Encoder.XES_TS_ATTR).toString();
			DateTime dt2 = DateTime.parse(ts2);
			
			if (dt1.compareTo(dt2) > 0) {
				System.out.println("false on " + i);
				return false;
			}
		}
		return true;
	}

	//check if it is sorted based on the length - ascending
	public static boolean isSortedBasedOnTheLength(XLog log) {
		
		for (int ii = 0; ii < log.size()-1; ii++) 
			if(log.get(ii).size() > log.get(ii+1).size())
				return false;
		
		return true;
	}

	/**
	 * Filtering outliers/abnormal traces w.r.t. trace length by using interquartile range (IQR) method.
	 * 
	 * @param xlog - XLog
	 * @return xlog - filtered XLog
	 * @throws Exception 
	 */
	public static XLog traceLengthFilterIQR(XLog log, PrintStream output) {
		
		output.println("====================================================================================");
		output.println("Outlier Traces Filtering - w.r.t. trace length - Using IQR method");

		XLog xlog = (XLog) log.clone();
		int initialSizeOfXLog = xlog.size();
		
		DescriptiveStatistics traceLengthStats = XESUtil.getTraceLengthStatistic(xlog, null);
		
		double Q1 = traceLengthStats.getPercentile(25); //percentile 25
		double Q3 = traceLengthStats.getPercentile(75); //percentile 75
		double IQR = Q3 - Q1;
		double lowerBound = Q1 - (1.5 * IQR);
		double upperBound = Q3 + (1.5 * IQR);
		
		output.println(
				"Q1: "+Q1+"\nQ3: "+Q3+"\nIQR: "+IQR+
				"\nLowerBound (Q1 - (1.5 * IQR)): "+lowerBound+"\nUpperBound (Q3 + (1.5 * IQR)): "+upperBound);

		xlog.clear();
		for(XTrace xtrace: log){
			
			int curLength = xtrace.size();
			
			//System.out.println(lowerBound+" "+curLength+" "+upperBound);
			
			//if(curLength < lowerBound || curLength > upperBound)
			if(curLength > lowerBound && curLength < upperBound)
				xlog.add(xtrace);
		}
		
		int sizeOfXLogAfterFiltering = xlog.size();
		
		output.println("Result: "+(initialSizeOfXLog - sizeOfXLogAfterFiltering)+
				" traces out of "+initialSizeOfXLog+" have been removed (i.e., "+
				((double)((initialSizeOfXLog - sizeOfXLogAfterFiltering) * 100)/initialSizeOfXLog)+" %)");
		output.println("END OF Outlier Traces Filtering - w.r.t. trace length - Using IQR method");
		output.println("====================================================================================");

		
		return xlog;
	}

	/**
	 * Filtering outliers/abnormal traces w.r.t. trace length by using interquartile range (IQR) method.
	 * 
	 * @param xlog - XLog
	 * @return xlog - filtered XLog
	 * @throws Exception 
	 */
	public static XLog traceLengthFilterIQR(XLog log) {
		
		return traceLengthFilterIQR(log, System.out);
	}

	
	@Deprecated
	public static XLog traceLengthFilter(XLog xlog, int percentToCut){
		
		try {
				PrintStream out = System.out;
				System.setErr(new PrintStream(new ByteArrayOutputStream()));
				System.setOut(new PrintStream(new ByteArrayOutputStream()));

				double avgLength = XESUtil.getAvgTraceLength(xlog);

				System.setOut(out);

				System.out.println("-------------------------------------------------");
				System.out.println("log original size: "+xlog.size());
				System.out.println("avgLength: "+avgLength);
				System.out.println("Max Length: "+XESUtil.getMaxTracesLength(xlog));
				System.out.println("Min Length: "+XESUtil.getMinTracesLength(xlog));
				System.out.println("-------------------------------------------------");

				xlog = XESUtil.sortXLogBasedOnTraceLength(xlog);
				
				System.out.println("the log has been sorted based on the trace length: "+ XESUtil.isSortedBasedOnTheLength(xlog));
				
				int outliers = (int)(((double) percentToCut/100) * xlog.size());
				System.out.println("Total outliers to cut: "+ (outliers * 2) + " remaining log size: "+ (xlog.size() - (outliers * 2)));
				
				XLog xlogFiltered = (XLog) xlog.clone();

				//remove first outliers
				for(int jj = 0; jj < outliers; jj++){
					
					xlogFiltered.remove(jj);
					xlogFiltered.remove(xlogFiltered.size()-1);
				}
				
				System.out.println("-------------------------------------------------");
				System.out.println("log size - after filtering: "+xlogFiltered.size());
				System.out.println("avgLength: "+XESUtil.getAvgTraceLength(xlogFiltered));
				System.out.println("Max Length: "+XESUtil.getMaxTracesLength(xlogFiltered));
				System.out.println("Min Length: "+XESUtil.getMinTracesLength(xlogFiltered));
				System.out.println("-------------------------------------------------");
				
				return xlogFiltered;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	//////////////////////////////////////////////////////////////////////////////
	// END OF FILTERING
	//////////////////////////////////////////////////////////////////////////////	

	
}

class XTraceComparatorTimeStampFirstEvent implements Comparator<XTrace>{
	
	@Override
	public int compare(XTrace o1, XTrace o2) {
		String ts1 = o1.get(0).getAttributes().get(Encoder.XES_TS_ATTR).toString();
		DateTime dt1 = DateTime.parse(ts1);
		String ts2 = o2.get(0).getAttributes().get(Encoder.XES_TS_ATTR).toString();
		DateTime dt2 = DateTime.parse(ts2);
		return dt1.compareTo(dt2);
	}
}

class XTraceComparatorTraceLength implements Comparator<XTrace>{
	
	@Override
	public int compare(XTrace o1, XTrace o2) {

		int o1Length = o1.size();
		int o2Length = o2.size();
		
		if( o1Length < o2Length)
			return -1;
		
		if( o1Length > o2Length)
			return 1;
		
		return 0;
	}
}
