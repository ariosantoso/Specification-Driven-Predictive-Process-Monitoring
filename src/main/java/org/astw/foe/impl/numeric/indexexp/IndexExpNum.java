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
import org.astw.util.Const.ValueType;
import org.astw.util.Const.XESDataType;
import org.deckfour.xes.model.XTrace;

/**
 * @author Ario Santoso (santoso.ario@gmail.com)
 */

public class IndexExpNum implements IndexExp{

	private int component;
	private ValueType valueType = ValueType.NUMERIC_EXP;//numeric or non-numeric

	public IndexExpNum(String component) throws Exception{

		//TODO: some input sanity check on the input component
		//- it must be positive natural number
		
		try{
			this.component = Integer.parseInt(component);		
			if(this.component <= 0)
				throw new Exception("An index must be a positive integer");
		}catch(Exception e){
			throw new Exception("An index must be a positive integer");
		}
	}

	public int getComponent() {
		return component;
	}

	public void setComponent(int component) {
		this.component = component;
	}
	
	public int getValue(){
		return this.component;
	}
	
	public String toString(){
        return this.component+"";
    }

	@Override
	public String prettyFormat(String tab) {
        return tab+""+this.component;
	}

	public IndexExpNum clone(){
		try {
			return new IndexExpNum(new String(this.component+""));
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
	}

	@Override
	public XESDataType getXESDataType() {

		return XESDataType.XES_INT;
	}
}
