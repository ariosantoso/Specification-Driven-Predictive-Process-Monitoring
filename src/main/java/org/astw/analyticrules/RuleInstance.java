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
package org.astw.analyticrules;

import java.util.Set;

import org.astw.util.Const;
import org.astw.util.Const.ValueType;
import org.astw.util.encoder.AttributeEncodingInfo;
import org.astw.util.encoder.Encoder;
import org.astw.util.encoder.OneHotEncodingV2Info;
import org.astw.util.encoder.Encoder.EncodingType;
import org.deckfour.xes.model.XTrace;

import weka.core.Instances;

/**
 * This rule instance keeps the information about how to map 
 * a "Trace's prefix" into the corresponding "target value"
 * 
 * @author Ario Santoso (santoso.ario@gmail.com)
 * 
 */
public class RuleInstance {

	private XTrace xtrace;
	private String targetValue;
	private int consideredPrefixLength; // the index of the last element in the considered prefix of the trace 'xtrace' in this rule
	private ValueType targetValueType;
	
	public RuleInstance(XTrace xtrace, String targetValue, int consideredPrefixLength, ValueType targetValueType){
		
		this.xtrace = xtrace;
		this.targetValue = targetValue;
		this.consideredPrefixLength = consideredPrefixLength;
		this.targetValueType = targetValueType;
	}


	public XTrace getXtrace() {
		return xtrace;
	}


	public String getTargetValue() {
		return targetValue;
	}


	public int getConsideredPrefixLength() {
		return consideredPrefixLength;
	}


	public ValueType getTargetValueType() {
		return targetValueType;
	}
	
	public String toString(){
		
//		return this.xtrace+".subSeq[1, "+this.consideredPrefixLength+"] ~~> "+this.targetValue;
		return "Trace (with "+this.xtrace.size()+" events, and "+this.targetValueType+" target value type)"
				+ ".subSeq[1, "+this.consideredPrefixLength+"] ~~> "+this.targetValue;
	}
	
	
	public Instances computeWEKAInstances(
			String wekaInstancesName, EncodingType[] encodingType, OneHotEncodingV2Info[] oneHotEncodingV2Info, AttributeEncodingInfo[] attEncodingInfo, Set<String> allPossibleTargetValue) throws Exception{
			
			//for the classifier, set the target class
			if(this.targetValueType == Const.ValueType.NON_NUMERIC_EXP)
				return getWEKAInstancesForNonNumeric(wekaInstancesName, 
						encodingType, oneHotEncodingV2Info, attEncodingInfo,
						allPossibleTargetValue);

			//for the regressor
			if(this.targetValueType == Const.ValueType.NUMERIC_EXP)
				return getWEKAInstancesForNumeric(wekaInstancesName, 
						encodingType, oneHotEncodingV2Info, attEncodingInfo);
			
			return null;
	}
	
	private Instances getWEKAInstancesForNonNumeric(
			String wekaInstancesName, 
			EncodingType[] encodingType, OneHotEncodingV2Info[] oneHotEncodingV2Info, AttributeEncodingInfo[] attEncodingInfo,
			Set<String> allPossibleTargetValue) throws Exception{
		
		//Create the template for the WEKA Instances
		Instances wekaInstances = Encoder.createInstancesTemplateV2(
				1, wekaInstancesName, encodingType, 
				allPossibleTargetValue, oneHotEncodingV2Info, attEncodingInfo);
		
		//System.out.println("rule.getConsideredPrefixLength(): "+rule.getConsideredPrefixLength());
		Encoder.encodeAnXTraceV2(
				wekaInstances, this.getXtrace(), this.getConsideredPrefixLength(), encodingType, 
				oneHotEncodingV2Info, attEncodingInfo, 
				this.getTargetValue());
		
		wekaInstances.setClass(wekaInstances.attribute(Encoder.ATTR_TARGET));
		
		return wekaInstances;
	}

	private Instances getWEKAInstancesForNumeric(
			String wekaInstancesName, EncodingType[] encodingType, 
			OneHotEncodingV2Info[] oneHotEncodingV2Info, AttributeEncodingInfo[] attEncodingInfo) throws Exception{
		
		//Create the template for the WEKA Instances
		Instances wekaInstances = Encoder.createInstancesTemplateNumericTarget(
				1, wekaInstancesName, encodingType, oneHotEncodingV2Info, attEncodingInfo);

		Encoder.encodeAnXTraceNumericTarget(
				wekaInstances, this.getXtrace(), this.getConsideredPrefixLength(), encodingType, 
				oneHotEncodingV2Info, attEncodingInfo, 
				Double.parseDouble(this.getTargetValue()));
			
		wekaInstances.setClass(wekaInstances.attribute(Encoder.ATTR_TARGET));
		
		return wekaInstances;
	}
	
	
}
