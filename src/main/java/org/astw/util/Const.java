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
package org.astw.util;

import org.deckfour.xes.model.XTrace;

import weka.classifiers.Classifier;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;

/**
 * 
 * @author Ario Santoso (santoso.ario@gmail.com)
 *
 */
public class Const {

	public final static String CONST_PREFIX = "$#@&≠¶§∞¢£™¡";
	public final static String SPACE = "     ";

	//Undefined values
	public final static double UNDEFINED_NUM_VAL = Double.NaN;
	public final static String UNDEFINED_NON_NUM_VAL = "∞§¡¶¢∞§¢¶™£";
	
	
	//constanst for index expression
	public final static String CURR = CONST_PREFIX+"CURR";
	public final static String CURRStr = "CURR";
	public final static String LAST = CONST_PREFIX+"LAST";
	public final static String LASTStr = "LAST";
	public enum SpecialIndexType {CURR, LAST};
	
	//operators
//	public enum ACOP {GREATER_THAN, LESS_THAN, GREATER_THAN_OR_EQUAL, LESS_THAN_OR_EQUAL};
//	public enum LCOP {EQUAL, NOT_EQUAL};
	public enum ComparisonOperator {EQUAL, NOT_EQUAL, GREATER_THAN, LESS_THAN, GREATER_THAN_OR_EQUAL, LESS_THAN_OR_EQUAL};
	public enum BinaryLogicalOperator {AND, OR, IMPL};
	public enum UnaryLogicalOperator {NEG};
	public enum Quantifier {FORALL, EXISTS};
	public enum ArithmeticOperator {PLUS, MINUS, MUL, DIV};

	//type stuff
	public enum NumberType {INT, DOUBLE}
	public enum ValueType {NUMERIC_EXP, NON_NUMERIC_EXP, UNKNOWN}
	//public enum EECompType {NUMERIC_EXP, NON_NUMERIC_EXP, BOOLEAN, UNKNOWN}
	
	//XES data type. Note: XES_UNKNOWN is used before we assign/discover the type of an attribute accessor
	public enum XESDataType {	XES_INT, XES_DOUBLE, XES_DATE_TIME, XES_LONG, 
								XES_STRING, XES_BOOLEAN, 
								XES_UNKNOWN }
	
	//Prediction types
	public enum PrefLengthType {EARLY_EVENT, MID_EVENT, LATE_EVENT, VERY_LATE_EVENT}

	//Predictor models
	public enum PredictorModelType {DecisionTree, RandomForest, LinearRegression};

	//Special Output Format
	public enum SpecialOutputFormat {NO_SPECIAL_FORMAT, TIMESTAMP_STRING, HOURS};

	//====================================================================================================
	
	//Some methods to get the string representation of some constant
	public static String arithmeticOperatorToString(ArithmeticOperator operator){
		
		if(operator == ArithmeticOperator.PLUS) return "+";
		else if(operator == ArithmeticOperator.MINUS) return "-";
		else if(operator == ArithmeticOperator.MUL) return "*";
		else if(operator == ArithmeticOperator.DIV) return "/";
		else return "";
	}

	public static String comparisonOperatorToString(ComparisonOperator operator){
		
		if(operator == ComparisonOperator.EQUAL) return "==";
		else if(operator == ComparisonOperator.NOT_EQUAL) return "!=";
		else if(operator == ComparisonOperator.GREATER_THAN) return ">";
		else if(operator == ComparisonOperator.LESS_THAN) return "<";
		else if(operator == ComparisonOperator.GREATER_THAN_OR_EQUAL) return ">=";
		else if(operator == ComparisonOperator.LESS_THAN_OR_EQUAL) return "<=";
		else return "";
	}

	public static String binaryLogicalOperatorToString(BinaryLogicalOperator operator){
		
		if(operator == BinaryLogicalOperator.AND) return "&&";
		else if(operator == BinaryLogicalOperator.OR) return "||";
		else if(operator == BinaryLogicalOperator.IMPL) return "->";
		else return "";
	}

	public static String unaryLogicalOperatorToString(UnaryLogicalOperator operator){
		
		if(operator == UnaryLogicalOperator.NEG) return "!";
		else return "";
	}

	public static String specialIndexTypeToString(SpecialIndexType operator){
		
		if(operator == SpecialIndexType.CURR) return "CURR";
		else if(operator == SpecialIndexType.LAST) return "LAST";
		else return "";
	}
	//END OF Some methods to get the string representation of some constant
	
	public static int getPrefixLength(int traceLength, PrefLengthType prefLengthType){
		
		int minLength = 1;
		
		if(prefLengthType == PrefLengthType.EARLY_EVENT){

			return Math.max((traceLength / 4), minLength);
//			return (traceLength / 4);
						
		}else if(prefLengthType == PrefLengthType.MID_EVENT){
			
			return Math.max((traceLength / 2), minLength);
//			return (traceLength / 2);

		}else if(prefLengthType == PrefLengthType.LATE_EVENT){
			
			return Math.max(((traceLength / 4) * 3), minLength);
//			return ((traceLength / 4) * 3);
			
		}else if(prefLengthType == PrefLengthType.VERY_LATE_EVENT){
			
			return Math.max((traceLength - 3), minLength);
//			return traceLength - 3;
			
		}else
			return traceLength;
		
	}
	
	public static boolean isNotUnknown(XESDataType xdt){
		
		return (xdt != XESDataType.XES_UNKNOWN);
	}

	public static boolean isNumeric(XESDataType xdt){
		
		if(	xdt == XESDataType.XES_INT || xdt == XESDataType.XES_DOUBLE || 
			xdt == XESDataType.XES_LONG || xdt == XESDataType.XES_DATE_TIME)
			return true;
		else 
			return false;
	}

	public static boolean isNonNumeric(XESDataType xdt){
		
		if(xdt == XESDataType.XES_STRING || xdt == XESDataType.XES_BOOLEAN)
			return true;
		else 
			return false;
	}
	
	public static ValueType getValueType(XESDataType xdt){
		
		if(isNumeric(xdt))
			return ValueType.NUMERIC_EXP;
		else if(isNonNumeric(xdt))
			return ValueType.NON_NUMERIC_EXP;
		else
			return ValueType.UNKNOWN;
	}

	public static Classifier getModelInstance(PredictorModelType predModelType){
		
		if(predModelType == PredictorModelType.DecisionTree)
			return new J48();
		else if(predModelType == PredictorModelType.LinearRegression)
			return new LinearRegression();
		else if(predModelType == PredictorModelType.RandomForest)
			return new RandomForest();
		
		return null;
	}
}
