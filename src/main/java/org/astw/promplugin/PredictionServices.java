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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import org.astw.analyticrules.AnalyticRuleSpec;
import org.astw.util.Const.Mode;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;

import gnu.trove.map.hash.TObjectIntHashMap;
import jep.JepException;
import jep.MainInterpreter;
import jep.PyConfig;
import jep.SharedInterpreter;

/*
 * Note: XLog is given as the input for theplugin
 * 
 * Flow:
 * - choose xlog as the plugin input
 * - start plugin
 * - add a set/collection of Analytic Rules (Predictor)
 * 		- each Analytic Rules is a set of Rule Spec, and it will become one predictor function
 * - determine the type of the Rules (check coherent, etc)
 * - Choose the desired (classifier/regressor) model and the encoding
 * - build the model
 * 
 */
/**
 * @author Ario Santoso (santoso.ario@gmail.com)
 * 
 */
public class PredictionServices {
	
	private ArrayList<Predictor> predictors = new ArrayList<Predictor>();
	private boolean initialized = false;
	private Mode mode = Mode.PYTHON;
	
	// For Python Stuff
	private SharedInterpreter jep = null;
	//private SharedInterpreter jep2 = null;
	
	private String pyLoc = System.getProperty("user.home")+"/sdprom/";	
	private String initScript = "inits.py";

	//private final String defaultInternalPythonScriptPath = "/python/";
	//private String clfPythonFolder = "clf/";
	//private String regPythonFolder = "reg/";

	private String clfNeuralNetworkModelScript = "fn_nn_model_clf.py";
	//private String clfModelInitScript = "init_models_clf.py";
	//private String clfEvalScript = "fn_eval_clf.py";
	
	private String regNeuralNetworkModelScript  = "fn_nn_model_reg.py";
	//private String regModelInitScript = "init_models_reg.py";
	//private String regEvalScript = "fn_eval_reg.py";

	private ArrayList<String> initsStatements = new ArrayList<String>();
	//private ArrayList<String> modelsStatementsClf = new ArrayList<String>();
	//private ArrayList<String> modelsStatementsReg = new ArrayList<String>();
	private String nnFunctionClf = new String();
	private String nnFunctionReg = new String();
	//private String evaluationClf = new String();
	//private String evaluationReg = new String();
	
	private int nClfModels = 0;
	private int nRegModels = 0;
	private TObjectIntHashMap<String> availableClfModelNames = new TObjectIntHashMap<String>();
	private TObjectIntHashMap<String> availableRegModelNames = new TObjectIntHashMap<String>();
	private ArrayList<String> availableClfModelConfigs = new ArrayList<String>();
	private ArrayList<String> availableRegModelConfigs = new ArrayList<String>();
	
	private boolean hasNNClf = false;
	private boolean hasNNReg = false;
	// END OF for Python Stuff
	
	public PredictionServices(ArrayList<AnalyticRuleSpec> analRulesSpecCollection){
	
		for(AnalyticRuleSpec ars : analRulesSpecCollection)
			predictors.add(new Predictor(ars));
	}
	
	
	
	////////////////////////////////////////////////////////////////////////////////
	//for the weka mode
	////////////////////////////////////////////////////////////////////////////////

	public boolean initWekaMode(XLog xlog, ArrayList<String> allAttributeNames) throws Exception{
		
		this.mode = Mode.WEKA;
		
		for(Predictor pred : predictors){
			
			boolean initRes = pred.initWekaMode(xlog, allAttributeNames);
			
			if(initRes == false){
				
				this.initialized = false;
				throw new Exception("Failure in initializing the prediction service");
			}
		}
		
		this.initialized = true;
		
		return this.initialized;
	}

	public void buildPredictionServicesWekaMode() throws Exception{

		for(Predictor pred : predictors)
			pred.buildPredictorWekaMode();

	}

	////////////////////////////////////////////////////////////////////////////////
	//END OF for the weka mode
	////////////////////////////////////////////////////////////////////////////////

	
	
	////////////////////////////////////////////////////////////////////////////////
	//for the python mode
	////////////////////////////////////////////////////////////////////////////////
	
	public boolean initPyMode(XLog xlog, ArrayList<String> allAttributeNames) throws Exception{

		boolean initPyResult = initPyStuff();
		
		if(initPyResult == false)
			throw new Exception("Failure in initializing the prediction service");
        
		int ii = 0;
		for(Predictor pred : predictors){
			
			boolean initRes = pred.initPyMode(xlog, allAttributeNames, ""+ii++,
											availableClfModelNames, availableRegModelNames, 
											availableClfModelConfigs, availableRegModelConfigs);
			
			if(initRes == false){
				
				this.initialized = false;
				throw new Exception("Failure in initializing the prediction service");
			}
		}
		

        this.initialized = true;
		
		return this.initialized;
	}

	private boolean initPyStuff() throws Exception{
		
		//System.out.println("\n\n============================\ninitPyStuff()\n============================\n\n");
		//System.out.println("this.initialized: "+this.initialized);
		
		this.mode = Mode.PYTHON;
		
		//----------------------------------------------------------------------
		//Checking libraries
		//----------------------------------------------------------------------
		java.lang.reflect.Field LIBRARIES = ClassLoader.class.getDeclaredField("loadedLibraryNames");
		LIBRARIES.setAccessible(true);
		Vector<String> libraries = (Vector<String>) LIBRARIES.get(ClassLoader.getSystemClassLoader());		
		//final String[] librariesArr = libraries.toArray(new String[] {});
		System.out.println("\nloaded libraries are:");
		
		for (String lib : libraries) 
			System.out.println(lib);
		
		System.out.println("END OF loaded libraries");
		System.out.println("\njava.library.path: \n");
		StringTokenizer st = new StringTokenizer(System.getProperty("java.library.path"), ":");
		while(st.hasMoreTokens())
			System.out.println("\t"+st.nextToken().trim());
		System.out.println("\nEND OF java.library.path: \n");
		//----------------------------------------------------------------------
		//END OF Checking libraries
		//----------------------------------------------------------------------

		//init Jep 
		//Jep localJep = new Jep();
		//this.jep = localJep;
		//this.jep = new Jep();
		//this.jep = JepFactory.getJep();
		if(this.jep == null){

			if(!MainInterpreterMonitor.mainInterpreterIsRunning()){

				PyConfig pyConfig = new PyConfig();
				pyConfig.setHashRandomizationFlag(0);
				//MainInterpreter mi = MainInterpreter.getMainInterpreter();
				//mainInterpreter.setInitParams(pyConfig);
				MainInterpreter.setInitParams(pyConfig);
			}

			this.jep = new SharedInterpreter();
			
			if(this.jep != null && !MainInterpreterMonitor.mainInterpreterIsRunning()){
				MainInterpreterMonitor.init();
			}
		}


		//----------------------------------------------------------------------
		//Init Python Scripts
		//----------------------------------------------------------------------
		
		//InputStream in = null;
		BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
		
        try{

    		//---------------------------------------------------------------------------
    		//Read python init script (e.g., import libraries) - inits.py
    		//---------------------------------------------------------------------------
        	br = new BufferedReader(new FileReader(pyLoc+initScript));
    		
            String s = "";
        	while ((s = br.readLine()) != null) {
        		if (!s.startsWith("#"))
        			if(!s.equals(""))
        				initsStatements.add(s);
            }
        	br.close();
    		//---------------------------------------------------------------------------
    		//END OF Read python init script (e.g., import libraries) - inits.py
    		//---------------------------------------------------------------------------

        	
			//---------------------------------------------------------------------------
	    	//Read python script for neural network model - fn_nn_model_clf.py
			//---------------------------------------------------------------------------
        	File nnClfConfig = new File(pyLoc+this.clfNeuralNetworkModelScript);
        	sb = new StringBuilder();
        	if(nnClfConfig.exists()){
        		br = new BufferedReader(new FileReader(nnClfConfig));
		    	
    	        while ((s = br.readLine()) != null) {
    	        	if (!s.startsWith("#"))
    	        		sb.append(s + "\n");
    	        }
    	        nnFunctionClf = sb.toString();
    	        
            	br.close();
            	
            	this.hasNNClf = true;
        	}
			//---------------------------------------------------------------------------
	    	//END OF Read python script for neural network model - fn_nn_model_clf.py
			//---------------------------------------------------------------------------
        	
			//---------------------------------------------------------------------------
	    	//Read python script for neural network model - fn_nn_model_reg.py
			//---------------------------------------------------------------------------
        	File nnRegConfig = new File(pyLoc+this.regNeuralNetworkModelScript);
        	sb = new StringBuilder();
        	if(nnRegConfig.exists()){
        		br = new BufferedReader(new FileReader(nnRegConfig));
		    	
    	        while ((s = br.readLine()) != null) {
    	        	if (!s.startsWith("#"))
    	        		sb.append(s + "\n");
    	        }
    	        nnFunctionReg = sb.toString();
    	        
            	br.close();
            	
            	this.hasNNReg = true;
        	}
			//---------------------------------------------------------------------------
	    	//END OF Read python script for neural network model - fn_nn_model_reg.py
			//---------------------------------------------------------------------------
        	
			//---------------------------------------------------------------------------
	    	//Read python script for initializing ML models - init_models_clf.py
			//---------------------------------------------------------------------------
			/*
        	br = new BufferedReader(new FileReader(pyLoc+this.clfModelInitScript));

			while ((s = br.readLine()) != null) {
	        	if (!s.startsWith("#"))
	        		modelsStatementsClf.add(s);
	        }
			br.close();
			*/
			//---------------------------------------------------------------------------
	    	//END OF Read python script for initializing ML models - init_models_clf.py
			//---------------------------------------------------------------------------
			
			//---------------------------------------------------------------------------
	    	//Read python script for initializing ML models - init_models_reg.py
			//---------------------------------------------------------------------------
			/*
        	br = new BufferedReader(new FileReader(pyLoc+this.regModelInitScript));

			while ((s = br.readLine()) != null) {
	        	if (!s.startsWith("#"))
	        		modelsStatementsReg.add(s);
	        }
			br.close();
			*/
			//---------------------------------------------------------------------------
	    	//END OF Read python script for initializing ML models - init_models_reg.py
			//---------------------------------------------------------------------------
			
			//---------------------------------------------------------------------------
	    	//Read python script for evaluating trained models - fn_eval_clf.py
			//---------------------------------------------------------------------------
	        /*
			in = getClass().getResourceAsStream(defaultInternalPythonScriptPath+clfPythonFolder+clfEvalScript);
	        br = new BufferedReader(new InputStreamReader(in));
	        sb = new StringBuilder();
	        while ((s = br.readLine()) != null) {
	        	if (!s.startsWith("#"))
	        		sb.append(s + "\n");
	        }
	        evaluationClf = sb.toString();
	        */
			//---------------------------------------------------------------------------
	    	//END OF Read python script for evaluating trained models - fn_eval_clf.py
			//---------------------------------------------------------------------------

			//---------------------------------------------------------------------------
	    	//Read python script for evaluating trained models - fn_eval_reg.py
			//---------------------------------------------------------------------------
			/*
	        in = getClass().getResourceAsStream(defaultInternalPythonScriptPath+regPythonFolder+regEvalScript);
	        br = new BufferedReader(new InputStreamReader(in));
	        sb = new StringBuilder();
	        while ((s = br.readLine()) != null) {
	        	if (!s.startsWith("#"))
	        		sb.append(s + "\n");
	        }
	        evaluationReg = sb.toString();
			*/
			//---------------------------------------------------------------------------
	    	//END OF Read python script for evaluating trained models - fn_eval_reg.py
			//---------------------------------------------------------------------------

        }catch(FileNotFoundException fnfe){
        	fnfe.printStackTrace();
        	br.close();
        	System.out.println("\n\n\nSome files for the initialization are not found. Please check your installation");
        }
		
        br.close();
        
		//----------------------------------------------------------------------		
        //END OF Init Python Scripts
		//----------------------------------------------------------------------

        
        
		//--------------------------------------------------------
		// Run the imports statement in Python
		//--------------------------------------------------------
        
    	System.out.println("\n----------------------------------------------------");
    	System.out.println("Running inits.py\n");
		for (String cmd : initsStatements){
			
        	System.out.println(cmd);
            jep.eval(cmd);
		}
    	System.out.println("\nEND OF Running inits.py");
    	System.out.println("----------------------------------------------------\n");
		//--------------------------------------------------------
		// END OF Run the imports statement in Python
		//--------------------------------------------------------

		//--------------------------------------------------------
		// Create 'python function' for creating NN model
		//--------------------------------------------------------
		if(this.hasNNClf){

	    	System.out.println("\n\n----------------------------------------------------");
			System.out.println("nnFunctionClf: \n");
			System.out.println(this.nnFunctionClf);
			jep.eval(this.nnFunctionClf);
	    	System.out.println("\n----------------------------------------------------\n");
		}
		if(this.hasNNReg){
			
	    	System.out.println("\n\n----------------------------------------------------");
			System.out.println("nnFunctionReg: \n\n");
			System.out.println(this.nnFunctionReg);
			jep.eval(this.nnFunctionReg);
	    	System.out.println("\n----------------------------------------------------\n");
		}
		//--------------------------------------------------------
		// END OF Create 'python function' for creating NN model
		//--------------------------------------------------------

		
		//--------------------------------------------------------
		// Handling the available classifiers/regressors information
		//--------------------------------------------------------
		nClfModels = (int)((long)jep.getValue("len(availableClassifierConfig)"));
		nRegModels = (int)((long)jep.getValue("len(availableRegressorConfig)"));

		if(nClfModels == 0 && nRegModels == 0)
			throw new Exception("Invalid Configuration. There is neither Classifier or Regressor model");
		
		System.out.println("Number of available classifiers: "+nClfModels);
		System.out.println("Number of available regressors: "+nRegModels);

		for(int ii = 0; ii < nClfModels; ii++){
			
			String clfName = (String) jep.getValue("availableClassifierConfig["+ii+"][0]");
			String clfConfig = (String) jep.getValue("availableClassifierConfig["+ii+"][1]");

			this.availableClfModelNames.put(clfName, ii);
			this.availableClfModelConfigs.add(clfConfig);
		}

		for(int ii = 0; ii < nRegModels; ii++){
			
			String regName = (String) jep.getValue("availableRegressorConfig["+ii+"][0]");
			String regConfig = (String) jep.getValue("availableRegressorConfig["+ii+"][1]");

			this.availableRegModelNames.put(regName, ii);
			this.availableRegModelConfigs.add(regConfig);
		}
		
		//DEBUG
		/*
		Iterator<String> it = this.availableClfModelNames.keySet().iterator();
		while(it.hasNext()){
			String clfName = it.next();
			System.out.println("clfName: "+clfName );
			System.out.println("clfConfig: "+this.availableClfModelConfigs.get(this.availableClfModelNames.get(clfName)));
		}
		it = this.availableRegModelNames.keySet().iterator();
		while(it.hasNext()){
			String regName = it.next();
			System.out.println("regName: "+regName );
			System.out.println("regConfig: "+this.availableRegModelConfigs.get(this.availableRegModelNames.get(regName)));
		}
		*/
		//END OF DEBUG
		
		//--------------------------------------------------------
		// END OF Handling the available classifiers/regressors information
		//--------------------------------------------------------
		
		return true;
	}
	
	public void buildPredictionServicesPyMode() throws Exception{

		//System.out.println("buildPredictionServicesPyMode()");
		
		for(Predictor pred : predictors)
			pred.buildPredictorPyMode(this.jep);

	}


//	public boolean reinitJep() throws Exception{
//		
//		System.out.println("\n\n============================\nreinitJep()\n============================\n\n");
//
//		//this.jep.close();
//		//this.jep = null;
//		
//		boolean initPyResult = initPyStuff();
//		
//		if(initPyResult == false){
//			
//			this.initialized = false;
//			throw new Exception("Failure in initializing the prediction service");
//		}
//		
//		this.initialized = true;
//		
//		return this.initialized;
//	}

	public void close(){
		
		try {
			if(this.jep != null){
				
				this.jep.close();
			}
			
			this.jep = null;

			//if(this.jep2 != null) 
				//this.jep2.close();
			
			//this.jep2 = null;
			
		} catch (JepException e) {
			e.printStackTrace();
		}
	}

	////////////////////////////////////////////////////////////////////////////////
	//END OF for the python mode
	////////////////////////////////////////////////////////////////////////////////

	
	
	////////////////////////////////////////////////////////////////////////////////
	//Do the prediction
	////////////////////////////////////////////////////////////////////////////////

//	public boolean initForPrediction() throws JepException{
//		
//		if(this.jep2 == null){
//			this.jep2 = new SharedInterpreter();
//			this.jep2.eval("import pickle");
//		}
//
//		return true;
//	}
	
	public PredictionResults[] predict(XTrace xtrace){
		
		PredictionResults[] predictionResults = new PredictionResults[predictors.size()];
		
		if(this.mode == Mode.PYTHON){//python mode
			
			try {
				//this.jep = jep2;
				
				//Jep jp2 = new Jep();
				//System.out.println("import pickle");
				//jp2.eval("import pickle");
				//System.out.println("finish import pickle");

				//this.jep2 = JepFactory.getJep();

				if(this.jep == null){
					
					//PyConfig pyConfig = new PyConfig();
					//pyConfig.setHashRandomizationFlag(0);
					//MainInterpreter.setInitParams(pyConfig);
					
					this.jep = new SharedInterpreter();
					this.jep.eval("seed = 17");
					this.jep.eval("import os");
					this.jep.eval("os.environ['PYTHONHASHSEED'] = '0'");
					this.jep.eval("import numpy as np");
					this.jep.eval("import random as rn");
					this.jep.eval("np.random.seed(seed)");
					this.jep.eval("rn.seed(seed)");
					this.jep.eval("from keras.models import load_model");
					this.jep.eval("import pickle");
				}
				
				int ii = 0;
				for(Predictor pred: predictors){
					
					predictionResults[ii++] = pred.predictPy(xtrace, this.jep);
					//predictionResults[ii++] = pred.predictPy(xtrace, jp2);
					//predictionResults[ii++] = pred.predictPy(xtrace, this.jep);
				}
				
				//jp2.close();
				
			} catch (JepException e) {
				e.printStackTrace();
			}finally{
				try {
					this.jep.close();
				} catch (JepException e) {
					e.printStackTrace();
				}
				this.jep = null;
			} 
			
		}else{//should be weka mode
			int ii = 0;
			for(Predictor pred: predictors)
				predictionResults[ii++] = pred.predictWk(xtrace);
			
		}
		
		
		return predictionResults;
			
	}

	////////////////////////////////////////////////////////////////////////////////
	//END OF Do the prediction
	////////////////////////////////////////////////////////////////////////////////

	
	
	public ArrayList<Predictor> getPredictors() {
		return predictors;
	}
	
	public boolean isInitialized() {
		return initialized;
	}
	
	public Set<String> getAvailableClfModelNames() {
		return availableClfModelNames.keySet();
	}

	public Set<String> getAvailableRegModelNames() {
		return availableRegModelNames.keySet();
	}

	public Mode getMode(){
		return this.mode;
	}

//	public void setJep2(Jep jep2){
//		
//		this.jep2 = jep2;
//	}
	
	public static void main(String[] ar){
		
		//String initScr = "~/sdprom/inits.py";
		String userHome = System.getProperty("user.home");
		String initScr = userHome+"/sdprom/inits.py";		
//		String initScr = userHome+"/sdprom/inits2.py";
		
		ArrayList<String> initsSt = new ArrayList<String>();

		//---------------------------------------------------------------------------
		//Read python init script (e.g., import libraries) - inits.py
		//---------------------------------------------------------------------------
		//File file = new File(classLoader.getResource(initScript).getFile());
		
		BufferedReader br;
		
		System.out.println("home: "+ System.getProperty("user.home")+"\n\n\n");
		
		
		try {

			File f = new File(initScr);
			
			System.out.println(f.exists());
			
			System.exit(0);
			
			FileReader fr = new FileReader(initScr);
			
			br = new BufferedReader(fr);
	        String s = "";
	    	while ((s = br.readLine()) != null) {
	    		if (!s.startsWith("#"))
	    			initsSt.add(s);
	        }

	    	for(String str: initsSt)
	    		System.out.println(str);
	    	
	    	
		} catch (IOException io) {
			io.printStackTrace();
		}
		
		//---------------------------------------------------------------------------
		//END OF Read python init script (e.g., import libraries) - inits.py
		//---------------------------------------------------------------------------

	}
}



