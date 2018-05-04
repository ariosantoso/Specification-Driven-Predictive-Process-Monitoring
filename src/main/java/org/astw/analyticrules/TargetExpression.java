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

import org.astw.util.Const.ValueType;
import org.astw.util.Const.XESDataType;
import org.deckfour.xes.model.XTrace;

public interface TargetExpression {

	public TargetExpression clone();
	public void assignQueryXESDataType(HashMap<String, XESDataType> attributeNamesAndTypes) throws Exception;
	public void evaluateSpecialIndex(int current, int last);
	public void evaluateQuery(XTrace xtrace) throws Exception;
	public ValueType getValueType();

}
