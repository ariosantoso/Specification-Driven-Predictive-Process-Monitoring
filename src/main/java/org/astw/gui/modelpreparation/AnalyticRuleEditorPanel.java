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

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.astw.analyticrules.AnalyticRuleSpec;

/**
 * 
 * @author Ario Santoso (santoso.ario@gmail.com)
 * @author Yasmin Khairina (yasminkhairina08@gmail.com)
 */

public class AnalyticRuleEditorPanel extends JPanel{

	private AnalyticRuleSpec ars;
	private JButton addConditionalRule = new JButton("Add Conditional Rule");
	private JButton addDefaultRule = new JButton("Add Default Target");
	
	
	public AnalyticRuleEditorPanel(AnalyticRuleSpec ars){
		
		this.ars = ars;
		
		//button panel on the right
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2,1));
		buttonPanel.add(this.addConditionalRule);
		buttonPanel.add(this.addDefaultRule);
		
		//panel for viewing An Analytic Rule
		AnalyticRulePanel analRulePanel = new AnalyticRulePanel(ars);
		analRulePanel.setVisible(true);
		this.setLayout(new GridLayout(1,2));
		this.add(new JScrollPane(analRulePanel));
		this.add(buttonPanel);
	}
	
	
	
}
