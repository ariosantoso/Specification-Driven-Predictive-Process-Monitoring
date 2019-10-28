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
package org.astw.exp;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

import org.astw.analyticrules.AnalyticRuleSpec;
import org.astw.analyticrules.AnalyticRulesInstance;
import org.astw.util.Const.Mode;
import org.astw.util.Const.PrefLengthType;
import org.astw.util.Const.ValueType;
import org.astw.util.Pair;
import org.astw.util.PythonHelper;
import org.astw.util.Util;
import org.astw.util.WEKAUtil;
import org.astw.util.XESUtil;
import org.astw.util.encoder.AttributeEncodingInfo;
import org.astw.util.encoder.Encoder.EncodingType;
import org.astw.util.encoder.OneHotEncodingV2Info;
import org.deckfour.xes.factory.XFactory;
import org.deckfour.xes.factory.XFactoryNaiveImpl;
import org.deckfour.xes.model.XLog;

import jep.Jep;
import jep.JepException;
import jep.MainInterpreter;
import jep.NDArray;
import jep.PyConfig;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;

/**
 * @author Ario Santoso (santoso.ario@gmail.com)
 */
public class SDPROM {

	//SDPROM stuff
	protected String identifier = this.getClass().getSimpleName();
	protected String title = "SDPROM";
	protected DecimalFormat f = new DecimalFormat("###,###.###");

	private boolean initialized = false;
	private ValueType predictionTaskType = ValueType.UNKNOWN; //either numeric (regression) or non-numeric (classification)
	private Mode sdpromMode = Mode.WEKA;
	protected boolean showErrorsInDaysOrHours = true;
	/*
		usingDefaultMode - true if there is not (programatically) specified Mode, 
		in this case, we assign default mode but it may be overriden by the config file	 
	 */
	private boolean usingDefaultMode = false;
	//END OF SDPROM stuff
		

	//File and I/O related stuff
	protected String configFileLoc = "config.txt";
	protected final String configInputLabel = "INPUT";
	protected final String configOutputLabel = "OUTPUT_LOCATION";
	protected final String configPythonScriptPathLabel = "PYTHON_SCRIPT_PATH";
	protected final String configPythonEnvironmentLabel = "PYTHON_ENVIRONMENT";
	protected final String configModeLabel = "MODE";
	protected final String configModeWeka = "Weka";
	protected final String configModePython = "Python";
	protected final String configModeNN = "NeuralNetwork";

	protected String mainOutputFileName = "MainOutput-"+identifier+".txt";
	protected String mainOutputFileFullPath = "MainOutput-"+identifier+".txt";
	
	protected String latexOutputFileName = "Latex-"+identifier+".txt";
	protected String latexOutputFileFullPath = "Latex-"+identifier+".txt";
	protected String latexOutputShortFileName = "LatexShort-"+identifier+".txt";
	protected String latexOutputShortFileFullPath = "LatexShort-"+identifier+".txt";
		
	protected String input = "";
	protected String outputLocation = "";
	protected String outputModelLocation= "";
	protected String outputFolder = identifier+"/";
	protected String inputLogPath = "";

	protected FileWriter mainOutputWriter = null;
	protected FileWriter latexOutputWriter = null;
	protected FileWriter latexOutputShortWriter = null;
	//END OF File and I/O related stuff

	
	//Time related stuff
	protected String startTime = "";
	protected String endTime = "";
	protected long startExecTime = 0;
	protected long endExecTime = 0;
	//END OF Time related stuff
	
	
	//Log Stuff
	protected XLog xlogOri = null; //the original log after preprocessing but before the split into the train/test log
	protected XLog xlogTrain = null;
	protected XLog xlogTest = null;
	float trainDataProportion = (float) 2 / 3; // 2/3 training data 
//	float trainDataProportion = (float) 70/100; // 70% training data 
	//END OF Log Stuff
	
	
	//Parameters for training
	protected AnalyticRuleSpec ars = null;
	protected EncodingType [] encodingType = null;
	protected OneHotEncodingV2Info[] oneHotEncodingV2Info = null;
	protected AttributeEncodingInfo[] attEncodingInfo = null;
	//END OF Parameters for training
	
	
	// For Weka Stuff
	//protected Classifier[] models  = new Classifier[]{new RandomForest()}; //Only needed in WEKA Mode
	protected Classifier[] models  = new Classifier[]{new J48()}; //Only needed in WEKA Mode
	// END OF For Weka Stuff
	
	
	// For Python Stuff
	protected Jep jep = null;
	protected String pythonPackagePath = null;
	protected final String defaultPythonScriptPath = "/python/";
	protected String pythonPath = defaultPythonScriptPath;
	protected String clfPythonFolder = "clf/";
	protected String regPythonFolder = "reg/";
	protected String nnPythonFolder = "nn/";
	protected boolean useCustomPythonScript = false;
	
	protected String initScript = "inits.py";

	protected String clfNeuralNetworkModelScript = "fn_nn_model_clf.py";
	protected String clfModelInitScript = "init_models_clf.py";
	protected String clfEvalScript = "fn_eval_clf.py";
	
	protected String regNeuralNetworkModelScript  = "fn_nn_model_reg.py";
	protected String regModelInitScript = "init_models_reg.py";
	protected String regEvalScript = "fn_eval_reg.py";

	private ArrayList<String> initsStatements = new ArrayList<String>();
	private ArrayList<String> modelsStatements = new ArrayList<String>();
	private String nnFunction = new String();
	private String evaluation = new String();
	// END OF For Python Stuff
			
		
	protected SDPROM(){}

	
	////////////////////////////////////////////////////////////////////////////
	// Initialization
	////////////////////////////////////////////////////////////////////////////

	//init - Weka mode- without config file at all
	protected void init(String id, String title, AnalyticRuleSpec analyticRuleSpec) 
			throws IOException, JepException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, Exception{
		
		this.usingDefaultMode = true;
		init(identifier, title, Mode.WEKA, null, this.configFileLoc, analyticRuleSpec);
	}

	//Default init - using weka - minimal config
	protected void init(String identifier, String title, String desiredConfigFileLoc, AnalyticRuleSpec analyticRuleSpec) 
			throws IOException, JepException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, Exception{

		this.usingDefaultMode = true;
		init(identifier, title, Mode.WEKA, null, desiredConfigFileLoc, analyticRuleSpec);
	}
	
	//Normal init - can be Weka or Python Mode (using default python script)
	protected void init(String identifier, String title, Mode mode, String desiredConfigFileLoc, AnalyticRuleSpec analyticRuleSpec) 
			throws IOException, JepException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, Exception{

		init(identifier, title, mode, null, desiredConfigFileLoc, analyticRuleSpec);
	}

	//init python mode with custom python script
	protected void init(String identifier, String title, String pythonScriptPath, String desiredConfigFileLoc, AnalyticRuleSpec analyticRuleSpec) 
			throws IOException, JepException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, Exception{

		this.usingDefaultMode = true;
		init(identifier, title, Mode.PYTHON, pythonScriptPath, desiredConfigFileLoc, analyticRuleSpec);
	}
	
	//full init
	protected void init(String identifier, String title, Mode mode, String pythonCustomScriptPath, String desiredConfigFileLoc, AnalyticRuleSpec analyticRuleSpec) 
			throws IOException, JepException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, Exception{
		
		
		
		System.setErr(System.out);
		
		this.startTime = Util.getCurrentTimeStamp();
		this.sdpromMode = mode;
		this.title = title;
		this.configFileLoc = desiredConfigFileLoc;
		this.ars = analyticRuleSpec;
		this.identifier = identifier;

		//////////////////////////////////////////////////////////////////
		// Check Loaded Libraries
		//////////////////////////////////////////////////////////////////

		//System.setProperty("java.library.path", "/Users/ykhairina/pcloud/dev/.virtualenvs/jp1/lib/python3.6/site-packages/jep");
		java.lang.reflect.Field LIBRARIES = ClassLoader.class.getDeclaredField("loadedLibraryNames");
		LIBRARIES.setAccessible(true);
		Vector<String> libraries = (Vector<String>) LIBRARIES.get(ClassLoader.getSystemClassLoader());		
		//final String[] librariesArr = libraries.toArray(new String[] {});
		System.out.println("\nloaded libraries are:");
		
		for (String lib : libraries) 
			System.out.println(lib);
		
		System.out.println("END OF loaded libraries");
		
		//String javaLibPath = System.getProperty("java.library.path");
		//javaLibPath += ":"+"./lib";
		//javaLibPath += ":"+".";
		//javaLibPath += ":"+"src/main/resources/lib";
		//System.setProperty("java.library.path", javaLibPath);
		System.out.println("\njava.library.path: \n"+System.getProperty("java.library.path"));

		//////////////////////////////////////////////////////////////////
		// END OF Check Loaded Libraries
		//////////////////////////////////////////////////////////////////
		
		
		//////////////////////////////////////////////////////////////////
		// Read Config
		//////////////////////////////////////////////////////////////////

		//read the configuration file
		BufferedReader reader = new BufferedReader(new FileReader(configFileLoc));
		
		String inp = reader.readLine();
		String out = reader.readLine();
		String input3rd = reader.readLine();
		String input4th = reader.readLine();
		String input5th = reader.readLine();
		reader.close();

		//----------------------------------------------------------------
		//Deal with the INPUT and OUTPUT info
		//----------------------------------------------------------------

		if(inp == null || out == null)
			throw new Exception("Invalid Config File!");
		
		StringTokenizer strtokInp = new StringTokenizer(inp, ":"); 
		StringTokenizer strtokOutLoc = new StringTokenizer(out, ":");
		
		if(!strtokInp.nextToken().equalsIgnoreCase(this.configInputLabel)) //clean up the string "INPUT"
			throw new Exception("Invalid Config File!");
			
		if(!strtokOutLoc.nextToken().equalsIgnoreCase(this.configOutputLabel)) //clean up the string "OUTPUT_LOCATION"
			throw new Exception("Invalid Config File!");

		input = strtokInp.nextToken().trim();
		outputLocation = strtokOutLoc.nextToken().trim() + outputFolder;
		
		//----------------------------------------------------------------
		//END OF Deal with the INPUT and OUTPUT info
		//----------------------------------------------------------------

		
		//----------------------------------------------------------------
		//Deal with the THIRD, FOURTH, FIFTH info in the config file (if exists)
		//----------------------------------------------------------------
		
		StringTokenizer strtok3rdInput = null; 
		StringTokenizer strtok4thInput = null; 
		StringTokenizer strtok5thInput = null; 
		ArrayList<String> labels = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();
		
		if (input3rd != null && !input3rd.equals("")){
			strtok3rdInput = new StringTokenizer(input3rd, ":");
			if(strtok3rdInput.countTokens() > 2)
				throw new Exception("Invalid Config File!");
			labels.add(strtok3rdInput.nextToken().trim());
			values.add(strtok3rdInput.nextToken().trim());
		}
		
		if (input4th != null && !input4th.equals("")) {
			strtok4thInput = new StringTokenizer(input4th, ":");
			if(strtok4thInput.countTokens() > 2)
				throw new Exception("Invalid Config File!");
			labels.add(strtok4thInput.nextToken().trim());
			values.add(strtok4thInput.nextToken().trim());
		}

		if (input5th != null && !input5th.equals("")) {
			strtok5thInput = new StringTokenizer(input5th, ":");
			if(strtok5thInput.countTokens() > 2)
				throw new Exception("Invalid Config File!");
			labels.add(strtok5thInput.nextToken().trim());
			values.add(strtok5thInput.nextToken().trim());
		}

		for(int ii = 0; ii < labels.size(); ii ++)
			for(int jj = ii+1; jj < labels.size(); jj++)
				if(labels.get(ii).equalsIgnoreCase(labels.get(jj)))
					throw new Exception("Invalid config file");
		
		StringBuilder options = new StringBuilder();
		
		for(int ii = 0; ii < labels.size(); ii ++){
			
			String label = labels.get(ii);
			String value= values.get(ii);
			
			if(label.equalsIgnoreCase(this.configPythonEnvironmentLabel)){
				
				pythonPackagePath = value;
				options.append(this.configPythonEnvironmentLabel+" : "+pythonPackagePath+"\n");
			
			}else if(label.equalsIgnoreCase(this.configPythonScriptPathLabel)){
				
				pythonCustomScriptPath = value;
				options.append(this.configPythonScriptPathLabel+" : "+pythonCustomScriptPath+"\n");
				
			}else if(label.equalsIgnoreCase(this.configModeLabel)){

				if(this.usingDefaultMode == false){
				
					if(value.equalsIgnoreCase(this.configModeWeka) && this.sdpromMode != Mode.WEKA)
						throw new Exception("Mode Mismatch!");
					if(value.equalsIgnoreCase(this.configModePython) && this.sdpromMode != Mode.PYTHON)
						throw new Exception("Mode Mismatch!");
					if(value.equalsIgnoreCase(this.configModeNN) && this.sdpromMode != Mode.NEURAL_NETWORK)
						throw new Exception("Mode Mismatch!");
				}else{
					//in this case, usingDefaultMode == true. If the config contains information about the specified mode,
					//then we will consider it and change the mode
					if(value.equalsIgnoreCase(this.configModeWeka))
						this.sdpromMode = Mode.WEKA;
					else if(value.equalsIgnoreCase(this.configModePython))
						this.sdpromMode = Mode.PYTHON;
					else if(value.equalsIgnoreCase(this.configModeNN))
						this.sdpromMode = Mode.NEURAL_NETWORK;
					else throw new Exception("Invalid Config File!");
				}
				
				options.append(this.configModeLabel+" : "+this.sdpromMode+"\n");

					
			}else throw new Exception("Invalid Config File!");
		}
		
		//System.out.println(thirdInputLabel+" : "+ thirdInputValue);
		//System.out.println(forthInputLabel+" : "+ forthInputValue);
	
		//----------------------------------------------------------------
		//END OF Deal with the THIRD and FOURTH info in the config file (if exists)
		//----------------------------------------------------------------

		//////////////////////////////////////////////////////////////////
		// END OF Read Config
		//////////////////////////////////////////////////////////////////
		
		
		//to use neural network mode, the specification of the neural network must be given
		if(pythonCustomScriptPath == null && this.sdpromMode == Mode.NEURAL_NETWORK)
			throw new Exception("This mode can only be used when the custom python script is provided");
		
		
		//////////////////////////////////////////////////////////////////
		// Init
		//////////////////////////////////////////////////////////////////

		inputLogPath = input;
		outputModelLocation = outputLocation + "model/";
		
		mainOutputFileFullPath = outputLocation + mainOutputFileName;
		latexOutputFileFullPath = outputLocation + latexOutputFileName;
		latexOutputShortFileFullPath = outputLocation + latexOutputShortFileName;
		
		new File(outputLocation).mkdirs();
		new File(outputModelLocation).mkdirs();
		
		new File(mainOutputFileFullPath).createNewFile();
		new File(latexOutputFileFullPath).createNewFile();
		new File(latexOutputShortFileFullPath).createNewFile();
		
		mainOutputWriter = new FileWriter(mainOutputFileFullPath);
		latexOutputWriter = new FileWriter(latexOutputFileFullPath);
		latexOutputShortWriter = new FileWriter(latexOutputShortFileFullPath);

		StringBuilder outInfo = new StringBuilder();
		outInfo.append("\n\n\n------------------------------------------------------------\n");
		outInfo.append("Start Time: "+this.startTime+"\n");
		outInfo.append("Prediction Task Title: "+this.title+"\n");
		outInfo.append("Mode: "+this.sdpromMode+"\n");
		outInfo.append("Input Log Path: "+this.inputLogPath+"\n");
		outInfo.append("Output Location: " + this.outputLocation+"\n");
		outInfo.append("Main Output File Name: " + this.mainOutputFileFullPath+"\n");
		outInfo.append("\nAdditional config:\n"+options.toString());
		outInfo.append("------------------------------------------------------------\n\n\n");

		System.out.print(outInfo);
		mainOutputWriter.append(outInfo);
		
		filterAndPrepareTheTrainTestLog();
		
		//////////////////////////////////////////////////////////////////
		// END OF Init
		//////////////////////////////////////////////////////////////////

		
		
		//////////////////////////////////////////////////////////////////
		// Init Analytic Rules
		//////////////////////////////////////////////////////////////////
		
		if(ars == null)
			throw new Exception("There is no Analytic Rule Specification");
		
		System.out.println("initializing 'Analytic Rule Specification'...");
		ars.init(xlogOri);
		this.predictionTaskType = ars.getTargetValueType();
		
		System.out.print("Prediction Task Type: "+this.predictionTaskType+"\n\n");
		mainOutputWriter.append("Prediction Task Type: "+this.predictionTaskType+"\n\n");
		
		System.out.println("finish initializing 'Analytic Rule Specification'...");
		

		//////////////////////////////////////////////////////////////////
		// END OF Init Analytic Rules
		//////////////////////////////////////////////////////////////////
		
		
		
		//////////////////////////////////////////////////////////////////
		// Handling Python stuff
		//////////////////////////////////////////////////////////////////		
		
		if(this.sdpromMode == Mode.PYTHON || this.sdpromMode == Mode.NEURAL_NETWORK){
			
			//////////////////////////////////////////////////////////////////
			// Handling Python Script Path
			//////////////////////////////////////////////////////////////////
			
			String pythonPathClf = "";
			String pythonPathReg = "";
			
			if(pythonCustomScriptPath == null){//using default python script
			
				if(this.sdpromMode == Mode.NEURAL_NETWORK){
					pythonPath = defaultPythonScriptPath;
					pythonPathClf = defaultPythonScriptPath + nnPythonFolder;
					pythonPathReg = defaultPythonScriptPath + nnPythonFolder;
				}else{
					pythonPath = defaultPythonScriptPath;
					pythonPathClf = defaultPythonScriptPath + clfPythonFolder;
					pythonPathReg = defaultPythonScriptPath + regPythonFolder;
				}
			
			}else{//using custom python script
			
				pythonPath = pythonCustomScriptPath;
				pythonPathClf = pythonCustomScriptPath;
				pythonPathReg = pythonCustomScriptPath;
				useCustomPythonScript = true;
			}
			
			initScript = pythonPath+"inits.py";
			
			//neural network python script
			if(this.sdpromMode == Mode.NEURAL_NETWORK){
				
				clfNeuralNetworkModelScript = pythonPathClf+"fn_nn_model_clf.py";
				regNeuralNetworkModelScript  = pythonPathReg+"fn_nn_model_reg.py";
			}
			
			//model initialization python script
			clfModelInitScript = pythonPathClf+"init_models_clf.py";
			regModelInitScript = pythonPathReg+"init_models_reg.py";
			
			//evaluation script
			//in any case the evaluation is using the default script
			clfEvalScript = defaultPythonScriptPath+clfPythonFolder+"fn_eval_clf.py";
			regEvalScript = defaultPythonScriptPath+regPythonFolder+"fn_eval_reg.py";
			
			StringBuilder pyScr = new StringBuilder();
			pyScr.append("\n------------------------------------------------------------"+"\n");
			pyScr.append("Python scripts"+"\n");
			pyScr.append("------------------------------------------------------------"+"\n");
			
			if(this.predictionTaskType == ValueType.NON_NUMERIC_EXP){
				pyScr.append("Classification Task\n");
				pyScr.append("initScript: " + initScript+"\n");
				pyScr.append("ModelInitScript: " + clfModelInitScript+"\n");
				
				if(this.sdpromMode == Mode.NEURAL_NETWORK)
					pyScr.append("NeuralNetworkModelScript: " + clfNeuralNetworkModelScript+"\n");
				
				pyScr.append("EvalScript: " + clfEvalScript+"\n");
			}
			
			if(this.predictionTaskType == ValueType.NUMERIC_EXP){
				pyScr.append("Regression Task\n");
				pyScr.append("initScript: " + initScript+"\n");
				pyScr.append("ModelInitScript: " + regModelInitScript+"\n");
				
				if(this.sdpromMode == Mode.NEURAL_NETWORK)
					pyScr.append("NeuralNetworkModelScript: " + regNeuralNetworkModelScript+"\n");
			
				pyScr.append("EvalScript: " + regEvalScript+"\n");
			}
			pyScr.append("------------------------------------------------------------"+"\n\n");
			
			System.out.println(pyScr.toString());
			mainOutputWriter.append(pyScr.toString());

			//////////////////////////////////////////////////////////////////
			// END OF Handling Python Script Path
			//////////////////////////////////////////////////////////////////


			//////////////////////////////////////////////////////////////////
			// Python bridge init
			//////////////////////////////////////////////////////////////////
			
			PyConfig pyConfig = new PyConfig();
			pyConfig.setHashRandomizationFlag(0);
			MainInterpreter.setInitParams(pyConfig);
			jep = new Jep();
			
			if (pythonPackagePath != null) {
				jep.eval("import sys");
				jep.eval("sys.path.insert(0, '" + pythonPackagePath + "')");
				System.out.println("python sys.path is " + jep.getValue("sys.path"));
			}
			
			//////////////////////////////////////////////////////////////////
			// END OF Python bridge init
			//////////////////////////////////////////////////////////////////
			
			
			//////////////////////////////////////////////////////////////////
			// Read python statements from the file
			//////////////////////////////////////////////////////////////////
			
		    String neuralNetworkModelPyScript = "";	
		    String modelInitScript = "";	
		    String evaluationPyScript = "";	
			//ClassLoader classLoader = getClass().getClassLoader();
			InputStream in = null;
			BufferedReader br = null;
	        StringBuilder sb = new StringBuilder();
			
			//---------------------------------------------------------------------------
			// Selecting Python script based on the type of the prediction task
			//---------------------------------------------------------------------------

	    	if(this.predictionTaskType == ValueType.NON_NUMERIC_EXP){// For Non-Numeric Experiments (Classification)
		    	
	    	    neuralNetworkModelPyScript = this.clfNeuralNetworkModelScript;	
	    	    modelInitScript = this.clfModelInitScript;	
	    	    evaluationPyScript = this.clfEvalScript;	
		        
	    	}else if(this.predictionTaskType == ValueType.NUMERIC_EXP){// For Numeric Experiments (Regression)
	    		
	    	    neuralNetworkModelPyScript = this.regNeuralNetworkModelScript;	
	    	    modelInitScript = this.regModelInitScript;	
	    	    evaluationPyScript = this.regEvalScript;	
	
	    	}else throw new Exception("The Analytic Rule Specification is not coherent. Please re-check the specification.");

			//---------------------------------------------------------------------------
			// END OF Selecting Python script based on the type of the prediction task
			//---------------------------------------------------------------------------


			//---------------------------------------------------------------------------
			//Read python init script (e.g., import libraries) - inits.py
			//---------------------------------------------------------------------------
			//File file = new File(classLoader.getResource(initScript).getFile());
			if(this.useCustomPythonScript)
				br = new BufferedReader(new FileReader(initScript));
			else
				br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(initScript)));
			
	        String s = "";
	    	while ((s = br.readLine()) != null) {
	    		if (!s.startsWith("#"))
	    			initsStatements.add(s);
	        }
			//---------------------------------------------------------------------------
			//END OF Read python init script (e.g., import libraries) - inits.py
			//---------------------------------------------------------------------------
	        
	        
			//---------------------------------------------------------------------------
	    	//Read python script for neural network model - fn_nn_model_clf.py
			//---------------------------------------------------------------------------
	    	if(this.sdpromMode == Mode.NEURAL_NETWORK){
		    	//file = new File(classLoader.getResource(defNnModelScript).getFile());
				if(this.useCustomPythonScript)
					br = new BufferedReader(new FileReader(neuralNetworkModelPyScript));
				else
					br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(neuralNetworkModelPyScript)));
		    	
		        while ((s = br.readLine()) != null) {
		        	if (!s.startsWith("#"))
		        		sb.append(s + "\n");
		        }
		        nnFunction = sb.toString();
	    	}
			//---------------------------------------------------------------------------
	    	//END OF Read python script for neural network model - fn_nn_model_clf.py
			//---------------------------------------------------------------------------

			//---------------------------------------------------------------------------
	    	//Read python script for initializing ML models - init_models_clf.py
			//---------------------------------------------------------------------------
	        //file = new File(classLoader.getResource(initModelScript).getFile());
			if(this.useCustomPythonScript)
				br = new BufferedReader(new FileReader(modelInitScript));
			else
				br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(modelInitScript)));
	
	        while ((s = br.readLine()) != null) {
	        	if (!s.startsWith("#"))
	        		modelsStatements.add(s);
	        }
			//---------------------------------------------------------------------------
	    	//END OF Read python script for initializing ML models - init_models_clf.py
			//---------------------------------------------------------------------------
	
			//---------------------------------------------------------------------------
	    	//Read python script for evaluating trained models - fn_eval_clf.py
			//---------------------------------------------------------------------------
	        //file = new File(classLoader.getResource(evalScript).getFile());
	        in = getClass().getResourceAsStream(evaluationPyScript);
	        br = new BufferedReader(new InputStreamReader(in));
	        sb = new StringBuilder();
	        while ((s = br.readLine()) != null) {
	        	if (!s.startsWith("#"))
	        		sb.append(s + "\n");
	        }
	        evaluation = sb.toString();
			//---------------------------------------------------------------------------
	    	//END OF Read python script for evaluating trained models - fn_eval_clf.py
			//---------------------------------------------------------------------------

	        br.close();
	        
			//////////////////////////////////////////////////////////////////
			// END OF Read python statements from the file
			//////////////////////////////////////////////////////////////////
		}
		
		//////////////////////////////////////////////////////////////////
		// END OF Handling Python stuff
		//////////////////////////////////////////////////////////////////		

        this.initialized = true;
	}
	
	

	
	/**
	 * Prepare the log (filter outliers, split, etc)
	 * @param logPath
	 */
	private void filterAndPrepareTheTrainTestLog(){

		try {
			StringBuilder output1 = new StringBuilder();

			output1.append("\n\n\n\n\n\n\n\n\n\n");
			output1.append("-------------------------------------------------------------------------------------------\n");
			output1.append("-------------------------------------------------------------------------------------------\n");
			output1.append("filterAndPrepareTheTrainTestLog\n");
			output1.append("-------------------------------------------------------------------------------------------\n");
			output1.append("-------------------------------------------------------------------------------------------\n");

			System.out.print(output1);
			mainOutputWriter.append(output1.toString());
			
			//=================================================================================
			//Read and Preprocess log - 
			//filter the XES Log and Sort traces in the Event logs based on the timestamp of the first event
			//=================================================================================

			XLog log = XESUtil.readXESLog(this.inputLogPath);
			int oriLogSize = log.size();
			
			//filter the log
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			XLog filterredLog = XESUtil.standardFiltering(log, true, new PrintStream(baos, true, "UTF-8"));
			log = filterredLog;
		    String outputFiltering = new String(baos.toByteArray(), StandardCharsets.UTF_8);
			System.out.print(outputFiltering);
		    mainOutputWriter.append(outputFiltering);
			
		    //sorted the log based on the timestamp
			XLog sortedLog = XESUtil.sortXLogBasedOnTimeStamp(filterredLog);
			log = sortedLog;
			
			//=================================================================================
			//END OF Read and Preprocess log - 
			//filter the XES Log and Sort traces in the Event logs based on the timestamp of the first event
			//=================================================================================

			//=================================================================================
			//Split Event Logs into Train and Test
			//=================================================================================

			int logSize = log.size();
			//int numTrainingData = logSize * 2 / 3;
			//int numTrainingData = Math.round(((float)70/100) * logSize);
			int numTrainingData = Math.round(trainDataProportion * logSize);
			XFactory fact = new XFactoryNaiveImpl();
			XLog trainLog = (XLog) log.clone();
			XLog testLog = fact.createLog();
			
			while (trainLog.size() > numTrainingData) {
				testLog.add(0, trainLog.get(trainLog.size()-1));
				trainLog.remove(trainLog.size()-1);
			}
			
			this.xlogOri = log; //the original log after preprocessing, but before the split into the train/test log
			this.xlogTrain = trainLog;
			this.xlogTest = testLog;

			//=================================================================================
			//END OF Split Event Logs
			//=================================================================================
			

			//=================================================================================
			//Print Output
			//=================================================================================

			StringBuilder output = new StringBuilder();

			output.append("Event Log original size: "+oriLogSize+"\n");
			output.append("Event Log size after filtering some traces: "+filterredLog.size()+"\n\n");
			output.append("training log size: " + trainLog.size()+"\n");
			output.append("test log size: " + testLog.size()+"\n");
			output.append("max trace length - log: " + XESUtil.getMaxTracesLength(log)+"\n");
			output.append("min trace length - log: " + XESUtil.getMinTracesLength(log)+"\n");
			output.append("max trace length - trainLog: " + XESUtil.getMaxTracesLength(trainLog)+"\n");
			output.append("min trace length - trainLog: " + XESUtil.getMinTracesLength(trainLog)+"\n");
			output.append("max trace length - testLog: " + XESUtil.getMaxTracesLength(testLog)+"\n");
			output.append("min trace length - testLog: " + XESUtil.getMinTracesLength(testLog)+"\n");
			output.append("-------------------------------------------------------------------------------------------\n");
			output.append("-------------------------------------------------------------------------------------------\n");
			output.append("END OF filterAndPrepareTheTrainTestLog\n");
			output.append("-------------------------------------------------------------------------------------------\n");
			output.append("-------------------------------------------------------------------------------------------\n");
			output.append("\n\n\n\n\n\n\n\n\n\n");

			System.out.print(output.toString());
			mainOutputWriter.append(output.toString());
			mainOutputWriter.flush();

			//=================================================================================
			//END OF Print Output
			//=================================================================================

		} catch (Exception e) {
			e.printStackTrace();
		}	

	}

	////////////////////////////////////////////////////////////////////////////
	// END OF Initialization
	////////////////////////////////////////////////////////////////////////////

	
	////////////////////////////////////////////////////////////////////////////
	// PYTHON MODE - Train and Evaluate Models
	////////////////////////////////////////////////////////////////////////////
	
	/**
	 * train and evaluate the models - Classification task - Python Mode
	 */
	private void trainAndEvaluateClassificationModelsPythonMode() throws Exception{
	
		StringBuilder allSummary = new StringBuilder("");//for the summary at the end of the day!
		
		StringBuilder experimentSummary = new StringBuilder("\n\n\n\n\n\n\n\n\n\n");
		experimentSummary.append("=========================================================================================================\n");
		experimentSummary.append("Experiment Summary - "+this.title+"\n");
		experimentSummary.append("=========================================================================================================\n\n");

		////////////////////////////////////////////////////////////////////
		//SETTING!!!
		////////////////////////////////////////////////////////////////////
		
			String trainInstanceCodeName = identifier +"-Train-";
			String testInstanceCodeName = identifier +"-Test-";
			
			//====================================================================
			// Some sanity check
			//====================================================================
			
			// ENCODING CHOICE				
			if(encodingType == null)
				throw new Exception("There is no specification of the desired encoding");

			for(int ii = 0; ii < encodingType.length; ii++){
				
				if(this.encodingType[ii] == EncodingType.OneHotV2 && this.oneHotEncodingV2Info == null)
					throw new Exception("One hot encoding is chosen but the specification is not yet given");
				
				if(this.encodingType[ii] == EncodingType.AttEnc && this.attEncodingInfo == null)
					throw new Exception("One attribute encoding is chosen but the specification is not yet given");
			}

			//====================================================================
			// END OF Some sanity check
			//====================================================================

		////////////////////////////////////////////////////////////////////
		//END OF SETTING!!!
		////////////////////////////////////////////////////////////////////


			
		////////////////////////////////////////////////////////////////////
		//Print Input info
		////////////////////////////////////////////////////////////////////
		
		StringBuilder inputInfo = new StringBuilder();
		
		inputInfo.append("\n\n\n\n\n\n\n\n\n\n"
				+ "-------------------------------------------------------------------------------------------\n"
				+ "START: Building the Training and Test Instances\n"
				+ "-------------------------------------------------------------------------------------------\n\n");
		inputInfo.append("ori log size: " + xlogOri.size()+"\n");
		inputInfo.append("training log size: " + xlogTrain.size()+"\n");
		inputInfo.append("test log size: " + xlogTest.size()+"\n");
		inputInfo.append("max trace length: " + XESUtil.getMaxTracesLength(xlogOri)+"\n");
		inputInfo.append("min trace length: " + XESUtil.getMinTracesLength(xlogOri)+"\n");
			
		System.out.print(inputInfo);
		mainOutputWriter.append(inputInfo);
		allSummary.append(inputInfo);

		StringBuilder encodingInfo = new StringBuilder("");
		
		encodingInfo.append("\nEncodings:"+"\n");
		
		for(OneHotEncodingV2Info ohi:oneHotEncodingV2Info)
			encodingInfo.append(ohi.toString()+"\n");

		for(AttributeEncodingInfo ae:attEncodingInfo)
			encodingInfo.append(ae.toString()+"\n");
		
		encodingInfo.append("END OF Encodings"+"\n\n");

		allSummary.append(encodingInfo);
		experimentSummary.append(encodingInfo);
		
		////////////////////////////////////////////////////////////////////
		//END OF Print Input info
		////////////////////////////////////////////////////////////////////
		

		
		////////////////////////////////////////////////////////////////////
		//CREATE TRAINING & TEST INSTANCES
		////////////////////////////////////////////////////////////////////			
		
		ArrayList<Pair<String, Instances>> testInstances = new ArrayList<Pair<String, Instances>>();
		
		AnalyticRulesInstance ari = ars.computeAnalyticRulesInstanceAllPrefixLength(2, xlogTrain);
		Instances trainingInstances = ari.computeWEKAInstances(trainInstanceCodeName, encodingType, oneHotEncodingV2Info, attEncodingInfo);
		testInstances.add(new Pair<String,Instances>("TEST WITH TRAINING INSTANCE", trainingInstances));
		
		AnalyticRulesInstance ariTestEarly = ars.computeAnalyticRulesInstanceWithFixPrefixLength(PrefLengthType.EARLY_EVENT, xlogTest);
		ariTestEarly.setAllPossibleTargetValue(ari.getAllPossibleTargetValue());
		Instances testInstancesEarly = ariTestEarly.computeWEKAInstances(testInstanceCodeName+"Early", encodingType, oneHotEncodingV2Info, attEncodingInfo);
		testInstances.add(new Pair<String,Instances>("EARLY PREDICTION", testInstancesEarly));
		
		AnalyticRulesInstance ariTestMid = ars.computeAnalyticRulesInstanceWithFixPrefixLength(PrefLengthType.MID_EVENT, xlogTest);
		ariTestMid.setAllPossibleTargetValue(ari.getAllPossibleTargetValue());
		Instances testInstancesMid = ariTestMid.computeWEKAInstances(testInstanceCodeName+"Mid", encodingType, oneHotEncodingV2Info, attEncodingInfo);
		testInstances.add(new Pair<String,Instances>("MID PREDICTION", testInstancesMid));

		AnalyticRulesInstance ariTestLate = ars.computeAnalyticRulesInstanceWithFixPrefixLength(PrefLengthType.LATE_EVENT, xlogTest);
		ariTestLate.setAllPossibleTargetValue(ari.getAllPossibleTargetValue());
		Instances testInstancesLate = ariTestLate.computeWEKAInstances(testInstanceCodeName+"Late", encodingType, oneHotEncodingV2Info, attEncodingInfo);
		testInstances.add(new Pair<String,Instances>("LATE PREDICTION", testInstancesLate));
		
		AnalyticRulesInstance ariALL = ars.computeAnalyticRulesInstanceAllPrefixLength(2, xlogTest);
		ariALL.setAllPossibleTargetValue(ari.getAllPossibleTargetValue());
		Instances testInstancesALL = ariALL.computeWEKAInstances(testInstanceCodeName+"ALL", encodingType, oneHotEncodingV2Info, attEncodingInfo);
		testInstances.add(new Pair<String,Instances>("PREDICTION FOR ALL POSSIBLE PREFIXES", testInstancesALL));
		
		StringBuilder trainInfo = new StringBuilder();
		trainInfo.append("num of training instances: " + trainingInstances.size()+"\n");
		trainInfo.append("num of test instances (Early Events): " + testInstancesEarly.size()+"\n");
		trainInfo.append("num of test instances (Mid Events): " + testInstancesMid.size()+"\n");
		trainInfo.append("num of test instances (Late Events): " + testInstancesLate.size()+"\n");
		trainInfo.append("num of test instances (All): " + testInstancesALL.size()+"\n\n");
		trainInfo.append("-------------------------------------------------------------------------------------------\n");
		trainInfo.append("END OF Building the Training and Test Instances\n");
		trainInfo.append("-------------------------------------------------------------------------------------------\n");
		trainInfo.append("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

		allSummary.append(trainInfo);
		System.out.print(trainInfo);
		mainOutputWriter.append(trainInfo);
		mainOutputWriter.flush();
		
		////////////////////////////////////////////////////////////////////
		//END OF CREATE TRAINING & TEST INSTANCES
		////////////////////////////////////////////////////////////////////

		
		////////////////////////////////////////////////////////////////////
		// PREPARE PYTHON DATA
		////////////////////////////////////////////////////////////////////
		
		//--------------------------------------------------------
		// convert training and testing data for Python
		//--------------------------------------------------------
		
		long[] featuresTrain = PythonHelper.getFeaturesFromInstances(trainingInstances);
		String[] targetTrain = PythonHelper.getNominalTargetFromInstances(trainingInstances);
		NDArray<long[]> ndFeatures = new NDArray<>(featuresTrain, trainingInstances.numInstances(), 
				trainingInstances.numAttributes() - 1);
		
		long[] earlyTestData = PythonHelper.getFeaturesFromInstances(testInstancesEarly);
		String[] earlyTestTarget = PythonHelper.getNominalTargetFromInstances(testInstancesEarly);
		NDArray<long[]> ndEarlyTest = new NDArray<>(earlyTestData, testInstancesEarly.numInstances(),
				testInstancesEarly.numAttributes() - 1);
		
		long[] midTestData = PythonHelper.getFeaturesFromInstances(testInstancesMid);
		String[] midTestTarget = PythonHelper.getNominalTargetFromInstances(testInstancesMid);
		NDArray<long[]> ndMidTest = new NDArray<>(midTestData, testInstancesMid.numInstances(),
				testInstancesMid.numAttributes() - 1);
		
		long[] lateTestData = PythonHelper.getFeaturesFromInstances(testInstancesLate);
		String[] lateTestTarget = PythonHelper.getNominalTargetFromInstances(testInstancesLate);
		NDArray<long[]> ndLateTest = new NDArray<>(lateTestData, testInstancesLate.numInstances(),
				testInstancesLate.numAttributes() - 1);
		
		long[] allTestData = PythonHelper.getFeaturesFromInstances(testInstancesALL);
		String[] allTestTarget = PythonHelper.getNominalTargetFromInstances(testInstancesALL);
		NDArray<long[]> ndAllTest = new NDArray<>(allTestData, testInstancesALL.numInstances(),
				testInstancesALL.numAttributes() - 1);
		
		//--------------------------------------------------------
		// END OF convert training and testing data for Python
		//--------------------------------------------------------

		// K-Fold related stuff
		/*
		long[] featuresTest = PythonHelper.getFeaturesFromInstances(testInstancesALL);
		String[] targetTestStr = PythonHelper.getNominalTargetFromInstances(testInstancesALL);
		
		long[] featuresFold = Arrays.copyOf(featuresTrain, featuresTrain.length + featuresTest.length);
		System.arraycopy(featuresTest, 0, featuresFold, featuresTrain.length, featuresTest.length);
		String[] targetFoldStr = Arrays.copyOf(targetTrain, targetTrain.length + targetTestStr.length);
		System.arraycopy(targetTestStr, 0, targetFoldStr, targetTrain.length, targetTestStr.length);
		*/
		
		//NDArray<long[]> ndFeaturesFold = new NDArray<>(featuresFold, targetFoldStr.length, trainingInstances.numAttributes() - 1);

		//--------------------------------------------------------
		// Run the imports statement in Python
		//--------------------------------------------------------
		for (String cmd : initsStatements) 
            jep.eval(cmd);
		
		//--------------------------------------------------------
		// Create 'python function' for creating NN model
		//--------------------------------------------------------
		if(this.sdpromMode == Mode.NEURAL_NETWORK)
			jep.eval(nnFunction);
		
		//--------------------------------------------------------
		// Create 'python function' for building and evaluating models
		//--------------------------------------------------------
		jep.eval(evaluation);
		
		//--------------------------------------------------------
		// Set the test & train data in python
		//--------------------------------------------------------
		jep.set("training", ndFeatures);
		jep.set("target", targetTrain);
		
		jep.set("early_test_data", ndEarlyTest);
		jep.set("early_target", earlyTestTarget);
		
		jep.set("mid_test_data", ndMidTest);
		jep.set("mid_target", midTestTarget);
		
		jep.set("late_test_data", ndLateTest);
		jep.set("late_target", lateTestTarget);
		
		jep.set("all_test_data", ndAllTest);
		jep.set("all_target", allTestTarget);
		
		// set a variable that is used as argument of the NN function
		if(this.sdpromMode == Mode.NEURAL_NETWORK)
			jep.set("nr_features", trainingInstances.numAttributes() - 1);
		
		//--------------------------------------------------------
		// initialize the models and create a list of them 
		// (it is a list of pair between 'model name' and the 'model object')
		// at this point, we also create a list of testing dataset
		//--------------------------------------------------------
		for (String cmd : modelsStatements) 
			jep.eval(cmd);
		
		Integer nModels = (int)((long)jep.getValue("len(models)"));
		
		double[] accuracyList = new double[nModels];
		double[] aucList = new double[nModels];
		double[] wPrecisionList = new double[nModels];
		double[] wRecallList = new double[nModels];
		
		////////////////////////////////////////////////////////////////////
		// END OF PREPARE PYTHON DATA
		////////////////////////////////////////////////////////////////////

		
		////////////////////////////////////////////////////////////////////
		//TRAIN AND EVALUATE CLASSIFIERS
		////////////////////////////////////////////////////////////////////
		
		//=================================================
		//FOR LATEX TABLE
		//=================================================
		
		EvalTableClassification table = 
				new EvalTableClassification(this.identifier, this.title);
		
		EvalTableClassificationContent tableContentTraining = 
				new EvalTableClassificationContent("Evaluation on Training Data");
		
		EvalTableClassificationContent tableContentTest = 
				new EvalTableClassificationContent("Evaluation on Test Data");
		
		//=================================================
		//END OF FOR LATEX TABLE
		//=================================================
		
		// loop all the models
		for (int i = 0; i < nModels; i++) {
			StringBuilder partialOutout = new StringBuilder("");//partial output is for showing the partial output during the training
			long startTime = System.currentTimeMillis();
			
			String modelName = (String) jep.getValue("models[" + i + "][0]");//get model name
			
			StringBuilder output = new StringBuilder();
			output.append("\n\n\n\n\n");
			output.append("----------------------------------------------------------------------------------------------------\n");
			output.append("START: Building and Evaluating the classifier "+modelName+"\n");
			output.append("----------------------------------------------------------------------------------------------------\n\n");

			experimentSummary.append("\n----------------------------------------------------------------------------------------------------\n");
			experimentSummary.append(modelName+"\n");
			
			System.out.print(output);
			mainOutputWriter.append(output+"\n");
			partialOutout.append(output);

			//--------------------------------------------------------
			//execute the python function 'build_and_evaluate'
			//--------------------------------------------------------
			jep.set("outputModelFile", outputModelLocation + this.identifier);
			jep.eval("result = build_and_evaluate(models[" +i+ "], outputModelFile, test_list)");
			//--------------------------------------------------------
			//END OF executing the python function 'build_and_evaluate'
			//--------------------------------------------------------
			
			System.out.println("Model Type: "+jep.getValue("result[0]['modelType']") );
			
			Integer nResult = (int)((long) jep.getValue("len(result)"));			

			StringBuilder buildMsg = new StringBuilder("Building "+modelName+"\n");
			buildMsg.append("Params used:\n" + jep.getValue("result[0]['params']") + "\n");
			System.out.println(buildMsg);
			mainOutputWriter.append(buildMsg);
			mainOutputWriter.flush();

			StringBuilder evalMsg = new StringBuilder("Evaluating "+modelName+"\n");
			System.out.println(evalMsg);
			mainOutputWriter.append(evalMsg);
			mainOutputWriter.flush();
			
			
			//--------------------------------------------------------
			// loop the result - loop over all evaluation results that were done in python
			//--------------------------------------------------------
			for (int j = 0; j < nResult; j++) {
				String testInstName = (String) jep.getValue("result[" +j+ "]['testname']");
				
				NDArray<double[]> ndPrecisions = (NDArray<double[]>)jep.getValue("result[" +j+ "]['precisions']");
				double[] prec = ndPrecisions.getData();
				NDArray<double[]> ndRecalls = (NDArray<double[]>)jep.getValue("result[" +j+ "]['recalls']");
				double[] rec = ndRecalls.getData();
				NDArray<double[]> ndFmeasure = (NDArray<double[]>)jep.getValue("result[" +j+ "]['f1_scores']");
				double[] f1 = ndFmeasure.getData();
				NDArray<long[]> ndSupports = (NDArray<long[]>)jep.getValue("result[" +j+ "]['support']");
				long[] support = ndSupports.getData();
				
				StringBuilder evaluationOutput = new StringBuilder();
				
				evaluationOutput.append("\n"+testInstName+"\n");
				String label0 = (String) jep.getValue("result[" +j+ "]['labels'][0]");
				String label1 = (String) jep.getValue("result[" +j+ "]['labels'][1]");
				evaluationOutput.append("Class [0]: " + label0 + "\n");
				evaluationOutput.append("Class [1]: " + label1 + "\n");
				
				evaluationOutput.append("Mean accuracy: " + jep.getValue("result[" +j+ "]['accuracy']") + "\n");
				evaluationOutput.append("roc_auc_0: " + jep.getValue("result[" +j+ "]['roc_auc_0']") + "\n");
				evaluationOutput.append("roc_auc_1: " + jep.getValue("result[" +j+ "]['roc_auc_1']") + "\n");
				evaluationOutput.append("AUC 1: " + jep.getValue("result[" +j+ "]['auc1']") + "\n");
				evaluationOutput.append("AUC 0: " + jep.getValue("result[" +j+ "]['auc0']") + "\n");
				
				evaluationOutput.append("Precisions:\n - " +label0+ ": " + prec[0] + "\n - " +label1+ ": " + prec[1] + "\n");
				evaluationOutput.append("Weighted precision: " + jep.getValue("result[" +j+ "]['weighted_precision']") + "\n");
				evaluationOutput.append("Recalls:\n - " +label0+ ": " + rec[0] + "\n - " +label1+ ": " + rec[1] + "\n");
				evaluationOutput.append("Weighted recall: " + jep.getValue("result[" +j+ "]['weighted_recall']") + "\n");
				evaluationOutput.append("F measures:\n - " +label0+ ": " + f1[0] + "\n - " +label1+ ": " + f1[1] + "\n");
				evaluationOutput.append("Weighted F-measure: " + jep.getValue("result[" +j+ "]['weighted_f1']") + "\n");
				evaluationOutput.append("Support:\n - " +label0+ ": " + support[0] + "\n - " +label1+ ": " + support[1] + "\n\n");
				
				partialOutout.append(evaluationOutput);
				System.out.print(evaluationOutput);
				mainOutputWriter.append(evaluationOutput);
				mainOutputWriter.flush();
				
				//=================================================
				//FOR LATEX TABLE
				//=================================================
				
				if (testInstName.equals("TEST WITH TRAINING INSTANCE") || 
						testInstName.equals("PREDICTION FOR ALL POSSIBLE PREFIXES")) {
				
					String lAUC = String.format("%.2f", jep.getValue("result[" +j+ "]['auc1']"));
					String lAcc = String.format("%.2f", jep.getValue("result[" +j+ "]['accuracy']"));
					String lWPrecision = String.format("%.2f", jep.getValue("result[" +j+ "]['weighted_precision']"));
					String lWRecall= String.format("%.2f", jep.getValue("result[" +j+ "]['weighted_recall']"));
					String lWFMeasure= String.format("%.2f", jep.getValue("result[" +j+ "]['weighted_f1']"));
					
					EvalTableClassificationRow row = new EvalTableClassificationRow(
							modelName, lAUC, lAcc, lWPrecision, lWRecall, lWFMeasure);
					
					for (int kk = 0; kk < trainingInstances.numClasses(); kk ++) {
					
						String className = trainingInstances.classAttribute().value(kk);
						String classPrecision = String.format("%.2f", prec[kk]);
						String classRecall= String.format("%.2f", rec[kk]);
						String classFMeasure= String.format("%.2f", f1[kk]);
					
						row.addSubRow(className, classPrecision, classRecall, classFMeasure);
					}
					
					if (testInstName.equals("TEST WITH TRAINING INSTANCE"))
						tableContentTraining.addRow(row);
					
					if (testInstName.equals("PREDICTION FOR ALL POSSIBLE PREFIXES"))
						tableContentTest.addRow(row);
				}
				
				//=================================================
				//END OF FOR LATEX TABLE
				//=================================================

				//if (j == testInstances.size() - 1) {
				if (testInstName.equals("PREDICTION FOR ALL POSSIBLE PREFIXES")) {
					
					experimentSummary.append(evaluationOutput);
					accuracyList[i] = (double) jep.getValue("result[" +j+ "]['accuracy']");
					aucList[i] = (double) jep.getValue("result[" +j+ "]['auc1']");
					wPrecisionList[i] = (double) jep.getValue("result[" +j+ "]['weighted_precision']");
					wRecallList[i] = (double) jep.getValue("result[" +j+ "]['weighted_recall']");
				}
			}
			//--------------------------------------------------------
			// END OF loop the result - loop over all evaluation results that were done in python
			//--------------------------------------------------------

			
			System.out.print("------------------------------------------------------------------------\n");
			mainOutputWriter.append("------------------------------------------------------------------------\n");
			
			long runTime = (System.currentTimeMillis() - startTime);
			
			String runTimeStr = "Total running time of " + modelName + " is " +
			(runTime/60000)+" minute(s) or "+runTime+ " millisecond(s)\n";
			
			System.out.println(runTimeStr);
			mainOutputWriter.append(runTimeStr);
			partialOutout.append("\n"+runTimeStr+"\n\n");
			
			StringBuilder endBuildEvalMsg = new StringBuilder();
			endBuildEvalMsg.append("\n\n----------------------------------------------------------------------------------------------------\n");
			endBuildEvalMsg.append("END OF Building and Evaluating "+modelName+"\n");
			endBuildEvalMsg.append("----------------------------------------------------------------------------------------------------\n\n");
			
			System.out.println(endBuildEvalMsg);
			mainOutputWriter.append(endBuildEvalMsg);
			partialOutout.append(endBuildEvalMsg);
			mainOutputWriter.flush();
			
			allSummary.append(partialOutout);
			
			experimentSummary.append("----------------------------------------------------------------------------------------------------\n");		
			
		}
		
//		jep.eval("K.clear_session()");
		//=================================================
		//Writing Output
		//=================================================

			//=================================================
			//FOR LATEX TABLE
			//=================================================
			table.addContent(tableContentTraining);
			table.addContent(tableContentTest);
			latexOutputWriter.append(table.generateLatexTableDetail());
			latexOutputShortWriter.append(table.generateLatexTableOverview());
			latexOutputWriter.flush();
			latexOutputShortWriter.flush();
			//=================================================
			//END OF FOR LATEX TABLE
			//=================================================

		experimentSummary.append("=========================================================================================================\n");
		experimentSummary.append("END OF Experiment Summary - "+this.title+"\n");
		experimentSummary.append("=========================================================================================================\n\n");

		StringBuilder output = new StringBuilder();
		
		output.append("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		output.append("=========================================================================================================\n");
		output.append("FINAL SUMMARY: \n");
		output.append("=========================================================================================================\n");
		output.append(allSummary);
		output.append("=========================================================================================================\n");
		output.append(experimentSummary);

		output.append("=========================================================================================================\n");
		output.append("Accuracy summary: ");
		for (double accuracy : accuracyList) {
			output.append(accuracy + ", ");
		}
		output.append("\n");
		
		output.append("AUC summary: ");
		for (double auc : aucList) {
			output.append(auc + ", ");
		}
		output.append("\n");
		
		output.append("Weighted precision summary: ");
		for (double prec : wPrecisionList) {
			output.append(prec + ", ");
		}
		output.append("\n");
		
		output.append("Weighted recall summary: ");
		for (double rec : wRecallList) {
			output.append(rec + ", ");
		}
		output.append("\n");
		
		output.append("=========================================================================================================\n");
		
		System.out.print(output);
		mainOutputWriter.append(output);

		//=================================================
		//END OF Writing Output
		//=================================================

		////////////////////////////////////////////////////////////////////
		//END OF TRAIN AND EVALUATE CLASSIFIERS
		////////////////////////////////////////////////////////////////////
			
	}

	/**
	 * train and evaluate the models - Regression task - Python Mode
	 */
	private void trainAndEvaluateRegressionModelsPythonMode() throws Exception{
	
		StringBuilder allSummary = new StringBuilder("");//for the summary at the end of the day!
		
		StringBuilder experimentSummary = new StringBuilder("\n\n\n\n\n\n\n\n\n\n");
		experimentSummary.append("=========================================================================================================\n");
		experimentSummary.append("Experiment Summary - "+this.title+"\n");
		experimentSummary.append("=========================================================================================================\n\n");


		////////////////////////////////////////////////////////////////////
		//SETTING!!!
		////////////////////////////////////////////////////////////////////
		
			String trainInstanceCodeName = identifier +"-Train-";
			String testInstanceCodeName = identifier +"-Test-";
			
			//====================================================================
			// Some sanity check
			//====================================================================
			
			// ENCODING CHOICE				
			if(encodingType == null)
				throw new Exception("There is no specification of the desired encoding");

			for(int ii = 0; ii < encodingType.length; ii++){
				
				if(this.encodingType[ii] == EncodingType.OneHotV2 && this.oneHotEncodingV2Info == null)
					throw new Exception("One hot encoding is chosen but the specification is not yet given");
				
				if(this.encodingType[ii] == EncodingType.AttEnc && this.attEncodingInfo == null)
					throw new Exception("One attribute encoding is chosen but the specification is not yet given");
			}
		
			//====================================================================
			// END OF Some sanity check
			//====================================================================

		////////////////////////////////////////////////////////////////////
		//END OF SETTING!!!
		////////////////////////////////////////////////////////////////////


			
		////////////////////////////////////////////////////////////////////
		//Print Input info
		////////////////////////////////////////////////////////////////////
		
		StringBuilder inputInfo = new StringBuilder();
		
		inputInfo.append("\n\n\n\n\n\n\n\n\n\n"
				+ "-------------------------------------------------------------------------------------------\n"
				+ "START: Building the Training and Test Instances\n"
				+ "-------------------------------------------------------------------------------------------\n\n");
		inputInfo.append("ori log size: " + xlogOri.size()+"\n");
		inputInfo.append("training log size: " + xlogTrain.size()+"\n");
		inputInfo.append("test log size: " + xlogTest.size()+"\n");
		inputInfo.append("max trace length: " + XESUtil.getMaxTracesLength(xlogOri)+"\n");
		inputInfo.append("min trace length: " + XESUtil.getMinTracesLength(xlogOri)+"\n");
			
		System.out.print(inputInfo);
		mainOutputWriter.append(inputInfo);
		allSummary.append(inputInfo);

		StringBuilder encodingInfo = new StringBuilder("");
		
		encodingInfo.append("\nEncodings:"+"\n");
		
		for(OneHotEncodingV2Info ohi:oneHotEncodingV2Info)
			encodingInfo.append(ohi.toString()+"\n");

		for(AttributeEncodingInfo ae:attEncodingInfo)
			encodingInfo.append(ae.toString()+"\n");
		
		encodingInfo.append("END OF Encodings"+"\n\n");

		allSummary.append(encodingInfo);
		experimentSummary.append(encodingInfo);

		////////////////////////////////////////////////////////////////////
		//END OF Print Input info
		////////////////////////////////////////////////////////////////////
		

		
		////////////////////////////////////////////////////////////////////
		//CREATE TRAINING & TEST INSTANCES
		////////////////////////////////////////////////////////////////////			

		ArrayList<Pair<String, Instances>> testInstances = new ArrayList<Pair<String, Instances>>();
		
		AnalyticRulesInstance ari = ars.computeAnalyticRulesInstanceAllPrefixLength(2, xlogTrain);
		Instances trainingInstances = ari.computeWEKAInstances(trainInstanceCodeName, encodingType, oneHotEncodingV2Info, attEncodingInfo);
		testInstances.add(new Pair<String,Instances>("TEST WITH TRAINING INSTANCE", trainingInstances));
		
		AnalyticRulesInstance ariTestEarly = ars.computeAnalyticRulesInstanceWithFixPrefixLength(PrefLengthType.EARLY_EVENT, xlogTest);
		ariTestEarly.setAllPossibleTargetValue(ari.getAllPossibleTargetValue());
		Instances testInstancesEarly = ariTestEarly.computeWEKAInstances(testInstanceCodeName+"Early", encodingType, oneHotEncodingV2Info, attEncodingInfo);
		testInstances.add(new Pair<String,Instances>("EARLY PREDICTION", testInstancesEarly));
		
		AnalyticRulesInstance ariTestMid = ars.computeAnalyticRulesInstanceWithFixPrefixLength(PrefLengthType.MID_EVENT, xlogTest);
		ariTestMid.setAllPossibleTargetValue(ari.getAllPossibleTargetValue());
		Instances testInstancesMid = ariTestMid.computeWEKAInstances(testInstanceCodeName+"Mid", encodingType, oneHotEncodingV2Info, attEncodingInfo);
		testInstances.add(new Pair<String,Instances>("MID PREDICTION", testInstancesMid));

		AnalyticRulesInstance ariTestLate = ars.computeAnalyticRulesInstanceWithFixPrefixLength(PrefLengthType.LATE_EVENT, xlogTest);
		ariTestLate.setAllPossibleTargetValue(ari.getAllPossibleTargetValue());
		Instances testInstancesLate = ariTestLate.computeWEKAInstances(testInstanceCodeName+"Late", encodingType, oneHotEncodingV2Info, attEncodingInfo);
		testInstances.add(new Pair<String,Instances>("LATE PREDICTION", testInstancesLate));
		
		AnalyticRulesInstance ariALL = ars.computeAnalyticRulesInstanceAllPrefixLength(2, xlogTest);
		ariALL.setAllPossibleTargetValue(ari.getAllPossibleTargetValue());
		Instances testInstancesALL = ariALL.computeWEKAInstances(testInstanceCodeName+"ALL", encodingType, oneHotEncodingV2Info, attEncodingInfo);
		testInstances.add(new Pair<String,Instances>("PREDICTION FOR ALL POSSIBLE PREFIXES", testInstancesALL));
		
		StringBuilder trainInfo = new StringBuilder();
		trainInfo.append("num of training instances: " + trainingInstances.size()+"\n");
		trainInfo.append("num of test instances (Early Events): " + testInstancesEarly.size()+"\n");
		trainInfo.append("num of test instances (Mid Events): " + testInstancesMid.size()+"\n");
		trainInfo.append("num of test instances (Late Events): " + testInstancesLate.size()+"\n");
		trainInfo.append("num of test instances (All): " + testInstancesALL.size()+"\n\n");
		trainInfo.append("-------------------------------------------------------------------------------------------\n");
		trainInfo.append("END OF Building the Training and Test Instances\n");
		trainInfo.append("-------------------------------------------------------------------------------------------\n");
		trainInfo.append("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

		allSummary.append(trainInfo);
		System.out.print(trainInfo);
		mainOutputWriter.append(trainInfo);
		mainOutputWriter.flush();
		
		////////////////////////////////////////////////////////////////////
		//END OF CREATE TRAINING & TEST INSTANCES
		////////////////////////////////////////////////////////////////////

				
		
		////////////////////////////////////////////////////////////////////
		// PREPARE PYTHON DATA
		////////////////////////////////////////////////////////////////////
		
		
		//--------------------------------------------------------
		// convert training and testing data for Python
		//--------------------------------------------------------

		long[] featuresTrain = PythonHelper.getFeaturesFromInstances(trainingInstances);
		double[] targetTrain = PythonHelper.getNumericTargetFromInstances(trainingInstances);
		NDArray<long[]> ndFeatures = new NDArray<>(featuresTrain, trainingInstances.numInstances(), 
				trainingInstances.numAttributes() - 1);
		NDArray<double[]> ndTarget = new NDArray<>(targetTrain);
		
		long[] earlyTestData = PythonHelper.getFeaturesFromInstances(testInstancesEarly);
		double[] earlyTestTarget = PythonHelper.getNumericTargetFromInstances(testInstancesEarly);
		NDArray<long[]> ndEarlyTest = new NDArray<>(earlyTestData, testInstancesEarly.numInstances(),
				testInstancesEarly.numAttributes() - 1);
		NDArray<double[]> ndEarlyTarget = new NDArray<>(earlyTestTarget);
		
		long[] midTestData = PythonHelper.getFeaturesFromInstances(testInstancesMid);
		double[] midTestTarget = PythonHelper.getNumericTargetFromInstances(testInstancesMid);
		NDArray<long[]> ndMidTest = new NDArray<>(midTestData, testInstancesMid.numInstances(),
				testInstancesMid.numAttributes() - 1);
		NDArray<double[]> ndMidTarget = new NDArray<>(midTestTarget);
		
		long[] lateTestData = PythonHelper.getFeaturesFromInstances(testInstancesLate);
		double[] lateTestTarget = PythonHelper.getNumericTargetFromInstances(testInstancesLate);
		NDArray<long[]> ndLateTest = new NDArray<>(lateTestData, testInstancesLate.numInstances(),
				testInstancesLate.numAttributes() - 1);
		NDArray<double[]> ndLateTarget = new NDArray<>(lateTestTarget);
		
		long[] allTestData = PythonHelper.getFeaturesFromInstances(testInstancesALL);
		double[] allTestTarget = PythonHelper.getNumericTargetFromInstances(testInstancesALL);
		NDArray<long[]> ndAllTest = new NDArray<>(allTestData, testInstancesALL.numInstances(),
				testInstancesALL.numAttributes() - 1);
		NDArray<double[]> ndAllTarget = new NDArray<>(allTestTarget);
		
		//--------------------------------------------------------
		// END OF convert training and testing data for Python
		//--------------------------------------------------------

		//--------------------------------------------------------
		// Run the imports statement in Python
		//--------------------------------------------------------
		for (String cmd : initsStatements) 
            jep.eval(cmd);
		
		//--------------------------------------------------------
		// Create 'python function' for creating NN model
		//--------------------------------------------------------
		if(this.sdpromMode == Mode.NEURAL_NETWORK)
			jep.eval(nnFunction);
		
		//--------------------------------------------------------
		// Create 'python function' for building and evaluating models
		//--------------------------------------------------------
		jep.eval(evaluation);
		
		//--------------------------------------------------------
		// Set the test & train data in python
		//--------------------------------------------------------
		jep.set("training", ndFeatures);
		jep.set("target", ndTarget);
		
		jep.set("early_test_data", ndEarlyTest);
		jep.set("early_target", ndEarlyTarget);
		
		jep.set("mid_test_data", ndMidTest);
		jep.set("mid_target", ndMidTarget);
		
		jep.set("late_test_data", ndLateTest);
		jep.set("late_target", ndLateTarget);
		
		jep.set("all_test_data", ndAllTest);
		jep.set("all_target", ndAllTarget);
		
		// set a variable that is used as argument of the NN function
		if(this.sdpromMode == Mode.NEURAL_NETWORK)
			jep.set("nr_features", trainingInstances.numAttributes() - 1);
		
		
		//--------------------------------------------------------
		// initialize the models and create a list of them 
		// (it is a list of pair between 'model name' and the 'model object')
		// at this point, we also create a list of testing dataset
		//--------------------------------------------------------
		for (String cmd : modelsStatements) 
			jep.eval(cmd);
				
		Integer nModels = (int)((long)jep.getValue("len(models)"));
		
		double[] maeList = new double[nModels];
		double[] rmseList = new double[nModels];
		
		////////////////////////////////////////////////////////////////////
		// END OF PREPARE PYTHON DATA
		////////////////////////////////////////////////////////////////////

		

		////////////////////////////////////////////////
		//FOR LATEX TABLE
		////////////////////////////////////////////////				
		
		EvalTableRegression table = 
				new EvalTableRegression(this.identifier, this.title);
		
		EvalTableRegressionContent tableContentTraining = 
				new EvalTableRegressionContent("Evaluation on Training Data");
		
		EvalTableRegressionContent tableContentTest = 
				new EvalTableRegressionContent("Evaluation on Test Data");
		
		////////////////////////////////////////////////
		//END OF FOR LATEX TABLE
		////////////////////////////////////////////////
		
		
		
		////////////////////////////////////////////////////////////////////
		//TRAIN AND EVALUATE REGRESSION MODELS
		////////////////////////////////////////////////////////////////////

		// loop all the models
		for (int i = 0; i < nModels; i++) {
			StringBuilder partialOutout = new StringBuilder("");//partial output is for showing the partial output during the training
			long startTime = System.currentTimeMillis();
			
			String modelName = (String) jep.getValue("models[" + i + "][0]");
			
			StringBuilder output = new StringBuilder();
			output.append("\n\n\n\n\n");
			output.append("----------------------------------------------------------------------------------------------------\n");
			output.append("START: Building and Evaluating the regressor "+modelName+"\n");
			output.append("----------------------------------------------------------------------------------------------------\n\n");

			experimentSummary.append("\n----------------------------------------------------------------------------------------------------\n");
			experimentSummary.append(modelName+"\n");
			
			System.out.print(output);
			mainOutputWriter.append(output+"\n");
			partialOutout.append(output);

			//--------------------------------------------------------
			//execute the python function 'build_and_evaluate'
			//--------------------------------------------------------
			jep.set("outputModelFile", outputModelLocation + this.identifier);
			jep.eval("result = build_and_evaluate(models[" +i+ "], outputModelFile, test_list)");
			//--------------------------------------------------------
			//END OF executing the python function 'build_and_evaluate'
			//--------------------------------------------------------
			
			Integer nResult = (int)((long) jep.getValue("len(result)"));

			StringBuilder buildMsg = new StringBuilder("Building "+modelName+"\n");
			buildMsg.append("Params used:\n" + jep.getValue("result[0]['params']") + "\n");
			System.out.println(buildMsg);
			mainOutputWriter.append(buildMsg);
			mainOutputWriter.flush();

			StringBuilder evalMsg = new StringBuilder("Evaluating "+modelName+"\n");
			System.out.println(evalMsg);
			mainOutputWriter.append(evalMsg);
			mainOutputWriter.flush();
			
			//--------------------------------------------------------
			// loop the result - loop over all evaluation results that were done in python
			//--------------------------------------------------------
			for (int j = 0; j < nResult; j++) {
				String testInstName = (String) jep.getValue("result[" +j+ "]['testname']");
				
				//double correlationCoef = (double) jep.getValue("result[" +j+ "]['correlation_coeff']");
				//double coefficientOfDetermination = (double) jep.getValue("result[" +j+ "]['coeff_of_determination']");
				double maeD = (double) jep.getValue("result[" +j+ "]['mae']");
				double rmseD = (double) jep.getValue("result[" +j+ "]['rmse']");
				
				//String correlationCoefficient = f.format(correlationCoef);
				//String coeffOfDetermination = f.format(coefficientOfDetermination);
				String mae = f.format(maeD);
				String maeHours = f.format( (maeD/(1000*60*60)) );
				String maeDays = f.format( (maeD/(1000*60*60*24)) );
				String rmse = f.format( rmseD);
				String rmseHours = f.format( (rmseD/(1000*60*60)) );
				String rmseDays = f.format( (rmseD/(1000*60*60*24)) );
				
				StringBuilder evaluationOutput= new StringBuilder();
				evaluationOutput.append("\n"+testInstName+"\n");
				//evaluationOutput.append("Correlation Coefficient: \t"+ correlationCoef +"\n");
				//evaluationOutput.append("Coefficient Of Determination: \t"+ coefficientOfDetermination +"\n");

				if(showErrorsInDaysOrHours){
					evaluationOutput.append("MAE: "+ mae+" i.e., "+maeHours+" hours, "+ " i.e., "+maeDays+" day(s)"+"\n");
					evaluationOutput.append("RMSE: "+ rmse+" i.e., "+rmseHours+" hours, i.e., "+rmseDays+" day(s)"+"\n");
					
				}else{
				
					evaluationOutput.append("MAE: " + mae + "\n");
					evaluationOutput.append("RMSE: " + rmse + "\n");
				}

				//=================================================
				//FOR LATEX TABLE
				//=================================================
				
				if (testInstName.equals("TEST WITH TRAINING INSTANCE") || 
						testInstName.equals("PREDICTION FOR ALL POSSIBLE PREFIXES")) {
				
//					EvalTableRegressionRow row = new EvalTableRegressionRow(
//						modelName, correlationCoefficient, coeffOfDetermination, 
//						mae, maeHours, maeDays, rmse, rmseHours, rmseDays);

					EvalTableRegressionRow row = new EvalTableRegressionRow(
							modelName, "0", "0", 
							mae, maeHours, maeDays, rmse, rmseHours, rmseDays);

					if (testInstName.equals("TEST WITH TRAINING INSTANCE"))
					tableContentTraining.addRow(row);
					
					if (testInstName.equals("PREDICTION FOR ALL POSSIBLE PREFIXES"))
					tableContentTest.addRow(row);
				}
				
				//=================================================
				//END OF FOR LATEX TABLE
				//=================================================

				partialOutout.append(evaluationOutput);
				System.out.print(evaluationOutput);
				mainOutputWriter.append(evaluationOutput);
				mainOutputWriter.flush();
				
				//if (j == testInstances.size() - 1) {
				if (testInstName.equals("PREDICTION FOR ALL POSSIBLE PREFIXES")) {
					experimentSummary.append(evaluationOutput);
					maeList[i] = maeD;
					rmseList[i] = rmseD;
				}
			}
			//--------------------------------------------------------
			// END OF loop the result - loop over all evaluation results that were done in python
			//--------------------------------------------------------

			
			System.out.print("------------------------------------------------------------------------\n");
			mainOutputWriter.append("------------------------------------------------------------------------\n");
			
			long runTime = (System.currentTimeMillis() - startTime);
			
			String runTimeStr = "Total running time of " + modelName + " is " +
			(runTime/60000)+" minute(s) or "+runTime+ " millisecond(s)\n";
			
			System.out.println(runTimeStr);
			mainOutputWriter.append(runTimeStr);
			partialOutout.append("\n"+runTimeStr+"\n\n");
			
			StringBuilder endBuildEvalMsg = new StringBuilder();
			endBuildEvalMsg.append("\n\n----------------------------------------------------------------------------------------------------\n");
			endBuildEvalMsg.append("END OF Building and Evaluating "+modelName+"\n");
			endBuildEvalMsg.append("----------------------------------------------------------------------------------------------------\n\n");
			
			System.out.println(endBuildEvalMsg);
			mainOutputWriter.append(endBuildEvalMsg);
			partialOutout.append(endBuildEvalMsg);
			mainOutputWriter.flush();
			
			allSummary.append(partialOutout);
			
			experimentSummary.append("----------------------------------------------------------------------------------------------------\n");			
		}
		
//		jep.eval("K.clear_session()");
		
		//=================================================
		//Writing Output
		//=================================================

			//=================================================
			//FOR LATEX TABLE
			//=================================================
			table.addContent(tableContentTraining);
			table.addContent(tableContentTest);
			latexOutputWriter.append(table.generateLatexTableDetail());
			latexOutputShortWriter.append(table.generateLatexTableDetail());
			latexOutputWriter.flush();
			latexOutputShortWriter.flush();
			//=================================================
			//END OF FOR LATEX TABLE
			//=================================================

		experimentSummary.append("=========================================================================================================\n");
		experimentSummary.append("END OF Experiment Summary - "+this.title+"\n");
		experimentSummary.append("=========================================================================================================\n\n");
		
		StringBuilder output = new StringBuilder();
		
		output.append("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		output.append("=========================================================================================================\n");
		output.append("FINAL SUMMARY: \n");
		output.append("=========================================================================================================\n");
		output.append(allSummary);
		output.append("=========================================================================================================\n");
		output.append(experimentSummary);

		output.append("=========================================================================================================\n");
		output.append("MAE summary:  ");
		for (double mae : maeList) 
			output.append(f.format(mae) + " | ");
		
		output.append("\n");
		output.append("RMSE summary: ");
		
		for (double rmse : rmseList) 
			output.append(f.format(rmse) + " | ");

		output.append("\n");
		output.append("=========================================================================================================\n");
		
		System.out.print(output);
		mainOutputWriter.append(output);
		//=================================================
		//END OF Writing Output
		//=================================================

		////////////////////////////////////////////////////////////////////
		//END OF TRAIN AND EVALUATE REGRESSION MODELS
		////////////////////////////////////////////////////////////////////
	}

	////////////////////////////////////////////////////////////////////////////
	// END OF PYTHON MODE - Train and Evaluate Models
	////////////////////////////////////////////////////////////////////////////

	
	////////////////////////////////////////////////////////////////////////////
	// WEKA MODE - Train and Evaluate Models
	////////////////////////////////////////////////////////////////////////////

	
	/**
	 * train and evaluate the models - Classification task - Weka Mode
	 */
	private void trainAndEvaluateClassificationModelsWekaMode() throws Exception{
	
		StringBuilder allSummary = new StringBuilder("");//for the summary at the end of the day!

		StringBuilder experimentSummary = new StringBuilder("\n\n\n\n\n\n\n\n\n\n");
		experimentSummary.append("=========================================================================================================\n");
		experimentSummary.append("Experiment Summary - "+this.title+"\n");
		experimentSummary.append("=========================================================================================================\n\n");

		////////////////////////////////////////////////////////////////////
		//SETTING!!!
		////////////////////////////////////////////////////////////////////
		
			String trainInstanceCodeName = identifier +"-Train-";
			String testInstanceCodeName = identifier +"-Test-";
			
			//====================================================================
			// Some sanity check
			//====================================================================
			
			// Analytic Rules
			if(ars == null)
				throw new Exception("There is no Analytic Rule Specification");

			// ENCODING CHOICE				
			if(encodingType == null)
				throw new Exception("There is no specification of the desired encoding");

			for(int ii = 0; ii < encodingType.length; ii++){
				
				if(this.encodingType[ii] == EncodingType.OneHotV2 && this.oneHotEncodingV2Info == null)
					throw new Exception("One hot encoding is chosen but the specification is not yet given");
				
				if(this.encodingType[ii] == EncodingType.AttEnc && this.attEncodingInfo == null)
					throw new Exception("One attribute encoding is chosen but the specification is not yet given");
			}
		
			// CLASSIFIER CHOICE
			if(models == null)
				throw new Exception("Models have not been specified");

			//====================================================================
			// END OF Some sanity check
			//====================================================================

		////////////////////////////////////////////////////////////////////
		//END OF SETTING!!!
		////////////////////////////////////////////////////////////////////


			
		////////////////////////////////////////////////////////////////////
		//Print Input info
		////////////////////////////////////////////////////////////////////
		
		StringBuilder inputInfo = new StringBuilder();
		
		inputInfo.append("\n\n\n\n\n\n\n\n\n\n"
				+ "-------------------------------------------------------------------------------------------\n"
				+ "START: Building the Training and Test Instances\n"
				+ "-------------------------------------------------------------------------------------------\n\n");
		inputInfo.append("ori log size: " + xlogOri.size()+"\n");
		inputInfo.append("training log size: " + xlogTrain.size()+"\n");
		inputInfo.append("test log size: " + xlogTest.size()+"\n");
		inputInfo.append("max trace length: " + XESUtil.getMaxTracesLength(xlogOri)+"\n");
		inputInfo.append("min trace length: " + XESUtil.getMinTracesLength(xlogOri)+"\n");
			
		System.out.print(inputInfo);
		mainOutputWriter.append(inputInfo);
		allSummary.append(inputInfo);

		StringBuilder encodingInfo = new StringBuilder("");
		
		encodingInfo.append("\nEncodings:"+"\n");
		
		for(OneHotEncodingV2Info ohi:oneHotEncodingV2Info)
			encodingInfo.append(ohi.toString()+"\n");

		for(AttributeEncodingInfo ae:attEncodingInfo)
			encodingInfo.append(ae.toString()+"\n");
		
		encodingInfo.append("END OF Encodings"+"\n\n");

		allSummary.append(encodingInfo);
		experimentSummary.append(encodingInfo);
		
		////////////////////////////////////////////////////////////////////
		//END OF Print Input info
		////////////////////////////////////////////////////////////////////
		

		
		////////////////////////////////////////////////////////////////////
		//CREATE TRAINING & TEST INSTANCES
		////////////////////////////////////////////////////////////////////			

		System.out.println("initializing ARS...");
		ars.init(xlogOri);
		System.out.println("finish initializing ARS...");
		
		ArrayList<Pair<String, Instances>> testInstances = new ArrayList<Pair<String, Instances>>();
		
		AnalyticRulesInstance ari = ars.computeAnalyticRulesInstanceAllPrefixLength(2, xlogTrain);
		Instances trainingInstances = ari.computeWEKAInstances(trainInstanceCodeName, encodingType, oneHotEncodingV2Info, attEncodingInfo);
		testInstances.add(new Pair<String,Instances>("TEST WITH TRAINING INSTANCE", trainingInstances));
		
		AnalyticRulesInstance ariTestEarly = ars.computeAnalyticRulesInstanceWithFixPrefixLength(PrefLengthType.EARLY_EVENT, xlogTest);
		ariTestEarly.setAllPossibleTargetValue(ari.getAllPossibleTargetValue());
		Instances testInstancesEarly = ariTestEarly.computeWEKAInstances(testInstanceCodeName+"Early", encodingType, oneHotEncodingV2Info, attEncodingInfo);
		testInstances.add(new Pair<String,Instances>("EARLY PREDICTION", testInstancesEarly));
		
		AnalyticRulesInstance ariTestMid = ars.computeAnalyticRulesInstanceWithFixPrefixLength(PrefLengthType.MID_EVENT, xlogTest);
		ariTestMid.setAllPossibleTargetValue(ari.getAllPossibleTargetValue());
		Instances testInstancesMid = ariTestMid.computeWEKAInstances(testInstanceCodeName+"Mid", encodingType, oneHotEncodingV2Info, attEncodingInfo);
		testInstances.add(new Pair<String,Instances>("MID PREDICTION", testInstancesMid));

		AnalyticRulesInstance ariTestLate = ars.computeAnalyticRulesInstanceWithFixPrefixLength(PrefLengthType.LATE_EVENT, xlogTest);
		ariTestLate.setAllPossibleTargetValue(ari.getAllPossibleTargetValue());
		Instances testInstancesLate = ariTestLate.computeWEKAInstances(testInstanceCodeName+"Late", encodingType, oneHotEncodingV2Info, attEncodingInfo);
		testInstances.add(new Pair<String,Instances>("LATE PREDICTION", testInstancesLate));
		
		AnalyticRulesInstance ariALL = ars.computeAnalyticRulesInstanceAllPrefixLength(2, xlogTest);
		ariALL.setAllPossibleTargetValue(ari.getAllPossibleTargetValue());
		Instances testInstancesALL = ariALL.computeWEKAInstances(testInstanceCodeName+"ALL", encodingType, oneHotEncodingV2Info, attEncodingInfo);
		testInstances.add(new Pair<String,Instances>("PREDICTION FOR ALL POSSIBLE PREFIXES", testInstancesALL));
		
		StringBuilder trainInfo = new StringBuilder();
		trainInfo.append("num of training instances: " + trainingInstances.size()+"\n");
		trainInfo.append("num of test instances (Early Events): " + testInstancesEarly.size()+"\n");
		trainInfo.append("num of test instances (Mid Events): " + testInstancesMid.size()+"\n");
		trainInfo.append("num of test instances (Late Events): " + testInstancesLate.size()+"\n");
		trainInfo.append("num of test instances (All): " + testInstancesALL.size()+"\n\n");
		trainInfo.append("-------------------------------------------------------------------------------------------\n");
		trainInfo.append("END OF Building the Training and Test Instances\n");
		trainInfo.append("-------------------------------------------------------------------------------------------\n");
		trainInfo.append("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

		allSummary.append(trainInfo);
		System.out.print(trainInfo);
		mainOutputWriter.append(trainInfo);
		mainOutputWriter.flush();
		
		////////////////////////////////////////////////////////////////////
		//END OF CREATE TRAINING & TEST INSTANCES
		////////////////////////////////////////////////////////////////////


		
		////////////////////////////////////////////////////////////////////
		//TRAIN AND EVALUATE CLASSIFIERS
		////////////////////////////////////////////////////////////////////
		
		////////////////////////////////////////////////
		//FOR LATEX TABLE
		////////////////////////////////////////////////				
		
		EvalTableClassification table = 
				new EvalTableClassification(this.identifier, this.title);

		EvalTableClassificationContent tableContentTraining = 
				new EvalTableClassificationContent("Evaluation on Training Data");
		
		EvalTableClassificationContent tableContentTest = 
				new EvalTableClassificationContent("Evaluation on Test Data");
		
		////////////////////////////////////////////////
		//END OF FOR LATEX TABLE
		////////////////////////////////////////////////				

		
		for (int i = 0; i < models.length; i++) {
			
			StringBuilder partialOutout = new StringBuilder("");//partial output is for showing the partial output during the training
			long startTime = System.currentTimeMillis();
			
			////////////////////////////////////////////////////////////////////
			//BUILD AND SAVE THE MODEL
			////////////////////////////////////////////////////////////////////

			StringBuilder output = new StringBuilder();
			output.append("\n\n\n\n\n");
			output.append("----------------------------------------------------------------------------------------------------\n");
			output.append("START: Building and Evaluating the classifier "+models[i].getClass().toString()+"\n");
			output.append("----------------------------------------------------------------------------------------------------\n\n");

			experimentSummary.append("\n----------------------------------------------------------------------------------------------------\n");
			experimentSummary.append(models[i].getClass().toString()+"\n");
			
			System.out.print(output);
			mainOutputWriter.append(output+"\n");
			partialOutout.append(output);

			StringBuilder buildMsg = new StringBuilder("Building "+models[i].getClass().toString()+"\n");
			System.out.println(buildMsg);
			mainOutputWriter.append(buildMsg);
			mainOutputWriter.flush();

			models[i].buildClassifier(trainingInstances);
			String outputModelFile = outputModelLocation + models[i].getClass().toString()+".model";
			WEKAUtil.saveClassifier(outputModelFile, models[i]);

			mainOutputWriter.append("model output location: "+outputModelFile+"\n");
			
			////////////////////////////////////////////////////////////////////
			//END OF BUILD AND SAVE THE MODEL
			////////////////////////////////////////////////////////////////////
			
			
			
			////////////////////////////////////////////////////////////////////
			//EVALUATING MODEL
			////////////////////////////////////////////////////////////////////
			
			StringBuilder evalMsg = new StringBuilder("Evaluating "+models[i].getClass().toString()+"\n");
			System.out.println(evalMsg);
			mainOutputWriter.append(evalMsg);
			mainOutputWriter.flush();

			
			int ii = 1;
			//evaluating the model with each test instance
			for(Pair<String, Instances> testInst : testInstances){
			
				String testInstName = testInst.getObj1();
				Evaluation evaluation = new Evaluation(trainingInstances);
				evaluation.evaluateModel(models[i], testInst.getObj2());

				//Some Specific Classification Metrics
				String acc1_str = 	"Accuracy: "+String.format("%3.2f", (evaluation.correct() / evaluation.numInstances())) + 
									" ("+String.format("%3.2f", evaluation.pctCorrect())+"%)";
				String AUC_1_0_str = "AUC-0: "+String.format("%3.2f", evaluation.areaUnderROC(0));
				String AUC_1_1_str = "AUC-1: "+String.format("%3.2f", evaluation.areaUnderROC(1));
				//END OF Some Specific Classification Metrics

				StringBuilder evaluationOutput = new StringBuilder();

				evaluationOutput.append("\n"+testInstName+"\n");
				evaluationOutput.append(evaluation.toSummaryString(true) +"\n");
				evaluationOutput.append(evaluation.toClassDetailsString() + "\n");
				evaluationOutput.append(acc1_str+"\n");
				evaluationOutput.append(AUC_1_0_str+"\n");
				evaluationOutput.append(AUC_1_1_str+"\n");

				partialOutout.append(evaluationOutput);
				System.out.print(evaluationOutput);
				mainOutputWriter.append(evaluationOutput);
				mainOutputWriter.flush();

				
				////////////////////////////////////////////////
				//FOR LATEX TABLE
				////////////////////////////////////////////////				
				
				if(testInstName.equals("TEST WITH TRAINING INSTANCE") || 
						testInstName.equals("PREDICTION FOR ALL POSSIBLE PREFIXES")){
					
					String lAUC = String.format("%.2f", evaluation.areaUnderROC(1));
					String lAcc = String.format("%.2f", (evaluation.correct() / evaluation.numInstances()));
					String lWPrecision = String.format("%.2f", evaluation.weightedPrecision());
					String lWRecall= String.format("%.2f", evaluation.weightedRecall());
					String lWFMeasure= String.format("%.2f", evaluation.weightedFMeasure());
					
					EvalTableClassificationRow row = new EvalTableClassificationRow(
							models[i].getClass().getSimpleName(), lAUC, lAcc, lWPrecision, lWRecall, lWFMeasure);

					for(int kk = 0; kk < trainingInstances.numClasses(); kk ++){
						
						String className = trainingInstances.classAttribute().value(kk);
						String classPrecision = String.format("%.2f", evaluation.precision(kk));
						String classRecall= String.format("%.2f", evaluation.recall(kk));
						String classFMeasure= String.format("%.2f", evaluation.fMeasure(kk));
						
						row.addSubRow(className, classPrecision, classRecall, classFMeasure);
					}
					
					if(testInstName.equals("TEST WITH TRAINING INSTANCE"))
						tableContentTraining.addRow(row);
					
					if(testInstName.equals("PREDICTION FOR ALL POSSIBLE PREFIXES"))
						tableContentTest.addRow(row);
				}
							
				////////////////////////////////////////////////
				//END OF FOR LATEX TABLE
				////////////////////////////////////////////////				
				
				//If evaluation for all, add the evaluation to the experiment summary
				//if(ii == testInstances.size())
				if (testInstName.equals("PREDICTION FOR ALL POSSIBLE PREFIXES")) {
					experimentSummary.append(evaluationOutput);
				}
				
				ii++;
			}

			////////////////////////////////////////////////////////////////////
			//END OF EVALUATING MODEL
			////////////////////////////////////////////////////////////////////

			System.out.print("------------------------------------------------------------------------\n");
			mainOutputWriter.append("------------------------------------------------------------------------\n");
			
			long runTime = (System.currentTimeMillis() - startTime);

			String runTimeStr = "Total running time of " + models[i].getClass().toString() + " is " +
								(runTime/60000)+" minute(s) or "+runTime+ " millisecond(s)\n";
				
			System.out.println(runTimeStr);
			mainOutputWriter.append(runTimeStr);
			partialOutout.append("\n"+runTimeStr+"\n\n");
			
			StringBuilder endBuildEvalMsg = new StringBuilder();
			endBuildEvalMsg.append("\n\n----------------------------------------------------------------------------------------------------\n");
			endBuildEvalMsg.append("END OF Building and Evaluating"+models[i].getClass().toString()+"\n");
			endBuildEvalMsg.append("----------------------------------------------------------------------------------------------------\n\n");

			System.out.println(endBuildEvalMsg);
			mainOutputWriter.append(endBuildEvalMsg);
			partialOutout.append(endBuildEvalMsg);

			//print partial summary
//				mainOutputWriter.append("\n\n\n\n\n");
//				mainOutputWriter.append("=========================================================================================================\n");
//				mainOutputWriter.append("PARTIAL SUMARY #"+(i+1)+": \n");
//				mainOutputWriter.append("=========================================================================================================\n");
//				mainOutputWriter.append(partialOutout);
//				mainOutputWriter.append("=========================================================================================================\n");
//				mainOutputWriter.append("END OF PARTIAL SUMARY #"+(i+1)+":  \n");
//				mainOutputWriter.append("=========================================================================================================\n");
//				mainOutputWriter.append("\n\n\n\n\n");
			mainOutputWriter.flush();
			
			allSummary.append(partialOutout);
			
			experimentSummary.append("----------------------------------------------------------------------------------------------------\n");		
		}

		////////////////////////////////////////////////
		//FOR LATEX TABLE
		////////////////////////////////////////////////				
		table.addContent(tableContentTraining);
		table.addContent(tableContentTest);
		latexOutputWriter.append(table.generateLatexTableDetail());
		latexOutputShortWriter.append(table.generateLatexTableOverview());
		latexOutputWriter.flush();
		latexOutputShortWriter.flush();
		
		////////////////////////////////////////////////
		//END OF FOR LATEX TABLE
		////////////////////////////////////////////////	

		experimentSummary.append("=========================================================================================================\n");
		experimentSummary.append("END OF Experiment Summary - "+this.title+"\n");
		experimentSummary.append("=========================================================================================================\n\n");

		StringBuilder output = new StringBuilder();
		
		output.append("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		output.append("=========================================================================================================\n");
		output.append("FINAL SUMARY: \n");
		output.append("=========================================================================================================\n");
		output.append(allSummary);
		output.append("=========================================================================================================\n");
		output.append(experimentSummary);

		System.out.print(output);
		mainOutputWriter.append(output);
		
		////////////////////////////////////////////////////////////////////
		//END OF TRAIN AND EVALUATE CLASSIFIERS
		////////////////////////////////////////////////////////////////////
			
	}

	
	/**
	 * train and evaluate the models - Regression task - Weka Mode
	 */
	private void trainAndEvaluateRegressionModelsWekaMode() throws Exception{
	
		StringBuilder allSummary = new StringBuilder("");//for the summary at the end of the day!
		
		StringBuilder experimentSummary = new StringBuilder("\n\n\n\n\n\n\n\n\n\n");
		experimentSummary.append("=========================================================================================================\n");
		experimentSummary.append("Experiment Summary - "+this.title+"\n");
		experimentSummary.append("=========================================================================================================\n\n");


		////////////////////////////////////////////////////////////////////
		//SETTING!!!
		////////////////////////////////////////////////////////////////////
		
			String trainInstanceCodeName = identifier +"-Train-";
			String testInstanceCodeName = identifier +"-Test-";
			
			//====================================================================
			// Some sanity check
			//====================================================================
			
			// Analytic Rules
			if(ars == null)
				throw new Exception("There is no Analytic Rule Specification");

			// ENCODING CHOICE				
			if(encodingType == null)
				throw new Exception("There is no specification of the desired encoding");

			for(int ii = 0; ii < encodingType.length; ii++){
				
				if(this.encodingType[ii] == EncodingType.OneHotV2 && this.oneHotEncodingV2Info == null)
					throw new Exception("One hot encoding is chosen but the specification is not yet given");
				
				if(this.encodingType[ii] == EncodingType.AttEnc && this.attEncodingInfo == null)
					throw new Exception("One attribute encoding is chosen but the specification is not yet given");
			}
		
			// regression model CHOICE
			if(models == null)
				throw new Exception("Models have not been specified");

			//====================================================================
			// END OF Some sanity check
			//====================================================================

		////////////////////////////////////////////////////////////////////
		//END OF SETTING!!!
		////////////////////////////////////////////////////////////////////


			
		////////////////////////////////////////////////////////////////////
		//Print Input info
		////////////////////////////////////////////////////////////////////
		
		StringBuilder inputInfo = new StringBuilder();
		
		inputInfo.append("\n\n\n\n\n\n\n\n\n\n"
				+ "-------------------------------------------------------------------------------------------\n"
				+ "START: Building the Training and Test Instances\n"
				+ "-------------------------------------------------------------------------------------------\n\n");
		inputInfo.append("ori log size: " + xlogOri.size()+"\n");
		inputInfo.append("training log size: " + xlogTrain.size()+"\n");
		inputInfo.append("test log size: " + xlogTest.size()+"\n");
		inputInfo.append("max trace length: " + XESUtil.getMaxTracesLength(xlogOri)+"\n");
		inputInfo.append("min trace length: " + XESUtil.getMinTracesLength(xlogOri)+"\n");
			
		System.out.print(inputInfo);
		mainOutputWriter.append(inputInfo);
		allSummary.append(inputInfo);

		
		StringBuilder encodingInfo = new StringBuilder("");
		
		encodingInfo.append("\nEncodings:"+"\n");
		
		for(OneHotEncodingV2Info ohi:oneHotEncodingV2Info)
			encodingInfo.append(ohi.toString()+"\n");

		for(AttributeEncodingInfo ae:attEncodingInfo)
			encodingInfo.append(ae.toString()+"\n");
		
		encodingInfo.append("END OF Encodings"+"\n\n");

		allSummary.append(encodingInfo);
		experimentSummary.append(encodingInfo);

		////////////////////////////////////////////////////////////////////
		//END OF Print Input info
		////////////////////////////////////////////////////////////////////
		

		
		////////////////////////////////////////////////////////////////////
		//CREATE TRAINING & TEST INSTANCES
		////////////////////////////////////////////////////////////////////			

		System.out.println("initializing ARS...");
		ars.init(xlogOri);
		System.out.println("finish initializing ARS...");
		
		ArrayList<Pair<String, Instances>> testInstances = new ArrayList<Pair<String, Instances>>();
		
		AnalyticRulesInstance ari = ars.computeAnalyticRulesInstanceAllPrefixLength(2, xlogTrain);
		Instances trainingInstances = ari.computeWEKAInstances(trainInstanceCodeName, encodingType, oneHotEncodingV2Info, attEncodingInfo);
		testInstances.add(new Pair<String,Instances>("TEST WITH TRAINING INSTANCE", trainingInstances));
		
		AnalyticRulesInstance ariTestEarly = ars.computeAnalyticRulesInstanceWithFixPrefixLength(PrefLengthType.EARLY_EVENT, xlogTest);
		ariTestEarly.setAllPossibleTargetValue(ari.getAllPossibleTargetValue());
		Instances testInstancesEarly = ariTestEarly.computeWEKAInstances(testInstanceCodeName+"Early", encodingType, oneHotEncodingV2Info, attEncodingInfo);
		testInstances.add(new Pair<String,Instances>("EARLY PREDICTION", testInstancesEarly));
		
		AnalyticRulesInstance ariTestMid = ars.computeAnalyticRulesInstanceWithFixPrefixLength(PrefLengthType.MID_EVENT, xlogTest);
		ariTestMid.setAllPossibleTargetValue(ari.getAllPossibleTargetValue());
		Instances testInstancesMid = ariTestMid.computeWEKAInstances(testInstanceCodeName+"Mid", encodingType, oneHotEncodingV2Info, attEncodingInfo);
		testInstances.add(new Pair<String,Instances>("MID PREDICTION", testInstancesMid));

		AnalyticRulesInstance ariTestLate = ars.computeAnalyticRulesInstanceWithFixPrefixLength(PrefLengthType.LATE_EVENT, xlogTest);
		ariTestLate.setAllPossibleTargetValue(ari.getAllPossibleTargetValue());
		Instances testInstancesLate = ariTestLate.computeWEKAInstances(testInstanceCodeName+"Late", encodingType, oneHotEncodingV2Info, attEncodingInfo);
		testInstances.add(new Pair<String,Instances>("LATE PREDICTION", testInstancesLate));
		
		AnalyticRulesInstance ariALL = ars.computeAnalyticRulesInstanceAllPrefixLength(2, xlogTest);
		ariALL.setAllPossibleTargetValue(ari.getAllPossibleTargetValue());
		Instances testInstancesALL = ariALL.computeWEKAInstances(testInstanceCodeName+"ALL", encodingType, oneHotEncodingV2Info, attEncodingInfo);
		testInstances.add(new Pair<String,Instances>("PREDICTION FOR ALL POSSIBLE PREFIXES", testInstancesALL));
		
		StringBuilder trainInfo = new StringBuilder();
		trainInfo.append("num of training instances: " + trainingInstances.size()+"\n");
		trainInfo.append("num of test instances (Early Events): " + testInstancesEarly.size()+"\n");
		trainInfo.append("num of test instances (Mid Events): " + testInstancesMid.size()+"\n");
		trainInfo.append("num of test instances (Late Events): " + testInstancesLate.size()+"\n");
		trainInfo.append("num of test instances (All): " + testInstancesALL.size()+"\n\n");
		trainInfo.append("-------------------------------------------------------------------------------------------\n");
		trainInfo.append("END OF Building the Training and Test Instances\n");
		trainInfo.append("-------------------------------------------------------------------------------------------\n");
		trainInfo.append("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

		allSummary.append(trainInfo);
		System.out.print(trainInfo);
		mainOutputWriter.append(trainInfo);
		mainOutputWriter.flush();
		
		////////////////////////////////////////////////////////////////////
		//END OF CREATE TRAINING & TEST INSTANCES
		////////////////////////////////////////////////////////////////////


		
		
		
		
		
		
		////////////////////////////////////////////////////////////////////
		//MEAN BASED PREDICTION
		////////////////////////////////////////////////////////////////////
		/*
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
		*/
		////////////////////////////////////////////////////////////////////
		//END OF MEAN BASED PREDICTION
		////////////////////////////////////////////////////////////////////
		
		
		
		
		
		
		
		////////////////////////////////////////////////////////////////////
		//TRAIN AND EVALUATE REGRESSION MODELS
		////////////////////////////////////////////////////////////////////
		
		////////////////////////////////////////////////
		//FOR LATEX TABLE
		////////////////////////////////////////////////				
		
		EvalTableRegression table = 
				new EvalTableRegression(this.identifier, this.title);

		EvalTableRegressionContent tableContentTraining = 
				new EvalTableRegressionContent("Evaluation on Training Data");
		
		EvalTableRegressionContent tableContentTest = 
				new EvalTableRegressionContent("Evaluation on Test Data");
		
		////////////////////////////////////////////////
		//END OF FOR LATEX TABLE
		////////////////////////////////////////////////	
		
		for (int i = 0; i < models.length; i++) {
			
			StringBuilder partialOutout = new StringBuilder("");//partial output is for showing the partial output during the training
			long startTime = System.currentTimeMillis();
			
			////////////////////////////////////////////////////////////////////
			//BUILD AND SAVE THE MODEL
			////////////////////////////////////////////////////////////////////

			StringBuilder output = new StringBuilder();
			output.append("\n\n\n\n\n");
			output.append("----------------------------------------------------------------------------------------------------\n");
			output.append("START: Building and Evaluating the regression model "+models[i].getClass().toString()+"\n");
			output.append("----------------------------------------------------------------------------------------------------\n\n");

			experimentSummary.append("\n----------------------------------------------------------------------------------------------------\n");
			experimentSummary.append(models[i].getClass().toString()+"\n");

			System.out.print(output);
			mainOutputWriter.append(output+"\n");
			partialOutout.append(output);

			StringBuilder buildMsg = new StringBuilder("Building "+models[i].getClass().toString()+"\n");
			System.out.println(buildMsg);
			mainOutputWriter.append(buildMsg);
			mainOutputWriter.flush();

			models[i].buildClassifier(trainingInstances);
			String outputModelFile = outputModelLocation + models[i].getClass().toString()+".model";
			WEKAUtil.saveClassifier(outputModelFile, models[i]);

			mainOutputWriter.append("model output location: "+outputModelFile+"\n");
			
			////////////////////////////////////////////////////////////////////
			//END OF BUILD AND SAVE THE MODEL
			////////////////////////////////////////////////////////////////////
			
			
			
			////////////////////////////////////////////////////////////////////
			//EVALUATING MODEL
			////////////////////////////////////////////////////////////////////
			
			StringBuilder evalMsg = new StringBuilder("Evaluating "+models[i].getClass().toString()+"\n");
			System.out.println(evalMsg);
			mainOutputWriter.append(evalMsg);
			mainOutputWriter.flush();

			int ii = 1;

			//evaluating the model with each test instance
			for(Pair<String, Instances> testInst : testInstances){
			
				String testInstName = testInst.getObj1();
				Evaluation evaluation = new Evaluation(trainingInstances);
				evaluation.evaluateModel(models[i], testInst.getObj2());

				//Some Specific Regressions Metrics

				double correlationCoef = evaluation.correlationCoefficient();
				double coefficientOfDetermination = Math.pow(correlationCoef, 2);
				double maeD = evaluation.meanAbsoluteError();
				double rmseD = evaluation.rootMeanSquaredError();

				String correlationCoefficient = f.format( correlationCoef);
				String coeffOfDetermination = f.format( coefficientOfDetermination);
				String mae = f.format(maeD);
				String maeHours = f.format( (maeD/(1000*60*60)) );
				String maeDays = f.format( (maeD/(1000*60*60*24)) );
				String rmse = f.format(rmseD);
				String rmseHours = f.format( (rmseD/(1000*60*60)) );
				String rmseDays = f.format( (rmseD/(1000*60*60*24)) );

				StringBuilder evaluationOutput= new StringBuilder();
				evaluationOutput.append("\n"+testInstName+"\n");
				evaluationOutput.append(evaluation.toSummaryString(true) +"\n");
				evaluationOutput.append("Correlation Coefficient: \t"+ correlationCoef +"\n");
				evaluationOutput.append("Coefficient Of Determination: \t"+ coefficientOfDetermination +"\n");

				if(showErrorsInDaysOrHours){
					evaluationOutput.append("MAE: "+ mae+" i.e., "+maeHours+" hours, "+ " i.e., "+maeDays+" day(s)"+"\n");
					evaluationOutput.append("RMSE: "+ rmse+" i.e., "+rmseHours+" hours, i.e., "+rmseDays+" day(s)"+"\n");
					
				}else{
				
					evaluationOutput.append("MAE: " + mae + "\n");
					evaluationOutput.append("RMSE: " + rmse + "\n");
				}

				//END OF Some Specific Regressions Metrics
				
				////////////////////////////////////////////////
				//FOR LATEX TABLE
				////////////////////////////////////////////////				
				
				if(testInstName.equals("TEST WITH TRAINING INSTANCE") || 
						testInstName.equals("PREDICTION FOR ALL POSSIBLE PREFIXES")){
									
					EvalTableRegressionRow row = new EvalTableRegressionRow(
						models[i].getClass().getSimpleName(), correlationCoefficient, coeffOfDetermination, 
						mae, maeHours, maeDays, rmse, rmseHours, rmseDays);
				
					if(testInstName.equals("TEST WITH TRAINING INSTANCE"))
						tableContentTraining.addRow(row);
					
					if(testInstName.equals("PREDICTION FOR ALL POSSIBLE PREFIXES"))
						tableContentTest.addRow(row);
				}
				
				////////////////////////////////////////////////
				//END OF FOR LATEX TABLE
				////////////////////////////////////////////////
				

				partialOutout.append(evaluationOutput);
				System.out.print(evaluationOutput);
				mainOutputWriter.append(evaluationOutput);
				mainOutputWriter.flush();
				
				//If evaluation for all, add the evaluation to the experiment summary
				//if(ii == testInstances.size())
				if (testInstName.equals("PREDICTION FOR ALL POSSIBLE PREFIXES")) {
					experimentSummary.append(evaluationOutput);
				}
				
				ii++;
			}

			////////////////////////////////////////////////////////////////////
			//END OF EVALUATING MODEL
			////////////////////////////////////////////////////////////////////

			System.out.print("------------------------------------------------------------------------\n");
			mainOutputWriter.append("------------------------------------------------------------------------\n");
			
			long runTime = (System.currentTimeMillis() - startTime);

			String runTimeStr = "Total running time of " + models[i].getClass().toString() + " is " +
								(runTime/60000)+" minute(s) or "+runTime+ " millisecond(s)\n";
				
			System.out.println(runTimeStr);
			mainOutputWriter.append(runTimeStr);
			partialOutout.append("\n"+runTimeStr+"\n\n");
			
			StringBuilder endBuildEvalMsg = new StringBuilder();
			endBuildEvalMsg.append("\n\n----------------------------------------------------------------------------------------------------\n");
			endBuildEvalMsg.append("END OF Building and Evaluating"+models[i].getClass().toString()+"\n");
			endBuildEvalMsg.append("----------------------------------------------------------------------------------------------------\n\n");

			System.out.println(endBuildEvalMsg);
			mainOutputWriter.append(endBuildEvalMsg);
			partialOutout.append(endBuildEvalMsg);

			//print partial summary
//				mainOutputWriter.append("\n\n\n\n\n");
//				mainOutputWriter.append("=========================================================================================================\n");
//				mainOutputWriter.append("PARTIAL SUMARY #"+(i+1)+": \n");
//				mainOutputWriter.append("=========================================================================================================\n");
//				mainOutputWriter.append(partialOutout);
//				mainOutputWriter.append("=========================================================================================================\n");
//				mainOutputWriter.append("END OF PARTIAL SUMARY #"+(i+1)+":  \n");
//				mainOutputWriter.append("=========================================================================================================\n");
//				mainOutputWriter.append("\n\n\n\n\n");
			mainOutputWriter.flush();
			
			allSummary.append(partialOutout);
			experimentSummary.append("----------------------------------------------------------------------------------------------------\n");
		}

		////////////////////////////////////////////////
		//FOR LATEX TABLE
		////////////////////////////////////////////////				
		table.addContent(tableContentTraining);
		table.addContent(tableContentTest);
		latexOutputWriter.append(table.generateLatexTableDetail());
		latexOutputShortWriter.append(table.generateLatexTableDetail());
		latexOutputWriter.flush();
		latexOutputShortWriter.flush();
		
		////////////////////////////////////////////////
		//END OF FOR LATEX TABLE
		////////////////////////////////////////////////
		
		experimentSummary.append("=========================================================================================================\n");
		experimentSummary.append("END OF Experiment Summary - "+this.title+"\n");
		experimentSummary.append("=========================================================================================================\n\n");

		StringBuilder output = new StringBuilder();
		
		output.append("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		output.append("=========================================================================================================\n");
		output.append("FINAL SUMARY: \n");
		output.append("=========================================================================================================\n");
		output.append(allSummary);
		output.append("=========================================================================================================\n");
		output.append(experimentSummary);

		System.out.print(output);
		mainOutputWriter.append(output);
		
		////////////////////////////////////////////////////////////////////
		//END OF TRAIN AND EVALUATE REGRESSION MODELS
		////////////////////////////////////////////////////////////////////
	}

	////////////////////////////////////////////////////////////////////////////
	// END OF WEKA MODE - Train and Evaluate Models
	////////////////////////////////////////////////////////////////////////////

	
	////////////////////////////////////////////////////////////////////////////
	// Exec method
	////////////////////////////////////////////////////////////////////////////

	/**
	 * Execute the experiment
	 * @throws Exception
	 */
	protected void exec() throws Exception {
		
		if(!initialized)
			throw new Exception("This experiment is not yet initialized, therefore we can't execute it. Please run the 'initExperiment' first");
		
		System.setErr(System.out);
		this.startExecTime = System.currentTimeMillis();

		try{
			//////////////////////////////////////////////////////////////////
			// Build the model and evaluate it 
			//////////////////////////////////////////////////////////////////
			
			if(this.predictionTaskType == ValueType.NON_NUMERIC_EXP){
				System.out.println("START OF training and evaluating the classifiers");
				
				if(this.sdpromMode == Mode.PYTHON || this.sdpromMode == Mode.NEURAL_NETWORK)
					trainAndEvaluateClassificationModelsPythonMode();
				else if(this.sdpromMode == Mode.WEKA)
					trainAndEvaluateClassificationModelsWekaMode();
				
				System.out.println("END OF training and evaluating the classifiers");
			
			}else if(this.predictionTaskType == ValueType.NUMERIC_EXP){
				
				System.out.println("START OF training and evaluating the regression models");
				
				if(this.sdpromMode == Mode.PYTHON || this.sdpromMode == Mode.NEURAL_NETWORK)
					trainAndEvaluateRegressionModelsPythonMode();
				else if(this.sdpromMode == Mode.WEKA)
					trainAndEvaluateRegressionModelsWekaMode();
				
				System.out.println("END OF training and evaluating the regression models");
			
			}else 
				throw new Exception(
						"The Analytic Rule Specification is not coherent. "
					+ 	"Please re-check the specification.");
			
			//////////////////////////////////////////////////////////////////
			// END OF Build the model and evaluate it 
			//////////////////////////////////////////////////////////////////
	
			//////////////////////////////////////////////////////////////////
			// Print Final Output - timing, etc
			//////////////////////////////////////////////////////////////////

			this.endExecTime = System.currentTimeMillis();
			this.endTime = new Timestamp(this.endExecTime).toString();
			long duration = (this.endExecTime - this.startExecTime);
	
			StringBuilder sb = new StringBuilder();
			
			sb.append("\nStart Time: \t\t"+startTime+"\n");
			sb.append("Start Execution Time: \t"+new Timestamp(this.startExecTime).toString()+"\n");
			sb.append("End Execution Time: \t"+new Timestamp(this.endExecTime).toString()+"\n");
			sb.append("Total Execution Time: \t"+f.format(duration)+" millisecond(s)\n");
			sb.append("End Time: \t\t"+endTime+"\n");
			sb.append("\nDONE!!! :)\n");
			
			System.out.print(sb.toString());
			mainOutputWriter.append(sb.toString());
			mainOutputWriter.flush();
			mainOutputWriter.close();
			
			//////////////////////////////////////////////////////////////////
			// END OF Print Final Output - timing, etc
			//////////////////////////////////////////////////////////////////
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			mainOutputWriter.append(e.getMessage());
			mainOutputWriter.flush();
			mainOutputWriter.close();
		}
	}
	
	////////////////////////////////////////////////////////////////////////////
	// END OF Exec method
	////////////////////////////////////////////////////////////////////////////

	
	
	////////////////////////////////////////////////////////////////////////////
	// Some setter for some training information
	////////////////////////////////////////////////////////////////////////////
	
	protected void setEncodingType(EncodingType[] encodingType){
		
		this.encodingType = encodingType;
	}

	protected void setOneHotEncodingInfo(OneHotEncodingV2Info[] oneHotEncodingV2Info){
		
		this.oneHotEncodingV2Info = oneHotEncodingV2Info;
	}

	protected void setAttributeEncodingInfo(AttributeEncodingInfo[] attEncodingInfo){
		
		this.attEncodingInfo = attEncodingInfo;
	}

	
	//to overide neural network python script - classification
	protected void setClfNeuralNetworkPyScript(String clfNeuralNetworkModelScript){

		this.clfNeuralNetworkModelScript = clfNeuralNetworkModelScript;
	}

	//to overide ML models python script - classification
	protected void setClfNonNeuralNetworkPyScript(String clfNonNeuralNetworkModelScript){
		
		this.clfModelInitScript = clfNonNeuralNetworkModelScript;
	}
	
	//to overide neural network python script - regression
	protected void setRegNeuralNetworkPyScript(String regNeuralNetworkModelScript){

		this.regNeuralNetworkModelScript = regNeuralNetworkModelScript;
	}
	
	//to overide ML models python script - regression
	protected void setRegNonNeuralNetworkPyScript(String regNonNeuralNetworkModelScript){
		
		this.regModelInitScript = regNonNeuralNetworkModelScript;
	}


	protected void setModels(Classifier[] models){
		
		this.models = models;
	}

//	protected void setAnalyticRules(AnalyticRuleSpec ars){
//		
//		this.ars = ars;
//	}
	
//	protected void setIdentifier(String id){
//		
//		this.identifier = id;
//	}
	
	////////////////////////////////////////////////////////////////////////////
	// END OF Some setter for some training information
	////////////////////////////////////////////////////////////////////////////
}

