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
import org.astw.util.Const.Mode;
import org.astw.util.Const.PredictorModelType;
import org.astw.util.Const.SpecialOutputFormat;
import org.astw.util.Const.ValueType;
import org.astw.util.PythonHelper;
import org.astw.util.encoder.AttributeEncodingInfo;
import org.astw.util.encoder.Encoder;
import org.astw.util.encoder.Encoder.EncodingType;
import org.astw.util.encoder.OneHotEncodingV2Info;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;

import gnu.trove.map.hash.TObjectIntHashMap;
import jep.Jep;
import jep.NDArray;
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
	private String predModelTypePy = ""; 
	private XLog xlog = null;
	private Mode mode = Mode.PYTHON;

	private TObjectIntHashMap<String> availableClfModelNames; 
	private TObjectIntHashMap<String> availableRegModelNames;
	private ArrayList<String> availableClfModelConfigs;
	private ArrayList<String> availableRegModelConfigs;
	
	//private Jep jep;
	
	///////////////////////////////////////////////////	
	// END OF We do not serialize these
	///////////////////////////////////////////////////
	
	private String id;
	private String modelPrefixName = "model";
	private String modelName = modelPrefixName+id;
	private String pyLoc = System.getProperty("user.home")+"/sdprom/";	
	private String predictorFileName;
	private String modelType;
	
	
	//prediction model information - For Weka Mode
	private Classifier model;
	private ArrayList<String> classValue = new ArrayList<String>();
	private Set<String> targetValues;
	private SpecialOutputFormat specialOutputFormat = SpecialOutputFormat.NO_SPECIAL_FORMAT;
	//END OF prediction model information - For Weka Mode

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

	
	
	//---------------------------------------------------------------------------
	//for the weka mode
	//---------------------------------------------------------------------------
	
	public boolean initWekaMode(XLog xlog, ArrayList<String> allAttributeNames) throws Exception{
		
		if(runningPhase == true)
			throw new Exception("This method can't be called during the running phase");

		this.mode = Mode.WEKA;

		
		if(this.analRule != null){

			//the usual initialization of the analytic rule specification
			analRule.init(xlog);
			
			this.xlog = xlog;
			this.targetValueType = analRule.getTargetValueType();//get target value type (numeric or non-numeric)
			
			//init the default encoding (just in case the user does not want to choose the desired encoding)
			this.defaultEncodingInit(xlog, allAttributeNames);

			this.predModelType = PredictorModelType.RandomForest;//just set the default model (in case the user doesn't choose)

			this.initialized = true;
		}
		
		return this.initialized;
	}

	public void buildPredictorWekaMode() throws Exception{
		
		if(runningPhase == true)
			throw new Exception("This method can't be called during the running phase");
		
		String trainInstanceCodeName = predModelType+"-"+System.currentTimeMillis();
		
		AnalyticRulesInstance ari = this.analRule.computeAnalyticRulesInstanceAllPrefixLength(2, this.xlog);
		Instances trainingInstances = ari.computeWEKAInstances(trainInstanceCodeName, encodingType, oneHotEncodingV2Info, attEncodingInfo);

		this.model = Const.getModelInstance(this.predModelType);
		this.model.buildClassifier(trainingInstances);
		
		if(this.targetValueType == Const.ValueType.NON_NUMERIC_EXP){
			//this is just to get the mapping between the 'number' that represents a 
			//particular class and the corresponding class name. This will be needed when we do the prediction
			//since the output of the prediction is only the number of the class. Hence, we need to decode it back
			//into the categorical name
			
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

	//---------------------------------------------------------------------------
	//END OF for the weka mode
	//---------------------------------------------------------------------------

	

	//---------------------------------------------------------------------------
	//for the python mode
	//---------------------------------------------------------------------------

	public boolean initPyMode(XLog xlog, ArrayList<String> allAttributeNames, String id,
			TObjectIntHashMap<String> availableClfModelNames, TObjectIntHashMap<String> availableRegModelNames,
			ArrayList<String> availableClfModelConfigs, ArrayList<String> availableRegModelConfigs) throws Exception{
		
		if(runningPhase == true)
			throw new Exception("This method can't be called during the running phase");

		this.mode = Mode.PYTHON;
		this.id = id;
		//this.jep = jep;
		this.availableClfModelNames = availableClfModelNames;
		this.availableRegModelNames = availableRegModelNames;
		this.availableClfModelConfigs = availableClfModelConfigs; 
		this.availableRegModelConfigs = availableRegModelConfigs; 
		
		if(this.analRule != null){
			
			//the usual initialization of the analytic rule specification
			analRule.init(xlog);
			
			this.xlog = xlog;
			this.targetValueType = analRule.getTargetValueType();//get target value type (numeric or non-numeric)
			
			//init the default encoding (just in case the user does not want to choose the desired encoding)
			this.defaultEncodingInit(xlog, allAttributeNames);

			//Set the default model (in case the user doesn't choose)
			if(this.targetValueType == ValueType.NON_NUMERIC_EXP){
				
				this.predModelTypePy = this.availableClfModelNames.keySet().iterator().next();
				
			}else if(this.targetValueType == ValueType.NUMERIC_EXP){
				
				this.predModelTypePy = this.availableRegModelNames.keySet().iterator().next();
				
			}else{
				throw new Exception("Invalid Analytic Rule Specification");
			}
			
			this.initialized = true;
		}
		
		return this.initialized;
	}

	public void buildPredictorPyMode(Jep jep) throws Exception{
		
		if(runningPhase == true)
			throw new Exception("This method can't be called during the running phase");
		
		//System.out.println("buildPredictorPyMode");
		
		//Build the training instance
		String trainInstanceCodeName = predModelTypePy+"-"+System.currentTimeMillis();
		AnalyticRulesInstance ari = this.analRule.computeAnalyticRulesInstanceAllPrefixLength(2, this.xlog);
		Instances trainingInstances = 
			ari.computeWEKAInstances(trainInstanceCodeName, encodingType, oneHotEncodingV2Info, attEncodingInfo);

		//set the value of the variable 'nr_features'
		jep.set("nr_features", trainingInstances.numAttributes() - 1);

		//System.out.println("buildPredictorPyMode: "+trainInstanceCodeName);

		if(this.targetValueType == Const.ValueType.NON_NUMERIC_EXP){//Build Classifier

			//System.out.println("buildPredictorPyMode - NON_NUMERIC_EXP ");

			// convert training data for Python
			long[] featuresTrain = PythonHelper.getFeaturesFromInstances(trainingInstances);
			String[] targetTrain = PythonHelper.getNominalTargetFromInstances(trainingInstances);
			NDArray<long[]> ndFeatures = new NDArray<>(featuresTrain, trainingInstances.numInstances(), 
															trainingInstances.numAttributes() - 1);

			//System.out.println("buildPredictorPyMode - END prepare data for python");

			String modelConfig = this.availableClfModelConfigs.get(this.availableClfModelNames.get(this.predModelTypePy));
			this.modelName = modelPrefixName+this.id;

			//System.out.println("buildPredictorPyMode - Build the model");

			//Build the model
			System.out.println("\n------------------------------------------------------------------------------------------------------");
			System.out.println("Training: "+modelConfig+"\n(Please wait! Depending on the model and the data, it may take some times)");
			jep.set("training", ndFeatures);
			jep.set("target", targetTrain);
			jep.eval(modelName+" = "+modelConfig);
		    jep.eval(modelName+".fit(training, target)");			
			this.targetValues = ari.getAllPossibleTargetValue(); //needed for input encoding in the predictor

			//System.out.println("buildPredictorPyMode - Save the model");
			//save the model
			this.modelType = (String) jep.getValue("type("+modelName+").__name__");
			this.predictorFileName = pyLoc + modelName +"x"+ System.currentTimeMillis()+".sav";

			if(this.modelType.equals("KerasClassifier")){
				
				//if type(model).__name__ == 'KerasClassifier':
		        //model.model.save(outputModelFile + m[0]+ '.hd5')
				this.predictorFileName += ".hd5";
		    	jep.eval(modelName+".model.save('"+this.predictorFileName+"', 'wb')");
		    	
		    	
		    	//save the mapping between the class number and the class label name
			    jep.eval("target_classes = "+modelName+".classes_.tolist()");
			    this.classValue = (ArrayList<String>)jep.getValue("target_classes");
			    //System.out.println("jep.getValue(\"target_classes\"): "+jep.getValue("target_classes"));
			    //System.out.println("jep.getValue(\"type(target_classes)\"): "+jep.getValue("type(target_classes)"));
			    //System.out.println("this.classValue: "+this.classValue);
			    
				
			}else jep.eval("pickle.dump("+modelName+", open('"+this.predictorFileName+"', 'wb'))");
			
			System.out.println("\nModel type: "+this.modelType );
			System.out.println("Temporarily save the model at: "+this.predictorFileName);
			System.out.println("------------------------------------------------------------------------------------------------------\n");

		    //JUST DEBUGGING
			/*
			    jep.eval("predicted = "+modelName+".predict(training)");			
			    System.out.println("modelName: "+modelName);
			    System.out.println("type("+modelName+"): "+jep.getValue("type("+modelName+")"));
			    System.out.println("len(training): "+jep.getValue("len(training)"));
			    System.out.println("len(target): " + jep.getValue("len(target)"));
			    System.out.println("len(predicted): " +jep.getValue("len(predicted)"));
	
			    int numRes = (int)((long)jep.getValue("len(predicted)"));
			    
			    for(int ii = 0 ; ii < numRes; ii++)
			    	System.out.print(jep.getValue("predicted["+ii+"]")+", ");
			    
			    System.out.println("\n");
			*/
		    //END OF JUST DEBUGGING
		    			
		}else if(this.targetValueType == Const.ValueType.NUMERIC_EXP){//Build Regressor
			
			// convert training data for Python
			long[] featuresTrain = PythonHelper.getFeaturesFromInstances(trainingInstances);
			double[] targetTrain = PythonHelper.getNumericTargetFromInstances(trainingInstances);
			NDArray<long[]> ndFeatures = new NDArray<>(featuresTrain, trainingInstances.numInstances(), 	
															trainingInstances.numAttributes() - 1);
			NDArray<double[]> ndTarget = new NDArray<>(targetTrain);

			String modelConfig = this.availableRegModelConfigs.get(this.availableRegModelNames.get(this.predModelTypePy));
			this.modelName = modelPrefixName+this.id;

			//Build the model
			System.out.println("\n------------------------------------------------------------------------------------------------------");
			System.out.println("Training: "+modelConfig+"\n(Please wait! Depending on the model and the data, it may take some times)");
			jep.set("training", ndFeatures);
			jep.set("target", ndTarget);
			jep.eval(modelName+" = "+modelConfig);
		    jep.eval(modelName+".fit(training, target)");			
		    
			//save the model
		    this.modelType = (String) jep.getValue("type("+modelName+").__name__");
			this.predictorFileName = pyLoc + modelName +"x"+ System.currentTimeMillis()+".sav";
			
			if(this.modelType.equals("KerasRegressor")){
				
				//if type(model).__name__ == 'KerasRegressor':
		        //model.model.save(outputModelFile + m[0]+ '.hd5')
				this.predictorFileName += ".hd5";
				jep.eval(modelName+".model.save('"+this.predictorFileName+"', 'wb')");
				
			}else jep.eval("pickle.dump("+modelName+", open('"+this.predictorFileName+"', 'wb'))");
			
			System.out.println("\nModelType: "+this.modelType );
			System.out.println("Temporarily save the model at: "+this.predictorFileName);
			System.out.println("------------------------------------------------------------------------------------------------------\n");
		}
		
		//////////////////////////////
		// JUST DEBUGGING
		//////////////////////////////
		/*
		System.out.println("DEBUGA: trainingInstances.numClasses(): "+trainingInstances.numClasses());
		System.out.println("DEBUGA: trainingInstances.classIndex(): "+trainingInstances.classIndex());
		System.out.println("DEBUGA: trainingInstances.classAttribute(): "+trainingInstances.classAttribute());
		System.out.println("DEBUGA: trainingInstances.classAttribute().numValues(): "+trainingInstances.classAttribute().numValues());
		System.out.println("DEBUGA: trainingInstances.classAttribute().value(0): "+trainingInstances.classAttribute().value(0));
		System.out.println("DEBUGA: trainingInstances.classAttribute().value(1): "+trainingInstances.classAttribute().value(1));
		System.out.println("DEBUGA: trainingInstances.classAttribute().value(2): "+trainingInstances.classAttribute().value(2));

		System.out.println("Finish building: "+this.model.toString());

		Evaluation evaluation = new Evaluation(trainingInstances);
		evaluation.evaluateModel(this.model, trainingInstances);
		System.out.println(evaluation.toSummaryString());
		System.out.println(evaluation.toClassDetailsString());
		*/
		//////////////////////////////
		// END OF JUST DEBUGGING
		//////////////////////////////
	}

	//---------------------------------------------------------------------------
	//END OF for the python mode
	//---------------------------------------------------------------------------

	
	///////////////////////////////////////////////////////////////////////////
	// END OF TRAINING PHASE METHODS
	///////////////////////////////////////////////////////////////////////////

	
	
	///////////////////////////////////////////////////////////////////////////
	// RUNNING PHASE METHODS
	///////////////////////////////////////////////////////////////////////////
	
	//For Weka Mode
	public PredictionResults predictWk(XTrace xtrace){
		
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
	
	//For Python Mode
	public PredictionResults predictPy(XTrace xtrace, Jep jep){
				
		
//		try {
//			this.jep.close();
//		} catch (JepException e2) {
//			e2.printStackTrace();
//		}
		
//		Jep jep;
//		try {
//			System.out.println("re-init JEP");
//			jep = new Jep();
//			System.out.println("Finish re-init JEP");
//			System.out.println("import pickle");
//			jep.eval("import pickle");
//			System.out.println("finish import pickle");
//			
//		} catch (Exception e1) {
//			e1.printStackTrace();
//			System.out.println("There is an error with the connection to Python. Program Aborted");
//			return new PredictionResults(this.predictionTaskName, this.predictionTaskDescription, "");
//		}		

		//get the prediction value
		String predictionResult = "";
		String instanceName = this.predictionTaskName+System.currentTimeMillis();

		//System.out.println("Before Enter Non Num Exp");

		if(this.targetValueType == Const.ValueType.NON_NUMERIC_EXP){
			//System.out.println("Enter Non Num Exp");

			
			Instances encodedTrace = Encoder.createInstancesTemplateV2(1, instanceName, encodingType, 
					targetValues, oneHotEncodingV2Info, attEncodingInfo);

			//System.out.println("Enter Non Num Exp 2");

			String dummy = this.targetValues.iterator().next();
			
			Encoder.encodeAnXTraceV2(
					encodedTrace, xtrace, xtrace.size(), encodingType, 
					oneHotEncodingV2Info, attEncodingInfo, dummy);

			//System.out.println("Enter Non Num Exp 3");
			//System.out.println("DUMMY: "+dummy);

			encodedTrace.setClass(encodedTrace.attribute(Encoder.ATTR_TARGET));

			System.out.println("\nDoing Prediction (Please wait) . . .\n");

			try {
				//System.out.println("Transform features for Python 1");
				
				long[] inputFeatures = PythonHelper.getFeaturesFromInstance(encodedTrace.firstInstance());
				
				//System.out.println("Transform features for Python 2");

				NDArray<long[]> inpFeatures = new NDArray<>(inputFeatures, 1, encodedTrace.numAttributes() - 1);

				//System.out.println("END OF Transform features for Python");

				//set input features
				jep.set("inputFeatures", inpFeatures);

				//System.out.println("END OF Set input features");

				//load model and predict result
				
				
				if(this.modelType.equals("KerasClassifier")){
					
					jep.eval("predictionModel = load_model('"+this.predictorFileName+"')");
					jep.eval("predicted = predictionModel.predict_classes(inputFeatures)");	
				    
					/*
				    System.out.println("jep.getValue(\"predicted\"): "+jep.getValue("predicted"));
				    System.out.println("jep.getValue(\"type(predicted)\"): "+jep.getValue("type(predicted)"));
				    System.out.println("jep.getValue(\"predicted[0]\"): "+jep.getValue("predicted[0]"));
				    System.out.println("jep.getValue(\"type(predicted[0])\"): "+jep.getValue("type(predicted[0])"));
				    System.out.println("jep.getValue(\"predicted.tolist()\"): "+jep.getValue("predicted.tolist()"));
				    System.out.println("jep.getValue(\"type(predicted.tolist())\"): "+jep.getValue("type(predicted.tolist())"));
				    System.out.println("jep.getValue(\"predicted.tolist()[0]\"): "+jep.getValue("predicted.tolist()[0]"));
				    System.out.println("jep.getValue(\"type(predicted.tolist()[0])\"): "+jep.getValue("type(predicted.tolist()[0])"));
				    System.out.println("jep.getValue(\"predicted.tolist()[0][0]\"): "+jep.getValue("predicted.tolist()[0][0]"));
				    System.out.println("jep.getValue(\"type(predicted.tolist()[0][0])\"): "+jep.getValue("type(predicted.tolist()[0][0])"));
				    
				    predictionResult = ((ArrayList) jep.getValue("predicted.tolist()[0]")).get(0).toString();
				    predictionResult = ""+((long) jep.getValue("predicted.tolist()[0][0]"));
				    */
					
				    long classIdx = ((long) jep.getValue("predicted.tolist()[0][0]"));
				    predictionResult = this.classValue.get(Integer.parseInt(classIdx+""));
				    
				}else{
					
					//System.out.println("exec -> "+"predictionModel = pickle.load(open('"+this.predictorFileName+"', 'rb'))");
					jep.eval("predictionModel = pickle.load(open('"+this.predictorFileName+"', 'rb'))");
					jep.eval("predicted = predictionModel.predict(inputFeatures)");	
				    predictionResult = (String) jep.getValue("predicted[0]");
				}
				
				//System.out.println("END OF Predict");


				//System.out.println("END OF get predict result");

			    //JUST DEBUGGING
			    /*
			    System.out.println("Predictor DEBUGGING - modelName: "+modelName);
			    System.out.println("Predictor DEBUGGING - type("+modelName+"): "+jep.getValue("type("+modelName+")"));
			    System.out.println("Predictor DEBUGGING - len(predicted): " +jep.getValue("len(predicted)"));

			    int numRes = (int)((long)jep.getValue("len(predicted)"));
			    for(int ii = 0 ; ii < numRes; ii++)
			    	System.out.println("Predictor DEBUGGING - prediction result: "+jep.getValue("predicted["+ii+"]"));
			    */
			    //END OF JUST DEBUGGING
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				
				System.out.println("\n. . .Done \n");

			}
			
		}else if(this.targetValueType == Const.ValueType.NUMERIC_EXP){
			
			//Create the template for the WEKA Instances
			Instances encodedTrace = Encoder.createInstancesTemplateNumericTarget(
					1, instanceName, encodingType, oneHotEncodingV2Info, attEncodingInfo);

			Encoder.encodeAnXTraceNumericTarget(
					encodedTrace, xtrace, xtrace.size(), encodingType, 
					oneHotEncodingV2Info, attEncodingInfo, 0);
				
			encodedTrace.setClass(encodedTrace.attribute(Encoder.ATTR_TARGET));

			System.out.println("\nDoing Prediction (Please wait) . . .\n");

			try {
				
				long[] inputFeatures = PythonHelper.getFeaturesFromInstance(encodedTrace.firstInstance());
				NDArray<long[]> inpFeatures = new NDArray<>(inputFeatures, 1, encodedTrace.numAttributes() - 1);

				//set input features
				jep.set("inputFeatures", inpFeatures);
				
				//load model and predict result
				if(this.modelType.equals("KerasRegressor")){
				
					jep.eval("predictionModel = load_model('"+this.predictorFileName+"')");
				    jep.eval("predicted = predictionModel.predict(inputFeatures)");	
				    
				    /*
				    System.out.println("jep.getValue(\"predicted\"): "+jep.getValue("predicted"));
				    System.out.println("jep.getValue(\"type(predicted)\"): "+jep.getValue("type(predicted)"));
				    System.out.println("jep.getValue(\"predicted[0]\"): "+jep.getValue("predicted[0]"));
				    System.out.println("jep.getValue(\"type(predicted[0])\"): "+jep.getValue("type(predicted[0])"));
				    System.out.println("jep.getValue(\"predicted.tolist()\"): "+jep.getValue("predicted.tolist()"));
				    System.out.println("jep.getValue(\"type(predicted.tolist())\"): "+jep.getValue("type(predicted.tolist())"));
				    System.out.println("jep.getValue(\"predicted.tolist()[0]\"): "+jep.getValue("predicted.tolist()[0]"));
				    System.out.println("jep.getValue(\"type(predicted.tolist()[0])\"): "+jep.getValue("type(predicted.tolist()[0])"));
				    System.out.println("jep.getValue(\"predicted.tolist()[0][0]\"): "+jep.getValue("predicted.tolist()[0][0]"));
				    System.out.println("jep.getValue(\"type(predicted.tolist()[0][0])\"): "+jep.getValue("type(predicted.tolist()[0][0])"));
				    
				    predictionResult = ((ArrayList) jep.getValue("predicted.tolist()[0]")).get(0).toString();
				    */
				    
				    predictionResult = ""+((double) jep.getValue("predicted.tolist()[0][0]"));
				
				}else {
					
					jep.eval("predictionModel = pickle.load(open('"+this.predictorFileName+"', 'rb'))");
				    jep.eval("predicted = predictionModel.predict(inputFeatures)");	
				    predictionResult = ""+(double) jep.getValue("predicted[0]");
				}
				

				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				
				System.out.println("\n. . .Done \n");

			}
		} 

//		try {
//			jep.close();
//		} catch (JepException e) {
//			e.printStackTrace();
//		}
		
		return new PredictionResults(this.predictionTaskName, this.predictionTaskDescription, formatOutput(predictionResult));
	}
	

	///////////////////////////////////////////////////////////////////////////
	// END OF RUNNING PHASE METHODS
	///////////////////////////////////////////////////////////////////////////

	
	public String formatOutput(String output){
		
		//System.out.println("result asli: "+output);
		
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

	public String getPredModelTypePy() {
		return predModelTypePy;
	}

	public void setPredModelTypePy(String predModelTypePy) {
		this.predModelTypePy = predModelTypePy;
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



	public Set<String> getAvailableClfModelNames() {
		return availableClfModelNames.keySet();
	}

	public Set<String> getAvailableRegModelNames() {
		return availableRegModelNames.keySet();
	}

	public Mode getMode() {
		return mode;
	}

	
	///////////////////////////////////////////////////////////////////////////
	// END OF SETTER & GETTER
	///////////////////////////////////////////////////////////////////////////

}
