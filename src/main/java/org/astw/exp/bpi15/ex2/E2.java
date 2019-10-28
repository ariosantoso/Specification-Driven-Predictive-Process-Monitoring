package org.astw.exp.bpi15.ex2;

import org.astw.analyticrules.AnalyticRuleSpec;
import org.astw.analyticrules.ConditionExpression;
import org.astw.analyticrules.TargetExpression;
import org.astw.exp.SDPROM;
import org.astw.parser.foe.FOEFormulaParser;
import org.astw.parser.targetexp.TargetExpressionParser;

import weka.classifiers.Classifier;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.rules.ZeroR;
import weka.classifiers.trees.REPTree;
import weka.classifiers.trees.RandomForest;

class E2 extends SDPROM{

	private String title = 
		"BPI 15 - predict the number of remanining activities";

	protected E2() {}
	
	protected void initializeE2(String id, String configFile) throws Exception{

		this.init(id, title, configFile, genAnalyticRulesSpec());
		
		this.showErrorsInDaysOrHours = false;
		
		//Choose the models
		this.setModels(new Classifier[]{
				new ZeroR(), 
				new LinearRegression(), 
				new REPTree(), 
				new RandomForest(),
		});
	}

	private AnalyticRuleSpec genAnalyticRulesSpec() throws Exception{
		
		String te = "COUNT{TRUE # CURR:LAST}";

		AnalyticRuleSpec ars = new AnalyticRuleSpec();

		ConditionExpression ce1 = FOEFormulaParser.parse("(CURR < LAST)");
		TargetExpression te1 =TargetExpressionParser.parse(te);
		TargetExpression te2 = TargetExpressionParser.parse("0");
		
		ars.addRuleSpec(ce1, te1);
		ars.setOtherwiseTarget(te2);
		
		return ars;
	}

}
