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
package org.astw.foe.impl.numeric.indexexp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.astw.foe.IndexExp;
import org.astw.util.Const.ValueType;
import org.astw.util.Const.XESDataType;
import org.deckfour.xes.model.XTrace;

/**
 * @author Ario Santoso (santoso.ario@gmail.com)
 */

public class IndexExpVar implements IndexExp{

	private String component;
	private int instantiatedValue = Integer.MIN_VALUE;
	private boolean isInstantiated = false;

	private ValueType valueType = ValueType.NUMERIC_EXP;//numeric or non-numeric

	public IndexExpVar(String component){

		
		this.component = component;
		this.isInstantiated = false;
	}

	public IndexExpVar(String component, int instantiatedValue, boolean isInstantiated){

		this.component = component;
		this.instantiatedValue = instantiatedValue;
		this.isInstantiated = isInstantiated;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public int getIdxValue(){
		return this.instantiatedValue;
	}
	
	public String toString(){
		if(this.isInstantiated)
			return this.instantiatedValue+"";
		else
			return this.component;
	}

	@Override
	public String prettyFormat(String tab) {
		if(this.isInstantiated)
			return tab+this.instantiatedValue+"";
		else
			return tab+this.component;
	}
	
	public IndexExpVar clone(){
		return new IndexExpVar(this.component, this.instantiatedValue, this.isInstantiated);
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
		return this.getIdxValue();
	}

	public boolean isInstantiated() {
		return isInstantiated;
	}

	@Override
	public void substituteVar(String varName, int value) {

		if(this.component.equals(varName)){
			
			this.instantiatedValue = value;
			this.isInstantiated = true;
		}
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

		return XESDataType.XES_INT;
	}
	
	@Override
	public Set<String> getVariables(){

		HashSet<String> vars = new HashSet<String>();
		vars.add(this.component);
		
		return vars;
	}


}
