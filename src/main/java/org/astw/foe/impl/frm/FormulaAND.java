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
import org.astw.util.Const.BinaryLogicalOperator;

/**
 * @author Ario Santoso (santoso.ario@gmail.com)
 */
public class FormulaAND extends FormulaBinary{

    public FormulaAND(Formula formula1, Formula formula2){
        super(formula1, BinaryLogicalOperator.AND, formula2);
    }

	@Override
	public Formula clone() {

		return new FormulaAND(this.getComponent1().clone(), this.getComponent2().clone());
	}

	@Override
	public Formula eliminateQuantifier(int domainSize) {

		return new FormulaAND(	this.getComponent1().eliminateQuantifier(domainSize), 
								this.getComponent2().eliminateQuantifier(domainSize));
	}
	
	@Override
	public boolean evaluateGroundedFormula() throws Exception {
		
		return (this.getComponent1().evaluateGroundedFormula() && this.getComponent2().evaluateGroundedFormula());
	}

}
