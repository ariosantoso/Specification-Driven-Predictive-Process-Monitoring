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
import org.astw.util.Const.ValueType;
import org.astw.util.Const.XESDataType;
import org.deckfour.xes.model.XTrace;

/**
 * @author Ario Santoso (santoso.ario@gmail.com)
 */
public class BracketEventExp implements EventExp{

	private EventExp component;

    public BracketEventExp(EventExp component){
        this.component = component;
    }
  
	public EventExp getComponent() {
		return component;
	}

	public void setComponent(EventExp component) {
		this.component = component;
	}

	public String toString(){
        return "( "+ getComponent() + " )";
	}

	@Override
	public String prettyFormat(String tab) {
        return 	tab+"(\n"+ 
        			getComponent().prettyFormat(tab+Const.SPACE) + "\n"+
        		tab+")";
	}
	
	public EventExp clone(){
		return new BracketEventExp(this.component.clone());
	}
	
	/**
	 * To assign the corresponding query type based on the given Attributes and types information
	 */
	@Override
	public void assignQueryXESDataType(HashMap<String, XESDataType> attributeNamesAndTypes)  throws Exception {
		this.component.assignQueryXESDataType(attributeNamesAndTypes);
	}

	@Override
	public ValueType ensureTypeCorrectness() throws Exception {
		
		return this.component.ensureTypeCorrectness();
	}

	@Override
	public boolean getValue() {
		return this.component.getValue();
	}

	@Override
	public void substituteVar(String varName, int value) {

		this.component.substituteVar(varName, value);
	}

	@Override
	public Formula eliminateQuantifier(int domainSize) {
	
		return this.component.eliminateQuantifier(domainSize);
	}

	@Override
	public void evaluateQuery(XTrace xtrace)  throws Exception{
		
		this.component.evaluateQuery(xtrace);
	}
	
	@Override
	public void evaluateAggregateFunction(XTrace xtrace) throws Exception{
		
		this.component.evaluateAggregateFunction(xtrace);
	}

	@Override
	public void evaluateSpecialIndex(int current, int last) {

		this.component.evaluateSpecialIndex(current, last);
	}

	@Override
	public boolean evaluateGroundedFormula() throws Exception {

		return this.component.evaluateGroundedFormula();
	}

	@Override
	public Set<String> getVariables(){
		
		return this.component.getVariables();
	}

}
