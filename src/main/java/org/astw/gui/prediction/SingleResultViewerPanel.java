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

import javax.swing.JPanel;

import org.astw.promplugin.PredictionResults;

/**
 * 
 * @author Ario Santoso (santoso.ario@gmail.com)
 * @author Yasmin Khairina (yasminkhairina08@gmail.com)
 */

public class SingleResultViewerPanel extends JPanel {

	private PredictionResults pred;
	private int index;
	
	public SingleResultViewerPanel(PredictionResults pred, int index) {
		this.pred = pred;
		this.index = index;
		initComponents();
	}
	

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

    	predNameLabel = new javax.swing.JLabel();
        resultLabel = new javax.swing.JLabel();
        textAreaScrollPane = new javax.swing.JScrollPane();
        predDescTextArea = new javax.swing.JTextArea();
        descriptionLabel = new javax.swing.JLabel();
        resultText = new javax.swing.JLabel();

        predNameLabel.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        predNameLabel.setForeground(new java.awt.Color(51, 51, 51));
        predNameLabel.setText(pred.getPredictionName());

        resultLabel.setForeground(java.awt.Color.darkGray);
        resultLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        resultLabel.setText("Result:");

        textAreaScrollPane.setBorder(null);

        predDescTextArea.setEditable(false);
        predDescTextArea.setBackground(new java.awt.Color(250, 250, 250));
        predDescTextArea.setColumns(20);
        predDescTextArea.setForeground(new java.awt.Color(80, 80, 80));
        predDescTextArea.setLineWrap(true);
        predDescTextArea.setRows(4);
        predDescTextArea.setText(pred.getPredictionDescription());
        predDescTextArea.setWrapStyleWord(true);
        predDescTextArea.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray));
        textAreaScrollPane.setViewportView(predDescTextArea);

        descriptionLabel.setForeground(java.awt.Color.darkGray);
        descriptionLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        descriptionLabel.setText("Description:");

        resultText.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        resultText.setText(pred.getResult());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(resultLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resultText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(predNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(descriptionLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textAreaScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(predNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textAreaScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descriptionLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(resultLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(resultText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JTextArea predDescTextArea;
    private javax.swing.JLabel predNameLabel;
    private javax.swing.JLabel resultLabel;
    private javax.swing.JLabel resultText;
    private javax.swing.JScrollPane textAreaScrollPane;
    // End of variables declaration      
}