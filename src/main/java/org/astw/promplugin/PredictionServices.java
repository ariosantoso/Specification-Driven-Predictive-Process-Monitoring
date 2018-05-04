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
import org.astw.analyticrules.AnalyticRuleSpec;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;

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

	public PredictionServices(ArrayList<AnalyticRuleSpec> analRulesSpecCollection){
	
		for(AnalyticRuleSpec ars : analRulesSpecCollection)
			predictors.add(new Predictor(ars));
	}
	
	public boolean init(XLog xlog, ArrayList<String> allAttributeNames) throws Exception{
		
		for(Predictor pred : predictors){
			
			boolean initRes = pred.init(xlog, allAttributeNames);
			
			if(initRes == false){
				
				this.initialized = false;
				throw new Exception("Failure in initializing the prediction service");
			}
		}
		
		this.initialized = true;
		
		return this.initialized;
	}
	
	public void buildPredictionServices() throws Exception{

		for(Predictor pred : predictors)
			pred.buildPredictor();

	}
	
	public ArrayList<Predictor> getPredictors() {
		return predictors;
	}
	
	public boolean isInitialized() {
		return initialized;
	}

	
	public PredictionResults[] predict(XTrace xtrace){
		
		PredictionResults[] predictionResults = new PredictionResults[predictors.size()];
		
		int ii = 0;
		for(Predictor pred: predictors)
			predictionResults[ii++] = pred.predict(xtrace);
		
		return predictionResults;
			
	}
}



