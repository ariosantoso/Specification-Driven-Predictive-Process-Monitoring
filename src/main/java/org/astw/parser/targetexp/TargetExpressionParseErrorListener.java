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
package org.astw.parser.targetexp;

import org.antlr.v4.runtime.*;

/**
 * @author Ario Santoso (santoso.ario@gmail.com)
 */
public class TargetExpressionParseErrorListener extends BaseErrorListener {

	private boolean error = false;
	private RecognitionException recognitionException = null;
	private String message = "";
	
	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
					int line, int charPositionInLine, String msg, RecognitionException re){

		this.error = true;
		this.recognitionException = re;
		this.message = msg;
	}

	public String getMessage() {
		return message;
	}

	public boolean isError() {
		return error;
	}
	
	public RecognitionException getRecognitionException(){
		return this.recognitionException;
	}
}
