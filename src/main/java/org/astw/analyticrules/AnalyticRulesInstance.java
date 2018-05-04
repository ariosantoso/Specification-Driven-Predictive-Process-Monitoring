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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.astw.util.Const;
import org.astw.util.XESUtil;
import org.astw.util.Const.ValueType;
import org.astw.util.encoder.AttributeEncodingInfo;
import org.astw.util.encoder.Encoder;
import org.astw.util.encoder.OneHotEncodingV2Info;
import org.astw.util.encoder.Encoder.EncodingType;
import org.deckfour.xes.model.XLog;

import weka.core.Instances;

/**
 * It keeps the information about how to map 
 * each "Trace prefix" into its corresponding "target value".
 * 
 * @author Ario Santoso (santoso.ario@gmail.com)
 */
public class AnalyticRulesInstance {

	//default XES attribute name reference for one hot encoding
	private static String XES_DEFAULT_ONE_HOT_ATTR = "concept:name";

	private Set<RuleInstance> rules;
	private ValueType targetValueType = ValueType.UNKNOWN;
	private Set<String> targetValues = null;
	private XLog oriXlog = null;

	public AnalyticRulesInstance(ValueType targetValueType, XLog xlog){
		
		this(new HashSet<RuleInstance>(), targetValueType, null, xlog);
	}

	public AnalyticRulesInstance(Set<RuleInstance> rules, ValueType targetValueType, Set<String> targetValues, XLog xlog){
		
		this.rules = rules;
		this.targetValueType = targetValueType;
		this.targetValues = targetValues;
		this.oriXlog = xlog;
	}
	
	public void add(RuleInstance ri){
		
//		System.out.println("DEBUGA: ADD RULE INSTANCE KE ARI "+ ri);
		
		this.rules.add(ri);
		if(targetValues == null){
			targetValues = new HashSet<String>();
		}
		this.targetValues.add(ri.getTargetValue());
	}
	
	public Set<RuleInstance> getRules() {
		return rules;
	}
	
	public ValueType getTargetValueType() {
		return targetValueType;
	}
	
	public int size(){
		return this.rules.size();
	}
	
	/**
	 * get all possible targetvalues
	 * @return
	 */
	public Set<String> getAllPossibleTargetValue() {
		
		int ii = 0;
		if(this.targetValues == null){
			this.targetValues = new HashSet<String>();
			
			for(RuleInstance ri : this.rules){
				
				this.targetValues.add(ri.getTargetValue());
				ii++;
			}
		}
		
//		System.out.println("DEBUGA: SEMUA NILAI: "+this.targetValues.size()+ ", ii = "+ii+", "+this.targetValues);
		
		return this.targetValues;
	}

	/**
	 * set all possible targetvalues
	 * 
	 * This might be needed in case we don't want to extract the target values from all RuleInstances that belong to this object.
	 * This might be usefull for the evaluation in WEKA, in which not all target values are present in the given testing set
	 * 
	 * @return
	 */
	public void setAllPossibleTargetValue(Set<String> targetValues) {
		
		this.targetValues = targetValues;
	}

	public String toString(){
		
		System.out.println("ARI: "+this.targetValueType);

		
		StringBuilder sb = new StringBuilder();
		int ii = 1;
		for(RuleInstance r: rules){
			sb.append(ii+": "+r.toString());

			if(ii < rules.size())
				sb.append("\n");
			ii++;
		}
		
		sb.append("\n\nSummary:\n");
		sb.append("- There are "+this.rules.size()+" rule(s)\n");
		sb.append("- The target value (rule) type is "+this.targetValueType+"\n");
		
		return sb.toString();
	}
	
	public XLog getXlog() {
		return oriXlog;
	}

	public Instances computeWEKAInstances(
			String wekaInstancesName, EncodingType[] encodingType, OneHotEncodingV2Info[] oneHotEncodingV2Info, AttributeEncodingInfo[] attEncodingInfo) throws Exception{
			
		
			//for the classifier, set the target class
			if(this.targetValueType == Const.ValueType.NON_NUMERIC_EXP)
				return getWEKAInstancesForNonNumeric(wekaInstancesName, encodingType, oneHotEncodingV2Info, attEncodingInfo);

			//for the regressor
			if(this.targetValueType == Const.ValueType.NUMERIC_EXP)
				return getWEKAInstancesForNumeric(wekaInstancesName, encodingType, oneHotEncodingV2Info, attEncodingInfo);
		
			return null;
	}

	private Instances getWEKAInstancesForNonNumeric(String wekaInstancesName, EncodingType[] encodingType, 
			OneHotEncodingV2Info[] oneHotEncodingV2Info, AttributeEncodingInfo[] attEncodingInfo) throws Exception{
		
		//Create the template for the WEKA Instances
		Instances wekaInstances = Encoder.createInstancesTemplateV2(
				this.oriXlog.size(), wekaInstancesName, encodingType, 
				this.getAllPossibleTargetValue(), oneHotEncodingV2Info, attEncodingInfo);
		
		//add the encoding of each rule instance into the WEKA Instances
		for (RuleInstance rule: this.rules) {
			
			//System.out.println("rule.getConsideredPrefixLength(): "+rule.getConsideredPrefixLength());
			Encoder.encodeAnXTraceV2(
					wekaInstances, rule.getXtrace(), rule.getConsideredPrefixLength(), encodingType, 
					oneHotEncodingV2Info, attEncodingInfo, 
//					maxTraceLength, attrNameForOneHotEncoding, setOfAllPossibleValuesOfConceptNameAtt, 
					rule.getTargetValue());
		}
		
		wekaInstances.setClass(wekaInstances.attribute(Encoder.ATTR_TARGET));
		
		return wekaInstances;
	}

	private Instances getWEKAInstancesForNumeric(String wekaInstancesName, EncodingType[] encodingType, 
			OneHotEncodingV2Info[] oneHotEncodingV2Info, AttributeEncodingInfo[] attEncodingInfo) throws Exception{
		
		//Create the template for the WEKA Instances
		Instances wekaInstances = Encoder.createInstancesTemplateNumericTarget(
				this.oriXlog.size(), wekaInstancesName, encodingType, oneHotEncodingV2Info, attEncodingInfo);
		
		//add the encoding of each rule instance into the WEKA Instances
		for (RuleInstance rule: this.rules) {
			
			Encoder.encodeAnXTraceNumericTarget(
					wekaInstances, rule.getXtrace(), rule.getConsideredPrefixLength(), encodingType, 
					oneHotEncodingV2Info, attEncodingInfo, 
					Double.parseDouble(rule.getTargetValue()));
		}
		
		wekaInstances.setClass(wekaInstances.attribute(Encoder.ATTR_TARGET));
		
		return wekaInstances;
	}


	
	////////////////////////////////////////////////////////////////////////
	//OLD VERSION - better not use it
	////////////////////////////////////////////////////////////////////////
	public Instances computeWEKAInstances(
		String wekaInstancesName, EncodingType[] encodingType, String attrNameForOneHotEncoding) throws Exception{
		
		//for the classifier, set the target class
		if(this.targetValueType == Const.ValueType.NON_NUMERIC_EXP)
			return getWEKAInstancesForNonNumeric(wekaInstancesName, encodingType, attrNameForOneHotEncoding);

		//for the regressor
		if(this.targetValueType == Const.ValueType.NUMERIC_EXP)
			throw new Exception("Not yet implemented!!");
		
		return null;
	}

	private Instances getWEKAInstancesForNonNumeric(String wekaInstancesName, EncodingType [] encodingType, String attrNameForOneHotEncoding) throws Exception{
		
		//--------------------------------------------------------
//		boolean useOneHotEncoding = false;
//		
//		//check if one hot encoding is chosen
//		for(EncodingType et : encodingType){
//			
//			if(et == EncodingType.OneHot){
//				
//				useOneHotEncoding = true;
////				if(attrNameForOneHotEncoding == null)
////					throw new Exception("One hot encoding is chosen but the attribute name for one hot encoding is not specified");
//			}			
//		}
		//--------------------------------------------------------

		if(attrNameForOneHotEncoding == null || attrNameForOneHotEncoding.isEmpty())
			attrNameForOneHotEncoding = XES_DEFAULT_ONE_HOT_ATTR;
		
//		System.out.println("this.xlog: "+this.xlog);

		// These things are needed only for one hot encoding of concept:name
		int maxTraceLength = XESUtil.getMaxTracesLength(this.oriXlog);
		ArrayList<String> setOfAllPossibleValuesOfConceptNameAtt = 
			XESUtil.getAllPossibleAttValues(this.oriXlog, attrNameForOneHotEncoding);//dapet semua nilai dari attribute dengan nama "attrName"
		// END OF These things are needed only for one hot encoding of concept:name

		//Create the template for the WEKA Instances
		Instances wekaInstances= Encoder.createInstancesTemplate(this.oriXlog.size(), wekaInstancesName, encodingType, 
				this.getAllPossibleTargetValue(),
				maxTraceLength, setOfAllPossibleValuesOfConceptNameAtt.size());
		
		//add the encoding of each rule instance into the WEKA Instances
		for (RuleInstance rule: this.rules) {
			
//			System.out.println("rule.getConsideredPrefixLength(): "+rule.getConsideredPrefixLength());
			
			Encoder.encodeAnXTrace(
					wekaInstances, rule.getXtrace(), rule.getConsideredPrefixLength(), encodingType, 
					maxTraceLength, attrNameForOneHotEncoding, setOfAllPossibleValuesOfConceptNameAtt, 
					rule.getTargetValue());
		}
		
		wekaInstances.setClass(wekaInstances.attribute(Encoder.ATTR_TARGET));
		
		return wekaInstances;
	}
	////////////////////////////////////////////////////////////////////////
	//END OF OLD VERSION - better not use it
	////////////////////////////////////////////////////////////////////////

}
