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
package org.astw.util;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import weka.classifiers.Classifier;

/**
 * 
 * @author Ario Santoso (santoso.ario@gmail.com)
 *
 */
public class WEKAUtil {

	
	
	public static void saveClassifier(String filePath, Classifier cls) throws Exception{
		
		weka.core.SerializationHelper.write(filePath, cls);
	}

	public static Classifier readClassifier(String filePath) throws Exception{
		
		Classifier cls = (Classifier) weka.core.SerializationHelper.read(filePath);
		
		return cls;
	}
}
