package org.astw.util;

import jep.Jep;
import jep.JepException;

public class JepFactory {

	private static Jep jep;
	
	private JepFactory(){}
	
	public static Jep getJep() throws JepException{
		
		if(JepFactory.jep == null)
			JepFactory.jep = new Jep();
		
		return JepFactory.jep;
	}
	
}
