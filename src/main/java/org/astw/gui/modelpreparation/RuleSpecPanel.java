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

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.astw.analyticrules.RuleSpec;
import org.astw.analyticrules.TargetExpression;

/**
 * 
 * @author Ario Santoso (santoso.ario@gmail.com)
 * @author Yasmin Khairina (yasminkhairina08@gmail.com)
 */

public class RuleSpecPanel extends JPanel{

	public enum RuleSpecPanelType {CONDITIONAL_TARGET_EXPRESSION, UNCONDITIONAL_TARGET_EXPRESSION};

	private RuleSpecPanelType ruleSpecPanelType;
	
	public RuleSpecPanel(RuleSpec ruleSpec){
	
		this.setLayout(new GridLayout(2,1));
		this.add(new JLabel("Condition: "+ruleSpec.getCondition().toString()));
		this.add(new JLabel("Target: "+ruleSpec.getTarget().toString()));
		this.ruleSpecPanelType = RuleSpecPanelType.CONDITIONAL_TARGET_EXPRESSION;
		this.setBorder(new LineBorder(Color.BLACK));
	}
	
	public RuleSpecPanel(TargetExpression targetExp){
		
		this.setLayout(new GridLayout(1,1));
		this.add(new JLabel("Default Target: "+targetExp.toString()));
		this.ruleSpecPanelType = RuleSpecPanelType.UNCONDITIONAL_TARGET_EXPRESSION;
		this.setBorder(new LineBorder(Color.BLACK));
	}
	
}
