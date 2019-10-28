package org.astw.exp.bpi13.ex1;

import org.astw.analyticrules.AnalyticRuleSpec;
import org.astw.analyticrules.ConditionExpression;
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

	private String title = "BPI 13 - PingPong - change of group while the concept:name is not 'queued'";
	
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

		try{
			AnalyticRuleSpec ars = new AnalyticRuleSpec();

			TargetExpression te1 = TargetExpressionParser.parse("\"PingPong\"");
			TargetExpression te2 = TargetExpressionParser.parse("\"No PingPong\"");

			//String cestr = " EXISTS i.( (i >= CURR) && (i+1 <= LAST) && e[i].org:group !=  e[i+1].org:group && e[i].concept:name != \"Queued\" )";
			String cestr = " EXISTS i.( (i > CURR) && (i < LAST) && "
									+ "e[i].org:group !=  e[i+1].org:group && "
									+ "e[i].concept:name != \"Queued\" )";
			ConditionExpression ce3 = FOEFormulaParser.parse(cestr);
			
			ars.addRuleSpec(ce3, te1);
			ars.setOtherwiseTarget(te2);
			
			return ars;

		}catch(Exception e){
			e.printStackTrace();
		}

		return null;
	}

}
