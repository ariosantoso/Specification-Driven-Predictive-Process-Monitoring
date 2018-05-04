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
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;

import org.astw.analyticrules.AnalyticRuleSpec;
import org.astw.analyticrules.RuleSpec;
import org.astw.analyticrules.TargetExpression;

import javax.swing.JPanel;

/**
 * 
 * @author Ario Santoso (santoso.ario@gmail.com)
 * @author Yasmin Khairina (yasminkhairina08@gmail.com)
 */

public class SetOfAnalyticRulePanel extends JPanel{

	private HashSet<AnalyticRuleSpec> ars;
	
	public void setAnalyticRulesSpec(ArrayList<AnalyticRuleSpec> ars){
		

			this.removeAll();
		
			ArrayList<AnalyticRuleSpecSimpleViewerPanel> cteps = new ArrayList<AnalyticRuleSpecSimpleViewerPanel>();
			
			for(AnalyticRuleSpec analRuleSpec : ars){
				
				cteps.add(new AnalyticRuleSpecSimpleViewerPanel(analRuleSpec));
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
	
	

	public void addRuleSpec(RuleSpec ruleSpec){
		
//		RuleSpecPanel arp = new RuleSpecPanel(ruleSpec);
//		this.add(new JScrollPane(arp));		
	}
	
}
