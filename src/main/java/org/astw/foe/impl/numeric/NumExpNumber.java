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
package org.astw.foe.impl.numeric;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.astw.foe.NumExp;
import org.astw.util.Const.NumberType;
import org.astw.util.Const.ValueType;
import org.astw.util.Const.XESDataType;
import org.deckfour.xes.model.XTrace;

/**
 * @author Ario Santoso (santoso.ario@gmail.com)
 */
public class NumExpNumber implements NumExp{
    
	private String component;
	private NumberType numType;
	private XESDataType xesDataType = XESDataType.XES_DOUBLE;
	private ValueType valueType = ValueType.NUMERIC_EXP;//numeric or non-numeric
	private double value;
	
	public NumExpNumber(String component) throws Exception{
		this.component = component;
		
		if(component.equals("-0"))
			throw new Exception("Note: -0 is not a number! perhaps you want to have 0?");
		
		value = Double.parseDouble(component);
		
		if(component.contains(".")){
			this.numType = NumberType.DOUBLE;
			this.xesDataType = XESDataType.XES_DOUBLE;

		}else{
			this.numType = NumberType.INT;
			this.xesDataType = XESDataType.XES_LONG;
		}
	}
	
	public NumExpNumber(String component, NumberType numType, XESDataType xesDataType, ValueType valueType, double value) {

		this.component = component;
		this.numType = numType;
		this.xesDataType = xesDataType;
		this.valueType = valueType;
		this.value = value;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}
	
	public String toString(){
	
		return this.component;
	}

	@Override
	public String prettyFormat(String tab) {
		return tab+this.component;
	}
	
	public NumExpNumber clone(){
		return new NumExpNumber(this.component, this.numType, this.xesDataType, this.valueType, this.value);
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
	public double getNumericValue() {
		return value;
	}

	@Override
	public void substituteVar(String varName, int value) {
	}

	@Override
	public void evaluateQuery(XTrace xtrace)  throws Exception{
	}

	@Override
	public void evaluateAggregateFunction(XTrace xtrace) throws Exception{
	}

	@Override
	public void evaluateSpecialIndex(int current, int last) {
	}

	
	@Override
	public XESDataType getXESDataType() {

		return this.xesDataType;
	}
	
	@Override
	public Set<String> getVariables(){

		return new HashSet<String>();
	}

}
