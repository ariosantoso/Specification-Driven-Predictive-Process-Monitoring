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

import java.awt.event.ActionEvent;
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

public class SetOfAnalyticRuleSpecEditorPanel extends JPanel{

    private SetOfAnalyticRulePanel setOfAnalyticRulePanel;
    private UIPluginContext context;
    private InitialConfigPanel initConfigPanel;
    
	public SetOfAnalyticRuleSpecEditorPanel(
			UIPluginContext context, ArrayList<AnalyticRuleSpec> arsSet, InitialConfigPanel initConfigPanel){
		
		this.context = context;
		this.setOfAnalyticRulePanel = new SetOfAnalyticRulePanel();
		this.initConfigPanel = initConfigPanel;

		initComponents();
		
		this.setOfAnalyticRulePanel.setAnalyticRulesSpec(arsSet);		
	}
	
	public void setTheViewer(ArrayList<AnalyticRuleSpec> arsSet){

		this.setOfAnalyticRulePanel.setAnalyticRulesSpec(arsSet);		
	}
	
    /////////////////////////////////////////////
    //Button Actions
    /////////////////////////////////////////////    

    private void addAnalyticRule(ActionEvent evt) {                                                   

//    	UIPluginContext analyticRuleEditorContext = context.createChildContext("");
//
////    	context.clear();
////    	context.getFutureResult(0).cancel(true);
//		AnalyticRuleSpecEditorPanel are = new AnalyticRuleSpecEditorPanel("", "", new AnalyticRulesSpec());
//		System.out.println("DEBUGA: bikin dialog - addAnalyticRule");
//
//    	context.getFutureResult(0).cancel(true);
//
//		//InteractionResult result = context.showConfiguration("Add Analytic Rule", are);
//		
//		System.out.println("DEBUGA: selesai - addAnalyticRule");
//
//		repaint();
    	
    	this.initConfigPanel.showAddAnalyticRuleSpec();
    }                                                  

    
    /////////////////////////////////////////////
    //END OF Button Actions
    /////////////////////////////////////////////    

    
    
	
	
	//////////////////////////////////////////////////////////////////////////////
	// NetBeans Generated
	//////////////////////////////////////////////////////////////////////////////	
	
    
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        addAnalyticRule = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Analytic Rules"));
        jPanel1.setOpaque(false);

        setOfAnalyticRulePanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout setOfAnalyticRulePanelLayout = new javax.swing.GroupLayout(setOfAnalyticRulePanel);
        setOfAnalyticRulePanel.setLayout(setOfAnalyticRulePanelLayout);
        setOfAnalyticRulePanelLayout.setHorizontalGroup(
            setOfAnalyticRulePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 684, Short.MAX_VALUE)
        );
        setOfAnalyticRulePanelLayout.setVerticalGroup(
            setOfAnalyticRulePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 358, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(setOfAnalyticRulePanel);

        addAnalyticRule.setText("Add Analytic Rule");
        addAnalyticRule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAnalyticRuleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addAnalyticRule, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(addAnalyticRule, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>                        

    private void addAnalyticRuleActionPerformed(java.awt.event.ActionEvent evt) {                                                
    	this.addAnalyticRule(evt);
    }                                               


    // Variables declaration - do not modify                     
    private javax.swing.JButton addAnalyticRule;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration                   
    
    
	//////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////
    
    /*
    private void initComponents() {

        addAnalyticRule = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();

        addAnalyticRule.setText("Add Analytic Rule");
        addAnalyticRule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAnalyticRuleActionPerformed(evt);
            }
        });

        setOfAnalyticRulePanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout setOfAnalyticRulePanelLayout = new javax.swing.GroupLayout(setOfAnalyticRulePanel);
        setOfAnalyticRulePanel.setLayout(setOfAnalyticRulePanelLayout);
        setOfAnalyticRulePanelLayout.setHorizontalGroup(
            setOfAnalyticRulePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 684, Short.MAX_VALUE)
        );
        setOfAnalyticRulePanelLayout.setVerticalGroup(
            setOfAnalyticRulePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 358, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(setOfAnalyticRulePanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addAnalyticRule, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addAnalyticRule, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 189, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>                        

    private void addAnalyticRuleActionPerformed(java.awt.event.ActionEvent evt) {                                                
    	this.addAnalyticRule(evt);
    }                                               


    // Variables declaration - do not modify                     
    private javax.swing.JButton addAnalyticRule;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration                   
    */
    
	
}
