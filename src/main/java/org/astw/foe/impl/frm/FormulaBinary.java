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
package org.astw.foe.impl.frm;

import java.util.HashMap;
import java.util.Set;

import org.astw.foe.Formula;
import org.astw.util.Const;
import org.astw.util.Const.BinaryLogicalOperator;
import org.astw.util.Const.XESDataType;
import org.deckfour.xes.model.XTrace;

/**
 * @author Ario Santoso (santoso.ario@gmail.com)
 */
public abstract class FormulaBinary implements Formula{

    private Formula component1;
    private BinaryLogicalOperator logOp;
    private Formula component2;
    
    public FormulaBinary(Formula formula1, BinaryLogicalOperator logOp, Formula formula2){
        this.component1 = formula1;
        this.logOp = logOp;
        this.component2 = formula2;
    }
    
    public void setComponent(Formula formula1){
        this.component1 = formula1;
    }
    
    public Formula getComponent1(){
        return this.component1;
    }
    
    public void setComponent2(Formula formula2){
        this.component2 = formula2;
    }

    public Formula getComponent2(){
        return this.component2;
    }

	public BinaryLogicalOperator getLogOp() {
		return logOp;
	}

	public void setLogOp(BinaryLogicalOperator logOp) {
		this.logOp = logOp;
	}
    
	public String toString(){
        return
            "( "+
                getComponent1().toString() +
                " "+ Const.binaryLogicalOperatorToString(this.logOp)+" "+
                getComponent2().toString() +
            " )";
    }

	@Override
	public String prettyFormat(String tab) {
        return
                tab+"(\n"+
                	getComponent1().prettyFormat(tab+Const.SPACE) +"\n"+
                tab+ Const.binaryLogicalOperatorToString(this.logOp)+"\n"+
                    getComponent2().prettyFormat(tab+Const.SPACE) + "\n"+
                tab+")";
//        return
//                tab+"( \n"+
//                	getComponent().toString() +" "+
//                	Const.binaryLogicalOperatorToString(this.logOp)+" "+
//                    getComponent2().toString() + " \n"+
//                tab+" )";
	}
	
	@Override
	public void assignQueryXESDataType(HashMap<String, XESDataType> attributeNamesAndTypes)  throws Exception{
		this.component1.assignQueryXESDataType(attributeNamesAndTypes);
		this.component2.assignQueryXESDataType(attributeNamesAndTypes);
	}

	@Override
	public void substituteVar(String varName, int value) {

		this.component1.substituteVar(varName, value);
		this.component2.substituteVar(varName, value);
	}

	@Override
	public abstract Formula clone();

	@Override
	public abstract Formula eliminateQuantifier(int domainSize);

	@Override
	public void evaluateQuery(XTrace xtrace) throws Exception{
	
		this.component1.evaluateQuery(xtrace);
		this.component2.evaluateQuery(xtrace);
	}

	@Override
	public void evaluateAggregateFunction(XTrace xtrace) throws Exception{
		
		this.component1.evaluateAggregateFunction(xtrace);
		this.component2.evaluateAggregateFunction(xtrace);
	}

	@Override
	public void evaluateSpecialIndex(int current, int last) {

		this.component1.evaluateSpecialIndex(current, last);
		this.component2.evaluateSpecialIndex(current, last);
	}
	
	@Override
	public Set<String> getVariables(){
		
		Set<String> comp1Vars = this.component1.getVariables();
		Set<String> comp2Vars = this.component2.getVariables();
		
		comp1Vars.addAll(comp2Vars);
		
		return comp1Vars;
	}

}
