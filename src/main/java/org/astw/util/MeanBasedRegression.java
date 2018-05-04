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
package org.astw.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Set;

import org.astw.analyticrules.AnalyticRulesInstance;
import org.astw.analyticrules.RuleInstance;
import org.astw.util.encoder.AttributeEncodingInfo;
import org.astw.util.encoder.OneHotEncodingV2Info;
import org.astw.util.encoder.Encoder.EncodingType;

import weka.classifiers.Classifier;
import weka.core.Instances;

/**
 * 
 * @author Ario Santoso (santoso.ario@gmail.com)
 *
 */
public class MeanBasedRegression {

	private AnalyticRulesInstance ariTest;
	private EncodingType[] encodingType;
	private OneHotEncodingV2Info[] oneHotEncodingV2Info;
	private AttributeEncodingInfo[] attEncodingInfo;
	private double meanPrediction = 0 ;
	
	public MeanBasedRegression(AnalyticRulesInstance ariTest, AnalyticRulesInstance ariTrain,  
			EncodingType[] encodingType, OneHotEncodingV2Info[] oneHotEncodingV2Info, AttributeEncodingInfo[] attEncodingInfo){
		
		this.ariTest = ariTest;
		this.encodingType = encodingType;
		this.oneHotEncodingV2Info = oneHotEncodingV2Info;
		this.attEncodingInfo = attEncodingInfo;
		
		//compute mean prediction
		for(RuleInstance ri : ariTrain.getRules()){
			meanPrediction += Double.parseDouble(ri.getTargetValue());
		}
		meanPrediction = meanPrediction/ariTrain.size();
	}
	
	
	public String getEvaluation() throws Exception{
		
		int numOfTest = 0;
		double error = 0;
		double errorSQR = 0;//for RMSE

		PrintStream out = System.out;
		System.setOut(new PrintStream(new ByteArrayOutputStream()));
		
		for(RuleInstance ri : ariTest.getRules()){
			
			//Instances isnt = ri.computeWEKAInstances("Evaluate", encodingType, oneHotEncodingV2Info, attEncodingInfo, this.ariTest.getAllPossibleTargetValue());
			//double pred = this.cls.classifyInstance(isnt.firstInstance());
			double expected = Double.parseDouble(ri.getTargetValue());
			error += Math.abs(this.meanPrediction - expected);
			errorSQR += Math.pow((this.meanPrediction - expected), 2);
			numOfTest++;
		}
		
		double MAE = (error/numOfTest);
		double RMSE = Math.pow((errorSQR/numOfTest), 0.5);
		
		String output = "";
		
		DecimalFormat f = new DecimalFormat("###,###.###");
		
		output += "MAE (w.r.t. mean prediction): "+ f.format(MAE)+
						" - i.e., "+String.format("%3.2f", (MAE/(1000*60*60)))+
						" hours, i.e., "+String.format("%3.2f", (MAE/(1000*60*60*24)))+" day(s)\n";		
		output += "RMSE (w.r.t. mean prediction): "+ f.format(RMSE)+
						" - i.e., "+String.format("%3.2f", (RMSE/(1000*60*60)))+
						" hours, i.e., "+String.format("%3.2f", (RMSE/(1000*60*60*24)))+" day(s)\n";
		
		//out.println(output);
		
		System.setOut(out);
		return output;
		
	}
}
