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
package org.astw.analyticrules;

import java.util.HashMap;

import org.astw.foe.Formula;
import org.astw.foe.NonNumExp;
import org.astw.foe.NumExp;
import org.astw.util.FormulaUtil;
import org.astw.util.TargetExpUtil;
import org.astw.util.Const.ValueType;
import org.astw.util.Const.XESDataType;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;

/**
 * @author Ario Santoso (santoso.ario@gmail.com)
 */
public class RuleSpec {

	private ConditionExpression condition;
	private TargetExpression target;
	private ValueType targetValueType = ValueType.UNKNOWN;
	
	public RuleSpec(ConditionExpression condition, TargetExpression target){
		
		this.condition = condition;
		this.target = target;
	}

	public ConditionExpression getCondition() {
		return condition;
	}

	public TargetExpression getTarget() {
		return target;
	}
	
	public ValueType getTargetValueType() {
		return targetValueType;
	}

	public boolean satisfy(XTrace xtrace, int consideredPrefixLength){

		Formula f = (Formula) condition;
		try {
			return FormulaUtil.evaluateFormula(f, xtrace, consideredPrefixLength);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String computeTargetValue(XTrace xtrace, int consideredPrefixLength) throws Exception{
		
		return TargetExpUtil.computeTargetExpressionValue(this.target, xtrace, consideredPrefixLength);
	}

	
	public void init(HashMap<String, XESDataType> attDataType) throws Exception {
		this.condition.assignQueryXESDataType(attDataType);
		this.target.assignQueryXESDataType(attDataType);
		this.targetValueType = this.target.getValueType();
	}
	
	
}
