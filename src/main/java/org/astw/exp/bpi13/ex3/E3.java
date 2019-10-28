package org.astw.exp.bpi13.ex3;

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

class E3 extends SDPROM {

	private String title = "BPI 13 - PingPong - the case involves at least three different groups";

	protected E3() {}
	
	protected void initializeE3(String id, String configFile) throws Exception{

		this.init(id, title, configFile, genAnalyticRulesSpec());
		
		//Choose the models
		this.setModels(new Classifier[]{
				new ZeroR(), 
				new Logistic(),
				new J48(), 
				new RandomForest(),
		});
	}

	//Pingpong - 3 or 4 steps - involves at least three different groups
	private AnalyticRuleSpec genAnalyticRulesSpec() throws Exception{
		
		AnalyticRuleSpec ars = new AnalyticRuleSpec();
		
		TargetExpression te1 = TargetExpressionParser.parse("\"PingPong\"");
		TargetExpression te2 = TargetExpressionParser.parse("\"No PingPong\"");

		String cestr1 = 
					" EXISTS i. EXISTS j. EXISTS k.( (i < j) && (j < k) && "
				+  	"e[i].org:group !=  e[j].org:group && "
				+ 	"e[i].org:group !=  e[k].org:group && "
				+ 	"e[j].org:group !=  e[k].org:group )";

		ars.addRuleSpec(FOEFormulaParser.parse(cestr1), te1);
		ars.setOtherwiseTarget(te2);
		
		return ars;
	}


}
