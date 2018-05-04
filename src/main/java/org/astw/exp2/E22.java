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
package org.astw.exp2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import org.astw.analyticrules.AnalyticRuleSpec;
import org.astw.analyticrules.AnalyticRulesInstance;
import org.astw.analyticrules.ConditionExpression;
import org.astw.analyticrules.TargetExpression;
import org.astw.foe.Formula;
import org.astw.parser.foe.FOEFormulaParser;
import org.astw.parser.targetexp.TargetExpressionParser;
import org.astw.util.MeanBasedRegression;
import org.astw.util.Util;
import org.astw.util.WEKAUtil;
import org.astw.util.XESPreprocessor;
import org.astw.util.XESUtil;
import org.astw.util.Const.PrefLengthType;
import org.astw.util.encoder.AttributeEncodingInfo;
import org.astw.util.encoder.OneHotEncodingV2Info;
import org.astw.util.encoder.Encoder.EncodingType;
import org.deckfour.xes.factory.XFactory;
import org.deckfour.xes.factory.XFactoryNaiveImpl;
import org.deckfour.xes.model.XLog;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

/**
 * 
 * Ping-pong main experiment
 * 
 * @author Ario Santoso (santoso.ario@gmail.com)
 */
public class E22 {

//	private static String className = new Object(){}.getClass().getEnclosingClass().getCanonicalName();
	private static String classSimpleName = new Object(){}.getClass().getEnclosingClass().getSimpleName();
	
	private static String mainOutputFileName = "MainOutput-"+classSimpleName+".txt";
	private static String mainOutputFileFullPath = "MainOutput-"+classSimpleName+".txt";
	
	private static String configFileLoc = "config.txt";
	private static String inputLocation = "";
	private static String outputLocation = "";
	private static String outputModelLocation= "";
	private static String outputFolder = classSimpleName+"/";

	private static String inputLogName = "";
	private static String inputLogPath = "";

	private static FileWriter mainOutputWriter = null;
	
	//Log Stuff
	private static XLog xlogOri = null; //the original log after preprocessing but before the split into the train/test log
	private static XLog xlogTrain = null;
	private static XLog xlogTest = null;
	private static int percentToCut = 15;
	//END OF Log Stuff
	
	public static void main(String ar[]){

		try {

			String startTime = Util.getCurrentTimeStamp();

			//////////////////////////////////////////////////////////////////
			// Read Config
			//////////////////////////////////////////////////////////////////
			try {

				//read the configuration file
				BufferedReader reader = new BufferedReader(new FileReader(configFileLoc));
				inputLocation = reader.readLine();
				outputLocation = reader.readLine() + outputFolder;
				inputLogName = reader.readLine();
				reader.close();
			}catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
			
			//////////////////////////////////////////////////////////////////
			// END OF Read Config
			//////////////////////////////////////////////////////////////////
			
			//////////////////////////////////////////////////////////////////
			// Init
			//////////////////////////////////////////////////////////////////

			inputLogPath = inputLocation + inputLogName;
			outputModelLocation = outputLocation + "model/";
			mainOutputFileFullPath = outputLocation + mainOutputFileName;
			
			new File(outputLocation).mkdirs();
			new File(outputModelLocation).mkdirs();
			new File(mainOutputFileFullPath).createNewFile();
			
			mainOutputWriter = new FileWriter(mainOutputFileFullPath);

			System.out.println("Input Log Path: "+inputLogPath);
			System.out.println("Input Log Name: "+inputLogName);
			System.out.println("Output Location: " + outputLocation);
			System.out.println("Main Output File Name: " + mainOutputFileFullPath);

			mainOutputWriter.append("Start Time: "+startTime+"\n\n");
			mainOutputWriter.append("Input Log Path: "+inputLogPath+"\n");
			mainOutputWriter.append("Input Log Name: "+inputLogName+"\n");
			
			filterAndPrepareTheTrainTestLog(inputLogPath);
			
			//////////////////////////////////////////////////////////////////
			// END OF Init
			//////////////////////////////////////////////////////////////////

			
			//////////////////////////////////////////////////////////////////
			// BUILD THE MODEL AND EVALUATE IT
			//////////////////////////////////////////////////////////////////
			
			trainRegressionModel(xlogOri, xlogTrain, xlogTest);

			//////////////////////////////////////////////////////////////////
			// END OF BUILD THE MODEL AND EVALUATE IT
			//////////////////////////////////////////////////////////////////

			String endTime = Util.getCurrentTimeStamp();

			mainOutputWriter.append("\nEnd Time: "+endTime+"\n");
			mainOutputWriter.flush();
			mainOutputWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * Prepare the log (filter outliers, split, etc)
	 * @param logPath
	 */
	private static void filterAndPrepareTheTrainTestLog(String logPath){

		try {
			System.out.print("\n-------------------------------------------------------------------------------------------\n");
			System.out.print("filterAndPrepareTheTrainTestLog");
			System.out.print("\n-------------------------------------------------------------------------------------------\n");

			mainOutputWriter.append("\n-------------------------------------------------------------------------------------------\n");
			mainOutputWriter.append("filterAndPrepareTheTrainTestLog");
			mainOutputWriter.append("\n-------------------------------------------------------------------------------------------\n");

			
			XLog log = XESUtil.readXESLog(logPath);
			System.out.println("Event Log original size: "+log.size());
			mainOutputWriter.append("Event Log original size: "+log.size()+"\n");
			
			XLog filterredLog = XESPreprocessor.traceLengthFilter(log, percentToCut);
			System.out.println("Event Log size after filtering some traces: "+filterredLog.size());
			mainOutputWriter.append("Event Log size after filtering some traces: "+filterredLog.size()+"\n\n");
	
			//Sort traces in the Event logs based on the timestamp of the first event
			if (XESPreprocessor.isSortedBasedOnTimeStampFirstEvent(filterredLog)){
				
				System.out.println("The original Event log is already sorted based on the timestamp of the first event");
				mainOutputWriter.append("The original Event log is already sorted based on the timestamp of the first event"+"\n\n");
				
			}else{
				
				System.out.println("The original Event log is not sorted based on the timestamp of the first event. \n"
									+ "We will sort it now based on the timestamp of the first event");

				mainOutputWriter.append("The original Event log is not sorted based on the timestamp of the first event. \n"
									+ "We will sort it now based on the timestamp of the first event"+"\n\n");
				
				log = XESPreprocessor.sortXLogBasedOnTimeStamp(filterredLog);
				
				if (XESPreprocessor.isSortedBasedOnTimeStampFirstEvent(log)){
					
					System.out.println("Finish sorting the event log based on the timestamp of the first event");
					mainOutputWriter.append("Finish sorting the event log based on the timestamp of the first event"+"\n\n");
				}
			}
			//END OF Sort traces in the Event logs based on the timestamp of the first event

			//Split Event Logs into Train and Test
			int logSize = log.size();
			int numTrainingData = logSize * 2 / 3;
			XFactory fact = new XFactoryNaiveImpl();
			XLog trainLog = (XLog) log.clone();
			XLog testLog = fact.createLog();
			
			while (trainLog.size() > numTrainingData) {
				testLog.add(0, trainLog.get(trainLog.size()-1));
				trainLog.remove(trainLog.size()-1);
			}

			System.out.println("training log size: " + trainLog.size());
			mainOutputWriter.append("training log size: " + trainLog.size()+"\n");
			System.out.println("test log size: " + testLog.size());
			mainOutputWriter.append("test log size: " + testLog.size()+"\n");
			//END OF Split Event Logs
			
			System.out.println("max trace length - log: " + XESUtil.getMaxTracesLength(log));
			System.out.println("min trace length - log: " + XESUtil.getMinTracesLength(log));
			System.out.println("max trace length - trainLog: " + XESUtil.getMaxTracesLength(trainLog));
			System.out.println("min trace length - trainLog: " + XESUtil.getMinTracesLength(trainLog));
			System.out.println("max trace length - testLog: " + XESUtil.getMaxTracesLength(testLog));
			System.out.println("min trace length - testLog: " + XESUtil.getMinTracesLength(testLog));

			mainOutputWriter.append("max trace length - log: " + XESUtil.getMaxTracesLength(log)+"\n");
			mainOutputWriter.append("min trace length - log: " + XESUtil.getMinTracesLength(log)+"\n");
			mainOutputWriter.append("max trace length - trainLog: " + XESUtil.getMaxTracesLength(trainLog)+"\n");
			mainOutputWriter.append("min trace length - trainLog: " + XESUtil.getMinTracesLength(trainLog)+"\n");
			mainOutputWriter.append("max trace length - testLog: " + XESUtil.getMaxTracesLength(testLog)+"\n");
			mainOutputWriter.append("min trace length - testLog: " + XESUtil.getMinTracesLength(testLog)+"\n");

			xlogOri = log; //the original log after preprocessing but before the split into the train/test log
			xlogTrain = trainLog;
			xlogTest = testLog;

			
			System.out.print("\n-------------------------------------------------------------------------------------------\n");
			System.out.print("END OF filterAndPrepareTheTrainTestLog");
			System.out.print("\n-------------------------------------------------------------------------------------------\n");

			mainOutputWriter.append("\n-------------------------------------------------------------------------------------------\n");
			mainOutputWriter.append("END OF filterAndPrepareTheTrainTestLog");
			mainOutputWriter.append("\n-------------------------------------------------------------------------------------------\n");

			mainOutputWriter.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}	

	}

	private static void trainRegressionModel(XLog oriLog, XLog trainLog, XLog testLog){
	
		StringBuilder allSummary = new StringBuilder("");

		try {

			////////////////////////////////////////////////////////////////////
			//SETTING!!!
			////////////////////////////////////////////////////////////////////
			
				String trainInstanceCodeName = classSimpleName +"-Train-";
				String testInstanceCodeName = classSimpleName +"-Test-";
				
				//====================================================================
				// Analytic Rules
				//====================================================================

				AnalyticRuleSpec ars = genAnalyticRules_TimeUntilTheNextEvent();
				
				//====================================================================
				// END OF Analytic Rules
				//====================================================================
				
				
				
				//====================================================================
				// ENCODING CHOICE
				//====================================================================
				
				//Choose Encoding Type
				EncodingType [] encodingType = {
						EncodingType.OneHotV2, 
//						EncodingType.AttEnc,
						EncodingType.TimeSinceMidnight, 
						EncodingType.TimeSinceFDoW, 
						EncodingType.TimeDiffBefore, 
						};
		
				//Create One Hot Encoding V2 Info
				OneHotEncodingV2Info[] oneHotEncodingV2Info = {
	//				new OneHotEncodingV2Info("org:resource", oriLog), 
	//				new OneHotEncodingV2Info("org:group", oriLog), 
					new OneHotEncodingV2Info("concept:name", oriLog), 
	//				new OneHotEncodingV2Info("lifecycle:transition", oriLog), 
	//				new OneHotEncodingV2Info("organization involved", oriLog), 
	//				new OneHotEncodingV2Info("impact", oriLog), 
	//				new OneHotEncodingV2Info("product", oriLog), 
	//				new OneHotEncodingV2Info("resource country", oriLog), 
	//				new OneHotEncodingV2Info("organization country", oriLog), 
	//				new OneHotEncodingV2Info("org:role", oriLog), 
				};
				//END OF Create One Hot Encoding V2 Info
				
				//Create Attribute Encoding Info
				AttributeEncodingInfo[] attEncodingInfo = {
//						new AttributeEncodingInfo("org:resource", oriLog), 
//						new AttributeEncodingInfo("org:group", oriLog), 
//						new AttributeEncodingInfo("concept:name", oriLog), 
//						new AttributeEncodingInfo("lifecycle:transition", oriLog), 
//						new AttributeEncodingInfo("organization involved", oriLog), 
//						new AttributeEncodingInfo("impact", oriLog), 
//						new AttributeEncodingInfo("product", oriLog), 
//						new AttributeEncodingInfo("resource country", oriLog), 
//						new AttributeEncodingInfo("organization country", oriLog), 
//						new AttributeEncodingInfo("org:role", oriLog), 
				};
				//END OF Create Attribute Encoding Info
			
				//====================================================================
				// END OF ENCODING CHOICE
				//====================================================================

				//====================================================================
				// CLASSIFIER CHOICE
				//====================================================================
				
				Classifier[] models = {
						new LinearRegression(), 
						new RandomForest(),
						};

				//====================================================================
				// END OF CLASSIFIER CHOICE
				//====================================================================

			////////////////////////////////////////////////////////////////////
			//END OF SETTING!!!
			////////////////////////////////////////////////////////////////////
	
			
			
			////////////////////////////////////////////////////////////////////
			//Input info
			////////////////////////////////////////////////////////////////////
			
			System.out.println("ori log size: " + oriLog.size());
			System.out.println("training log size: " + trainLog.size());
			System.out.println("test log size: " + testLog.size());
			System.out.println("max trace length: " + XESUtil.getMaxTracesLength(oriLog));
			System.out.println("min trace length: " + XESUtil.getMinTracesLength(oriLog));

			mainOutputWriter.append("ori log size: " + oriLog.size()+"\n");
			mainOutputWriter.append("training log size: " + trainLog.size()+"\n");
			mainOutputWriter.append("test log size: " + testLog.size()+"\n");
			mainOutputWriter.append("max trace length: " + XESUtil.getMaxTracesLength(oriLog)+"\n");
			mainOutputWriter.append("min trace length: " + XESUtil.getMinTracesLength(oriLog)+"\n");

			allSummary.append("ori log size: " + oriLog.size()+"\n");
			allSummary.append("training log size: " + trainLog.size()+"\n");
			allSummary.append("test log size: " + testLog.size()+"\n");
			allSummary.append("max trace length: " + XESUtil.getMaxTracesLength(oriLog)+"\n");
			allSummary.append("min trace length: " + XESUtil.getMinTracesLength(oriLog)+"\n");

			allSummary.append("\nEncodings:"+"\n");
			
			for(OneHotEncodingV2Info ohi:oneHotEncodingV2Info)
				allSummary.append(ohi.toString()+"\n");

			for(AttributeEncodingInfo ae:attEncodingInfo)
				allSummary.append(ae.toString()+"\n");
			
			allSummary.append("END OF Encodings"+"\n\n");

			////////////////////////////////////////////////////////////////////
			//END OF Input info
			////////////////////////////////////////////////////////////////////
			
			
			////////////////////////////////////////////////////////////////////
			//CREATE TRAINING & TEST INSTANCES
			////////////////////////////////////////////////////////////////////

			System.out.println("initializing ARS...");
			ars.init(oriLog);
			System.out.println("finish initializing ARS...");
			
			AnalyticRulesInstance ari = ars.computeAnalyticRulesInstanceAllPrefixLength(2, trainLog);
			Instances trainingInstances = ari.computeWEKAInstances(trainInstanceCodeName, encodingType, oneHotEncodingV2Info, attEncodingInfo);

			AnalyticRulesInstance ariTestEarly = ars.computeAnalyticRulesInstanceWithFixPrefixLength(PrefLengthType.EARLY_EVENT, testLog);
			ariTestEarly.setAllPossibleTargetValue(ari.getAllPossibleTargetValue());
			Instances testInstancesEarly = ariTestEarly.computeWEKAInstances(testInstanceCodeName+"Early", encodingType, oneHotEncodingV2Info, attEncodingInfo);

			AnalyticRulesInstance ariTestMid = ars.computeAnalyticRulesInstanceWithFixPrefixLength(PrefLengthType.MID_EVENT, testLog);
			ariTestMid.setAllPossibleTargetValue(ari.getAllPossibleTargetValue());
			Instances testInstancesMid = ariTestMid.computeWEKAInstances(testInstanceCodeName+"Mid", encodingType, oneHotEncodingV2Info, attEncodingInfo);

			AnalyticRulesInstance ariTestLate = ars.computeAnalyticRulesInstanceWithFixPrefixLength(PrefLengthType.LATE_EVENT, testLog);
			ariTestLate.setAllPossibleTargetValue(ari.getAllPossibleTargetValue());
			Instances testInstancesLate = ariTestLate.computeWEKAInstances(testInstanceCodeName+"Late", encodingType, oneHotEncodingV2Info, attEncodingInfo);

			AnalyticRulesInstance ariALL = ars.computeAnalyticRulesInstanceAllPrefixLength(2, testLog);
			ariALL.setAllPossibleTargetValue(ari.getAllPossibleTargetValue());
			Instances testInstancesALL = ariALL.computeWEKAInstances(testInstanceCodeName+"ALL", encodingType, oneHotEncodingV2Info, attEncodingInfo);

			System.out.println("num of training instances: " + trainingInstances.size());
			System.out.println("num of test instances (Early Events): " + testInstancesEarly.size());
			System.out.println("num of test instances (Mid Events): " + testInstancesMid.size());
			System.out.println("num of test instances (Late Events): " + testInstancesLate.size());
			System.out.println("num of test instances (All): " + testInstancesALL.size());

			mainOutputWriter.append("num of training instances: " + trainingInstances.size()+"\n");
			mainOutputWriter.append("num of test instances (Early Events): " + testInstancesEarly.size()+"\n");
			mainOutputWriter.append("num of test instances (Mid Events): " + testInstancesMid.size()+"\n");
			mainOutputWriter.append("num of test instances (Late Events): " + testInstancesLate.size()+"\n");
			mainOutputWriter.append("num of test instances (All): " + testInstancesALL.size()+"\n");

			allSummary.append("num of training instances: " + trainingInstances.size()+"\n");
			allSummary.append("num of test instances (Early Events): " + testInstancesEarly.size()+"\n");
			allSummary.append("num of test instances (Mid Events): " + testInstancesMid.size()+"\n");
			allSummary.append("num of test instances (Late Events): " + testInstancesLate.size()+"\n");
			allSummary.append("num of test instances (All): " + testInstancesALL.size()+"\n");

			////////////////////////////////////////////////////////////////////
			//END OF CREATE TRAINING & TEST INSTANCES
			////////////////////////////////////////////////////////////////////

			
			mainOutputWriter.flush();
			
			DecimalFormat f = new DecimalFormat("###,###.###");

			
			////////////////////////////////////////////////////////////////////
			//MEAN BASED PREDICTION
			////////////////////////////////////////////////////////////////////
			
			mainOutputWriter.append("\n\n\n=============================================================================================================\n");
			mainOutputWriter.append("------------------------------------------------------------------------\n");
			mainOutputWriter.append("Mean Based Prediction\n");

			allSummary.append("\n\n----------------------------------------------------------------------------------------------------\n");
			allSummary.append("Mean Based Prediction \n");

			MeanBasedRegression re1 = new MeanBasedRegression(ariTestEarly, ari, encodingType, oneHotEncodingV2Info, attEncodingInfo);
			String out1c = re1.getEvaluation();
			allSummary.append("\nEarly Prediction: \n");
			mainOutputWriter.append("\nEarly Prediction:\n");
			allSummary.append(out1c);
			mainOutputWriter.append(out1c+"\n");

			MeanBasedRegression re2 = new MeanBasedRegression(ariTestMid, ari, encodingType, oneHotEncodingV2Info, attEncodingInfo);
			String out2c = re2.getEvaluation();
			allSummary.append("\nMid Prediction: \n");
			mainOutputWriter.append("\nMid Prediction:\n");
			allSummary.append(out2c);
			mainOutputWriter.append(out2c+"\n");

			MeanBasedRegression re3 = new MeanBasedRegression(ariTestLate, ari, encodingType, oneHotEncodingV2Info, attEncodingInfo);
			String out3c = re3.getEvaluation();
			allSummary.append("\nLate Prediction: \n");
			mainOutputWriter.append("\nLate Prediction:\n");
			allSummary.append(out3c);
			mainOutputWriter.append(out3c+"\n");

			MeanBasedRegression re5 = new MeanBasedRegression(ariALL, ari, encodingType, oneHotEncodingV2Info, attEncodingInfo);
			String out5c = re5.getEvaluation();
			allSummary.append("\nAll Prediction: \n");
			mainOutputWriter.append("\nAll Prediction:\n");
			allSummary.append(out5c);
			mainOutputWriter.append(out5c+"\n");
			
			////////////////////////////////////////////////////////////////////
			//END OF MEAN BASED PREDICTION
			////////////////////////////////////////////////////////////////////
			
			
			
			////////////////////////////////////////////////////////////////////
			//TRAIN AND EVALUATE 
			////////////////////////////////////////////////////////////////////
			
			for (int i = 0; i < models.length; i++) {
				
				StringBuilder partialOutout = new StringBuilder("");
				
				long startTime = System.currentTimeMillis();
				System.out.println("\n\n\n=============================================================================================================");

				String sep = "\n\n----------------------------------------------------------------------------------------------------\n";
				String buildCls = "Building the prediction model "+models[i].getClass().toString()+"\n";

				System.out.println(sep);
				System.out.println(buildCls);

				mainOutputWriter.append(sep+"\n");
				mainOutputWriter.append(buildCls+"\n");

				partialOutout.append(sep);
				partialOutout.append(models[i].getClass().toString()+"\n");

				
				
				////////////////////////////////////////////////////////////////////
				//BUILD AND SAVE THE MODEL
				////////////////////////////////////////////////////////////////////
				
				models[i].buildClassifier(trainingInstances);
				String outputModelFile = outputModelLocation + models[i].getClass().toString()+".model";
				WEKAUtil.saveClassifier(outputModelFile, models[i]);

				mainOutputWriter.append("model output location: "+outputModelFile+"\n");
				
				String sep2 = "------------------------------------------------------------------------";
				System.out.println(sep2);
				mainOutputWriter.append(sep2+"\n");
				partialOutout.append(sep2+"\n");
				////////////////////////////////////////////////////////////////////
				//END OF BUILD AND SAVE THE MODEL
				////////////////////////////////////////////////////////////////////
				
				
				
				////////////////////////////////////////////////////////////////////
				//EVALUATING MODEL
				////////////////////////////////////////////////////////////////////
				
				String evalMsg = "Evaluating "+models[i].getClass().toString()+"\n";
				System.out.println(evalMsg);
				mainOutputWriter.append(evalMsg);
				
				//==================================================================================================
				mainOutputWriter.append("Predicting at early event:\n" );
				Evaluation evaluation = new Evaluation(trainingInstances);
				evaluation.evaluateModel(models[i], testInstancesEarly);
				mainOutputWriter.append(evaluation.toSummaryString()+"\n");
				double MAE1 = evaluation.meanAbsoluteError();
				double RMSE1 = evaluation.rootMeanSquaredError();

				String out1a = "MAE: "+ f.format(MAE1)+
									" - i.e., - "+String.format("%3.2f", (MAE1/(1000*60*60)))+
									" hours, i.e., "+String.format("%3.2f", (MAE1/(1000*60*60*24)))+" day(s)\n";
				String out1b = "RMSE: "+ f.format(RMSE1)+
									" - i.e., - "+String.format("%3.2f", (RMSE1/(1000*60*60)))+
									" hours, i.e., "+String.format("%3.2f", (RMSE1/(1000*60*60*24)))+" day(s)\n";
								

				partialOutout.append("\nEarly Prediction: \n");
				partialOutout.append(out1a);
				partialOutout.append(out1b);
				
				mainOutputWriter.append(out1a+"\n");
				mainOutputWriter.append(out1b+"\n");
				
				//==================================================================================================
				mainOutputWriter.append("Predicting at mid event:\n" );
				Evaluation evaluation2 = new Evaluation(trainingInstances);
				evaluation2.evaluateModel(models[i], testInstancesMid);
				mainOutputWriter.append(evaluation2.toSummaryString()+"\n");
				double MAE2 = evaluation2.meanAbsoluteError();
				double RMSE2 = evaluation2.rootMeanSquaredError();
				
				String out2a = "MAE: "+ f.format(MAE2)+
						" - i.e., - "+String.format("%3.2f", (MAE2/(1000*60*60)))+
						" hours, i.e., "+String.format("%3.2f", (MAE2/(1000*60*60*24)))+" day(s)\n";
				String out2b = "RMSE: "+ f.format(RMSE2)+
									" - i.e., - "+String.format("%3.2f", (RMSE2/(1000*60*60)))+
									" hours, i.e., "+String.format("%3.2f", (RMSE2/(1000*60*60*24)))+" day(s)\n";


				partialOutout.append("\nMid Prediction: \n");
				partialOutout.append(out2a);
				partialOutout.append(out2b);
				
				mainOutputWriter.append(out2a+"\n");
				mainOutputWriter.append(out2b+"\n");
				
				//==================================================================================================
				mainOutputWriter.append("Predicting at late event:\n" );
				Evaluation evaluation3 = new Evaluation(trainingInstances);
				evaluation3.evaluateModel(models[i], testInstancesLate);
				mainOutputWriter.append(evaluation3.toSummaryString()+"\n");
				double MAE3 = evaluation3.meanAbsoluteError();
				double RMSE3 = evaluation3.rootMeanSquaredError();
				
				String out3a = "MAE: "+ f.format(MAE3)+
						" - i.e., - "+String.format("%3.2f", (MAE3/(1000*60*60)))+
						" hours, i.e., "+String.format("%3.2f", (MAE3/(1000*60*60*24)))+" day(s)\n";
				String out3b = "RMSE: "+ f.format(RMSE3)+
									" - i.e., - "+String.format("%3.2f", (RMSE3/(1000*60*60)))+
									" hours, i.e., "+String.format("%3.2f", (RMSE3/(1000*60*60*24)))+" day(s)\n";

				partialOutout.append("\nLate Prediction: \n");
				partialOutout.append(out3a);
				partialOutout.append(out3b);
				
				mainOutputWriter.append(out3a+"\n");
				mainOutputWriter.append(out3b+"\n");

				//==================================================================================================
				mainOutputWriter.append("Predicting ALL:\n" );
				Evaluation evaluation5 = new Evaluation(trainingInstances);
				evaluation5.evaluateModel(models[i], testInstancesALL);
				mainOutputWriter.append(evaluation5.toSummaryString()+"\n");
				double MAE5 = evaluation5.meanAbsoluteError();
				double RMSE5 = evaluation5.rootMeanSquaredError();

				String out5a = "MAE: "+ f.format(MAE5)+
						" - i.e., - "+String.format("%3.2f", (MAE5/(1000*60*60)))+
						" hours, i.e., "+String.format("%3.2f", (MAE5/(1000*60*60*24)))+" day(s)\n";
				String out5b = "RMSE: "+ f.format(RMSE5)+
									" - i.e., - "+String.format("%3.2f", (RMSE5/(1000*60*60)))+
									" hours, i.e., "+String.format("%3.2f", (RMSE5/(1000*60*60*24)))+" day(s)\n";

				partialOutout.append("\nAll Prediction: \n");
				partialOutout.append(out5a);
				partialOutout.append(out5b);
				
				mainOutputWriter.append(out5a+"\n");
				mainOutputWriter.append(out5b+"\n");
				
				//==================================================================================================

				////////////////////////////////////////////////////////////////////
				//END OF EVALUATING MODEL
				////////////////////////////////////////////////////////////////////

				System.out.println("------------------------------------------------------------------------");
				mainOutputWriter.append("------------------------------------------------------------------------\n");
				
				long runTime = (System.currentTimeMillis() - startTime);

				String runTimeStr = 
						"Total running time of " + models[i].getClass().toString() + " is " +
						(runTime/60000)+" minute(s) or "+runTime+ " millisecond(s)\n";
					
				System.out.println(runTimeStr);
				mainOutputWriter.append(runTimeStr);
				
				partialOutout.append("\n"+runTimeStr);
				partialOutout.append("\n\n");
				
				System.out.println("=========================================================================================================");
				System.out.println("PARTIAL SUMARY: ");
				System.out.println("=========================================================================================================");
				System.out.println(partialOutout);
				System.out.println("=========================================================================================================");

				mainOutputWriter.append("=========================================================================================================\n");
				mainOutputWriter.append("PARTIAL SUMARY: \n");
				mainOutputWriter.append("=========================================================================================================\n");
				mainOutputWriter.append(partialOutout);
				mainOutputWriter.append("=========================================================================================================\n");
				
				allSummary.append(partialOutout);
				
				mainOutputWriter.flush();
			}
			
			System.out.println("=========================================================================================================");
			System.out.println("SUMARY: ");
			System.out.println("=========================================================================================================");
			System.out.println(allSummary);
			System.out.println("=========================================================================================================");
			
			mainOutputWriter.append("=========================================================================================================\n");
			mainOutputWriter.append("SUMARY: \n");
			mainOutputWriter.append("=========================================================================================================\n");
			mainOutputWriter.append(allSummary);
			mainOutputWriter.append("=========================================================================================================\n");
			
			////////////////////////////////////////////////////////////////////
			//END OF TRAIN AND EVALUATE CLASSIFIERS
			////////////////////////////////////////////////////////////////////
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public static AnalyticRuleSpec genAnalyticRules_TimeUntilTheNextEvent() throws Exception{
		
		AnalyticRuleSpec ars = new AnalyticRuleSpec();

		ConditionExpression ce1 = genCondition_PlusOneBeforeLast();
		TargetExpression te1 = genTargetExp_EventDuration();
		TargetExpression te2 = genTargetExp_Zero();
		
		ars.addRuleSpec(ce1, te1);
		ars.setOtherwiseTarget(te2);
		
		return ars;
	}
	
	public static ConditionExpression genCondition_PlusOneBeforeLast() throws Exception{
		
		String ce = "(CURR + 1 <= LAST)";
		Formula formula = FOEFormulaParser.parse(ce);

		return formula;
	}

	public static TargetExpression genTargetExp_EventDuration() throws Exception{
		
		String te = "e[CURR + 1].time:timestamp - e[CURR].time:timestamp";
		TargetExpression targetExp = TargetExpressionParser.parse(te);
		return targetExp;
	}

	public static TargetExpression genTargetExp_Zero() throws Exception{
		
		String te = "0";
		TargetExpression targetExp = TargetExpressionParser.parse(te);
		return targetExp;
	}

}
