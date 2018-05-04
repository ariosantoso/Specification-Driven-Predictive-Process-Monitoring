/*******************************************************************************
 *     Specification-Driven-Predictive-Process-Monitoring
 *     Copyright (C) 2018 Ario Santoso (santoso.ario@gmail.com)
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package org.astw.parser.targetexp;


import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.astw.analyticrules.TargetExpression;
import org.astw.foe.Formula;
import org.astw.parser.foe.antlr.FOELexer;
import org.astw.parser.foe.antlr.FOEParser;
import org.astw.parser.targetexp.antlr.TargetExpParser;

/**
 * @author Ario Santoso (santoso.ario@gmail.com)
 */
public class TargetExpressionParser {

	/**
	 * @author Ario Santoso (santoso.ario@gmail.com)
	 */
	public static TargetExpression parse(String inputFormula) throws Exception{
		
		boolean failed = false;
		
//		System.out.println("============================================================");
//		System.out.println("INPUT: \n\t"+inputFormula+"\n");
		
		//Setup ANTLR generated lexer and parser
		CodePointCharStream inputStream = CharStreams.fromString(inputFormula);
		FOELexer lexer = new FOELexer(inputStream);
		TokenStream tokenStream = new CommonTokenStream(lexer);
		TargetExpParser parser = new TargetExpParser(tokenStream);
		//END OF Setup ANTLR generated lexer and parser

		//Setup ANTLR generated lexer error listener
		lexer.removeErrorListeners();
		TargetExpressionParseErrorListener lexerErrorListener = new TargetExpressionParseErrorListener();
		lexer.addErrorListener(lexerErrorListener);
		//END OF Setup ANTLR generated lexer error listener

		//Setup ANTLR generated parser error listener
		parser.removeErrorListeners();
		TargetExpressionParseErrorListener parserErrorListener = new TargetExpressionParseErrorListener();
		parser.addErrorListener(parserErrorListener );
		//END OF Setup ANTLR generated parser error listener

		//try to parse the given FOE formula
		TargetExpression targetExp= null;
		try{
			targetExp = parser.targetExp().te;
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
		return targetExp;
	}

	
}
