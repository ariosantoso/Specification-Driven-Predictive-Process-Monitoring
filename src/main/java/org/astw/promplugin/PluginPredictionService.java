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



import javax.swing.JScrollPane;

import org.astw.gui.prediction.PredictionPanel;
import org.deckfour.uitopia.api.event.TaskListener.InteractionResult;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginCategory;
import org.processmining.framework.plugin.annotations.PluginVariant;

@Plugin(name = "Prediction Service", 
		parameterLabels = {"Prediction Services Provider"}, 
	    returnLabels = {"Prediction Results"}, 
	    returnTypes = {PredictionResults[].class},
		userAccessible = true, 
		help = "This plug in provides (multi-perspective) prediction information",
		categories = {PluginCategory.Analytics}
)
/**
 * @author Ario Santoso (santoso.ario@gmail.com)
 */
public class PluginPredictionService {

	
    @UITopiaVariant(
            affiliation = "University of Innsbruck",
            author = "Ario Santoso",
            email = "[ario.santoso@uibk.ac.at/santoso.ario@gmail.com]",
            website = "http://bit.ly/ariosantoso"
    )
    @PluginVariant(variantLabel = "Prediction Service", requiredParameterLabels = {0})
	public PredictionResults[] applyPredictionModel(UIPluginContext context, PredictionServices predService) {
		
    	PredictionPanel predPanel = new PredictionPanel(predService);
//    	InteractionResult result = context.showWizard("Prediction Services", true, true, predPanel);
	    InteractionResult result = context.showConfiguration("Prediction Services", new JScrollPane(predPanel));
	    
	    if(result == InteractionResult.CONTINUE || result == InteractionResult.FINISHED){
	    	
	    	System.out.println("--- FIN ---");
	    	
	    	return predPanel.getPredResults();
	    	
	    }else if(result == InteractionResult.CANCEL){
	    	
	    	return null;
	    }
		return null;
	    
	}

	
}
