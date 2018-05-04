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
package org.astw.foe.impl.numeric.indexexp;

import java.util.HashMap;

import org.astw.foe.IndexExp;
import org.astw.util.Const;
import org.astw.util.Const.ArithmeticOperator;
import org.astw.util.Const.ValueType;
import org.astw.util.Const.XESDataType;
import org.deckfour.xes.model.XTrace;

/**
 * @author Ario Santoso (santoso.ario@gmail.com)
 */

public class IndexExpBinary implements IndexExp{

	private IndexExp component1;
	private ArithmeticOperator arithOp;
	private IndexExp component2;
	private ValueType valueType = ValueType.NUMERIC_EXP;
	
	public IndexExpBinary(IndexExp component1, ArithmeticOperator arithOp,  IndexExp component2){

		this.component1 = component1;
		this.arithOp = arithOp;
		this.component2 = component2;
	}

	public IndexExp getComponent1() {
		return component1;
	}

	public void setComponent1(IndexExp component1) {
		this.component1 = component1;
	}

	public IndexExp getComponent2() {
		return component2;
	}

	public void setComponent2(IndexExp component2) {
		this.component2 = component2;
	}

	public ArithmeticOperator getArithOp() {
		return arithOp;
	}
	
	public void setArithOp(ArithmeticOperator arithOp) {
		this.arithOp = arithOp;
	}	
	
	public int getValue(){

		if(this.arithOp == ArithmeticOperator.PLUS)
			return component1.getValue() + component2.getValue();

		if(this.arithOp == ArithmeticOperator.MINUS)
			return component1.getValue() - component2.getValue();
		
		if(this.arithOp == ArithmeticOperator.MUL)
			return component1.getValue() * component2.getValue();

		if(this.arithOp == ArithmeticOperator.DIV)
			return component1.getValue() / component2.getValue();

		return -1;
	}

	public String toString(){
        return
            "( "+
                this.component1.toString() +
                " "+ Const.arithmeticOperatorToString(this.arithOp)+" "+
                this.component2.toString() +
            " )";
    }

	@Override
	public String prettyFormat(String tab) {
        return
        		tab+"(\n"+
        			this.component1.prettyFormat(tab+Const.SPACE)+"\n"+ 
        			tab+Const.arithmeticOperatorToString(this.arithOp)+"\n"+
        			this.component2.prettyFormat(tab+Const.SPACE) +"\n"+
                tab+")";
		
	}

	public IndexExpBinary clone(){
		return new IndexExpBinary(this.component1.clone(), this.arithOp, this.component2.clone());
	}
	
	/**
	 * To assign the corresponding query type based on the given Attributes and types information
	 */
	@Override
	public void assignQueryXESDataType(HashMap<String, XESDataType> attributeNamesAndTypes)  throws Exception{

		// the implementation should be empty because an index expression can't contain any query
//		this.component1.assignQueryXESDataType(attributeNamesAndTypes);
//		this.component2.assignQueryXESDataType(attributeNamesAndTypes);
	}

	@Override
	public ValueType ensureTypeCorrectness() throws Exception {

		ValueType valTypeComp1 = this.component1.ensureTypeCorrectness();
		ValueType valTypeComp2 = this.component2.ensureTypeCorrectness();
		
		StringBuilder error = new StringBuilder("");
		int initLength = error.length();
		
		if(valTypeComp1 != ValueType.NUMERIC_EXP)
			error.append(this.component1+" is non-numeric, and hence can't be an index expression \n");

		if(valTypeComp2 != ValueType.NON_NUMERIC_EXP)
			error.append(this.component2+" is non-numeric, and hence can't be an index expression \n");

		if(valTypeComp1 != valTypeComp2)
			error.append("Type mismatch between "+this.component1+" and "+this.component2);
			
		if(error.length() > initLength)
			throw new Exception(error.toString());
		else 
			return ValueType.NUMERIC_EXP;
	}
	
	@Override
	public ValueType getValueType() {

		return this.valueType;
	}

	@Override
	public double getNumericValue() {
		return this.getValue();
	}

	@Override
	public void substituteVar(String varName, int value) {
		
		this.component1.substituteVar(varName, value);
		this.component2.substituteVar(varName, value);
	}

	@Override
	public void evaluateQuery(XTrace xtrace)  throws Exception{

		this.component1.evaluateQuery(xtrace);
		this.component2.evaluateQuery(xtrace);
	}

	@Override
	public void evaluateSpecialIndex(int current, int last) {
		
		this.component1.evaluateSpecialIndex(current, last);
		this.component2.evaluateSpecialIndex(current, last);
	}

	@Override
	public XESDataType getXESDataType() {

		return XESDataType.XES_INT;
	}
}
