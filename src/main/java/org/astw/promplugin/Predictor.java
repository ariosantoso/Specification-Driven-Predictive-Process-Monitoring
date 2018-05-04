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
package org.astw.promplugin;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Set;

import org.astw.analyticrules.AnalyticRuleSpec;
import org.astw.analyticrules.AnalyticRulesInstance;
import org.astw.analyticrules.RuleSpec;
import org.astw.util.Const;
import org.astw.util.Const.PredictorModelType;
import org.astw.util.Const.SpecialOutputFormat;
import org.astw.util.Const.ValueType;
import org.astw.util.encoder.AttributeEncodingInfo;
import org.astw.util.encoder.Encoder;
import org.astw.util.encoder.OneHotEncodingV2Info;
import org.astw.util.encoder.Encoder.EncodingType;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;

import weka.classifiers.Classifier;
import weka.core.Instances;

/**
 * @author Ario Santoso (santoso.ario@gmail.com)
 * 
 */

public class Predictor {

	///////////////////////////////////////////////////
	// We do not serialize these
	///////////////////////////////////////////////////
	
	private AnalyticRuleSpec analRule = null;
	private PredictorModelType predModelType = PredictorModelType.RandomForest; //the default is RandomForest
	private XLog xlog = null;
	///////////////////////////////////////////////////	
	// END OF We do not serialize these
	///////////////////////////////////////////////////
	

	//model information
	private Classifier model;
	private ArrayList<String> classValue = new ArrayList<String>();
	private Set<String> targetValues;
	private SpecialOutputFormat specialOutputFormat = SpecialOutputFormat.NO_SPECIAL_FORMAT;
	//END OF model information

	//Prediction related information
	private String predictionTaskName = "";
	private String predictionTaskDescription = "";
	//END OF Prediction related information
	
	//encoding information
	private EncodingType[] encodingType;
	private OneHotEncodingV2Info[] oneHotEncodingV2Info;
	private AttributeEncodingInfo[] attEncodingInfo;
	//END OF encoding information
	
	private boolean initialized = false;
	private boolean runningPhase = false;
	private ValueType targetValueType;
	
	
	///////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	///////////////////////////////////////////////////////////////////////////

	//------------------------------------------------------------------------
	// This constructor is used during the BUILDING/TRAINING phase
	//------------------------------------------------------------------------
	public Predictor(AnalyticRuleSpec analRules){
		
		this.analRule = analRules;
		this.initialized = false;
		this.runningPhase = false;
		this.predictionTaskName = analRules.getPredictionTaskName();
		this.predictionTaskDescription = analRules.getRuleDescription();
	}
	
	//------------------------------------------------------------------------
	// This constructor is used during the RE-LOAD/RUNNING phase
	//------------------------------------------------------------------------
	public Predictor(Classifier model, 
			EncodingType[] encodingType, OneHotEncodingV2Info[] oneHotEncodingV2Info, AttributeEncodingInfo[] attEncodingInfo){
		
		//NOTE: ntar bikin object lain SerializedPredictor yang nyimpen info2 yang bisa di serialize aja, trus pas re-load, dari situ instantiate Predictor ini pake constructor ini
		//NOTE: most likely blom smua dimasukin ke constructor ini
		
		this.model = model;
		this.encodingType = encodingType;
		this.oneHotEncodingV2Info = oneHotEncodingV2Info;
		this.attEncodingInfo = attEncodingInfo;
		this.initialized = true;
		this.runningPhase = true;
		
		this.predModelType = null;
		this.analRule = null;
	}

	///////////////////////////////////////////////////////////////////////////
	// END OF CONSTRUCTORS
	///////////////////////////////////////////////////////////////////////////

	
	
	///////////////////////////////////////////////////////////////////////////
	// TRAINING PHASE METHODS
	///////////////////////////////////////////////////////////////////////////

	public boolean init(XLog xlog, ArrayList<String> allAttributeNames) throws Exception{
		
		if(runningPhase == true)
			throw new Exception("This method can't be called during the running phase");

		
		if(this.analRule != null){
			analRule.init(xlog);
			this.xlog = xlog;
			this.initialized = true;
			this.targetValueType = analRule.getTargetValueType();
			this.defaultEncodingInit(xlog, allAttributeNames);
			this.predModelType = PredictorModelType.RandomForest;
		}
		
		return this.initialized;
	}
	
	public void defaultEncodingInit(XLog xlog, ArrayList<String> allAttributeNames){
		
		//Choose Encoding Type
		this.encodingType = new EncodingType[1];
		this.encodingType[0] = EncodingType.AttEnc;
		
		//Create One Hot Encoding V2 Info
		this.oneHotEncodingV2Info = new OneHotEncodingV2Info[0];
		//END OF Create One Hot Encoding V2 Info
		
		//Create Attribute Encoding Info
		this.attEncodingInfo = new AttributeEncodingInfo[allAttributeNames.size()];
		
		int ii = 0;
		for(String attName: allAttributeNames){
			this.attEncodingInfo[ii] = new AttributeEncodingInfo(attName, xlog);
			ii++;
		}				
	}
	
	public void addRuleSpec(RuleSpec ruleSpec) throws Exception{

		if(runningPhase == true)
			throw new Exception("This method can't be called during the running phase");

		if(this.analRule != null)
			this.analRule.addRuleSpec(ruleSpec);
	}
	
	public void buildPredictor() throws Exception{
		
		if(runningPhase == true)
			throw new Exception("This method can't be called during the running phase");
		
		String trainInstanceCodeName = predModelType+"-"+System.currentTimeMillis();
		
		AnalyticRulesInstance ari = this.analRule.computeAnalyticRulesInstanceAllPrefixLength(2, this.xlog);
		Instances trainingInstances = ari.computeWEKAInstances(trainInstanceCodeName, encodingType, oneHotEncodingV2Info, attEncodingInfo);

		this.model = Const.getModelInstance(this.predModelType);
		this.model.buildClassifier(trainingInstances);
		
		if(this.targetValueType == Const.ValueType.NON_NUMERIC_EXP){
			
			int numOfClass = trainingInstances.classAttribute().numValues();
			
			for(int ii = 0; ii < numOfClass; ii++)
				this.classValue.add(ii, trainingInstances.classAttribute().value(ii));
			
			this.targetValues = ari.getAllPossibleTargetValue();
		}
		
		//////////////////////////////
		// JUST DEBUGGING
		//////////////////////////////

//		System.out.println("DEBUGA: trainingInstances.numClasses(): "+trainingInstances.numClasses());
//		System.out.println("DEBUGA: trainingInstances.classIndex(): "+trainingInstances.classIndex());
//		System.out.println("DEBUGA: trainingInstances.classAttribute(): "+trainingInstances.classAttribute());
//		System.out.println("DEBUGA: trainingInstances.classAttribute().numValues(): "+trainingInstances.classAttribute().numValues());
//		System.out.println("DEBUGA: trainingInstances.classAttribute().value(0): "+trainingInstances.classAttribute().value(0));
//		System.out.println("DEBUGA: trainingInstances.classAttribute().value(1): "+trainingInstances.classAttribute().value(1));
//		System.out.println("DEBUGA: trainingInstances.classAttribute().value(2): "+trainingInstances.classAttribute().value(2));

//		System.out.println("Finish building: "+this.model.toString());
//
//		Evaluation evaluation = new Evaluation(trainingInstances);
//		evaluation.evaluateModel(this.model, trainingInstances);
//		System.out.println(evaluation.toSummaryString());
//		System.out.println(evaluation.toClassDetailsString());

		//////////////////////////////
		// END OF JUST DEBUGGING
		//////////////////////////////
	}

	///////////////////////////////////////////////////////////////////////////
	// END OF TRAINING PHASE METHODS
	///////////////////////////////////////////////////////////////////////////

	
	
	///////////////////////////////////////////////////////////////////////////
	// RUNNING PHASE METHODS
	///////////////////////////////////////////////////////////////////////////
	
	public PredictionResults predict(XTrace xtrace){
		
		//TODO: get the prediction value
		
		String predictionResult = "";
		String instanceName = this.predictionTaskName+System.currentTimeMillis();
		
		if(this.targetValueType == Const.ValueType.NON_NUMERIC_EXP){
			
			Instances encodedTrace = Encoder.createInstancesTemplateV2(1, instanceName, encodingType, 
					targetValues, oneHotEncodingV2Info, attEncodingInfo);
			
			String dummy = this.targetValues.iterator().next();
			
			Encoder.encodeAnXTraceV2(
					encodedTrace, xtrace, xtrace.size(), encodingType, 
					oneHotEncodingV2Info, attEncodingInfo, dummy);
			
			//System.out.println("DUMMY: "+dummy);

			encodedTrace.setClass(encodedTrace.attribute(Encoder.ATTR_TARGET));

			try {
				double predVal = this.model.classifyInstance(encodedTrace.firstInstance());
				predictionResult = ""+this.classValue.get((int)predVal);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(this.targetValueType == Const.ValueType.NUMERIC_EXP){
			
			
			//Create the template for the WEKA Instances
			Instances encodedTrace = Encoder.createInstancesTemplateNumericTarget(
					1, instanceName, encodingType, oneHotEncodingV2Info, attEncodingInfo);

			Encoder.encodeAnXTraceNumericTarget(
					encodedTrace, xtrace, xtrace.size(), encodingType, 
					oneHotEncodingV2Info, attEncodingInfo, 0);
				
			encodedTrace.setClass(encodedTrace.attribute(Encoder.ATTR_TARGET));

			try {
				double predVal = this.model.classifyInstance(encodedTrace.firstInstance());
				predictionResult = ""+predVal;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 

		
		return new PredictionResults(this.predictionTaskName, this.predictionTaskDescription, formatOutput(predictionResult));
	}

	///////////////////////////////////////////////////////////////////////////
	// END OF RUNNING PHASE METHODS
	///////////////////////////////////////////////////////////////////////////

	
	public String formatOutput(String output){
		
		System.out.println("result asli: "+output);
		
		try{
		
			if(this.specialOutputFormat == SpecialOutputFormat.TIMESTAMP_STRING){
			
				try{
					long time = (long) Double.parseDouble(output);
					String timestamp = new Timestamp(time).toString();
					return timestamp;
				}catch(Exception e){
					
					return output;
				}
				
			}else if(this.specialOutputFormat == SpecialOutputFormat.HOURS){

				DecimalFormat f = new DecimalFormat("###,###.###");

//				return label + tab + 
//						String.format("%20s", f.format(time)) + " msec. / ~"+ 
//						String.format("%3.2f", (double)time/1000)+ " sec. / ~"+ 
//						String.format("%3.2f", (double)time/60000)+" min. ";

				try{
					double time = Double.parseDouble(output);
					
					long minutes = (long) (time/(1000*60));
					long hours = (long) (time/(1000*60*60));
					long days = (long) (time/(1000*60*60*24));
										
					return f.format(minutes)+" minutes/ "+f.format(hours)+" hours/ "+f.format(days)+" days";
				}catch(Exception e){
					
					return output;
				}
				
				
			}else{

				try{
					double number = Double.parseDouble(output);
					
					return String.format("%3.2f", number);
					
				}catch(Exception e){
					
					return output;
				}
			}
			
		}catch(Exception e){
			return output;
		}
	}
	
	
	
	///////////////////////////////////////////////////////////////////////////
	// SETTER & GETTER
	///////////////////////////////////////////////////////////////////////////

	
	public SpecialOutputFormat getSpecialOutputFormat() {
		return specialOutputFormat;
	}

	public void setSpecialOutputFormat(SpecialOutputFormat specialOutputFormat) {
		this.specialOutputFormat = specialOutputFormat;
	}

	public ValueType getTargetType() {
		return analRule.getTargetValueType();
	}
	
	public PredictorModelType getPredModelType() {
		return predModelType;
	}

	public void setPredModelType(PredictorModelType predModelType) {
		this.predModelType = predModelType;
	}

	public EncodingType[] getEncodingType() {
		return encodingType;
	}

	public void setEncodingType(EncodingType[] encodingType) {
		this.encodingType = encodingType;
	}

	public OneHotEncodingV2Info[] getOneHotEncodingV2Info() {
		return oneHotEncodingV2Info;
	}

	public void setOneHotEncodingV2Info(OneHotEncodingV2Info[] oneHotEncodingV2Info) {
		this.oneHotEncodingV2Info = oneHotEncodingV2Info;
	}

	public AttributeEncodingInfo[] getAttEncodingInfo() {
		return attEncodingInfo;
	}

	public void setAttEncodingInfo(AttributeEncodingInfo[] attEncodingInfo) {
		this.attEncodingInfo = attEncodingInfo;
	}

	public AnalyticRuleSpec getAnalRule() {
		return analRule;
	}
	
	public XLog getXlog() {
		return xlog;
	}	
		
	///////////////////////////////////////////////////////////////////////////
	// END OF SETTER & GETTER
	///////////////////////////////////////////////////////////////////////////

}
