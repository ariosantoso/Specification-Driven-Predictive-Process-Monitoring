package org.astw.exp.bpi12.ex2;

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

class E2 extends SDPROM{

//	private String title = "BPI 12 - predict whether each 'SUBMITTED' event will be eventually followed by the 'APPROVED' event";
	private String title = "BPI 12 - predict whether the application will be eventually 'DECLINED'";

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

	private AnalyticRuleSpec genAnalyticRulesSpec() throws Exception{
		
//		String attName1 = "concept:name";
//		String attVal1 = "A_SUBMITTED";

		String attName2 = "concept:name";
		String attVal2 = "A_DECLINED";

		String target1 = "\"Declined\"";
		String target2 = "\"Not_Declined\"";

//		String cestr = 
//			"FORALL i . (e[i]."+attName1+" ==  \""+attVal1+"\" -> "
//					+ "(EXISTS j.( (j > i) && e[j]."+attName2+" ==  \""+attVal2+"\") ) )";

		String cestr = 	"EXISTS i.( (i > CURR) && e[i]."+attName2+" ==  \""+attVal2+"\")";

		
		AnalyticRuleSpec ars = new AnalyticRuleSpec();

		ConditionExpression ce1 = FOEFormulaParser.parse(cestr);
		TargetExpression te1 =TargetExpressionParser.parse(target1);
		TargetExpression te2 = TargetExpressionParser.parse(target2);
				
		ars.addRuleSpec(ce1, te1);
		ars.setOtherwiseTarget(te2);

		
		return ars;
	}

}
