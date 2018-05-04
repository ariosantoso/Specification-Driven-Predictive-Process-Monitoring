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
package org.astw.promplugin;

/**
 * @author Ario Santoso (santoso.ario@gmail.com)
 * 
 */

public class PredictionResults {

  private String predictionName;
  private String predictionDescription;
  private String result;
  
  public PredictionResults(String predictionName, String predictionDescription, String result){
    
    this.predictionName = predictionName;
    this.predictionDescription = predictionDescription;
    this.result = result;
  }

  public String getPredictionName() {
    return predictionName;
  }

  public void setPredictionName(String predictionName) {
    this.predictionName = predictionName;
  }

  public String getPredictionDescription() {
    return predictionDescription;
  }

  public void setPredictionDescription(String predictionDescription) {
    this.predictionDescription = predictionDescription;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }
 
}