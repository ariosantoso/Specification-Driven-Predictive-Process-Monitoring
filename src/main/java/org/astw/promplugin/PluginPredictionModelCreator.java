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

//@Plugin(name = "Prediction Model Synthesizer", 
@Plugin(name = "Prediction Model Creator - Version W", 
		parameterLabels = {"XES Event Log"}, 
	    returnLabels = { "Prediction Service" }, 
	    returnTypes = { PredictionServices.class },
		userAccessible = true, 
		help = "This plug in creates (multi-perspective) prediction models. It uses the machine learning libraries provided by WEKA",
		categories = {PluginCategory.Analytics}
)
/**
 * @author Ario Santoso (santoso.ario@gmail.com)
 */
public class PluginPredictionModelCreator {

	private final String pluginName = "SDPROM (Prediction Model Creator - Version W)";
	
    @UITopiaVariant(
            affiliation = "University of Innsbruck",
            author = "Ario Santoso",
            email = "[ario.santoso@uibk.ac.at/santoso.ario@gmail.com]",
            website = "http://bit.ly/ariosantoso"
    )
    @PluginVariant(variantLabel = "Prediction Model Creator - Version W", requiredParameterLabels = {0})
	public PredictionServices createPredictionModel(UIPluginContext context, XLog xlog) {
		
    	System.out.println("\n\n\n\n\n------------------------------------------------------------------------------------------");
    	System.out.println("------------------------------------------------------------------------------------------");
    	System.out.println(pluginName+" - Started");
    	System.out.println("------------------------------------------------------------------------------------------");
    	System.out.println("------------------------------------------------------------------------------------------\n\n\n");
    	
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
	    
        context.getProgress().setIndeterminate(true);
	    
	    ArrayList<AnalyticRuleSpec> arsSet = icp.getArsSet();
	    
//	    for(AnalyticRuleSpec ars : arsSet){
//	    	System.out.println("DEBUGA: "+ars.getRuleName());
//	    }
	    ////////////////////////////////////////////////////////////////////////////////////
	    //END OF specifying a set of analytic rules 
	    ////////////////////////////////////////////////////////////////////////////////////	    


	    ////////////////////////////////////////////////////////////////////////////////////
	    //process all of this set of analytic rules 
	    ////////////////////////////////////////////////////////////////////////////////////	    
	    
	    //create predictor Model 
	    PredictionServices predictionServices = new PredictionServices(arsSet);
	    ArrayList<String> allAttributeNames = new ArrayList<String>();
	    
	    //init with XLog
	    try {
			allAttributeNames = new ArrayList<String>(XESUtil.getAttributeNamesTypesMap(xlog).keySet());
			allAttributeNames.remove("time:timestamp");

			context.getProgress().setIndeterminate(true);
			boolean initRes = predictionServices.initWekaMode(xlog, allAttributeNames);
			
			if(initRes == false)
				throw new Exception("Error while processing the given set of Analytic Rules");
	    
	    } catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error while processing the given set of Analytic Rules. \nPlease check the inputs", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return null;
		}
	    
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
			predictionServices.buildPredictionServicesWekaMode();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	    
	    ////////////////////////////////////////////////////////////////////////////////////
	    //END OF Build the prediction model (classifier/regressor) 
	    ////////////////////////////////////////////////////////////////////////////////////	    

    	System.out.println("\n\n\n\n\n------------------------------------------------------------------------------------------");
    	System.out.println("------------------------------------------------------------------------------------------");
    	System.out.println(pluginName+" - Finished");
    	System.out.println("------------------------------------------------------------------------------------------");
    	System.out.println("------------------------------------------------------------------------------------------\n\n\n\n\n");

	    return predictionServices;
	}

	
//	/**
//	 * The method that does the heavy lifting for your plug-in.
//	 * 
//	 * Note that this method only uses the boolean which is stored in the configuration.
//	 * Nevertheless, it could have used the integer and/or the String as well.
//	 * 
//	 * @param context The context where to run in.
//	 * @param input1 The first input.
//	 * @param input2 The second input.
//	 * @param configuration The configuration to use.
//	 * @return The output.
//	 */
//    @UITopiaVariant(
//            affiliation = "University of Innsbruck",
//            author = "Ario Santoso",
//            email = "[ario.santoso@uibk.ac.at/santoso.ario@gmail.com]",
//            website = "bit.ly/ariosantoso"
//    )
//    @PluginVariant(variantLabel = "Your plug-in name, parameters", requiredParameterLabels = {})
//	public Object createPredictionModel(UIPluginContext context) {
//		
//		
//		System.out.println("hallo");
//		
//		AnalyticRulesSpec ars = null;
//		try {
//			ars = genAnalyticRules();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		AnalyticRuleSpecEditorPanel are = new AnalyticRuleSpecEditorPanel("Ping-Pong", "Predicting Ping-Pong behaviour", ars);
//
//
//		// Get the default configuration.
//	    //YourConfiguration configuration = new YourConfiguration(input1, input2);
//	    // Get a dialog for this configuration.
//	    //YourDialog dialog = new YourDialog(context, input1, input2, configuration);
//	    // Show the dialog. User can now change the configuration.
//
//		
//	    System.out.println("DEBUGA: MULAI");
//
////		InteractionResult result = context.showWizard("Title nya nih", true, false, are);
//		InteractionResult result = context.showConfiguration("haha", are);
//		
////	    // User has close the dialog.
////	    if (result == InteractionResult.FINISHED) {
////			// Do the heavy lifting.
////	    	return null;
////	    }
//	    // Dialog got canceled.
//	    
//	    System.out.println("DEBUGA: SELESAI");
//	    
//		
//	    return null;
//	}
    
    
    
    ///////////////////////////////////////////////////////////////////////////
    // JUST FOR TESTING
    ///////////////////////////////////////////////////////////////////////////
    
	public static AnalyticRuleSpec genAnalyticRules() throws Exception{
		
		AnalyticRuleSpec ars = new AnalyticRuleSpec();
		
		TargetExpression te1 = genTargetExpressionString("\"PingPong\"");
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
	
	public static TargetExpression genTargetExpressionString(String str) throws Exception{
		
		TargetExpression targetExp = TargetExpressionParser.parse(str);

		return targetExp;
	}

    ///////////////////////////////////////////////////////////////////////////
    // JUST FOR TESTING
    ///////////////////////////////////////////////////////////////////////////

}
