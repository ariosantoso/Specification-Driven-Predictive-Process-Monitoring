package org.astw.exp.bpi13.ex2;

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

class E2 extends SDPROM{

	private String title = "BPI 13 - PingPong - change of people/group and change back to the original person/group";

	protected E2() {}
	
	protected void initializeE2(String id, String configFile) throws Exception{

		this.init(id, title, configFile, genAnalyticRulesSpec());
		
		//Choose the models
		this.setModels(new Classifier[]{
				new ZeroR(), 
				new Logistic(),
				new J48(), 
				new RandomForest(),
		});
	}

	//Pingpong - 3 or 4 steps - change of people/group and change back to the original person/group
	private AnalyticRuleSpec genAnalyticRulesSpec() throws Exception{
		
		AnalyticRuleSpec ars = new AnalyticRuleSpec();
		
		TargetExpression te1 = TargetExpressionParser.parse("\"PingPong\"");
		TargetExpression te2 = TargetExpressionParser.parse("\"No PingPong\"");

		String cestr1 = " EXISTS i.(e[i].org:resource !=  e[i+1].org:resource && e[i].org:resource ==  e[i+3].org:resource)";
		String cestr2 = " EXISTS i.(e[i].org:resource !=  e[i+1].org:resource && e[i].org:resource ==  e[i+2].org:resource)";
		String cestr3 = " EXISTS i.(e[i].org:group !=  e[i+1].org:group && e[i].org:group ==  e[i+3].org:group)";
		String cestr4 = " EXISTS i.(e[i].org:group !=  e[i+1].org:group && e[i].org:group ==  e[i+2].org:group)";

		ars.addRuleSpec(FOEFormulaParser.parse(cestr1), te1);
		ars.addRuleSpec(FOEFormulaParser.parse(cestr2), te1);
		ars.addRuleSpec(FOEFormulaParser.parse(cestr3), te1);
		ars.addRuleSpec(FOEFormulaParser.parse(cestr4), te1);
		ars.setOtherwiseTarget(te2);
		
		return ars;
	}


}
