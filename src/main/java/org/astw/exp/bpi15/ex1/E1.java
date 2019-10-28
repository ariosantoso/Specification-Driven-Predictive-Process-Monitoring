package org.astw.exp.bpi15.ex1;

import org.astw.analyticrules.AnalyticRuleSpec;
import org.astw.analyticrules.TargetExpression;
import org.astw.exp.SDPROM;
import org.astw.parser.foe.FOEFormulaParser;
import org.astw.parser.targetexp.TargetExpressionParser;

import weka.classifiers.Classifier;
import weka.classifiers.functions.Logistic;
import weka.classifiers.rules.ZeroR;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;

class E1 extends SDPROM {

	private String title = "BPI15 - Predicting Complex Permit Application (has 25 different remaining activities)";

	protected E1() {}
	
	protected void initializeE1(String id, String configFile) throws Exception{

		this.init(id, title, configFile, genAnalyticRulesSpec());
		
		//Choose the models
		this.setModels(new Classifier[]{
				new ZeroR(), 
				new Logistic(),
				new J48(), 
				new RandomForest(),
		});
	}

	private AnalyticRuleSpec genAnalyticRulesSpec() throws Exception{
		
		String attName = "activityNameEN";
		int threshold = 25;
		
		AnalyticRuleSpec ars = new AnalyticRuleSpec();
		
		TargetExpression te1 = TargetExpressionParser.parse("\"Complex\"");
		TargetExpression te2 = TargetExpressionParser.parse("\"Not Complex\"");

		String ce = "COUNTVAL{"+attName+" # CURR:LAST } >= "+ threshold;

		ars.addRuleSpec(FOEFormulaParser.parse(ce), te1);
		ars.setOtherwiseTarget(te2);
		
		return ars;
	}


}
