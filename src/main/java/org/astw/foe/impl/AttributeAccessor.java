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
package org.astw.foe.impl;

import java.sql.Timestamp;
import java.util.HashMap;

import org.astw.foe.IndexExp;
import org.astw.foe.NonNumExp;
import org.astw.foe.NumExp;
import org.astw.util.Const;
import org.astw.util.XESUtil;
import org.astw.util.Const.ValueType;
import org.astw.util.Const.XESDataType;
import org.deckfour.xes.model.XAttribute;
import org.deckfour.xes.model.XAttributeBoolean;
import org.deckfour.xes.model.XAttributeContinuous;
import org.deckfour.xes.model.XAttributeDiscrete;
import org.deckfour.xes.model.XAttributeLiteral;
import org.deckfour.xes.model.XAttributeTimestamp;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XTrace;

/**
 * @author Ario Santoso (santoso.ario@gmail.com)
 */
public class AttributeAccessor implements NonNumExp, NumExp{

	private IndexExp index;
	private String attName;

	private ValueType valueType;//numeric or non-numeric
	private XESDataType xesDataType;//the corresponding data type based on the given XLog

	private double attNumValue = Const.UNDEFINED_NUM_VAL;
	private String attNonNumValue = Const.UNDEFINED_NON_NUM_VAL;
	
	private boolean typed; // true if this attribute accessor has received a XES data type (after the check with event log)
	private boolean isEvaluated = false;
	
 	public AttributeAccessor(IndexExp index, String attName){
		this(index, attName, ValueType.UNKNOWN, XESDataType.XES_UNKNOWN, 
				Const.UNDEFINED_NUM_VAL, Const.UNDEFINED_NON_NUM_VAL, false, false);
	}

	public AttributeAccessor(
			IndexExp index, String attName, ValueType eeType, XESDataType xesDataType, 
			double attNumValue, String attNonNumValue, boolean typed, boolean isEvaluated){
		
		this.index = index;
		this.attName = attName;
		this.valueType = eeType;
		this.xesDataType = xesDataType;
		this.attNumValue = attNumValue;
		this.attNonNumValue = attNonNumValue;
		this.typed = typed;
		this.isEvaluated = isEvaluated;
	}

	public IndexExp getIndex() {
		return index;
	}

	public String getAttName() {
		return attName;
	}

	public String toString(){
		
		if(this.isEvaluated == true && this.valueType == ValueType.NUMERIC_EXP){
		
			if(this.xesDataType == XESDataType.XES_DATE_TIME){
				
				return new Timestamp((long) this.attNumValue).toString();
				
			}else
				return this.attNumValue+"";
			
		}else if(this.isEvaluated == true && this.valueType == ValueType.NON_NUMERIC_EXP){

			if(this.xesDataType == XESDataType.XES_STRING){
				
				return "\""+this.attNonNumValue+"\"";
				
			}else
				return this.attNonNumValue;
			
		}else
			return "e["+index.toString()+"]."+this.attName;
	}

	@Override
	public String prettyFormat(String tab) {
		if(this.isEvaluated == true && this.valueType == ValueType.NUMERIC_EXP){
			
			if(this.xesDataType == XESDataType.XES_DATE_TIME){
				
				return new Timestamp((long) this.attNumValue).toString();
				
			}else
				return this.attNumValue+"";
			
		}else if(this.isEvaluated == true && this.valueType == ValueType.NON_NUMERIC_EXP){

			if(this.xesDataType == XESDataType.XES_STRING){
				
				return tab+"\""+this.attNonNumValue+"\"";
				
			}else
				return tab+this.attNonNumValue;
			
		}else
			return tab+"e["+index.toString()+"]."+this.attName;
	}

	public AttributeAccessor clone(){
		return new AttributeAccessor(
					this.index.clone(), new String(this.attName), this.valueType, this.xesDataType,
					this.attNumValue, this.attNonNumValue, this.typed, this.isEvaluated);
	}
	
	public ValueType getEeType() {
		return valueType;
	}
	
	public XESDataType getXESDataType() {

		return xesDataType;
	}
	
	public boolean isTyped(){
		return this.typed;
	}
	
	public boolean isEvaluated() {
		return isEvaluated;
	}
	

	/**
	 * To assign the corresponding type
	 */
	@Override
	public void assignQueryXESDataType(HashMap<String, XESDataType> attributeNamesAndTypes) throws Exception {

//		System.out.println(attributeNamesAndTypes.keySet());
		
		if(attributeNamesAndTypes.containsKey(this.attName)){
			
			this.xesDataType = attributeNamesAndTypes.get(this.attName);
		}else{
			
			this.xesDataType = XESDataType.XES_UNKNOWN;
			throw new Exception("The type of the attribute '"+this.attName+"' is UNKNOWN");
		}

		//NOTE: we need to do this check later because 'attributeNamesAndTypes' might gives XES_UNKNOWN
		//if(this.xesDataType == XESDataType.XES_UNKNOWN){
			//this.typed = false;
		//}else{
			this.typed = true;
		//}
	
		this.valueType = Const.getValueType(this.xesDataType);
	}

	@Override
	public ValueType ensureTypeCorrectness() throws Exception {
		
		if(this.valueType == ValueType.UNKNOWN)
			throw new Exception("Value type of '"+this.toString()+"' is UNKNOWN");
		else	
			return this.valueType;
	}

	@Override
	public ValueType getValueType() {

		return this.valueType;
	}

	////////////////////////////////////////////////////////////////////////////////////
	@Override
	public double getNumericValue() {

		return this.attNumValue;
	}

	@Override
	public String getNonNumericValue() {

		return this.attNonNumValue;
	}
	////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void substituteVar(String varName, int value) {
		
		this.index.substituteVar(varName, value);
	}

	@Override
	public void evaluateQuery(XTrace xtrace) throws Exception{

//		System.out.println("Evaluating the query!");
		
		if(this.valueType == Const.ValueType.UNKNOWN)
			throw new Exception(
					"The type of attribute accessor '"+this.toString()+"' is still UNKNOWN! "
				+ 	"Note: You should use the method 'assignQueryXESDataType' before evaluating the query "
				+ 	"in order to discover the corresponding data type of the corresponding attribute based on the "
				+ 	"given XES Log");
		
		this.isEvaluated = true;

		int idx = this.index.getValue();
		
		if(idx <=0 || idx > xtrace.size()){
			
			this.attNonNumValue = Const.UNDEFINED_NON_NUM_VAL;
			this.attNumValue = Const.UNDEFINED_NUM_VAL;
			return;
		}
		
		if(!xtrace.get(idx-1).getAttributes().containsKey(this.attName)){
			
			this.attNonNumValue = Const.UNDEFINED_NON_NUM_VAL;
			this.attNumValue = Const.UNDEFINED_NUM_VAL;
			return;
		}
		
		XAttribute xatt = xtrace.get(idx-1).getAttributes().get(this.attName);
		
		if(XESUtil.getXESDataType(xatt) == this.xesDataType){
			
			if(this.valueType == Const.ValueType.NUMERIC_EXP){
				
				if(xatt instanceof XAttributeTimestamp){
					this.attNumValue = ((XAttributeTimestamp) xatt).getValueMillis();
					
				}else if(xatt instanceof XAttributeDiscrete){
					this.attNumValue = ((XAttributeDiscrete) xatt).getValue();
				
				}else if(xatt instanceof XAttributeContinuous){
					this.attNumValue = ((XAttributeContinuous) xatt).getValue();
				
				}else
					this.attNumValue = Const.UNDEFINED_NUM_VAL;
			}
			
			this.attNonNumValue = xatt.toString();
			
		}else{
			
			this.attNonNumValue = Const.UNDEFINED_NON_NUM_VAL;
			this.attNumValue = Const.UNDEFINED_NUM_VAL;
			
		}
		
//		System.out.println("Evaluating the query Finish! "+ this.isEvaluated+" "+this.valueType);
//		System.out.println(this.attNonNumValue);
//		System.out.println(this.attNumValue);

	}

	@Override
	public void evaluateSpecialIndex(int current, int last) {

		this.index.evaluateSpecialIndex(current, last);
	}

}
