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
package org.astw.foe.impl.nonnumeric;

import java.util.HashMap;

import org.astw.foe.NonNumExp;
import org.astw.util.Const;
import org.astw.util.Const.ValueType;
import org.astw.util.Const.XESDataType;
import org.deckfour.xes.model.XTrace;

/**
 * @author Ario Santoso (santoso.ario@gmail.com)
 */
public class NonNumExpBoolean implements NonNumExp{
    
	private boolean value;
	private ValueType valueType = ValueType.NON_NUMERIC_EXP;//numeric or non-numeric

	public NonNumExpBoolean(String component) throws Exception{
		
		if(component.equalsIgnoreCase("TRUE"))
			this.value = true;
		else if(component.equalsIgnoreCase("FALSE"))
			this.value = false;
		else{
			throw new Exception(component + " is not a boolean value");
		}
	}

	public boolean getComponent() {
		return value;
	}

	public void setComponent(boolean component) {
		this.value = component;
	}
	
	public String toString(){
		
		if(this.value == true)
			return "true";
		else return "false";
	}

	@Override
	public String prettyFormat(String tab) {
		if(this.value == true)
			return tab+"true";
		else return tab+"false";
	}
	
	public NonNumExpBoolean clone(){
		try {
			return new NonNumExpBoolean((this.value+"").toUpperCase());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//this return statement should not be reachable
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

		return this.valueType;
	}

	@Override
	public ValueType getValueType() {

		return this.valueType;
	}

	@Override
	public String getNonNumericValue() {

		return this.value+"";
	}

	@Override
	public XESDataType getXESDataType() {

		return Const.XESDataType.XES_BOOLEAN;
	}

	@Override
	public void substituteVar(String varName, int value) {
	}

	@Override
	public void evaluateQuery(XTrace xtrace)  throws Exception{
	}

	@Override
	public void evaluateSpecialIndex(int current, int last) {
	}

}
