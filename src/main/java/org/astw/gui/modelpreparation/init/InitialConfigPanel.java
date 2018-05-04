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
package org.astw.gui.modelpreparation.init;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JPanel;

import org.astw.analyticrules.AnalyticRuleSpec;
import org.processmining.contexts.uitopia.UIPluginContext;

/**
 * 
 * @author Ario Santoso (santoso.ario@gmail.com)
 * @author Yasmin Khairina (yasminkhairina08@gmail.com)
 */

public class InitialConfigPanel extends JPanel{

	public enum InitialConfigPanelType {SET_OF_ANAL_RULE_EDITOR, ANAL_RULE_EDITOR};
	
	private ArrayList<AnalyticRuleSpec> arsSet;
	private InitialConfigPanelType initialConfigPanelType = InitialConfigPanelType.SET_OF_ANAL_RULE_EDITOR;
	
	private SetOfAnalyticRuleSpecEditorPanel setOfAnalRulesPanel;
	private AnalyticRuleSpecEditorPanel analyticRuleSpecEditorPanel;
	
	private UIPluginContext context;
	
	public InitialConfigPanel(UIPluginContext context){
		
		this.arsSet = new ArrayList<AnalyticRuleSpec>();
		this.context = context;
		this.setOfAnalRulesPanel = new SetOfAnalyticRuleSpecEditorPanel(context, arsSet, this);
		this.setLayout(new GridLayout(1,1));
		this.add(setOfAnalRulesPanel);
		this.initialConfigPanelType = InitialConfigPanelType.SET_OF_ANAL_RULE_EDITOR;
	}
	
	public void showAddAnalyticRuleSpec(){
		
		AnalyticRuleSpec ars = new AnalyticRuleSpec();
		this.arsSet.add(ars);
		this.analyticRuleSpecEditorPanel = new AnalyticRuleSpecEditorPanel("", ars);
		this.removeAll();
		this.add(this.analyticRuleSpecEditorPanel);
		this.initialConfigPanelType = InitialConfigPanelType.ANAL_RULE_EDITOR;
		this.repaint();
		this.validate();
		this.repaint();
	}

	public void showSetOfAnalyticRulesSpecEditor(){
		
		if(this.analyticRuleSpecEditorPanel != null)
			this.analyticRuleSpecEditorPanel.save();
		
//		for(AnalyticRuleSpec ars : this.arsSet){
//			System.out.println("DEBUGA: ars.getRuleName(): "+ars.getRuleName());
//		}
		
		this.removeAll();
		this.setOfAnalRulesPanel.setTheViewer(this.arsSet);
		this.add(this.setOfAnalRulesPanel);
		this.initialConfigPanelType = InitialConfigPanelType.SET_OF_ANAL_RULE_EDITOR;
		this.repaint();
		this.validate();
		this.repaint();
	}
	
	public InitialConfigPanelType getInitialConfigPanelType() {
		return initialConfigPanelType;
	}

	
	public ArrayList<AnalyticRuleSpec> getArsSet() {
		return arsSet;
	}
	
	
	
}
