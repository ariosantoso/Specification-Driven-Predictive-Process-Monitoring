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

import org.astw.foe.Formula;
import org.astw.util.Const;

/**
 * @author Ario Santoso (santoso.ario@gmail.com)
 */
public class FormulaFORALL extends FormulaQuantified{

    
    public FormulaFORALL(String variable, Formula formula){
        super(variable, formula);
    }

	public String toString(){
        return
            "FORALL "+getQuantifiedVariable().toString() +"." +
                "( "+
                	getComponent().toString() +
                " )" +
                "";
    }

	@Override
	public String prettyFormat(String tab) {
        return
                tab+"FORALL "+getQuantifiedVariable().toString() +".\n" +
//                tab+"(\n"+
                getComponent().prettyFormat(tab+Const.SPACE) +"\n"+
//                tab+")"+
				"";
	}
	
	public FormulaFORALL clone(){
		return new FormulaFORALL(new String(this.getQuantifiedVariable()), this.getComponent().clone());
	}

	@Override
	public Formula eliminateQuantifier(int domainSize) {

		Formula[] newFormula = new Formula[domainSize];
		
//		System.out.println("------------------------------------------------------------------------------------------------------------");
//		System.out.println("START process: "+this);

		for(int ii = 0; ii < domainSize; ii++){
//			System.out.println("-------------------------------------------");
//			System.out.println("Iteration "+ii+" for "+this);

			// to be safe we clone the component first because each component will be substituted with different domain value
			Formula temp = this.getComponent().clone();

//			System.out.println("DEBUGA temp before substitute var: \n"+temp.prettyFormat(""));

			temp.substituteVar(this.getQuantifiedVariable(), ii+1);
			
//			System.out.println("DEBUGA temp after substitute var: \n"+temp.prettyFormat(""));
			
			newFormula[ii] = temp.eliminateQuantifier(domainSize);

//			System.out.println("DEBUGA result: \n"+newFormula[ii].prettyFormat(""));
			
		}
//		System.out.println("END OF processing: "+this);
//		System.out.println("====================================================================================");

		if(newFormula.length == 1)
			return newFormula[0];

		Formula prev = new FormulaAND(newFormula[0], newFormula[1]);
		Formula result = new FormulaAND(newFormula[0], newFormula[1]);
		
		for(int ii = 2; ii < newFormula.length; ii++){
			
			result = new FormulaAND(prev, newFormula[ii]);
			prev = result;
		}
		
		return result;
	}

}
