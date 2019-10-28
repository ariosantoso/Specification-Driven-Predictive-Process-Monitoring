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
import org.astw.util.Const.XESDataType;
import org.deckfour.xes.model.XTrace;

/**
 * @author Ario Santoso (santoso.ario@gmail.com)
 */
public abstract class FormulaQuantified implements Formula{

    private String variable;
    private Formula formula;
    

    public FormulaQuantified(String variable, Formula formula){
        this.variable = variable;
        this.formula = formula;
    }

    public Formula getComponent(){
        return this.formula;
    }

    public void setComponent(Formula formula){
        this.formula = formula;
    }
    	
	public String getQuantifiedVariable() {
		return this.variable;
	}

	public void setQuantifiedVariable(String variable) {
		this.variable = variable;
	}
	
	public abstract Formula clone();
	
	/**
	 * To assign the corresponding query type based on the given Attributes and types information
	 */
	@Override
	public void assignQueryXESDataType(HashMap<String, XESDataType> attributeNamesAndTypes) throws Exception {
		this.formula.assignQueryXESDataType(attributeNamesAndTypes);
	}
	
	@Override
	public void substituteVar(String varName, int value) {
	
		if(!varName.equals(this.variable))
			this.formula.substituteVar(varName, value);
	}

	@Override
	public void evaluateQuery(XTrace xtrace) throws Exception{
		this.getComponent().evaluateQuery(xtrace);
	}

	@Override
	public void evaluateAggregateFunction(XTrace xtrace) throws Exception{
		
		this.getComponent().evaluateAggregateFunction(xtrace);
	}

	@Override
	public void evaluateSpecialIndex(int current, int last) {

		this.getComponent().evaluateSpecialIndex(current, last);
	}
	
	@Override
	public boolean evaluateGroundedFormula() throws Exception {
		
		throw new Exception("This method is only for evaluating a grounded formula!");
	}

	@Override
	public Set<String> getVariables(){
				
		return this.getComponent().getVariables();
	}

}
