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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;

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

public class AnalyticRuleSpecPanel extends JPanel{

	private AnalyticRuleSpec ars;
	
	public void setAnalyticRulesSpec(AnalyticRuleSpec ars){
		

			ArrayList<RuleSpecPanel> cteps = new ArrayList<RuleSpecPanel>();
			
			for(RuleSpec ruleSpec : ars.getRules()){
				
				cteps.add(new RuleSpecPanel(ruleSpec));
				cteps.get(cteps.size()-1).setBorder(javax.swing.BorderFactory.createLineBorder(Color.LIGHT_GRAY));
			}
			
			TargetExpression defaultTarget = ars.getOtherwiseTarget();
			
			if(defaultTarget != null){
				
				cteps.add(new RuleSpecPanel(defaultTarget));
				cteps.get(cteps.size()-1).setBorder(javax.swing.BorderFactory.createLineBorder(Color.LIGHT_GRAY));
			}
			
			javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
			
			ParallelGroup pg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
			for(int ii = 0; ii < cteps.size(); ii++)
				pg.addComponent(cteps.get(ii), 
								javax.swing.GroupLayout.PREFERRED_SIZE, 
								javax.swing.GroupLayout.DEFAULT_SIZE, 
								javax.swing.GroupLayout.PREFERRED_SIZE);

			
	        this.setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGap(18, 18, 18)
	                .addGroup(pg)
	                .addContainerGap(213, Short.MAX_VALUE))
	        );
	        
	        SequentialGroup sg = layout.createSequentialGroup();
			sg.addGap(14, 14, 14);

			for(int ii = 0; ii < cteps.size(); ii++){

                sg.addComponent(cteps.get(ii), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                sg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);

			}
			
			sg.addContainerGap(83, Short.MAX_VALUE);

			layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(sg));
			
			double maxWidth = 0;
			double maxHeight = 0;
			for(int ii = 0; ii < cteps.size(); ii++){
				
				cteps.get(ii).refresh();
				double currWidth = cteps.get(ii).getPreferredSize().getWidth();
				double currHeight = cteps.get(ii).getPreferredSize().getHeight();

				if(currWidth > maxWidth)
					maxWidth = currWidth;
				
				if(currHeight > maxHeight)
					maxHeight = currHeight;
			}

			for(int ii = 0; ii < cteps.size(); ii++){
				cteps.get(ii).setPreferredSize(new Dimension((int)Math.round(maxWidth)+1, (int)Math.round(maxHeight)+1));
			}

			repaint();
	}
	
	/*
	public void setAnalyticRulesSpec(AnalyticRulesSpec ars){
		

			RuleSpec rs = ars.getRules().iterator().next();
			
			ArrayList<ConditionalTargetExpressionPanel> cteps = new ArrayList<ConditionalTargetExpressionPanel>();
			
			for(int ii = 0; ii < 7; ii++){
				
				ConditionalTargetExpressionPanel ctep1 = new ConditionalTargetExpressionPanel();
//				ctep1.setRuleSpecification(rs);
				cteps.add(ctep1);
			}
						
			for(int ii = 0; ii < cteps.size(); ii++){
				
				ConditionalTargetExpressionPanel ctep = cteps.get(ii);
				
				//ctep.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
				
//		        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(ctep);
//		        ctep.setLayout(jPanel1Layout);
//		        jPanel1Layout.setHorizontalGroup(
//		            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//		            .addGap(0, 700, Short.MAX_VALUE)
//		        );
//		        jPanel1Layout.setVerticalGroup(
//		            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//		            .addGap(0, 150, Short.MAX_VALUE)
//		        );
			}
			
			
			
			javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
			
			ParallelGroup pg = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING);
			for(int ii = 0; ii < cteps.size(); ii++)
				pg.addComponent(cteps.get(ii), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
			
	        this.setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGap(18, 18, 18)
	                .addGroup(pg
//	                		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//	                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//	                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//	                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    )
	                .addContainerGap(213, Short.MAX_VALUE))
	        );
	        
	        
	        
	        SequentialGroup sg = layout.createSequentialGroup();
			sg.addGap(14, 14, 14);

			for(int ii = 0; ii < cteps.size(); ii++){

                sg.addComponent(cteps.get(ii), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE);
                sg.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);

			}
			
			sg.addContainerGap(83, Short.MAX_VALUE);


			layout.setVerticalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(sg
//		            	layout.createSequentialGroup()
//		                .addGap(14, 14, 14)
//		                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//		                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//		                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//		                .addContainerGap(83, Short.MAX_VALUE)
		            )
		        );
	
			
			for(int ii = 0; ii < cteps.size(); ii++){
				
				cteps.get(ii).setRuleSpecification(rs);
				cteps.get(ii).setMaximumSize(new Dimension(10,10));
			}

			repaint();
	}

	
	*/
	
	/*
	public void setAnalyticRulesSpec(AnalyticRulesSpec ars){
		
		this.ars = ars;
		int numRows = this.ars.getRules().size() * 3;
		TargetExpression te = ars.getOtherwiseTarget();

		if(te != null)
			numRows++;
		
		this.setLayout(new GridLayout(numRows,1));
		
		//add all Conditional Target Expressions
		for(RuleSpec rs : ars.getRules()){

			ConditionalTargetExpressionPanel ctep = new ConditionalTargetExpressionPanel();
			ctep.setRuleSpecification(rs);
//			ctep.setMaximumSize(new Dimension(10, 10));
			ctep.setPreferredSize(new Dimension(10,10));
//			this.add(ctep);
			this.add(new JScrollPane(ctep));
		}

		//add all Conditional Target Expressions
		for(RuleSpec rs : ars.getRules()){

			ConditionalTargetExpressionPanel ctep = new ConditionalTargetExpressionPanel();
			ctep.setRuleSpecification(rs);
//			ctep.setMaximumSize(new Dimension(10, 10));
			ctep.setPreferredSize(new Dimension(10,10));
//			this.add(ctep);
			this.add(new JScrollPane(ctep));
		}

		//add all Conditional Target Expressions
		for(RuleSpec rs : ars.getRules()){

			ConditionalTargetExpressionPanel ctep = new ConditionalTargetExpressionPanel();
			ctep.setRuleSpecification(rs);
//			ctep.setMaximumSize(new Dimension(10, 10));
			ctep.setPreferredSize(new Dimension(10,10));
//			this.add(ctep);
			this.add(new JScrollPane(ctep));
		}

		//add Default/Otherwise Target
		if(te != null){
			RuleSpecPanel arp = new RuleSpecPanel(te);
			this.add(new JScrollPane(arp));
		}
		
		System.out.println("Num of Rows: "+numRows);
		System.out.println("Num of Components: "+this.getComponentCount());
		
		this.setPreferredSize(new Dimension(10,10));
	}
	
	*/

	

	public void addRuleSpec(RuleSpec ruleSpec){
		
//		RuleSpecPanel arp = new RuleSpecPanel(ruleSpec);
//		this.add(new JScrollPane(arp));		
	}
	
}
