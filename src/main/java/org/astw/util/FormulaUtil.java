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

import org.astw.foe.Formula;
import org.astw.util.Const.XESDataType;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;

/**
 * 
 * @author Ario Santoso (santoso.ario@gmail.com)
 *
 */
public class FormulaUtil {

	/*
	 * TODO:
	 * [v]- quantifier elimination
	 * [v]- substitute index variable
	 * [v]- evaluate query
	 * [v]- evaluate special index?
	 * [v]- evaluate formula
	 */

	/**
	 * Assign the corresponding XES Data Type to each AttributeAccessor based on the given XES event log
	 * 
	 * @param formula
	 * @param xlog
	 * @throws Exception
	 */
	public static Formula assignXESDataType(Formula formula, XLog xlog) throws Exception{
		
		Formula f = formula.clone();
		
		HashMap<String, XESDataType> xdtMap = XESUtil.getAttributeNamesTypesMap(xlog);
		f.assignQueryXESDataType(xdtMap);
		
		return f;
	}

	/**
	 * Assign the corresponding XES Data Type to each AttributeAccessor based on the given XTrace
	 * 
	 * @param formula
	 * @param xtrace
	 * @throws Exception
	 */
	public static Formula assignXESDataType(Formula formula, XTrace xtrace) throws Exception{
		
		Formula f = formula.clone();
		
		HashMap<String, XESDataType> xdtMap = XESUtil.getAttributeNamesTypesMap(xtrace);
		f.assignQueryXESDataType(xdtMap);
				
		return f;
	}
	
	/**
	 * Assign the corresponding XES Data Type to each AttributeAccessor 
	 * based on the mapping between attribute names and their types
	 * 
	 * @param formula
	 * @param xdtmap
	 * @throws Exception
	 */
	public static Formula assignXESDataType(Formula formula, HashMap<String, XESDataType> xdtMap) throws Exception{
		
		Formula f = formula.clone();
		f.assignQueryXESDataType(xdtMap);
		
		return f;
	}

	public static Formula eliminateQuantifier(Formula formula, int domainSize){
		
		Formula f = formula.clone();
		return f.eliminateQuantifier(domainSize);
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
	public static Formula evaluateSpecialIndex(Formula formula, int current, int last){

		Formula f = formula.clone();
		f.evaluateSpecialIndex(current, last);
		return f;
	}

	public static Formula evaluateQuery(Formula formula, XTrace xtrace) throws Exception{

		Formula f = formula.clone();
		f.evaluateQuery(xtrace);
		return f;
	}

	/**
	 * Evaluate the truth value of the formula w.r.t. the given trace
	 * 
	 * NOTE: the input formula for this method is assumed to be a formula that is obtained after 
	 * the application of the method 'assignXESDataType'. I.e., we assume that the data type of
	 * each attribute in the queries (attribute accessor) has been clarified.
	 * 
	 * @return true - if the given xtrace satisfy the given formula (false - otherwise)
	 * @throws Exception - in case the evaluation is (currently) not possible
	 */
	public static boolean evaluateFormula(Formula formula, XTrace xtrace, int current) throws Exception{

		/*
		 * Note: we need to clone the formula first because during the evaluation we might need to change the formula. 
		 * For example when we do the quantifier elimination.
		 */
		
		Formula f0 = formula.clone();
				
		int last = xtrace.size();
		Formula f1 = FormulaUtil.eliminateQuantifier(f0, xtrace.size());
//		System.out.println("After Quantifier Elimination: \n"+f1.prettyFormat(""));
		Formula f2 = FormulaUtil.evaluateSpecialIndex(f1, current, last);
//		System.out.println("After Evaluating the Special Index: \n"+f2.prettyFormat(""));
		Formula f3 = FormulaUtil.evaluateQuery(f2, xtrace);
//		System.out.println("After Evaluating the Queries: \n"+f3.prettyFormat(""));
		
		return f3.evaluateGroundedFormula();
	}
}
