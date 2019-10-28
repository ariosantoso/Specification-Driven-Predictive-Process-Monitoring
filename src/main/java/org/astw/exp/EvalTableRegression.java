package org.astw.exp;

import java.util.ArrayList;

class EvalTableRegression {

	//File location for storing output
	String experimentTableFilePath = "";
	String paperTableFilePath = "";

	
	//Table Content
	ArrayList<EvalTableRegressionContent> content = new ArrayList<EvalTableRegressionContent>();
	String experimentTitle = "";
	String experimentID = "";
	
	EvalTableRegression(String expID, String expTitle){
		
		this.experimentTitle = expTitle;
		this.experimentID = expID;
	}
	
	void addContent(EvalTableRegressionContent newContent){
		
		this.content.add(newContent);
	}
	
	String generateLatexTableDetail(){
		
		StringBuilder table = new StringBuilder( 
				"%==================================================================================== \n"+
				"\\begin{table} \n"+
				"\\caption{"+this.experimentID+" - "+this.experimentTitle+"} \n"+
				"\\centering \n"+
				"\\begin{tabular}{|| l | c | c | c | c | c | c | c | c ||} \n"+
				"\\hline \n\n");

		StringBuilder tableFooter = new StringBuilder(
				"\n%---------------------------------------------------------------------------------------------------- \n"+
				"\\hline \n"+
				"\\end{tabular}  \n"+
				"\\end{table} \n"+
				"%==================================================================================== \n\n\n");

		for(EvalTableRegressionContent cnt : this.content)
			table.append(cnt.generateLatexTableContentDetail());

		table.append(tableFooter);
		
		return table.toString();
	}

	public static void main(String[] ar){
		
		
		EvalTableRegressionRow row1 = 
			new EvalTableRegressionRow("Random Forest", "0", "0", "0", "0", "0", "0", "0", "0");

		EvalTableRegressionRow row2 = 
			new EvalTableRegressionRow("Linear Regression", "0", "0", "0", "0", "0", "0", "0", "0");

		EvalTableRegressionContent contentTraining = 
			new EvalTableRegressionContent("Evaluation on Training");
		contentTraining.addRow(row1);
		contentTraining.addRow(row2);

		EvalTableRegressionContent contentTest = 
				new EvalTableRegressionContent("Evaluation on Test");
		contentTest.addRow(row1);
		contentTest.addRow(row2);
		
		EvalTableRegression table = new EvalTableRegression("E11", "Ping-Pong Generated");
		table.addContent(contentTraining);
		table.addContent(contentTest);
		

		System.out.println(table.generateLatexTableDetail());
	}

}


//////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////




/**
 * Container of an experiment over a particular model
 * @author Ario Santoso (santoso.ario@gmail.com)
 */
class EvalTableRegressionContent {
	
	//labels for the table header
	String contentName = "Evaluation";
	
//	final String lblModel = "Model";
//	final String lblClassName = "Class Name";
//	
//	final String lblCorrelationCoefficient = "CorrelationCoefficient";
//	final String lblCoefficientOfDetermination = "CoefficientOfDetermination";
//	
//	final String lblMAE = "MAE";
//	final String lblRMSE = "RMSE";
//	
//	final String lblmaeOri= "ori";
//	final String lblmaeHours = "in hour(s)";
//	final String lblmaeDays= "in day(s)";
//
//	final String lblrmseOri= "ori";
//	final String lblrmseHours = "in hour(s)";
//	final String lblrmseDays= "in day(s)";
	//END OF labels for the table header

	
	ArrayList<EvalTableRegressionRow> rows = new ArrayList<EvalTableRegressionRow>();
							
	EvalTableRegressionContent(String contentName){
		
		this.contentName = contentName;
	}
	
	void addRow(EvalTableRegressionRow row){
		
		this.rows.add(row);
	}
	
	String generateLatexTableContentDetail(){
		

		StringBuilder tableContent = new StringBuilder( 
			"%++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n"+
			"\\multicolumn{9}{||c||}{\\textsc{\\bfseries "+this.contentName+"}}\\\\ \n"+
			"\\hline\n"+
			"\\multirow{2}{*}{Model}&Correlation&Coefficient Of&\\multicolumn{3}{c|}{MAE}&\\multicolumn{3}{c||}{RMSE}\\\\\n"+
			"\\cline{4-9} \n"+
			"&Coefficient&Determination&ori& in hour(s)&in day(s)&ori& in hour(s)&in day(s)\\\\\n"+
			"\\hline \n");

		//mark the best
		
		double bestMAE = Double.MAX_VALUE;
		double bestRMSE = Double.MAX_VALUE;
		double bestCorrCoef = 0;
		double bestCoefDet = 0;
		
		for(EvalTableRegressionRow row : this.rows){

			double currMae = Double.parseDouble(row.maeOri.replace(",", ""));
			double currRMSE = Double.parseDouble(row.rmseOri.replace(",", ""));
			double currCorrCoef = Double.parseDouble(row.correlationCoefficient.replace(",", ""));
			double currCoefDet = Double.parseDouble(row.coefficientOfDetermination.replace(",", ""));
			
			if(currMae < bestMAE) bestMAE= currMae;
			if(currRMSE < bestRMSE) bestRMSE= currRMSE;
			if(currCorrCoef > bestCorrCoef) bestCorrCoef = currCorrCoef;
			if(currCoefDet > bestCoefDet) bestCoefDet = currCoefDet;
			
			row.bestMAE = false;
			row.bestRMSE = false;
			row.bestCorrelationCoef = false;
			row.bestCoefOfDet = false;
		}
		
		for(EvalTableRegressionRow row : this.rows){
			
			double currMae = Double.parseDouble(row.maeOri.replace(",", ""));
			double currRMSE = Double.parseDouble(row.rmseOri.replace(",", ""));
			double currCorrCoef = Double.parseDouble(row.correlationCoefficient.replace(",", ""));
			double currCoefDet = Double.parseDouble(row.coefficientOfDetermination.replace(",", ""));

			if(currMae == bestMAE) row.bestMAE = true;
			if(currRMSE == bestRMSE) row.bestRMSE = true;
			if(currCorrCoef == bestCorrCoef) row.bestCorrelationCoef= true;
			if(currCoefDet == bestCoefDet) row.bestCoefOfDet = true;
		}
		//END OF marking the best

		for(EvalTableRegressionRow row : this.rows)
			tableContent.append(row.generateRowForLatexTableContentDetail());
		

		tableContent.append("%++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");
		tableContent.append("\n\n\n");

		return tableContent.toString();
	}


}



//////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////



/**
 * Container of an experiment over a particular model
 * @author Ario Santoso (santoso.ario@gmail.com)
 */
class EvalTableRegressionRow {
	
	String modelName = "";
	String correlationCoefficient = "";
	String coefficientOfDetermination = "";
	
	String maeOri = "";
	String maeInHours = "";
	String maeInDays = "";

	String rmseOri = "";
	String rmseInHours = "";
	String rmseInDays = "";
	
	boolean bestCorrelationCoef = false;
	boolean bestCoefOfDet = false;
	boolean bestMAE = false;
	boolean bestRMSE = false;
	
	EvalTableRegressionRow(
			String modelName, String correlationCoefficient, String coefficientOfDetermination, 
			String maeOri, String maeInHours, String maeInDays,
			String rmseOri, String rmseInHours, String rmseInDays){
		
		this.modelName = modelName;
		this.correlationCoefficient = correlationCoefficient;
		this.coefficientOfDetermination = coefficientOfDetermination;
		
		this.maeOri = maeOri;
		this.maeInHours = maeInHours;
		this.maeInDays = maeInDays;
		
		this.rmseOri = rmseOri;
		this.rmseInHours = rmseInHours;
		this.rmseInDays = rmseInDays;
	}
	
	String generateRowForLatexTableContentDetail(){
		
		StringBuilder contentOut = new StringBuilder(
				"%----------------------------------------------------------------------------------------------------\n"+
				""+this.modelName+" & "
//						+ ""+(bestCorrelationCoef? "\\textbf{"+this.correlationCoefficient+"}" : this.correlationCoefficient )+" & "
//						+ ""+(bestCoefOfDet? "\\textbf{"+this.coefficientOfDetermination+"}" : this.coefficientOfDetermination )+" & "
						+ " & "
						+ " & "
						+ ""+(bestMAE? "\\textbf{"+this.maeOri+"}" : this.maeOri )+" & "
						+ ""+(bestMAE? "\\textbf{"+this.maeInHours+"}" :this.maeInHours)+" & "
						+ ""+(bestMAE? "\\textbf{"+this.maeInDays+"}" :this.maeInDays)+" & "
						+ ""+(bestRMSE? "\\textbf{"+this.rmseOri+"}" : this.rmseOri )+" & "
						+ ""+(bestRMSE? "\\textbf{"+this.rmseInHours+"}" : this.rmseInHours)+" & "
						+ ""+(bestRMSE? "\\textbf{"+this.rmseInDays+"}" : this.rmseInDays)+" \\\\ \n"+
				"\\hline\n");

		contentOut.append("%----------------------------------------------------------------------------------------------------\n");
		
		return contentOut.toString();
	}

}
