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
package org.astw.foe.impl.eventexp;

import java.util.HashMap;
import java.util.Set;

import org.astw.foe.EventExp;
import org.astw.foe.EventExpComponent;
import org.astw.foe.Formula;
import org.astw.util.Const;
import org.astw.util.Const.ComparisonOperator;
import org.astw.util.Const.ValueType;
import org.astw.util.Const.XESDataType;
import org.deckfour.xes.model.XTrace;

/**
 * UNUSED IN VERSION 2
 * 
 * @author Ario Santoso (santoso.ario@gmail.com)
 */
@Deprecated
public class EventExpComparison implements EventExp{

	//e ::= e1 lcop e2
    
	//public enum EventExpConnective {EQUAL, NOT_EQUAL, GREATER_THAN, LESS_THAN, GREATER_THAN_OR_EQUAL, LESS_THAN_OR_EQUAL}

	private EventExp component1;
	private ComparisonOperator connective; //=, !=, >, <, >=, <=, 
	private EventExp component2;
	
    public EventExpComparison(EventExp comp1, ComparisonOperator connective, EventExp comp2){

    	this.component1 = comp1;
    	this.connective = connective;
    	this.component2 = comp2;
    }

	public EventExp getComponent1() {
		return component1;
	}
	public void setComponent1(EventExp component1) {
		this.component1 = component1;
	}

	public ComparisonOperator getConnective() {
		return connective;
	}
	public void setConnective(ComparisonOperator connective) {
		this.connective = connective;
	}

	public EventExp getComponent2() {
		return component2;
	}
	public void setComponent2(EventExp component2) {
		this.component2 = component2;
	}
	
	public String toString(){
        return this.component1 +" "+ Const.comparisonOperatorToString(this.connective) +" "+ this.component2;
	}

	@Override
	public String prettyFormat(String tab) {
        return tab+this.component1 +" "+ Const.comparisonOperatorToString(this.connective) +" "+ this.component2;
//        return 	this.component1.prettyFormat(tab+Const.SPACE) +" \n"+ 
//        		tab+Const.comparisonOperatorToString(this.connective) +" \n"+ 
//        		this.component2.prettyFormat(tab+Const.SPACE);
	}

	public EventExp clone(){
		return new EventExpComparison(this.component1.clone(), this.connective, this.component2.clone());
	}
	
	/**
	 * To assign the corresponding query type based on the given Attributes and types information
	 */
	@Override
	public void assignQueryXESDataType(HashMap<String, XESDataType> attributeNamesAndTypes)  throws Exception{
		this.component1.assignQueryXESDataType(attributeNamesAndTypes);
		this.component2.assignQueryXESDataType(attributeNamesAndTypes);
	}

	@Override
	public ValueType ensureTypeCorrectness() throws Exception {

		ValueType valTypeComp1 = this.component1.ensureTypeCorrectness();
		ValueType valTypeComp2 = this.component2.ensureTypeCorrectness();
		
		if(valTypeComp1 != valTypeComp2)
			throw new Exception("Type mismatch between "+this.component1+" and "+this.component2);
		else 
			return ValueType.NON_NUMERIC_EXP; //because it is boolean
	}

	@Override
	public boolean getValue() {
		//return (this.component1.getValue() == this.component2.getValue());

		if(this.connective == Const.ComparisonOperator.EQUAL){
			return (this.component1.getValue() == this.component2.getValue());
			
		}else if(this.connective == Const.ComparisonOperator.NOT_EQUAL){
			
			
			return (this.component1.getValue() != this.component2.getValue());		

		}else if(this.connective == Const.ComparisonOperator.GREATER_THAN){
			return false;		
		
		}else if(this.connective == Const.ComparisonOperator.GREATER_THAN_OR_EQUAL){
			return false;		
		
		}else if(this.connective == Const.ComparisonOperator.LESS_THAN){
			return false;		
		
		}else if(this.connective == Const.ComparisonOperator.LESS_THAN_OR_EQUAL){
		
			return false;		
		}
		
		return false;
	}

	@Override
	public void substituteVar(String varName, int value) {

		this.component1.substituteVar(varName, value);
		this.component2.substituteVar(varName, value);
	}

	@Override
	public Formula eliminateQuantifier(int domainSize) {

		return this;
		
//		return new EventExpComparison(	(EventExp) this.component1.eliminateQuantifier(domainSize), 
//										connective, 
//										(EventExp) this.component2.eliminateQuantifier(domainSize));
	}

	@Override
	public void evaluateQuery(XTrace xtrace)  throws Exception{
		
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
	public boolean evaluateGroundedFormula() throws Exception {

		
		return this.getValue();
	}
	
	@Override
	public Set<String> getVariables(){
		
		Set<String> comp1Vars = this.component1.getVariables();
		Set<String> comp2Vars = this.component2.getVariables();
		
		comp1Vars.addAll(comp2Vars);
		
		return comp1Vars;
	}


}
