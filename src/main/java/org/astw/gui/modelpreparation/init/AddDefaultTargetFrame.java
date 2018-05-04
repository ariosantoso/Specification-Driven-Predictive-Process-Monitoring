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

public class AddDefaultTargetFrame extends JFrame {

	private AnalyticRuleSpecEditorPanel parentAnalyticRuleEditorPanel;
	
	public AddDefaultTargetFrame(AnalyticRuleSpecEditorPanel parentAnalyticRuleEditorPanel){
		
		this.parentAnalyticRuleEditorPanel = parentAnalyticRuleEditorPanel;
		initComponents();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	
    private void saveAction(ActionEvent evt) {        
    	
    	//System.out.println("Save default target expression");
    	this.parentAnalyticRuleEditorPanel.updateDefaultTarget(this.defaultTargetExpression.getText());
    	this.setVisible(false);
    	this.dispose();
    }                                    

	
    
	//////////////////////////////////////////////////////////////////////////////
	// NetBeans Generated
	//////////////////////////////////////////////////////////////////////////////	

	
	private void initComponents() {

	        defaultTargetLabel = new javax.swing.JLabel();
	        defaultTargetExpression = new javax.swing.JTextField();
	        save = new javax.swing.JButton();

	        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

	        defaultTargetLabel.setText("Default Target:");

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
	                    .addComponent(defaultTargetExpression)
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(defaultTargetLabel)
	                        .addGap(0, 342, Short.MAX_VALUE))
	                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                        .addGap(0, 0, Short.MAX_VALUE)
	                        .addComponent(save)))
	                .addContainerGap())
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(defaultTargetLabel)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(defaultTargetExpression, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(save)
	                .addContainerGap(20, Short.MAX_VALUE))
	        );

	        pack();
	    }// </editor-fold>                        

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {                                     

    	saveAction(evt);
    }                                    

    // Variables declaration - do not modify                     
    private javax.swing.JTextField defaultTargetExpression;
    private javax.swing.JLabel defaultTargetLabel;
    private javax.swing.JButton save;
    // End of variables declaration                

}
