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
package org.astw.gui.modelpreparation.config;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.astw.promplugin.Predictor;
import org.astw.util.Const.PredictorModelType;
import org.astw.util.Const.SpecialOutputFormat;
import org.astw.util.Const.ValueType;
import org.astw.util.encoder.AttributeEncodingInfo;
import org.astw.util.encoder.OneHotEncodingV2Info;
import org.astw.util.encoder.Encoder.EncodingType;

/**
 * 
 * @author Ario Santoso (santoso.ario@gmail.com)
 * @author Yasmin Khairina (yasminkhairina08@gmail.com)
 */

public class PredictorConfigPanel extends JPanel{

	private Predictor pred;
	private ArrayList<String> allAttributeNames;
	
	public PredictorConfigPanel(Predictor pred, ArrayList<String> allAttributeNames){
		
		this.pred = pred;
		this.allAttributeNames = allAttributeNames;
		
		initComponents();
		this.ruleName.setForeground(Color.DARK_GRAY);
		this.ruleDescription.setForeground(Color.DARK_GRAY);
	}
		
	public void refresh(){

		this.ruleName.setText(this.pred.getAnalRule().getRuleName());
		this.ruleDescription.setText(this.pred.getAnalRule().getRuleDescription());
	}

    private void configAction(ActionEvent evt) {         
    	
    	System.out.println("config: "+this.ruleName.getText());
    	
    	ValueType valType = pred.getTargetType();
    	boolean isNumeric = (valType == ValueType.NUMERIC_EXP? true : false);
    	
    	//show the config frame
    	ModelConfigFrame2 mcf = new ModelConfigFrame2(isNumeric, this.allAttributeNames, this.pred.getXlog(), this);
    	mcf.setVisible(true);
    }                        
    
    public void updateConfig(
	    	EncodingType[] encodingType, 
	    	OneHotEncodingV2Info[] oneHotEncodingV2Info, 
	    	AttributeEncodingInfo[] attEncodingInfo,
	    	PredictorModelType predModelType,
	    	SpecialOutputFormat specialOutputFormat){
    	
    	if(encodingType != null)
    		this.pred.setEncodingType(encodingType);
    	
    	if(attEncodingInfo != null)
    		this.pred.setAttEncodingInfo(attEncodingInfo);
    	
    	if(oneHotEncodingV2Info != null)
    		this.pred.setOneHotEncodingV2Info(oneHotEncodingV2Info);
    	
    	if(predModelType != null)
    		this.pred.setPredModelType(predModelType);
    	
    	if(specialOutputFormat != null)
    		this.pred.setSpecialOutputFormat(specialOutputFormat);

		System.out.println("\nupdate config for: "+this.pred.getAnalRule().getRuleName());

		if(this.pred.getEncodingType() != null)
			for(EncodingType et:this.pred.getEncodingType())
				if(et != null)
					System.out.println(et.toString());

		if(this.pred.getOneHotEncodingV2Info() != null)
			for(OneHotEncodingV2Info ohi:this.pred.getOneHotEncodingV2Info())
				if(ohi != null)
					System.out.println(ohi.toString());

		if(this.pred.getAttEncodingInfo() != null)
			for(AttributeEncodingInfo ae:this.pred.getAttEncodingInfo())
				if(ae != null)
					System.out.println(ae.toString());

		if(this.pred.getPredModelType() != null)
			System.out.println(this.pred.getPredModelType().toString());

		if(this.pred.getSpecialOutputFormat() != null)
			System.out.println(this.pred.getSpecialOutputFormat());

    }


    //old version
    public void updateConfig(
	    	EncodingType[] encodingType, 
	    	OneHotEncodingV2Info[] oneHotEncodingV2Info, 
	    	AttributeEncodingInfo[] attEncodingInfo,
	    	PredictorModelType predModelType){
    	
    	if(encodingType != null)
    		this.pred.setEncodingType(encodingType);
    	
    	if(attEncodingInfo != null)
    		this.pred.setAttEncodingInfo(attEncodingInfo);
    	
    	if(oneHotEncodingV2Info != null)
    		this.pred.setOneHotEncodingV2Info(oneHotEncodingV2Info);
    	
    	if(predModelType != null)
    		this.pred.setPredModelType(predModelType);
    	

		System.out.println("\nupdate config for: "+this.pred.getAnalRule().getRuleName());

		if(this.pred.getEncodingType() != null)
			for(EncodingType et:this.pred.getEncodingType())
				if(et != null)
					System.out.println(et.toString());

		if(this.pred.getOneHotEncodingV2Info() != null)
			for(OneHotEncodingV2Info ohi:this.pred.getOneHotEncodingV2Info())
				if(ohi != null)
					System.out.println(ohi.toString());

		if(this.pred.getAttEncodingInfo() != null)
			for(AttributeEncodingInfo ae:this.pred.getAttEncodingInfo())
				if(ae != null)
					System.out.println(ae.toString());

		if(this.pred.getPredModelType() != null)
			System.out.println(this.pred.getPredModelType().toString());


    }


	//////////////////////////////////////////////////////////////////////////////
	// NetBeans Generated
	//////////////////////////////////////////////////////////////////////////////	

    private void initComponents() {

        ruleNameLabel = new javax.swing.JLabel();
        ruleName = new javax.swing.JLabel();
        ruleDescriptionLabel = new javax.swing.JLabel();
        ruleDescription = new javax.swing.JLabel();
        config = new javax.swing.JButton();

        ruleNameLabel.setText("Rule name:");

        ruleName.setText("Rule name content");

        ruleDescriptionLabel.setText("Rule description:");

        ruleDescription.setText("Rule description content");

        config.setText("config");
        config.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(config)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ruleNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ruleName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ruleDescriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ruleDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(config, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ruleNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ruleName)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ruleDescriptionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ruleDescription)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>                        

    private void configActionPerformed(java.awt.event.ActionEvent evt) {                                       
    	configAction(evt);
    }                                      


    // Variables declaration - do not modify                     
    private javax.swing.JButton config;
    private javax.swing.JLabel ruleDescription;
    private javax.swing.JLabel ruleDescriptionLabel;
    private javax.swing.JLabel ruleName;
    private javax.swing.JLabel ruleNameLabel;
    // End of variables declaration                       
}
