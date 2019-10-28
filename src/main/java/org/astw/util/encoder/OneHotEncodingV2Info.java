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
package org.astw.util.encoder;

import java.util.ArrayList;

import org.astw.util.XESUtil;
import org.deckfour.xes.model.XLog;

/**
 * 
 * @author Ario Santoso (santoso.ario@gmail.com)
 */
public class OneHotEncodingV2Info {

	public static String XES_ONE_HOT_WEKA_ATTR_V2_TEMPLATE = "%s_%s_%s";
	private static String pref = "OH";

	private String xesAttName;
	private String wekaAttName;
	private ArrayList<String> listOfAllPossibleAttValues;
	private int maxTraceLength;
	private int numOfDifferentValues;

//	public OneHotEncodingV2Info(String xesAttName, XLog xlog, int maxTraceLength){
//		
//		this(xesAttName, xlog);
//		this.maxTraceLength = maxTraceLength;		
//	}

	//it uses the maximal trace length in the log as the number of the n-last events to be considered in the encoding
	public OneHotEncodingV2Info(String xesAttName, XLog xlog){
		
		this.xesAttName = xesAttName;		
		this.wekaAttName = this.pref+xesAttName;		
		
		this.maxTraceLength = XESUtil.getMaxTracesLength(xlog);
		
		//dapet semua nilai dari attribute dengan nama 'this.xesAttName'
		ArrayList<String> listOfAllPossibleAttValues = 
				XESUtil.getAllPossibleAttValues(xlog, this.xesAttName);//dapet semua nilai dari attribute dengan nama "attrName"

		listOfAllPossibleAttValues.sort(null);
		this.listOfAllPossibleAttValues = listOfAllPossibleAttValues;
		this.numOfDifferentValues = this.listOfAllPossibleAttValues.size();
				
	}

	/**
	 * This constructor was made for the purpose of serialization and deserialization. 
	 * It is better not to use this constructor unless you know how everything is work.
	 * 
	 * @param xesAttName
	 * @param wekaAttName
	 * @param listOfAllPossibleAttValues
	 * @param maxTraceLength
	 * @param numOfDifferentValues
	 */
	public OneHotEncodingV2Info(String xesAttName, String wekaAttName, ArrayList<String> listOfAllPossibleAttValues, 
			int maxTraceLength, int numOfDifferentValues){

		this.xesAttName = xesAttName;
		this.wekaAttName = wekaAttName;
		this.listOfAllPossibleAttValues = listOfAllPossibleAttValues;
		this.maxTraceLength = maxTraceLength;
		this.numOfDifferentValues = numOfDifferentValues;
	}

	public int getMaxTraceLength() {
		return maxTraceLength;
	}

	public String getXesAttName() {
		return xesAttName;
	}

	public int getNumOfDifferentValues() {
		return this.numOfDifferentValues;
	}

	public ArrayList<String> getListOfAllPossibleAttValues() {
		return listOfAllPossibleAttValues;
	}

	public String getWekaAttName() {
		return wekaAttName;
	}
	
	public String toString(){
		
		return "OneHotEncoding: "+this.xesAttName+
				" - NumOfAllPossibleValues: "+this.numOfDifferentValues+
				" - MaxTraceLength: "+this.maxTraceLength+
				" - WekaAttName: "+this.wekaAttName+
				" - OH_AttNum: "+(this.maxTraceLength * this.numOfDifferentValues);
	}
}
