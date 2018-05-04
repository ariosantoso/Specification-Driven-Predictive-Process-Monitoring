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
package org.astw.gui.modelpreparation;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.astw.analyticrules.AnalyticRuleSpec;
import org.astw.analyticrules.ConditionExpression;
import org.astw.analyticrules.RuleSpec;
import org.astw.analyticrules.TargetExpression;

import javax.swing.JLabel;

/**
 * 
 * @author Ario Santoso (santoso.ario@gmail.com)
 * @author Yasmin Khairina (yasminkhairina08@gmail.com)
 */

public class AnalyticRulePanel extends JPanel{

	private AnalyticRuleSpec ars;
//	private ArrayList<JLabel> labels = new ArrayList<JLabel>();

	public AnalyticRulePanel(AnalyticRuleSpec ars){
		
		this.ars = ars;
		int numRows = this.ars.getRules().size();
		TargetExpression te = ars.getOtherwiseTarget();

		if(te != null)
			numRows++;
		
		this.setLayout(new GridLayout(numRows,1));
		
		//add all Conditional Target Expressions
		for(RuleSpec rs : ars.getRules()){

			RuleSpecPanel arp = new RuleSpecPanel(rs);
			this.add(new JScrollPane(arp));
		}
		
		//add Default/Otherwise Target
		if(te != null){
			RuleSpecPanel arp = new RuleSpecPanel(te);
			this.add(new JScrollPane(arp));
		}
		
		System.out.println("Num of Rows: "+numRows);
		System.out.println("Num of Components: "+this.getComponentCount());
	}
	
	public void addRuleSpec(RuleSpec ruleSpec){
		
		RuleSpecPanel arp = new RuleSpecPanel(ruleSpec);
		this.add(new JScrollPane(arp));		
	}
	
//	public void paintComponent(Graphics g){
//		
//	}
}
