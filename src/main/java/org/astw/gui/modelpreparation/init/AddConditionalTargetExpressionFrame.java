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

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

/**
 * 
 * @author Ario Santoso (santoso.ario@gmail.com)
 * @author Yasmin Khairina (yasminkhairina08@gmail.com)
 */

public class AddConditionalTargetExpressionFrame extends JFrame{

	private AnalyticRuleSpecEditorPanel parentAnalyticRuleEditorPanel;
	
	public AddConditionalTargetExpressionFrame(AnalyticRuleSpecEditorPanel parentAnalyticRuleEditorPanel){
		
		this.parentAnalyticRuleEditorPanel = parentAnalyticRuleEditorPanel;
		initComponents();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}

	
    private void saveAction(ActionEvent evt) {        
    	
    	System.out.println("Save default target expression");
    	this.parentAnalyticRuleEditorPanel.addConditionalTargetExpression(
    			this.conditionExpression.getText(), this.targetExpression.getText());;
    	this.setVisible(false);
    	this.dispose();
    }                                    

	
	//////////////////////////////////////////////////////////////////////////////
	// NetBeans Generated
	//////////////////////////////////////////////////////////////////////////////	

	private void initComponents() {

	        jPanel1 = new javax.swing.JPanel();
	        conditionLabel = new javax.swing.JLabel();
	        targetExpressionLabel = new javax.swing.JLabel();
	        conditionExpression = new javax.swing.JTextField();
	        targetExpression = new javax.swing.JTextField();
	        save = new javax.swing.JButton();

	        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

	        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add Conditional Target Expression", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 15))); // NOI18N

	        conditionLabel.setText("Condition:");

	        targetExpressionLabel.setText("Target:");

	        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(conditionExpression)
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(conditionLabel)
	                            .addComponent(targetExpressionLabel))
	                        .addGap(0, 389, Short.MAX_VALUE))
	                    .addComponent(targetExpression))
	                .addContainerGap())
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(conditionLabel)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(conditionExpression, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(targetExpressionLabel)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(targetExpression, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(9, Short.MAX_VALUE))
	        );

	        save.setText("save");
	        save.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                saveActionPerformed(evt);
	            }
	        });

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                        .addGap(0, 0, Short.MAX_VALUE)
	                        .addComponent(save)))
	                .addContainerGap())
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGap(10, 10, 10)
	                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(save))
	        );

	        pack();
	    }// </editor-fold>                        

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {                                     
    	saveAction(evt);
    }                                    


    // Variables declaration - do not modify                     
    private javax.swing.JTextField conditionExpression;
    private javax.swing.JLabel conditionLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton save;
    private javax.swing.JTextField targetExpression;
    private javax.swing.JLabel targetExpressionLabel;
    // End of variables declaration  
}
