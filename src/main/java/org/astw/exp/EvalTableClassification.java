package org.astw.exp;

import java.util.ArrayList;

class EvalTableClassification {

	//File location for storing output
	String experimentTableFilePath = "";
	String paperTableFilePath = "";

	
	//Table Content
	ArrayList<EvalTableClassificationContent> content = new ArrayList<EvalTableClassificationContent>();
	String experimentTitle = "";
	String experimentID = "";
	
	EvalTableClassification(String expID, String expTitle){
		
		this.experimentTitle = expTitle;
		this.experimentID = expID;
	}
	
	void addContent(EvalTableClassificationContent newContent){
		
		this.content.add(newContent);
	}
	
	String generateLatexTableDetail(){
		
		StringBuilder table = new StringBuilder( 
				"%==================================================================================== \n"+
				"\\begin{table} \n"+
				"\\caption{"+this.experimentID+" - "+this.experimentTitle+"} \n"+
				"\\centering \n"+
				"\\begin{tabular}{|| l | c | c | c | c | c ||| c | c | c | c ||} \n"+
				"\\hline \n\n");

		StringBuilder tableFooter = new StringBuilder(
				"\n%---------------------------------------------------------------------------------------------------- \n"+
				"\\hline \n"+
				"\\end{tabular}  \n"+
				"\\end{table} \n"+
				"%==================================================================================== \n\n\n");

		for(EvalTableClassificationContent cnt : this.content)
			table.append(cnt.generateLatexTableContentDetail());

		table.append(tableFooter);
		
		return table.toString();
	}

	String generateLatexTableOverview(){
		
		StringBuilder table = new StringBuilder( 
				"%==================================================================================== \n"+
				"\\begin{table} \n"+
				"\\caption{"+this.experimentID+" - "+this.experimentTitle+"} \n"+
				"\\centering \n"+
				"\\begin{tabular}{|| l | c | c | c | c | c ||} \n"+
				"\\hline \n\n");

		StringBuilder tableFooter = new StringBuilder(
				"\n%---------------------------------------------------------------------------------------------------- \n"+
				"\\hline \n"+
				"\\end{tabular}  \n"+
				"\\end{table} \n"+
				"%==================================================================================== \n\n\n");

		for(EvalTableClassificationContent cnt : this.content)
			table.append(cnt.generateLatexTableContentOverview());

		table.append(tableFooter);
		
		return table.toString();
	}

	public static void main(String[] ar){
		
		
		EvalTableClassificationRow row1 = 
			new EvalTableClassificationRow("Random Forest", "0", "0", "0", "0", "0");

		EvalTableClassificationRow row2 = 
			new EvalTableClassificationRow("Decision Tree", "0", "0", "0", "0", "0");

		row1.addSubRow("Class A", "0", "0", "0");
		row1.addSubRow("Class B", "0", "0", "0");
		row2.addSubRow("Class A", "0", "0", "0");
		row2.addSubRow("Class B", "0", "0", "0");

		EvalTableClassificationContent contentTraining = 
			new EvalTableClassificationContent("Evaluation on Training");
		contentTraining.addRow(row1);
		contentTraining.addRow(row2);

		EvalTableClassificationContent contentTest = 
				new EvalTableClassificationContent("Evaluation on Test");
		contentTest.addRow(row1);
		contentTest.addRow(row2);
		
		EvalTableClassification table = new EvalTableClassification("E11", "Ping-Pong Generated");
		table.addContent(contentTraining);
		table.addContent(contentTest);
		

		System.out.println(table.generateLatexTableDetail());
//		System.out.println(table.generateLatexTableOverview());
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
class EvalTableClassificationContent {
	
	//labels for the table header
	String contentName = "Evaluation";
	
	final String lblModel = "Model";
	final String lblClassName = "Class Name";
	
	final String lblAUC = "AUC";
	final String lblAcc = "Acc";
	
	final String lblWPrecision = "Weighted Precision";
	final String lblWRecall= "Weighted Recall";
	final String lblWFMeasure= "Weighted F-Measure";

	final String lblPrecision = "Precision";
	final String lblRecall= "Recall";
	final String lblFMeasure= "F-Measure";
	//END OF labels for the table header

	
	ArrayList<EvalTableClassificationRow> rows = new ArrayList<EvalTableClassificationRow>();
							
	EvalTableClassificationContent(String contentName){
		
		this.contentName = contentName;
	}
	
	void addRow(EvalTableClassificationRow row){
		
		this.rows.add(row);
	}
	
	String generateLatexTableContentDetail(){
		
		markTheBest();
		
		StringBuilder tableContent = new StringBuilder( 
			"%++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n"+
			"\\multicolumn{10}{||c||}{\\textsc{\\bfseries "+this.contentName+"}}\\\\ \n"+
			"\\hline\n"+
			"Model & "+this.lblAUC+" & "+this.lblAcc+" & "+this.lblWPrecision+" & "+this.lblWRecall+" & "+this.lblFMeasure+
			"& "+this.lblClassName+" & "+this.lblPrecision+" & "+this.lblRecall+" & "+this.lblFMeasure+" \\\\ \n"+
			"\\hline \n"+
			"%++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");


		for(EvalTableClassificationRow row : this.rows)
			tableContent.append(row.generateRowForLatexTableContentDetail());
		
		tableContent.append("\n\n\n");

		return tableContent.toString();
	}

	String generateLatexTableContentOverview(){
		
		markTheBest();

		StringBuilder tableContent = new StringBuilder( 
			"%++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n"+
			//"\\multicolumn{6}{||c||}{\\textsc{\\bfseries "+this.contentName+"}}\\\\ \n"+
			"\\hline\n"+
			" & "+this.lblAUC+" & "+this.lblAcc+" & "+this.lblWPrecision+" & "+this.lblWRecall+" & "+this.lblFMeasure+" \\\\ \n"+
			"\\hline \n"+
			"%++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");


		for(EvalTableClassificationRow row : this.rows)
			tableContent.append(row.generateRowForLatexTableContentOverview());
		
		tableContent.append("\n\n\n");

		return tableContent.toString();
	}

	void markTheBest(){
				
		double bestAUC = 0;
		double bestAcc = 0;
		double bestwPrecision = 0;
		double bestwRecall = 0;
		double bestwFMeasure = 0;
		
		for(EvalTableClassificationRow row : this.rows){

			double currAUC = Double.parseDouble(row.AUC.replace(",", ""));
			double currAcc = Double.parseDouble(row.Acc.replace(",", ""));
			double currwPrecision = Double.parseDouble(row.wPrecision.replace(",", ""));
			double currwRecall = Double.parseDouble(row.wRecall.replace(",", ""));
			double currwFMeasure = Double.parseDouble(row.wFMeasure.replace(",", ""));
			
			if(currAUC > bestAUC) bestAUC= currAUC;
			if(currAcc > bestAcc) bestAcc= currAcc;
			if(currwPrecision > bestwPrecision) bestwPrecision= currwPrecision;
			if(currwRecall > bestwRecall) bestwRecall= currwRecall;
			if(currwFMeasure > bestwFMeasure) bestwFMeasure= currwFMeasure;
			
			row.bestAUC = false;
			row.bestAcc = false;
			row.bestwPrecision = false;
			row.bestwRecall = false;
			row.bestwFMeasure = false;
		}
		
		for(EvalTableClassificationRow row : this.rows){

			double currAUC = Double.parseDouble(row.AUC.replace(",", ""));
			double currAcc = Double.parseDouble(row.Acc.replace(",", ""));
			double currwPrecision = Double.parseDouble(row.wPrecision.replace(",", ""));
			double currwRecall = Double.parseDouble(row.wRecall.replace(",", ""));
			double currwFMeasure = Double.parseDouble(row.wFMeasure.replace(",", ""));
			
			if(currAUC == bestAUC) row.bestAUC = true;
			if(currAcc == bestAcc) row.bestAcc = true;
			if(currwPrecision == bestwPrecision) row.bestwPrecision = true;
			if(currwRecall == bestwRecall) row.bestwRecall = true;
			if(currwFMeasure == bestwFMeasure) row.bestwFMeasure = true;
		}

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
class EvalTableClassificationRow {
	
	String modelName = "";
	String AUC = "";
	String Acc = "";
	String wPrecision = "";
	String wRecall = "";
	String wFMeasure = "";
	
	boolean bestAUC = false;
	boolean bestAcc = false;
	boolean bestwPrecision = false;
	boolean bestwRecall = false;
	boolean bestwFMeasure = false;
	
	ArrayList<EvalTableClassificationSubRow> subContent = 
			new ArrayList<EvalTableClassificationSubRow>();
							
	EvalTableClassificationRow(
			String modelName, String AUC, String Acc, String wPrecision, String wRecall, String wFMeasure){
		
		this.modelName = modelName;
		this.AUC = AUC;
		this.Acc = Acc;
		this.wPrecision = wPrecision;
		this.wRecall = wRecall;
		this.wFMeasure = wFMeasure;
	}
	
	void addSubRow(String className, String precision, String recall, String fmeasure){
		
		this.subContent.add(new EvalTableClassificationSubRow(className, precision, recall, fmeasure));
	}
	
	String generateRowForLatexTableContentDetail(){
		
		int numOfClass = this.subContent.size();
		
		if(this.subContent.size() == 0){
		
			StringBuilder contentOut = new StringBuilder(
					"%----------------------------------------------------------------------------------------------------\n"+
					""+this.modelName+" & "
					+ ""+(this.bestAUC? "\\textbf{"+this.AUC+"}" : this.AUC )+" & "
					+ ""+(this.bestAcc? "\\textbf{"+this.Acc+"}" : this.Acc )+" & "
					+ ""+(this.bestwPrecision? "\\textbf{"+this.wPrecision+"}" : this.wPrecision )+" & "
					+ ""+(this.bestwRecall? "\\textbf{"+this.wRecall+"}" : this.wRecall )+" & "
					+ ""+(this.bestwFMeasure? "\\textbf{"+this.wFMeasure+"}" : this.wFMeasure )+" & "
									+ "- & "
									+ "- & "
									+ "- & "
									+ "- \\\\ \n"+
					"\\hline\n");

			contentOut.append("%----------------------------------------------------------------------------------------------------\n");
			
			return contentOut.toString();

		}
		
		StringBuilder contentOut = new StringBuilder(
			"%----------------------------------------------------------------------------------------------------\n"+
			"\\multirow{"+numOfClass+"}{*}{"+this.modelName+"} & "
					+ "\\multirow{"+numOfClass+"}{*}{"+(this.bestAUC? "\\textbf{"+this.AUC+"}" : this.AUC )+"} & "
					+ "\\multirow{"+numOfClass+"}{*}{"+(this.bestAcc? "\\textbf{"+this.Acc+"}" : this.Acc )+"} & "
					+ "\\multirow{"+numOfClass+"}{*}{"+(this.bestwPrecision? "\\textbf{"+this.wPrecision+"}" : this.wPrecision )+"} & "
					+ "\\multirow{"+numOfClass+"}{*}{"+(this.bestwRecall? "\\textbf{"+this.wRecall+"}" : this.wRecall )+"} & "
					+ "\\multirow{"+numOfClass+"}{*}{"+(this.bestwFMeasure? "\\textbf{"+this.wFMeasure+"}" : this.wFMeasure )+"} & "
							+ ""+this.subContent.get(0).className+" & "
							+ ""+this.subContent.get(0).precision+" & "
							+ ""+this.subContent.get(0).recall+" & "
							+ ""+this.subContent.get(0).fmeasure+" \\\\ \n"+
			"\\cline{7-10}\n");
		
		String contentTemplate = 
			" &   &   &   &   &   & %s & %s & %s & %s \\\\ \n"+
			"\\hline\n";

		if(numOfClass > 1){
			
			for(int ii = 1; ii < numOfClass; ii++){
				contentOut.append(String.format(contentTemplate, 
						this.subContent.get(ii).className, 
						this.subContent.get(ii).precision, 
						this.subContent.get(ii).recall, 
						this.subContent.get(ii).fmeasure));
			}
		}
		
		contentOut.append("%----------------------------------------------------------------------------------------------------\n");
		
		return contentOut.toString();
	}

	String generateRowForLatexTableContentOverview(){

		StringBuilder contentOut = new StringBuilder(
			"%----------------------------------------------------------------------------------------------------\n"+
			""+this.modelName+" & "
					+ ""+(this.bestAUC? "\\textbf{"+this.AUC+"}" : this.AUC )+" & "
					+ ""+(this.bestAcc? "\\textbf{"+this.Acc+"}" : this.Acc )+" & "
					+ ""+(this.bestwPrecision? "\\textbf{"+this.wPrecision+"}" : this.wPrecision )+" & "
					+ ""+(this.bestwRecall? "\\textbf{"+this.wRecall+"}" : this.wRecall )+" & "
					+ ""+(this.bestwFMeasure? "\\textbf{"+this.wFMeasure+"}" : this.wFMeasure )+" \\\\ \n"+
			"\\hline\n");

		contentOut.append("%----------------------------------------------------------------------------------------------------\n");
			
		return contentOut.toString();
	}

	class EvalTableClassificationSubRow{

		String className = "";
		String precision = "";
		String recall = "";
		String fmeasure = "";

		EvalTableClassificationSubRow(String className, String precision, String recall, String fmeasure){
			
			this.className = className;
			this.precision = precision;
			this.recall = recall;
			this.fmeasure = fmeasure;
		}
	}

}


