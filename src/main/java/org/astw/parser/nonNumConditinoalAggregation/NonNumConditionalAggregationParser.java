package org.astw.parser.nonNumConditinoalAggregation;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.astw.foe.impl.nonnumeric.NonNumConditionalAggregation;
import org.astw.foe.impl.numeric.NumConditionalAggregation;
import org.astw.parser.foe.ParseErrorListener;
import org.astw.parser.nonNumConditinoalAggregation.antlr.NonNumCondAggregationLexer;
import org.astw.parser.nonNumConditinoalAggregation.antlr.NonNumCondAggregationParser;
import org.astw.parser.numConditionalAggregation.antlr.NumCondAggregationLexer;
import org.astw.parser.numConditionalAggregation.antlr.NumCondAggregationParser;

/**
 * it parses the content of numeric aggregate functions SUM, AVG, MIN, MAX
 * @author Ario Santoso (santoso.ario@gmail.com)
 */


public class NonNumConditionalAggregationParser {


	/**
	 * @author Ario Santoso (santoso.ario@gmail.com)
	 */
	public static NonNumConditionalAggregation parse(String inputFormula) throws Exception{
		
		boolean failed = false;
		
//		System.out.println("============================================================");
//		System.out.println("INPUT: \n\t"+inputFormula+"\n");
		
		//Setup ANTLR generated lexer and parser
		CodePointCharStream inputStream = CharStreams.fromString(inputFormula);
		NonNumCondAggregationLexer lexer = new NonNumCondAggregationLexer(inputStream);
		TokenStream tokenStream = new CommonTokenStream(lexer);
		NonNumCondAggregationParser parser = new NonNumCondAggregationParser(tokenStream);
		//END OF Setup ANTLR generated lexer and parser

		//Setup ANTLR generated lexer error listener
		lexer.removeErrorListeners();
		ParseErrorListener lexerErrorListener = new ParseErrorListener();
		lexer.addErrorListener(lexerErrorListener);
		//END OF Setup ANTLR generated lexer error listener

		//Setup ANTLR generated parser error listener
		parser.removeErrorListeners();
		ParseErrorListener parserErrorListener = new ParseErrorListener();
		parser.addErrorListener(parserErrorListener );
		//END OF Setup ANTLR generated parser error listener

		//try to parse the given FOE formula
		NonNumConditionalAggregation parsedFormula= null;
		try{
			parsedFormula = parser.parseConditionalAggregation().parsedNonNumConditionalAggregation;

		}catch(Exception e){
			//Printing Error to console
			StringBuilder sb = new StringBuilder("\n---------------------------------------------\n");
			sb.append("PARSING ERROR: \n");
			sb.append("[ERROR] "+(e.getMessage() != null? e.getMessage(): "\n"));
			
			//failed = true;
			//parsedFormula =  null;
			
			throw new Exception(sb.toString());
		}

		//There is an error while parsing the formula
		if(parserErrorListener.isError() || lexerErrorListener.isError() || failed == true){
			
			//Printing Error to console
			StringBuilder sb = new StringBuilder("---------------------------------------------\n");
			sb.append("PARSING ERROR: \n");
			sb.append("[ERROR] "+(parserErrorListener.getMessage() != null? parserErrorListener.getMessage(): "\n"));
			
			//failed = true;
			//parsedFormula =  null;
			
			throw new Exception(sb.toString());

		}
		
//		if(failed == false)
//			System.out.println("OUTPUT: \n\t"+parsedFormula+"\n");

		//TODO: sanity check for the formula		

//		System.out.println("============================================================");
		
		//No error at all in the given formula, return the parsed temporal property
		return parsedFormula;
	}
}
