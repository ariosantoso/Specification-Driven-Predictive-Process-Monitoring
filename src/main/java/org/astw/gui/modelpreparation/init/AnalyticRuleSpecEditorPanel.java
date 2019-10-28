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

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.astw.analyticrules.AnalyticRuleSpec;
import org.astw.analyticrules.TargetExpression;
import org.astw.foe.Formula;
import org.astw.parser.foe.FOEFormulaParser;
import org.astw.parser.targetexp.TargetExpressionParser;

/**
 * 
 * @author Ario Santoso (santoso.ario@gmail.com)
 * @author Yasmin Khairina (yasminkhairina08@gmail.com)
 */

public class AnalyticRuleSpecEditorPanel extends JPanel{

	private AnalyticRuleSpec ars;
    private AnalyticRuleSpecPanel analyticRulePanel;
    private String ruleName;
    private String ruleDescription;


	public AnalyticRuleSpecEditorPanel(String ruleDescription, AnalyticRuleSpec ars){
		
		this("", ruleDescription, ars);
	}

	public AnalyticRuleSpecEditorPanel(String ruleName, String ruleDescription, AnalyticRuleSpec ars){
		
		this.ars = ars;
		this.ruleName = ruleName;
		this.ruleDescription = ruleDescription;

		analyticRulePanel = new AnalyticRuleSpecPanel();

		initComponents();

		this.analyticRulePanel.setAnalyticRulesSpec(ars);
		this.analyticRuleName.setText(this.ruleName);
		this.analyticalRuleDescription.setText(this.ruleDescription);
		
	}
	
	public void save(){
		
		this.ars.setRuleName(this.analyticRuleName.getText());
		this.ars.setPredictionTaskName(this.predictionTaskName.getText());
		this.ars.setRuleDescription(this.analyticalRuleDescription.getText());
	}
	
	
    /////////////////////////////////////////////
    //Button Actions
    /////////////////////////////////////////////    

	private void addDefaultTargetAction(ActionEvent evt){
		
		AddDefaultTargetFrame adtp = new AddDefaultTargetFrame(this);
		adtp.setVisible(true);
	}
	
    private void addConditionalRuleAction(ActionEvent evt) {                                                   

    	AddConditionalTargetExpressionFrame adtp = new AddConditionalTargetExpressionFrame(this);
		adtp.setVisible(true);
    }                                                  

    
    /////////////////////////////////////////////
    //END OF Button Actions
    /////////////////////////////////////////////    

    
    
    /////////////////////////////////////////////
    //update responses
    /////////////////////////////////////////////    
    
	public void updateDefaultTarget(String defaultTarget){
		
		TargetExpression targetExp = null;
		try {
			targetExp = TargetExpressionParser.parse(defaultTarget);
		} catch (Exception e) {
			//TODO: parsing error Check
			System.out.println("Invalid Target Expression Input");
			JOptionPane.showMessageDialog(this, "Invalid Target Expression Input!\n "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		if(targetExp != null){
			ars.setOtherwiseTarget(targetExp);
			this.analyticRulePanel.removeAll();
			this.analyticRulePanel.setAnalyticRulesSpec(ars);
			this.analyticRulePanel.repaint();
			this.validate();
			repaint();
		}
		
		
	}
	
	public void addConditionalTargetExpression(String cond, String target){
		
		TargetExpression targetExp = null;
		Formula condition = null;
		try {
			condition = FOEFormulaParser.parse(cond);
			targetExp = TargetExpressionParser.parse(target);
		} catch (Exception e) {
			//TODO: parsing error Check
			//e.printStackTrace();
			System.out.println("Invalid Input");
			JOptionPane.showMessageDialog(this, "Invalid Input!\n "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		if(targetExp != null && condition != null){
			
			ars.addRuleSpec(condition, targetExp);
			this.analyticRulePanel.removeAll();
			this.analyticRulePanel.setAnalyticRulesSpec(ars);
			this.analyticRulePanel.repaint();
			this.validate();
			repaint();
		}
	}
		
	/////////////////////////////////////////////
	//END OF update responses
	/////////////////////////////////////////////    

	
	
	//////////////////////////////////////////////////////////////////////////////
	// NetBeans Generated
	//////////////////////////////////////////////////////////////////////////////	
	
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        AddConditionalRule = new javax.swing.JButton();
        AddDefaultTarget1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        analyticalRuleDescription = new javax.swing.JTextArea();
        descriptionLabel = new javax.swing.JLabel();
        predictionTaskNameLabel = new javax.swing.JLabel();
        analyticalRuleNameLabel = new javax.swing.JLabel();
        analyticRuleName = new javax.swing.JTextField();
        predictionTaskName = new javax.swing.JTextField();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Add Analytic Rule"));

        analyticRulePanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout analyticRulePanelLayout = new javax.swing.GroupLayout(analyticRulePanel);
        analyticRulePanel.setLayout(analyticRulePanelLayout);
        analyticRulePanelLayout.setHorizontalGroup(
            analyticRulePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 684, Short.MAX_VALUE)
        );
        analyticRulePanelLayout.setVerticalGroup(
            analyticRulePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 357, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(analyticRulePanel);

        AddConditionalRule.setText("Add Conditional Target");
        AddConditionalRule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddConditionalRuleActionPerformed(evt);
            }
        });

        AddDefaultTarget1.setText("Add Default Target");
        AddDefaultTarget1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddDefaultTarget1ActionPerformed(evt);
            }
        });

        analyticalRuleDescription.setColumns(20);
        analyticalRuleDescription.setRows(5);
        jScrollPane2.setViewportView(analyticalRuleDescription);

        descriptionLabel.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        descriptionLabel.setText("Description:");

        predictionTaskNameLabel.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        predictionTaskNameLabel.setText("Prediction task name:");

        analyticalRuleNameLabel.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        analyticalRuleNameLabel.setText("Rule name:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(AddConditionalRule, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AddDefaultTarget1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(analyticalRuleNameLabel)
                        .addGap(93, 93, 93)
                        .addComponent(analyticRuleName))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(predictionTaskNameLabel)
                            .addComponent(descriptionLabel))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(predictionTaskName)))))
                .addGap(16, 16, 16))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(analyticalRuleNameLabel)
                    .addComponent(analyticRuleName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(predictionTaskNameLabel)
                    .addComponent(predictionTaskName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descriptionLabel)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(AddConditionalRule, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(AddDefaultTarget1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE))
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

    private void AddConditionalRuleActionPerformed(java.awt.event.ActionEvent evt) {                                                   

    	addConditionalRuleAction(evt);        
    }                                                  

    private void AddDefaultTarget1ActionPerformed(java.awt.event.ActionEvent evt) {                                                  

    	addDefaultTargetAction(evt);    	
    }                                                 


    // Variables declaration - do not modify                     
    private javax.swing.JButton AddConditionalRule;
    private javax.swing.JButton AddDefaultTarget1;
    private javax.swing.JTextField analyticRuleName;
    private javax.swing.JTextArea analyticalRuleDescription;
    private javax.swing.JLabel analyticalRuleNameLabel;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField predictionTaskName;
    private javax.swing.JLabel predictionTaskNameLabel;
    // End of variables declaration        
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////

	/*
	
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        AddConditionalRule = new javax.swing.JButton();
        finish = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        analyticalRuleDescription = new javax.swing.JTextArea();
        analyticalRuleNameLabel = new javax.swing.JLabel();
        descriptionLabel = new javax.swing.JLabel();
        analyticRuleName = new javax.swing.JTextField();
        predictionTaskNameLabel = new javax.swing.JLabel();
        predictionTaskName = new javax.swing.JTextField();
        AddDefaultTarget1 = new javax.swing.JButton();

        AddConditionalRule.setText("Add Conditional Target");
        AddConditionalRule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddConditionalRuleActionPerformed(evt);
            }
        });

        finish.setText("Finish");
        finish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishActionPerformed(evt);
            }
        });

        analyticRulePanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout analyticRulePanelLayout = new javax.swing.GroupLayout(analyticRulePanel);
        analyticRulePanel.setLayout(analyticRulePanelLayout);
        analyticRulePanelLayout.setHorizontalGroup(
            analyticRulePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 684, Short.MAX_VALUE)
        );
        analyticRulePanelLayout.setVerticalGroup(
            analyticRulePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 357, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(analyticRulePanel);

        analyticalRuleDescription.setEditable(false);
        analyticalRuleDescription.setColumns(20);
        analyticalRuleDescription.setRows(5);
        jScrollPane2.setViewportView(analyticalRuleDescription);

        analyticalRuleNameLabel.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        analyticalRuleNameLabel.setText("Rule name:");

        descriptionLabel.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        descriptionLabel.setText("Description:");

        predictionTaskNameLabel.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        predictionTaskNameLabel.setText("Prediction task name:");

        AddDefaultTarget1.setText("Add Default Target");
        AddDefaultTarget1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddDefaultTarget1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(predictionTaskNameLabel)
                            .addComponent(descriptionLabel)
                            .addComponent(analyticalRuleNameLabel))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(predictionTaskName, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                            .addComponent(analyticRuleName)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AddConditionalRule, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(finish, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AddDefaultTarget1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(analyticalRuleNameLabel)
                    .addComponent(analyticRuleName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(predictionTaskNameLabel)
                    .addComponent(predictionTaskName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descriptionLabel)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(AddConditionalRule)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddDefaultTarget1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                        .addComponent(finish))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>                        

    private void AddConditionalRuleActionPerformed(java.awt.event.ActionEvent evt) {                                                   

    	addConditionalRuleAction(evt);        
    }                                                  

    private void finishActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

    private void AddDefaultTarget1ActionPerformed(java.awt.event.ActionEvent evt) {                                                  

    	addDefaultTargetAction(evt);    	
    }                                                 


    // Variables declaration - do not modify                     
    private javax.swing.JButton AddConditionalRule;
    private javax.swing.JButton AddDefaultTarget1;
    private javax.swing.JTextField analyticRuleName;
    private javax.swing.JTextArea analyticalRuleDescription;
    private javax.swing.JLabel analyticalRuleNameLabel;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JButton finish;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField predictionTaskName;
    private javax.swing.JLabel predictionTaskNameLabel;
    // End of variables declaration                   

	*/
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////

	
	/*
	private void initComponents() {

        AddConditionalRule = new javax.swing.JButton();
        AddDefaultTarget = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        analyticalRuleName = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        analyticalRuleDescription = new javax.swing.JTextArea();

        AddConditionalRule.setText("Add Conditional Target");
        AddConditionalRule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddConditionalRuleActionPerformed(evt);
            }
        });

        AddDefaultTarget.setText("Add Default Target");
        AddDefaultTarget.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddDefaultTargetActionPerformed(evt);
            }
        });

        analyticRulePanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout analyticRulePanelLayout = new javax.swing.GroupLayout(analyticRulePanel);
        analyticRulePanel.setLayout(analyticRulePanelLayout);
        analyticRulePanelLayout.setHorizontalGroup(
            analyticRulePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        analyticRulePanelLayout.setVerticalGroup(
            analyticRulePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 357, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(analyticRulePanel);

        analyticalRuleName.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        analyticalRuleName.setText("Analytical Rule Name");

        analyticalRuleDescription.setEditable(false);
        analyticalRuleDescription.setColumns(20);
        analyticalRuleDescription.setRows(5);
        jScrollPane2.setViewportView(analyticalRuleDescription);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(analyticalRuleName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(AddConditionalRule, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AddDefaultTarget, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(analyticalRuleName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(AddConditionalRule, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AddDefaultTarget, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 223, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>                        

    private void AddConditionalRuleActionPerformed(java.awt.event.ActionEvent evt) {                                                   

    	addConditionalRuleAction(evt);        
    }                                                  

    private void AddDefaultTargetActionPerformed(java.awt.event.ActionEvent evt) {                                                 

    	addDefaultTargetAction(evt);
    }                                                

    private javax.swing.JButton AddConditionalRule;
    private javax.swing.JButton AddDefaultTarget;
    private javax.swing.JTextArea analyticalRuleDescription;
    private javax.swing.JLabel analyticalRuleName;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
 	
	*/
	
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    
/*
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        AddConditionalRule = new javax.swing.JButton();
        AddDefaultTarget = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();

        AddConditionalRule.setText("Add Conditional Rule");
        AddConditionalRule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddConditionalRuleActionPerformed(evt);
            }
        });

        AddDefaultTarget.setText("Add Default Target");
        AddDefaultTarget.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddDefaultTargetActionPerformed(evt);
            }
        });

        analyticRulePanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout analyticRulePanelLayout = new javax.swing.GroupLayout(analyticRulePanel);
        analyticRulePanel.setLayout(analyticRulePanelLayout);
        analyticRulePanelLayout.setHorizontalGroup(
            analyticRulePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 251, Short.MAX_VALUE)
        );
        analyticRulePanelLayout.setVerticalGroup(
            analyticRulePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 276, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(analyticRulePanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(AddConditionalRule, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AddDefaultTarget, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(AddConditionalRule)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddDefaultTarget)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>                        

    private void AddConditionalRuleActionPerformed(java.awt.event.ActionEvent evt) {                                                   

    	
    }                                                  

    private void AddDefaultTargetActionPerformed(java.awt.event.ActionEvent evt) {                                                 

    }                                                



    private javax.swing.JButton AddConditionalRule;
    private javax.swing.JButton AddDefaultTarget;
    private javax.swing.JScrollPane jScrollPane1;

	*/
}
