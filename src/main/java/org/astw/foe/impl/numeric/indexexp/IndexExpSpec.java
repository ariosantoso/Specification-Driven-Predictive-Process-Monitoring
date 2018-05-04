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

import org.astw.foe.IndexExp;
import org.astw.util.Const;
import org.astw.util.Const.SpecialIndexType;
import org.astw.util.Const.ValueType;
import org.astw.util.Const.XESDataType;
import org.deckfour.xes.model.XTrace;

/**
 * @author Ario Santoso (santoso.ario@gmail.com)
 */

public class IndexExpSpec implements IndexExp{

	private SpecialIndexType component;
	private int instantiatedValue = Integer.MIN_VALUE;
	private boolean isInstantiated = false;
	private ValueType valueType = ValueType.NUMERIC_EXP;//numeric or non-numeric

	public IndexExpSpec(SpecialIndexType component){

		this.component = component;
	}

	public IndexExpSpec(SpecialIndexType component, boolean isInstantiated, int instantiatedValue){

		this.component = component;
		this.isInstantiated = isInstantiated;
		this.instantiatedValue = instantiatedValue;
	}

	public SpecialIndexType getComponent() {
		return component;
	}

	public int getInstantiatedValue() {
		return instantiatedValue;
	}

	public int getValue(){
		return this.instantiatedValue;
	}

	public boolean isInstantiated() {
		return isInstantiated;
	}

	public String toString(){
		if(this.isInstantiated)
			return this.instantiatedValue+"";
		else
			return Const.specialIndexTypeToString(this.component);
    }

	@Override
	public String prettyFormat(String tab) {
		if(this.isInstantiated)
			return tab+this.instantiatedValue+"";
		else
			return tab+Const.specialIndexTypeToString(this.component);
	}

	public IndexExpSpec clone(){
		return new IndexExpSpec(this.component, this.isInstantiated, this.instantiatedValue);
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
		return this.getValue();
	}

	@Override
	public void substituteVar(String varName, int value) {
	}

	@Override
	public void evaluateQuery(XTrace xtrace)  throws Exception{
	}

	@Override
	public void evaluateSpecialIndex(int current, int last) {

		if(this.component == SpecialIndexType.CURR){
			
			this.instantiatedValue = current;
			this.isInstantiated = true;
			
		}else if(this.component == SpecialIndexType.LAST){
			
			this.instantiatedValue = last;
			this.isInstantiated = true;
		}
	}

	@Override
	public XESDataType getXESDataType() {

		return XESDataType.XES_INT;
	}
}
