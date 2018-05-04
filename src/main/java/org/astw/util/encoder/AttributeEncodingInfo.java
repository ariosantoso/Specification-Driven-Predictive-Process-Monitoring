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
public class AttributeEncodingInfo {

	public static String ATT_ENC_WEKA_ATTR_TEMPLATE = "%s_%s";
	private static String pref = "AC";

	private String xesAttName;
	private String wekaAttName;
	private ArrayList<String> listOfAllPossibleAttValues;
	private int maxTraceLength;
	private int numOfDifferentValues;
//	private XLog xlog;

	public AttributeEncodingInfo(String xesAttName, XLog xlog){
		
		this.xesAttName = xesAttName;		
		this.wekaAttName = this.pref+xesAttName;		
		
		this.maxTraceLength = XESUtil.getMaxTracesLength(xlog);
		
		//dapet semua nilai dari attribute dengan nama 'this.xesAttName'
		ArrayList<String> listOfAllPossibleAttValues = 
				XESUtil.getAllPossibleAttValues(xlog, this.xesAttName);

//		System.out.println("log size: "+this.xlog.size());
//		System.out.println("Hah attName: "+this.xesAttName+" - Num Dif: - "+listOfAllPossibleAttValues.size());
		
		listOfAllPossibleAttValues.sort(null);
		this.listOfAllPossibleAttValues = listOfAllPossibleAttValues;
		this.numOfDifferentValues = this.listOfAllPossibleAttValues.size();
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
		
		return "AttributeEncoding: "+this.xesAttName+
				" - NumOfAllPossibleValues: "+this.numOfDifferentValues+
				" - MaxTraceLength: "+this.maxTraceLength+
				" - WekaAttName: "+this.wekaAttName+
				" - AE_AttNum: "+this.maxTraceLength;
	}

}
