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
package org.astw.foe.impl.eventexp;

import java.util.HashMap;

import org.astw.foe.EventExp;
import org.astw.foe.EventExpComponent;
import org.astw.foe.Formula;
import org.astw.util.Const.ComparisonOperator;
import org.astw.util.Const.ValueType;
import org.astw.util.Const.XESDataType;
import org.deckfour.xes.model.XTrace;

/**
 * @author Ario Santoso (santoso.ario@gmail.com)
 */
public class EventExpBoolean implements EventExp{

	private boolean value;
	
    public EventExpBoolean(String inputVal) throws Exception{
    	
		if(inputVal.equalsIgnoreCase("TRUE"))
			this.value = true;
		else if(inputVal.equalsIgnoreCase("FALSE"))
			this.value = false;
		else{
			throw new Exception(inputVal + " is not a boolean value");
		}
    }

    public void setValue(boolean value) {
		this.value = value;
	}
    
	public String toString(){
        if(value == true )
        	return "TRUE";
        else return "FALSE";
	}

	@Override
	public String prettyFormat(String tab) {
        if(value == true )
        	return tab+"TRUE";
        else return tab+"FALSE";
	}

	public EventExpBoolean clone(){
		try {
			return new EventExpBoolean(this.value+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//the following return statement should not be reachable
		return null;
	}
	/**
	 * To assign the corresponding query type based on the given Attributes and types information
	 */
	@Override
	public void assignQueryXESDataType(HashMap<String, XESDataType> attributeNamesAndTypes) {
	}

	@Override
	public ValueType ensureTypeCorrectness() throws Exception {

		return ValueType.NON_NUMERIC_EXP;
	}

    @Override
	public boolean getValue() {
		return value;
	}

	@Override
	public void substituteVar(String varName, int value) {
	}

	@Override
	public Formula eliminateQuantifier(int domainSize) {

		return this; 
		
//		try {
//			
//			return new EventExpBoolean(this.value+"");
//			
//			//Note: the exception won't be thrown
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return this;
	}

	@Override
	public void evaluateQuery(XTrace xtrace)  throws Exception {
	}

	@Override
	public void evaluateSpecialIndex(int current, int last) {
	}

	@Override
	public boolean evaluateGroundedFormula() throws Exception {

		return this.value;
	}

}
