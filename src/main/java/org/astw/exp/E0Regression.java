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
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.rules.ZeroR;

public class E0Regression extends SDPROM{

	public E0Regression() throws Exception{

		
//		init(this.getClass().getSimpleName(), "Test Reg", Mode.PYTHON, "config-py.txt", genAnalyticRulesSpec());
//		init(this.getClass().getSimpleName(), "Test Reg", Mode.PYTHON, "config.txt", genAnalyticRulesSpec());
//		init(this.getClass().getSimpleName(), "Test Reg", Mode.WEKA, "config.txt", genAnalyticRulesSpec());
//		init(this.getClass().getSimpleName(), "Test Reg", "config.txt", genAnalyticRulesSpec());
//		init(this.getClass().getSimpleName(), "Test Reg", "config-weka.txt", genAnalyticRulesSpec());
//		init(this.getClass().getSimpleName(), "Test Reg", "config-python.txt", genAnalyticRulesSpec());
//		init(this.getClass().getSimpleName(), "Test Reg", "config-mode-py.txt", genAnalyticRulesSpec());
		init(this.getClass().getSimpleName(), "Test Reg", "config-nn.txt", genAnalyticRulesSpec());

		//Specify the Encoding Type
		this.setEncodingType(new EncodingType[]{
//				EncodingType.OneHotV2, 
				EncodingType.AttEnc,
//				EncodingType.TimeSinceMidnight, 
//				EncodingType.TimeSinceFDoW, 
//				EncodingType.TimeDiffBefore, 
				});
		
		//Choose the models
		this.setModels(new Classifier[]{
				new ZeroR(), 
//				new ZeroR(), 
				new LinearRegression(), 
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

		//this.showErrorsInDaysOrHours = false;

	}
	
	public static void main(String ar[]){
		
		try {
			
			new E0Regression().exec();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	
	protected static AnalyticRuleSpec genAnalyticRulesSpec() throws Exception{
			
		String attName = "concept:name";
		String attVal = "Queued";
		String te = "SUM{e[x + 1].time:timestamp - e[x].time:timestamp # 1:LAST # e[x]."+attName+" == \""+attVal+"\"}";

		AnalyticRuleSpec ars = new AnalyticRuleSpec();

		ConditionExpression ce1 = FOEFormulaParser.parse("(CURR <LAST)");
		TargetExpression te1 =TargetExpressionParser.parse(te);
		TargetExpression te2 = TargetExpressionParser.parse("0");
		
		ars.addRuleSpec(ce1, te1);
		ars.setOtherwiseTarget(te2);
		
		return ars;
	}



}
