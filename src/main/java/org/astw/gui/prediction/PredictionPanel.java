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
package org.astw.gui.prediction;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.astw.promplugin.PredictionResults;
import org.astw.promplugin.PredictionServices;
import org.astw.util.XESUtil;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;

/**
 * 
 * @author Ario Santoso (santoso.ario@gmail.com)
 * @author Yasmin Khairina (yasminkhairina08@gmail.com)
 */

public class PredictionPanel extends JPanel{

	private JFileChooser fc;
	private File inputTraceFile = null;
	private XTrace inputTrace = null;
	private PredictionServices predServices;
	
	private TraceViewerPanel inputTraceViewer;
	private AllResultViewer predResultPanel;
	private PredictionResults[] predResults;
	
	
	public PredictionPanel(PredictionServices predServices){
				
		this.predServices = predServices;

		fc = new JFileChooser();		
		inputTraceViewer = new TraceViewerPanel();
		predResultPanel = new AllResultViewer();
		
		initComponents();		
	}
	
    private void browseAction(ActionEvent evt) {  

		fc.setCurrentDirectory(new File(fc.getCurrentDirectory().getAbsolutePath()+"/Desktop"));
		int returnVal = fc.showOpenDialog(this);
		 
		if (returnVal == JFileChooser.APPROVE_OPTION) {
		    File file = fc.getSelectedFile();
		    String logPath = file.getAbsolutePath();
		     
		    System.out.println("Opening: " + file.getAbsolutePath());
		 
			try {
				XLog xlog = XESUtil.readXESLog(logPath);
				this.inputTrace = xlog.get(0);
			    this.inputFile.setText(file.getAbsolutePath());
			    this.inputTraceViewer.setTrace(this.inputTrace);
			
			    this.validate();
			    this.repaint();
			    
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Error in opening the input trace file", "Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
			}
		}
    }                                      

    private void predictAction(ActionEvent evt) {         
    	
    	if(this.inputTrace != null){
    		
    		this.predResults = this.predServices.predict(this.inputTrace);
    		this.predResultPanel.setPredResults(predResults);
    		this.predResultPanel.validate();
    		this.predResultPanel.repaint();
    		this.validate();
    		this.repaint();
    		this.predResultPanel.validate();
    		this.predResultPanel.repaint();
    	}
    }                                       

    public PredictionResults[] getPredResults() {
		return predResults;
	}

	
	////////////////////////////////////////////////////////
	// Netbeans generated
	////////////////////////////////////////////////////////
	

	private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        inputFile = new javax.swing.JTextField();
        browse = new javax.swing.JButton();
        predict = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Input"));

        browse.setText("Browse...");
        browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseActionPerformed(evt);
            }
        });

        predict.setText("Predict!");
        predict.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                predictActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inputFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(browse)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(predict))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browse)
                    .addComponent(predict))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane1.setDividerLocation(390);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Prediction Results"));

        javax.swing.GroupLayout predResultPanelLayout = new javax.swing.GroupLayout(predResultPanel);
        predResultPanel.setLayout(predResultPanelLayout);
        predResultPanelLayout.setHorizontalGroup(
            predResultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 512, Short.MAX_VALUE)
        );
        predResultPanelLayout.setVerticalGroup(
            predResultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(predResultPanel);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane1.setRightComponent(jPanel3);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Trace Information"));

        javax.swing.GroupLayout inputTraceViewerLayout = new javax.swing.GroupLayout(inputTraceViewer);
        inputTraceViewer.setLayout(inputTraceViewerLayout);
        inputTraceViewerLayout.setHorizontalGroup(
            inputTraceViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
        );
        inputTraceViewerLayout.setVerticalGroup(
            inputTraceViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 378, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(inputTraceViewer);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSplitPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1)
                .addContainerGap())
        );
    }// </editor-fold>                        

    private void browseActionPerformed(java.awt.event.ActionEvent evt) {                                       

    	browseAction(evt);
    }                                      

    private void predictActionPerformed(java.awt.event.ActionEvent evt) {                                        

    	predictAction(evt); 
    }                                       


    // Variables declaration - do not modify                     
    private javax.swing.JButton browse;
    private javax.swing.JTextField inputFile;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JButton predict;
    // End of variables declaration                     
}
