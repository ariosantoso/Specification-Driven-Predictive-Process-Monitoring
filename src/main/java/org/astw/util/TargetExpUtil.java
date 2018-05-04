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

import java.util.HashMap;

import org.astw.analyticrules.TargetExpression;
import org.astw.foe.Formula;
import org.astw.foe.NonNumExp;
import org.astw.foe.NumExp;
import org.astw.util.Const.ValueType;
import org.astw.util.Const.XESDataType;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;

/**
 * 
 * @author Ario Santoso (santoso.ario@gmail.com)
 *
 */
public class TargetExpUtil {

	/**
	 * Assign the corresponding XES Data Type to each AttributeAccessor based on the given XES event log
	 * 
	 * @param formula
	 * @param xlog
	 * @throws Exception
	 */
	public static TargetExpression assignXESDataType(TargetExpression targetExp, XLog xlog) throws Exception{
		
		TargetExpression te = targetExp.clone();
		
		HashMap<String, XESDataType> xdtMap = XESUtil.getAttributeNamesTypesMap(xlog);
		te.assignQueryXESDataType(xdtMap);
		
		return te;
	}

	/**
	 * Assign the corresponding XES Data Type to each AttributeAccessor based on the given XTrace
	 * 
	 * @param formula
	 * @param xtrace
	 * @throws Exception
	 */
	public static TargetExpression assignXESDataType(TargetExpression targetExp, XTrace xtrace) throws Exception{
		
		TargetExpression te = targetExp.clone();
		
		HashMap<String, XESDataType> xdtMap = XESUtil.getAttributeNamesTypesMap(xtrace);
		te.assignQueryXESDataType(xdtMap);
				
		return te;
	}
	
	/**
	 * Assign the corresponding XES Data Type to each AttributeAccessor 
	 * based on the mapping between attribute names and their types
	 * 
	 * @param formula
	 * @param xdtmap
	 * @throws Exception
	 */
	public static TargetExpression assignXESDataType(TargetExpression targetExp, HashMap<String, XESDataType> xdtMap) throws Exception{
		
		TargetExpression te = targetExp.clone();
		te.assignQueryXESDataType(xdtMap);
		
		return te;
	}

	
	/**
	 * Evaluate the special indexes (i.e., CURR and LAST) with the corresponding value. 
	 * CURR is evaluated with the last index of the current prefix (In this case it is given
	 * as the value of 'current' in this method)
	 * 
	 * @param formula
	 * @param current
	 * @return
	 */
	public static TargetExpression evaluateSpecialIndex(TargetExpression targetExp, int current, int last){

		TargetExpression te = targetExp.clone();
		te.evaluateSpecialIndex(current, last);
		return te;
	}

	public static TargetExpression evaluateQuery(TargetExpression targetExp, XTrace xtrace)  throws Exception{

		TargetExpression te = targetExp.clone();
		te.evaluateQuery(xtrace);
		return te;
	}
	
	/**
	 * Evaluate the value of the target expression w.r.t. the given trace
	 * 
	 * NOTE: the input target expression for this method is assumed to be the one that is obtained after 
	 * the application of the method 'assignXESDataType'. I.e., we assume that the data type of
	 * each attribute in the queries (attribute accessor) has been clarified.
	 * 
	 * @return true - if the given xtrace satisfy the given formula (false - otherwise)
	 * @throws Exception - in case the evaluation is (currently) not possible
	 */
	public static double evaluateDoubleNumericTargetExpression(
			NumExp numericTargetExpression, XTrace xtrace, int current) throws Exception{
		
		NumExp ne0 = numericTargetExpression.clone();

		int last = xtrace.size();
		NumExp ne1 = (NumExp) TargetExpUtil.evaluateSpecialIndex(ne0, current, last);
		System.out.println("After Evaluating the Special Index: \n"+ne1.prettyFormat(""));
		NumExp ne2 = (NumExp) TargetExpUtil.evaluateQuery(ne1, xtrace);
		System.out.println("After Evaluating the Queries: \n"+ne2.prettyFormat(""));
		
		return ne2.getNumericValue();
	}
	
	/**
	 * Evaluate the value of the target expression w.r.t. the given trace
	 * 
	 * NOTE: the input target expression for this method is assumed to be the one that is obtained after 
	 * the application of the method 'assignXESDataType'. I.e., we assume that the data type of
	 * each attribute in the queries (attribute accessor) has been clarified.
	 * 
	 * @return true - if the given xtrace satisfy the given formula (false - otherwise)
	 * @throws Exception - in case the evaluation is (currently) not possible
	 */
	public static long evaluateLongNumericTargetExpression(
			NumExp numericTargetExpression, XTrace xtrace, int current) throws Exception{
		
		NumExp ne0 = numericTargetExpression.clone();

		int last = xtrace.size();
		NumExp ne1 = (NumExp) TargetExpUtil.evaluateSpecialIndex(ne0, current, last);
//		System.out.println("After Evaluating the Special Index: \n"+ne1.prettyFormat(""));
		NumExp ne2 = (NumExp) TargetExpUtil.evaluateQuery(ne1, xtrace);
//		System.out.println("After Evaluating the Queries: \n"+ne2.prettyFormat(""));
		
		return (long) ne2.getNumericValue();
	}
	
	/**
	 * Evaluate the value of the target expression w.r.t. the given trace
	 * 
	 * NOTE: the input target expression for this method is assumed to be the one that is obtained after 
	 * the application of the method 'assignXESDataType'. I.e., we assume that the data type of
	 * each attribute in the queries (attribute accessor) has been clarified.
	 * 
	 * @return true - if the given xtrace satisfy the given formula (false - otherwise)
	 * @throws Exception - in case the evaluation is (currently) not possible
	 */
	public static String evaluateNumericTargetExpression(
			NumExp numericTargetExpression, XTrace xtrace, int current) throws Exception{
		
		NumExp ne0 = numericTargetExpression.clone();

		int last = xtrace.size();

		NumExp ne1 = (NumExp) TargetExpUtil.evaluateSpecialIndex(ne0, current, last);
//		System.out.println("After Evaluating the Special Index: \n"+ne1.prettyFormat(""));
		NumExp ne2 = (NumExp) TargetExpUtil.evaluateQuery(ne1, xtrace);
//		System.out.println("After Evaluating the Queries: \n"+ne2.prettyFormat(""));
		
		if(ne2.getXESDataType() == XESDataType.XES_LONG)
			return ""+(long) ne2.getNumericValue();
		else
			return ""+ne2.getNumericValue();
	}
	
	/**
	 * Evaluate the value of the target expression w.r.t. the given trace
	 * 
	 * NOTE: the input target expression for this method is assumed to be the one that is obtained after 
	 * the application of the method 'assignXESDataType'. I.e., we assume that the data type of
	 * each attribute in the queries (attribute accessor) has been clarified.
	 * 
	 * @return true - if the given xtrace satisfy the given formula (false - otherwise)
	 * @throws Exception - in case the evaluation is (currently) not possible
	 */
	public static String evaluateNonNumericTargetExpression(
			NonNumExp nonNumericTargetExpression, XTrace xtrace, int current) throws Exception{
		
		NonNumExp nne0 = nonNumericTargetExpression.clone();
		
		int last = xtrace.size();
		NonNumExp nne1 = (NonNumExp) TargetExpUtil.evaluateSpecialIndex(nne0, current, last);
//		System.out.println("After Evaluating the Special Index: \n"+nne1.prettyFormat(""));
		NonNumExp nne2 = (NonNumExp) TargetExpUtil.evaluateQuery(nne1, xtrace);
//		System.out.println("After Evaluating the Queries: \n"+nne2.prettyFormat(""));
		
		return nne2.getNonNumericValue();
	}

	public static String computeTargetExpressionValue(
				TargetExpression targetExpression, XTrace xtrace, int consideredPrefixLength) throws Exception{
		
		TargetExpression te = targetExpression.clone();
		
		if(te instanceof org.astw.foe.impl.AttributeAccessor){
			
			if(te.getValueType() == ValueType.NUMERIC_EXP){
				
				
				String result = 
						TargetExpUtil.evaluateNumericTargetExpression(
								(NumExp) te , xtrace, consideredPrefixLength);

				return result;
				
			}else if(te.getValueType() == ValueType.NON_NUMERIC_EXP){
				
				String result = 
						TargetExpUtil.evaluateNonNumericTargetExpression(
								(NonNumExp) te , xtrace, consideredPrefixLength);

				return result;
			}
			
		}else{
			if(te instanceof NumExp){
				
				
				String result = 
						TargetExpUtil.evaluateNumericTargetExpression(
								(NumExp) te , xtrace, consideredPrefixLength);

				return result;
				
			}else if(te instanceof NonNumExp){
				
				String result = 
						TargetExpUtil.evaluateNonNumericTargetExpression(
								(NonNumExp) te , xtrace, consideredPrefixLength);

				return result;
			}
		}
		

		throw new Exception("Can't compute the target value!");
	}
	
}
