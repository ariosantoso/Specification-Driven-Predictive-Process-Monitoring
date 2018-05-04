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
import java.io.PrintStream;
import java.util.Comparator;
import java.util.Iterator;

import org.astw.util.encoder.Encoder;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;
import org.joda.time.DateTime;

/**
 * 
 * @author Ario Santoso (santoso.ario@gmail.com)
 * @author Yasmin Khairina (yasminkhairina08@gmail.com)
 *
 */
public class XESPreprocessor {

	public static XLog removeTracesWithNoEvents(XLog log) {
		int beforeCount = log.size();
		Iterator<XTrace> iterator = log.iterator();
		while (iterator.hasNext()) {
			XTrace trace = iterator.next();
			if (trace.size() < 0) {
				iterator.remove();
				System.out.println("removed");
			}
		}
		int afterCount = log.size();
		double percentage = (double)((beforeCount - afterCount) * 100)/ beforeCount;
		System.out.println(percentage + "% has no events");
		
		return log;
	}
	
	/*
	 * note: the trace with the number of events equal to the threshold will be removed as well
	 * 
	 */
	public static XLog removeTracesWithNumEventsLessThanOrEqualToTheThreshold(XLog log, int threshold) {
		int beforeCount = log.size();
		Iterator<XTrace> iterator = log.iterator();
		while (iterator.hasNext()) {
			XTrace trace = iterator.next();
			if (trace.size() <= threshold) {
				iterator.remove();
			}
		}
		int afterCount = log.size();
		double percentage = (double)((beforeCount - afterCount) * 100)/ beforeCount; 
		System.out.println( + percentage + 
				"% has less than " + threshold + " events");
		
		return log;
	}
	
	public static double averageEventsEachTrace(XLog log) {
		int events = 0;
		int numTrace = log.size();
		Iterator<XTrace> iterator = log.iterator();
		while (iterator.hasNext()) {
			XTrace trace = iterator.next();
			events += trace.size();
		}
		System.out.println("average events per each trace is " + ((double)events/numTrace));
		return (double)events/numTrace;
	}
	
	//sort the traces in the log based on the timestamp of the first event. The sorting is ascending
	public static XLog sortXLogBasedOnTimeStamp(XLog log) {
		log.sort(new XTraceComparatorTimeStampFirstEvent());
		return log;
	}
	
	//sort the traces in the log based on the length of the trace. The sorting is ascending
	public static XLog sortXLogBasedOnTraceLength(XLog log) {
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
		
		for (int ii = 0; ii < log.size(); ii++) 
			if(log.get(ii).size() > log.get(ii).size())
				return false;

		return true;
	}

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

				xlog = XESPreprocessor.sortXLogBasedOnTraceLength(xlog);
				
				System.out.println("the log has been sorted based on the trace length: "+ XESPreprocessor.isSortedBasedOnTheLength(xlog));
				
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