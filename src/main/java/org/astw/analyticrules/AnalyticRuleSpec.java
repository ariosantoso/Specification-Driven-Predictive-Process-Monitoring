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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.astw.util.Const;
import org.astw.util.TargetExpUtil;
import org.astw.util.XESUtil;
import org.astw.util.Const.PredictorModelType;
import org.astw.util.Const.PrefLengthType;
import org.astw.util.Const.ValueType;
import org.astw.util.Const.XESDataType;
import org.astw.util.encoder.AttributeEncodingInfo;
import org.astw.util.encoder.OneHotEncodingV2Info;
import org.astw.util.encoder.Encoder.EncodingType;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;

/**
 * @author Ario Santoso (santoso.ario@gmail.com)
 */
public class AnalyticRuleSpec {

	//{<condition1, target1>, <condition2, target2>, ..., otherwiseTarget}
	//{rule1, rule2, ..., otherwiseTarget}
	
	//////////////////////////////////////////////////////////////////	
	// Some UI related information
	//////////////////////////////////////////////////////////////////	
	
	private String ruleName = "Default Rule Name (please change)";
	private String ruleDescription = "Default Rule Description (please change)";
	private String predictionTaskName = "Default Prediction Task (please change)";
	
	private PredictorModelType predictorModelType;
	private ArrayList<EncodingType> encodingType;
	private ArrayList<OneHotEncodingV2Info> oneHotEncodingV2Info;
	private ArrayList<AttributeEncodingInfo> attributeEncodingInfo;

	//////////////////////////////////////////////////////////////////	
	// END OF Some UI related information
	//////////////////////////////////////////////////////////////////	

	private Set<RuleSpec> rules;
	private TargetExpression otherwiseTarget = null;
	private ValueType targetValueType = ValueType.UNKNOWN;
	private XLog xlog = null;
	private HashMap<String, XESDataType> xesAttDataType; 

	private boolean initialized = false;

	
	
	
	
	
	public AnalyticRuleSpec(){
		
		this(new HashSet<RuleSpec>());
	}

	public AnalyticRuleSpec(Set<RuleSpec> rules){
		
		this.rules = rules;
	}
	
	public void addRuleSpec(ConditionExpression condition, TargetExpression target){

		this.addRuleSpec(new RuleSpec(condition, target));
	}
	
	public void addRuleSpec(RuleSpec ruleSpec){
		
		this.rules.add(ruleSpec);
	}

	public Set<RuleSpec> getRules(){
		return this.rules;
	}
	
	public void setOtherwiseTarget(TargetExpression otherwiseTarget){
		
		this.otherwiseTarget = otherwiseTarget;
	}

	public TargetExpression getOtherwiseTarget(){
		
		return this.otherwiseTarget;
	}

	/**
	 * Initialize this rule w.r.t. the given Event Log. This step is needed because of the following reason:
	 * 
	 * <ul>
	 * 	<li> 
	 * 	We need to discover the corresponding data types for each attribute names that is used in the rules. 
	 * 	This discovery should be based on the given Event Log.
	 * 	</li>
	 * 	<li> 
	 *	This step should also detect the inconsistencies between attribute data type (e.g., concept:name as String and Integer)
	 * 	</li>
	 * 	<li> 
	 *	We should also check whether all 'Target Expression' have the same ValueType (Numeric/NonNumeric) or not.
	 * 	</li>
	 * 	<li> 
	 * 	We need to check whether each trace only satisfy at most 1 rule 
	 * 	(i.e., to check whether the rules is suitable for the given log)
	 * 	</li>
	 * </ul>
	 * 
	 * @param xlog
	 */
	public void init(XLog xlog) throws Exception{


		this.xesAttDataType = XESUtil.getAttributeNamesTypesMap(xlog);

		ValueType valType = null;
		
		//discover the corresponding type and also check the target type mismatch
		for(RuleSpec r: rules){
			r.init(this.xesAttDataType);
			
			ValueType tempValType = r.getTargetValueType();
			
			if(valType != null && valType != tempValType)
				throw new Exception("There are some target expressions that have different value types");
			
			valType = tempValType;
		}
		
		/*
		 * TODO: implement the checker that checks whether the rule is well-defined w.r.t. the log.
		 * and throws an exception if it is not the case (In this case, the rule is not "good/safe" w.r.t. 
		 * the given Log). 
		 */

		this.xlog = xlog;
		this.targetValueType = valType;
		this.initialized = true;
	}
	
	public ValueType getTargetValueType() {
		return targetValueType;
	}

	////////////////////////////////////////////////////////////
	// UI related Info
	////////////////////////////////////////////////////////////
	
	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleDescription() {
		return ruleDescription;
	}

	public void setRuleDescription(String ruleDescription) {
		this.ruleDescription = ruleDescription;
	}

	public String getPredictionTaskName() {
		return predictionTaskName;
	}

	public void setPredictionTaskName(String predictionTaskName) {
		this.predictionTaskName = predictionTaskName;
	}
	
	public ArrayList<EncodingType> getEncodingType() {
		return encodingType;
	}

	public void setEncodingType(ArrayList<EncodingType> encodingType) {
		this.encodingType = encodingType;
	}

	public ArrayList<OneHotEncodingV2Info> getOneHotEncodingV2Info() {
		return oneHotEncodingV2Info;
	}

	public void setOneHotEncodingV2Info(ArrayList<OneHotEncodingV2Info> oneHotEncodingV2Info) {
		this.oneHotEncodingV2Info = oneHotEncodingV2Info;
	}

	public ArrayList<AttributeEncodingInfo> getAttributeEncodingInfo() {
		return attributeEncodingInfo;
	}

	public void setAttributeEncodingInfo(ArrayList<AttributeEncodingInfo> attributeEncodingInfo) {
		this.attributeEncodingInfo = attributeEncodingInfo;
	}

	public PredictorModelType getPredictorModelType() {
		return predictorModelType;
	}

	public void setPredictorModelType(PredictorModelType predictorModelType) {
		this.predictorModelType = predictorModelType;
	}

	
	////////////////////////////////////////////////////////////
	// END OF UI related Info
	////////////////////////////////////////////////////////////

	public String computeTargetValue(XTrace xtrace, int consideredPrefixLength) throws Exception{
			
		if(this.initialized == false)
			throw new Exception("Please initialise the rules with the desired XES Event Logs before using this rules!");
		
		for(RuleSpec r : rules){
			
			/*
			 * if the rule 'r' is satisfied by the <xtrace, prefixLength> 
			 * then compute the corresponding target value;
			 */
						
			//At this point it is safe that this is the only applicable rule (because the 'init' should check it)
			if(r.satisfy(xtrace, consideredPrefixLength) == true){
				
				return r.computeTargetValue(xtrace, consideredPrefixLength);
			}
//			else{
//				System.out.println("masuk: "+xtrace.size()+", "+consideredPrefixLength);
//			}
		}
		
		//at this step, it means that there is no rule applicable for the given Trace's prefix
		//thus we need to compute the default target value
		if(this.otherwiseTarget != null)
			return TargetExpUtil.computeTargetExpressionValue(this.otherwiseTarget, xtrace, consideredPrefixLength);
		else
			return "";
	}

	public RuleInstance computeRuleInstance(XTrace xtrace, int consideredPrefixLength) throws Exception{

		if(this.initialized == false)
			throw new Exception("Please initialise the rules with the desired XES Event Logs before using this rules!");

		String targetValue = computeTargetValue(xtrace, consideredPrefixLength);
		if(!targetValue.equals(""))
			return new RuleInstance(xtrace, targetValue, consideredPrefixLength, this.targetValueType);
		else
			return null;
	}

	////////////////////////////////////////////////////////////
	
	public AnalyticRulesInstance computeAnalyticRulesInstanceAllPrefixLength(int shortestPrefixLength) throws Exception{
		
		if(this.initialized == false || this.xlog == null)
			throw new Exception("Please initialise the rules with the desired XES Event Logs before using this rules!");

		if(shortestPrefixLength > 1){
			
			AnalyticRulesInstance ari = new AnalyticRulesInstance(this.targetValueType, this.xlog);
			for(XTrace xtrace : this.xlog){
				for(int ii = shortestPrefixLength; ii < xtrace.size(); ii++){
					RuleInstance ri = computeRuleInstance(xtrace, ii);					
					if(ri != null){
						
						ari.add(ri);
					}
				}
			}
					
			if(ari.size() <= 0)
				throw new Exception("No analytic rules are produced");
			
			return ari;
		}else{
			AnalyticRulesInstance ari = new AnalyticRulesInstance(this.targetValueType, this.xlog);
			for(XTrace xtrace : this.xlog){
				RuleInstance ri = computeRuleInstance(xtrace, xtrace.size());
				if(ri != null)
					ari.add(ri);
			}
					
			if(ari.size() <= 0)
				throw new Exception("No analytic rules are produced");
			
			return ari;
		}
	}

	public AnalyticRulesInstance computeAnalyticRulesInstanceAllPrefixLength(int shortestPrefixLength, XLog toBeEncoded) throws Exception{
		
		if(this.initialized == false || this.xlog == null)
			throw new Exception("Please initialise the rules with the desired XES Event Logs before using this rules!");

		if(shortestPrefixLength > 1){
			
			AnalyticRulesInstance ari = new AnalyticRulesInstance(this.targetValueType, this.xlog);
			for(XTrace xtrace : toBeEncoded){
				for(int ii = shortestPrefixLength; ii < xtrace.size(); ii++){
					RuleInstance ri = computeRuleInstance(xtrace, ii);					
					if(ri != null){
						
						ari.add(ri);
					}
				}
			}
					
			if(ari.size() <= 0)
				throw new Exception("No analytic rules are produced");
			
			return ari;
		}else{
			AnalyticRulesInstance ari = new AnalyticRulesInstance(this.targetValueType, this.xlog);
			for(XTrace xtrace : toBeEncoded){
				RuleInstance ri = computeRuleInstance(xtrace, xtrace.size()-1);
				if(ri != null)
					ari.add(ri);
			}
					
			if(ari.size() <= 0)
				throw new Exception("No analytic rules are produced");
			
			return ari;
		}
	}

	public AnalyticRulesInstance computeAnalyticRulesInstanceWithFixPrefixLength(int prefixLength, XLog toBeEncoded) throws Exception{
		
		if(this.initialized == false || this.xlog == null)
			throw new Exception("Please initialise the rules with the desired XES Event Logs before using this rules!");

		if(prefixLength > 1){
			
			AnalyticRulesInstance ari = new AnalyticRulesInstance(this.targetValueType, this.xlog);
			for(XTrace xtrace : toBeEncoded){
				RuleInstance ri = computeRuleInstance(xtrace, prefixLength);					
				if(ri != null){
					
					ari.add(ri);
				}
			}
					
			if(ari.size() <= 0)
				throw new Exception("No analytic rules are produced");
			
			return ari;
		}else{
			
			throw new Exception("Prefix length must be > 1");
//			AnalyticRulesInstance ari = new AnalyticRulesInstance(this.targetValueType, this.xlog);
//			for(XTrace xtrace : toBeEncoded){
//				RuleInstance ri = computeRuleInstance(xtrace, xtrace.size());
//				if(ri != null)
//					ari.add(ri);
//			}
//					
//			if(ari.size() <= 0)
//				throw new Exception("No analytic rules are produced");
//			
//			return ari;
		}
	}

	public AnalyticRulesInstance computeAnalyticRulesInstanceWithFixPrefixLength(PrefLengthType prefixType, XLog toBeEncoded) throws Exception{
		
		if(this.initialized == false || this.xlog == null)
			throw new Exception("Please initialise the rules with the desired XES Event Logs before using this rules!");

			AnalyticRulesInstance ari = new AnalyticRulesInstance(this.targetValueType, this.xlog);
			for(XTrace xtrace : toBeEncoded){
				
				int prefixLength = Const.getPrefixLength(xtrace.size(), prefixType);
				
				if(prefixLength > 1){
					
					RuleInstance ri = computeRuleInstance(xtrace, prefixLength);					
					if(ri != null){
						
						ari.add(ri);
					}
				}
			}
					
			if(ari.size() <= 0)
				throw new Exception("No analytic rules are produced");
			
			return ari;
	}

	
}
