package org.astw.exp;

import org.astw.analyticrules.AnalyticRuleSpec;
import org.astw.analyticrules.ConditionExpression;
import org.astw.analyticrules.TargetExpression;
import org.astw.parser.foe.FOEFormulaParser;
import org.astw.parser.targetexp.TargetExpressionParser;
import org.astw.util.Const.Mode;
import org.astw.util.encoder.AttributeEncodingInfo;
import org.astw.util.encoder.Encoder.EncodingType;
import org.astw.util.encoder.OneHotEncodingV2Info;

import weka.classifiers.Classifier;
import weka.classifiers.rules.ZeroR;
import weka.classifiers.trees.J48;

public class E0Classification extends SDPROM{

	public E0Classification() throws Exception{

		init(this.getClass().getSimpleName(), "Test Clf", Mode.PYTHON, "config-py.txt", genAnalyticRulesSpec());
//		init(this.getClass().getSimpleName(), "Test Clf", Mode.PYTHON, "config.txt", genAnalyticRulesSpec());
//		init(this.getClass().getSimpleName(), "Test Clf", Mode.WEKA, "config.txt", genAnalyticRulesSpec());
//		init(this.getClass().getSimpleName(), "Test Clf", "config.txt", genAnalyticRulesSpec());
//		init(this.getClass().getSimpleName(), "Test Clf", "config-weka.txt", genAnalyticRulesSpec());
//		init(this.getClass().getSimpleName(), "Test Clf", "config-python.txt", genAnalyticRulesSpec());
//		init(this.getClass().getSimpleName(), "Test Clf", "config-mode-py.txt", genAnalyticRulesSpec());
//		init(this.getClass().getSimpleName(), "Test Clf", "config-nn.txt", genAnalyticRulesSpec());
//		init(this.getClass().getSimpleName(), "Test Clf", Mode.PYTHON, "config.txt", genAnalyticRulesSpec());

		//Choose the models
		this.setModels(new Classifier[]{
//				new ZeroR(), 
//				new ZeroR(), 
				new ZeroR(), 
//				new J48(), 
		//		new RandomForest(),
		});

		//Specify the Encoding Type
		this.setEncodingType(new EncodingType[]{
//				EncodingType.OneHotV2, 
				EncodingType.AttEnc,
//				EncodingType.TimeSinceMidnight, 
//				EncodingType.TimeSinceFDoW, 
//				EncodingType.TimeDiffBefore, 
				});
		
		//Set One Hot Encoding V2 Info
		this.setOneHotEncodingInfo(new OneHotEncodingV2Info[]{
//				new OneHotEncodingV2Info("org:resource", xlogOri), 
//				new OneHotEncodingV2Info("org:group", xlogOri), 
//			new OneHotEncodingV2Info("concept:name", xlogOri), 
//				new OneHotEncodingV2Info("lifecycle:transition", xlogOri), 
//				new OneHotEncodingV2Info("organization involved", xlogOri), 
//				new OneHotEncodingV2Info("impact", xlogOri), 
//				new OneHotEncodingV2Info("product", xlogOri), 
//				new OneHotEncodingV2Info("resource country", xlogOri), 
//				new OneHotEncodingV2Info("organization country", xlogOri), 
//				new OneHotEncodingV2Info("org:role", xlogOri), 
		});
		//END OF Set One Hot Encoding V2 Info
		
		//Set Attribute Encoding Info
		this.setAttributeEncodingInfo(new AttributeEncodingInfo[]{
//				new AttributeEncodingInfo("org:resource", xlogOri), 
//				new AttributeEncodingInfo("org:group", xlogOri), 
				new AttributeEncodingInfo("concept:name", xlogOri), 
//				new AttributeEncodingInfo("lifecycle:transition", xlogOri), 
//				new AttributeEncodingInfo("organization involved", xlogOri), 
//				new AttributeEncodingInfo("impact", xlogOri), 
//				new AttributeEncodingInfo("product", xlogOri), 
//				new AttributeEncodingInfo("resource country", xlogOri), 
//				new AttributeEncodingInfo("organization country", xlogOri), 
//				new AttributeEncodingInfo("org:role", xlogOri), 
		});
		//END OF Set Attribute Encoding Info

	}
	
	public static void main(String ar[]){
		
		try {
			
			new E0Classification().exec();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	
	protected static AnalyticRuleSpec genAnalyticRulesSpec() throws Exception{

		try{
			AnalyticRuleSpec ars = new AnalyticRuleSpec();

			TargetExpression te1 = TargetExpressionParser.parse("\"PingPong\"");
			TargetExpression te2 = TargetExpressionParser.parse("\"No PingPong\"");

			String cestr = " EXISTS i.( (i >= CURR) && (i+1 <= LAST) && e[i].org:group !=  e[i+1].org:group && e[i].concept:name != \"Queued\" )";
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
