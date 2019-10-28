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



import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.astw.analyticrules.AnalyticRuleSpec;
import org.astw.analyticrules.ConditionExpression;
import org.astw.analyticrules.TargetExpression;
import org.astw.foe.Formula;
import org.astw.gui.modelpreparation.config.SetOfPredictorConfigPanel;
import org.astw.gui.modelpreparation.init.InitialConfigPanel;
import org.astw.gui.modelpreparation.init.InitialConfigPanel.InitialConfigPanelType;
import org.astw.parser.foe.FOEFormulaParser;
import org.astw.parser.targetexp.TargetExpressionParser;
import org.astw.util.XESUtil;
import org.deckfour.uitopia.api.event.TaskListener.InteractionResult;
import org.deckfour.xes.model.XLog;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginCategory;
import org.processmining.framework.plugin.annotations.PluginVariant;

@Plugin(name = "Prediction Model Creator - Version P", 
		parameterLabels = {"XES Event Log"}, 
	    returnLabels = { "Prediction Service" }, 
	    returnTypes = { PredictionServices.class },
		userAccessible = true, 
		help = "This plug in creates (multi-perspective) prediction models. It could use machine learning functionalities provided by scikit-learn, keras, theano, etc. We can set the configuration using python",
		categories = {PluginCategory.Analytics}
)
/**
 * @author Ario Santoso (santoso.ario@gmail.com)
 */
public class PluginPredictionModelCreatorPy {

	private final String pluginName = "SDPROM (Prediction Model Creator - Version P)";
	
    @UITopiaVariant(
            affiliation = "University of Innsbruck",
            author = "Ario Santoso",
            email = "[ario.santoso@uibk.ac.at/santoso.ario@gmail.com]",
            website = "http://bit.ly/ariosantoso"
    )
    @PluginVariant(variantLabel = "Prediction Model Creator - Version P", requiredParameterLabels = {0})
	public PredictionServices createPredictionModel(UIPluginContext context, XLog xlog) {
		
    	System.out.println("\n\n\n\n\n------------------------------------------------------------------------------------------");
    	System.out.println("------------------------------------------------------------------------------------------");
    	System.out.println(pluginName+" - Started");
    	System.out.println("------------------------------------------------------------------------------------------");
    	System.out.println("------------------------------------------------------------------------------------------\n\n\n");
    	
    	//just clean the old temporary files (those files that is older than 3 days)
    	cleanOldFiles();
    	
	    ////////////////////////////////////////////////////////////////////////////////////
	    //specify a set of analytic rules 
	    ////////////////////////////////////////////////////////////////////////////////////	    

	    InitialConfigPanel icp = new InitialConfigPanel(context);
	    
	    while(true){

		    InteractionResult result = context.showConfiguration("Specify the prediction task(s)", icp);
		    
		    if (result == InteractionResult.CONTINUE && icp.getInitialConfigPanelType() == InitialConfigPanelType.SET_OF_ANAL_RULE_EDITOR){
		    	break;
		    	
		    }else if(result == InteractionResult.CONTINUE && icp.getInitialConfigPanelType() == InitialConfigPanelType.ANAL_RULE_EDITOR){
		    
		    	icp.showSetOfAnalyticRulesSpecEditor();
		    	
		    }else{
		    	return null;
		    }
	    }
	    
	    ////////////////////////////////////////////////////////////////////////////////////
	    //END OF specifying a set of analytic rules 
	    ////////////////////////////////////////////////////////////////////////////////////	    

	    
	    PredictionServices predictionServices = null;
	    
	    try{
		    ////////////////////////////////////////////////////////////////////////////////////
		    //process all of this set of analytic rules 
		    ////////////////////////////////////////////////////////////////////////////////////	    
		    
			context.getProgress().setIndeterminate(true);
			//context.getProgress().setMaximum(100);
			//context.getProgress().setValue(1);
	
		    ArrayList<AnalyticRuleSpec> arsSet = icp.getArsSet();
	
			//context.getProgress().setValue(5);
	
		    //for(AnalyticRuleSpec ars : arsSet)
		    //	System.out.println("DEBUGA: "+ars.getRuleName());
	    
		    //create predictor Model 
		    predictionServices = new PredictionServices(arsSet);
		    ArrayList<String> allAttributeNames = new ArrayList<String>();
	
			//context.getProgress().setValue(35);
	
		    //init with XLog
		    try {
				//context.getProgress().setIndeterminate(true);
				allAttributeNames = new ArrayList<String>(XESUtil.getAttributeNamesTypesMap(xlog).keySet());
				allAttributeNames.remove("time:timestamp");
	
				//boolean initRes = predictionServices.init(xlog, allAttributeNames);
				boolean initRes = predictionServices.initPyMode(xlog, allAttributeNames);
				
				//context.getProgress().setValue(90);
	
				if(initRes == false)
					throw new Exception("Error while processing the given set of Analytic Rules");
		    
		    } catch (Exception e) {
				JOptionPane.showMessageDialog(null, "An error occurred (Restarting ProM might solve the issue)", "Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				return null;
			}
		    
			//context.getProgress().setValue(100);
	
		    
		    ////////////////////////////////////////////////////////////////////////////////////
		    //END OF process all of this set of analytic rules 
		    ////////////////////////////////////////////////////////////////////////////////////	    
		    
		    
		    
		    ////////////////////////////////////////////////////////////////////////////////////
		    //Show the prediction model configuration dialog and get the preferred configuration
		    ////////////////////////////////////////////////////////////////////////////////////	    
	
			SetOfPredictorConfigPanel setOfPredictorEditor = new SetOfPredictorConfigPanel(null, predictionServices, allAttributeNames);
	
		    while(true){
	
			    InteractionResult result = context.showConfiguration("Config the prediction model(s)", setOfPredictorEditor);
			    
			    if (result == InteractionResult.CONTINUE){
			    	break;
			    
			    }else if (result == InteractionResult.CANCEL){
			    	
			    	return null;
			    }
		    }
		    
		    ////////////////////////////////////////////////////////////////////////////////////
		    //END OF Show the prediction model configuration dialog and get the preferred configuration
		    ////////////////////////////////////////////////////////////////////////////////////	    
		    
		    
		    
		    ////////////////////////////////////////////////////////////////////////////////////
		    //Build the prediction model (classifier/regressor) 
		    ////////////////////////////////////////////////////////////////////////////////////	    
	
		    try {
		        
		    	context.getProgress().setIndeterminate(true);
		        System.out.println("predictionServices.buildPredictionServicesPyMode()");
				predictionServices.buildPredictionServicesPyMode();
		        context.getProgress().setIndeterminate(true);
		        
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		    
		    //predictionServices.closeJep();
		    
		    ////////////////////////////////////////////////////////////////////////////////////
		    //END OF Build the prediction model (classifier/regressor) 
		    ////////////////////////////////////////////////////////////////////////////////////	    
	
		    return predictionServices;
	    
	    }finally{
	    	if(predictionServices != null)
	    		predictionServices.close();
	    	
	    	System.out.println("\n\n\n\n\n------------------------------------------------------------------------------------------");
	    	System.out.println("------------------------------------------------------------------------------------------");
	    	System.out.println(pluginName+" - Finished");
	    	System.out.println("------------------------------------------------------------------------------------------");
	    	System.out.println("------------------------------------------------------------------------------------------\n\n\n\n\n");

	    }
	}


    
    private void cleanOldFiles(){
    	
    	String pyLoc = System.getProperty("user.home")+"/sdprom/";	
        File f = new File(pyLoc);
        File[] fList = f.listFiles();
        //System.out.println("this directory: "+f.getAbsolutePath());
        //System.out.println(fList.length+" files");

        for(int ii = 0; ii < fList.length; ii++){

        	File currentFile = fList[ii];
        	String fileName = currentFile.getName();
        	long currentTimeStamp = System.currentTimeMillis();
        	long fileTimeStamp = currentFile.lastModified();
        	long oneday = 86400000;
        	
        	if( (fileName.substring(fileName.length()-4, fileName.length()).equals(".sav") || 
        		 fileName.substring(fileName.length()-4, fileName.length()).equals(".hd5") ) &&
        			(currentTimeStamp - fileTimeStamp) > (oneday * 3 ) ){
        		
        			currentFile.delete();
        	}

        }

    }
    
    
    ///////////////////////////////////////////////////////////////////////////
    //
    // JUST FOR TESTING AND DEVELOPMENT
    //
    ///////////////////////////////////////////////////////////////////////////
    
    public static void main(String[] ar){
    	
    	String log = "/Volumes/COMMON/kerja/LOG/event-log.xes";

    	try {
			AnalyticRuleSpec ars = PluginPredictionModelCreatorPy.genAnalyticRules();
			AnalyticRuleSpec ars2 = PluginPredictionModelCreatorPy.genAnalyticRules();
			AnalyticRuleSpec ars3 = PluginPredictionModelCreatorPy.genAnalyticRules2();
			ArrayList<AnalyticRuleSpec> arsSet = new ArrayList<AnalyticRuleSpec>();
//			arsSet.add(ars);
			arsSet.add(ars2);//classification
//			arsSet.add(ars2);//classification
			arsSet.add(ars3);//regression
//			arsSet.add(ars3);//regression
			
			//READ XLOG
//			PrintStream out = System.out;
//			PrintStream err = System.err;
//			System.setErr(new PrintStream(new ByteArrayOutputStream()));
//			System.setOut(new PrintStream(new ByteArrayOutputStream()));
			XLog xlog = XESUtil.readXESLog(log);
//			System.setOut(out);
//			System.setErr(err);
			//END OF READ XLOG

		    ////////////////////////////////////////////////////////////////////////////////////
		    //process all of this set of analytic rules 
		    ////////////////////////////////////////////////////////////////////////////////////	    
		    
		    //create predictor Model 
		    PredictionServices predictionServices = new PredictionServices(arsSet);
		    ArrayList<String> allAttributeNames = new ArrayList<String>();
		    
		    System.out.println("predictionServices.getMode(): "+predictionServices.getMode());
		    
		    //init Analytic Rules with XLog
		    try {
				allAttributeNames = new ArrayList<String>(XESUtil.getAttributeNamesTypesMap(xlog).keySet());
				allAttributeNames.remove("time:timestamp");

				boolean initRes = predictionServices.initPyMode(xlog, allAttributeNames);
				
				predictionServices.getPredictors().get(0).setPredModelTypePy("DecisionTreeClassifier");//classification
				predictionServices.getPredictors().get(1).setPredModelTypePy("DecisionTreeRegressor");//regression
//				predictionServices.getPredictors().get(0).setPredModelTypePy("RandomForestClassifier");//classification
//				predictionServices.getPredictors().get(1).setPredModelTypePy("RandomForestRegressor");//regression
//				predictionServices.getPredictors().get(0).setPredModelTypePy("GradientBoostingClassifier");//classification
//				predictionServices.getPredictors().get(1).setPredModelTypePy("GradientBoostingRegressor");//regression
//				predictionServices.getPredictors().get(0).setPredModelTypePy("NeuralNetwork");//classification
//				predictionServices.getPredictors().get(1).setPredModelTypePy("NeuralNetwork");//regression

//				predictionServices.getPredictors().get(0).setPredModelTypePy("NeuralNetwork");//classification
//				predictionServices.getPredictors().get(1).setPredModelTypePy("DecisionTreeClassifier");//classification
//				predictionServices.getPredictors().get(2).setPredModelTypePy("NeuralNetwork");//regression
//				predictionServices.getPredictors().get(3).setPredModelTypePy("ZeroR");//regression

				
				if(initRes == false)
					throw new Exception("Error while processing the given set of Analytic Rules");
		    
		    } catch (Exception e) {
				e.printStackTrace();
			}
		    
		    ////////////////////////////////////////////////////////////////////////////////////
		    //END OF process all of this set of analytic rules 
		    ////////////////////////////////////////////////////////////////////////////////////	    

		    
		    ////////////////////////////////////////////////////////////////////////////////////
		    //Build the prediction model (classifier/regressor) 
		    ////////////////////////////////////////////////////////////////////////////////////	    

		    //just to test the MainInterpreterMonitor
//		    PredictionServices predictionServices2 = new PredictionServices(arsSet);
//			boolean initRes2 = predictionServices2.initPyMode(xlog, allAttributeNames);
		    //END OF just to test the MainInterpreterMonitor

		    try {
				
			    System.out.println("predictionServices.getMode(): "+predictionServices.getMode());
		    	predictionServices.buildPredictionServicesPyMode();
			    System.out.println("predictionServices.getMode(): "+predictionServices.getMode());

			    //predictionServices.closeJep();
			    
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				
				predictionServices.close();
			}
		    
		    ////////////////////////////////////////////////////////////////////////////////////
		    //END OF Build the prediction model (classifier/regressor) 
		    ////////////////////////////////////////////////////////////////////////////////////	    

			
		    
		    
		    ////////////////////////////////////////////////////////////////////////////////////
			//Test generated prediction models
		    ////////////////////////////////////////////////////////////////////////////////////
		    System.out.println("\n\n\n================================================================\nTest generated prediction models\n================================================================\n");
		    
		    //predictionServices.closeJep();
		    //System.out.println("\n\n\nRe-init Jep\n");
		    //predictionServices.reinitJep();
		    
		    
		    new Thread(){
		    	public void run(){
		    		
//		    		try {
//						predictionServices.reinitJep();
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
		    		
		    		System.out.println("predictionServices.toString(): "+predictionServices.toString());
				    System.out.println("predictionServices.getMode(): "+predictionServices.getMode());
				    PredictionResults[] predRes = predictionServices.predict(xlog.get(3));

					int ii = 0;
				    for(PredictionResults pres : predRes){
					    System.out.println("predictionServices.getMode(): "+predictionServices.getMode());

						System.out.println("============================================================");
						System.out.println("PredictionResults --- "+ ii++ +":");
						System.out.println("pres.getPredictionDescription(): "+pres.getPredictionDescription());
						System.out.println("pres.getPredictionName(): "+pres.getPredictionName());
						System.out.println("pres.getResult(): "+pres.getResult());
						System.out.println("============================================================");
				    }
		    	}
		    }.start();
		    
		    
			//ArrayList<Predictor> pred = predictionServices.getPredictors();
//		    System.out.println("predictionServices.getMode(): "+predictionServices.getMode());
//		    PredictionResults[] predRes = predictionServices.predict(xlog.get(3));
//
//			int ii = 0;
//		    for(PredictionResults pres : predRes){
//			    System.out.println("predictionServices.getMode(): "+predictionServices.getMode());
//
//				System.out.println("============================================================");
//				System.out.println("PredictionResults --- "+ ii++ +":");
//				System.out.println("pres.getPredictionDescription(): "+pres.getPredictionDescription());
//				System.out.println("pres.getPredictionName(): "+pres.getPredictionName());
//				System.out.println("pres.getResult(): "+pres.getResult());
//				System.out.println("============================================================");
//		    }
		    
//			int ii =0;
//			for(Predictor p :pred){
//			    System.out.println("predictionServices.getMode(): "+predictionServices.getMode());
//
//				PredictionResults pres = p.predictPy(xlog.get(3));
//				
//				System.out.println("============================================================");
//				System.out.println("Predict --- "+ ii++ +":");
//				System.out.println("pres.getPredictionDescription(): "+pres.getPredictionDescription());
//				System.out.println("pres.getPredictionName(): "+pres.getPredictionName());
//				System.out.println("pres.getResult(): "+pres.getResult());
//				System.out.println("============================================================");
//			}
		    ////////////////////////////////////////////////////////////////////////////////////
			//END OF Testing the generated prediction models
		    ////////////////////////////////////////////////////////////////////////////////////
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }

	private static AnalyticRuleSpec genAnalyticRules2() throws Exception{
		
		String attName = "concept:name";
		String attVal = "Queued";
		String te = "SUM{e[x + 1].time:timestamp - e[x].time:timestamp # 1:LAST # e[x]."+attName+" == \""+attVal+"\"}";

		AnalyticRuleSpec ars = new AnalyticRuleSpec();

		ConditionExpression ce1 = FOEFormulaParser.parse("(CURR <LAST)");
		TargetExpression te1 =TargetExpressionParser.parse(te);
		TargetExpression te2 = TargetExpressionParser.parse("0");
		
		ars.addRuleSpec(ce1, te1);
		ars.setOtherwiseTarget(te2);
		
		return ars;
	}

	private static AnalyticRuleSpec genAnalyticRules() throws Exception{
		
		AnalyticRuleSpec ars = new AnalyticRuleSpec();
		
		TargetExpression te1 = genTargetExpressionString("\"ZPingPong\"");
		TargetExpression te2 = genTargetExpressionString("\"No PingPong\"");

		String cestr1 = " EXISTS i.( e[i].org:group !=  e[i+1].org:group )";
		Formula formula1 = FOEFormulaParser.parse(cestr1);
		ConditionExpression ce1 = formula1;

		String cestr2 = " EXISTS i.( e[i].org:group !=  e[i+1].org:group && e[i].concept:name != \"Queued\" )";
		Formula formula2 = FOEFormulaParser.parse(cestr2);
		ConditionExpression ce2 = formula2;

		String cestr3 = " EXISTS i.( (i >= CURR) && (i+1 <= LAST) &&  e[i].org:group !=  e[i+1].org:group && e[i].concept:name != \"Queued\" )";
		Formula formula3 = FOEFormulaParser.parse(cestr3);
		ConditionExpression ce3 = formula3;
		
		ars.addRuleSpec(ce1, te1);
		ars.addRuleSpec(ce2, te1);
		ars.addRuleSpec(ce3, te1);
		ars.setOtherwiseTarget(te2);
		
		return ars;
	}
	
	private static TargetExpression genTargetExpressionString(String str) throws Exception{
		
		TargetExpression targetExp = TargetExpressionParser.parse(str);

		return targetExp;
	}

    ///////////////////////////////////////////////////////////////////////////
    //
    // END OF JUST FOR TESTING AND DEVELOPMENT
    //
    ///////////////////////////////////////////////////////////////////////////

}
