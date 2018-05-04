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
package org.astw.foe;

import java.util.HashMap;

import org.astw.analyticrules.TargetExpression;
import org.astw.util.Const.ValueType;
import org.astw.util.Const.XESDataType;

/**
 * @author Ario Santoso (santoso.ario@gmail.com)
 */
public interface NumExp extends EventExpComponent, TargetExpression {
    
	////////////////////////////////////////////////////////////////////////////////
	// Common shared method
	////////////////////////////////////////////////////////////////////////////////
	public String toString();
	public String prettyFormat(String tab);
	public NumExp clone();
	public ValueType getValueType();
	//public void assignQueryXESDataType(HashMap<String, XESDataType> attributeNamesAndTypes)  throws Exception;
	////////////////////////////////////////////////////////////////////////////////
	// END OF Common shared method
	////////////////////////////////////////////////////////////////////////////////

	public XESDataType getXESDataType();

	public double getNumericValue();
	
	/**
	 * it returns the ValueType of the component, and 
	 * should throws an exception in case there is a type mismatch detected
	 * @return
	 * @throws Exception
	 */
	public ValueType ensureTypeCorrectness() throws Exception;
}
