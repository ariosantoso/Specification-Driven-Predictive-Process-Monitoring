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

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.astw.util.Const.PredictorModelType;
import org.astw.util.encoder.AttributeEncodingInfo;
import org.astw.util.encoder.OneHotEncodingV2Info;
import org.astw.util.encoder.Encoder.EncodingType;
import org.deckfour.xes.model.XLog;

/**
 * 
 * @author Ario Santoso (santoso.ario@gmail.com)
 * @author Yasmin Khairina (yasminkhairina08@gmail.com)
 */
@Deprecated
public class ModelConfigFrame extends JFrame {

	private ArrayList<String> attributes = new ArrayList<String>();
	private ArrayList<EncodingType> encTypes = new ArrayList<EncodingType>();
	private XLog xlog;
	private OneHotEncodingV2Info [] oneHotEncodingV2Info;
	private AttributeEncodingInfo [] attEncodingInfo;
	private PredictorConfigPanel predPanel;
	private PredictorModelType modelType; 
	
	private final String DECISION_TREE_STR = "Decision Tree";
	private final String RANDOM_FOREST_STR = "Random Forest";
	private final String LINEAR_REGRESSION_STR = "Linear Regression";
    
    public ModelConfigFrame(boolean isNumeric, ArrayList<String> attr, XLog xLog, PredictorConfigPanel predPanel) {
    	this.attributes = attr;
    	this.xlog = xLog;
        initComponents(isNumeric);
		this.predPanel = predPanel;

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents(boolean isNumeric) {
        modelPanel= new JPanel();
        modelLabel = new JLabel();
        modelComboBox = new JComboBox<>();
        encodingScrollPane = new JScrollPane();
        encodingPanel = new JPanel();
        oneHotEncCheckBox = new JCheckBox();
        oneHotPanel = new JPanel();
        oneHotAttrLabel = new JLabel();
        
        oneHotCheckBoxes = new JCheckBox[attributes.size()];
        for (int i = 0; i < attributes.size(); i++) {
        	oneHotCheckBoxes[i] = new JCheckBox();
        }

        attributeEncCheckBox = new JCheckBox();
        attrEncPanel = new JPanel();
        attrEncAttrLabel = new JLabel();
        
        attrEncCheckBoxes = new JCheckBox[attributes.size()];
        for (int i = 0; i < attributes.size(); i++) {
        	attrEncCheckBoxes[i] = new JCheckBox();
        }
        
        timeSinceMidnightCheckBox = new JCheckBox();
        timeSinceFDoWCheckBox = new JCheckBox();
        timeSincePrevEventCheckBox = new JCheckBox();
        cancelButton = new JButton();
        saveButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        // set panel for selecting model
        
        modelPanel.setBorder(BorderFactory.createTitledBorder(" Select model"));
        modelPanel.setToolTipText("");

        modelLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        modelLabel.setLabelFor(modelComboBox);
        
        if (isNumeric) {
        	modelLabel.setText("Regression model:");
        	modelComboBox.setModel(new DefaultComboBoxModel<>(new String[] { LINEAR_REGRESSION_STR, RANDOM_FOREST_STR }));
        } else {
        	modelLabel.setText("Classification model:");
        	modelComboBox.setModel(new DefaultComboBoxModel<>(new String[] { DECISION_TREE_STR, RANDOM_FOREST_STR }));
        }

        // set encoding pane

        encodingScrollPane.setBorder(BorderFactory.createTitledBorder(" Select encoding"));
        encodingScrollPane.setPreferredSize(new Dimension(532, 300));

        int panelHeight = 30 * 2 * attributes.size() + 170;
//        int panelHeight = ((int)oneHotEncCheckBox.getPreferredSize().getHeight()) * 2 * attributes.size() + 170;
        encodingPanel.setPreferredSize(new Dimension(500, panelHeight));

        // set one hot check box and attributes
        
        oneHotEncCheckBox.setText("One hot encoding");
        oneHotEncCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				checkBoxItemStateChanged(e);
			}
        });
        
        oneHotPanel.setLayout(new GridLayout(attributes.size() + 1, 0));

        oneHotAttrLabel.setHorizontalAlignment(SwingConstants.LEFT);
        oneHotAttrLabel.setText("Attributes to encode:");
        oneHotAttrLabel.setHorizontalTextPosition(SwingConstants.LEFT);
        oneHotAttrLabel.setPreferredSize(new Dimension(400, 16));
        oneHotPanel.add(oneHotAttrLabel);
        
        for (int i = 0; i < attributes.size(); i++) {
        	JCheckBox checkBox = oneHotCheckBoxes[i];
        	String attr = attributes.get(i);
        	checkBox.setText(attr);
        	checkBox.setEnabled(false);
        	checkBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					attrCheckBoxItemStateChanged(e);
				}
			});
            oneHotPanel.add(checkBox);
        }
        
        // set attribute encoding check box and attributes
        
        attributeEncCheckBox.setText("Attribute encoding");
        attributeEncCheckBox.addItemListener(new ItemListener() {
        	@Override
        	public void itemStateChanged(ItemEvent e) {
        		checkBoxItemStateChanged(e);
        		
        	}
        });

        attrEncPanel.setLayout(new GridLayout(attributes.size() + 1, 0));

        attrEncAttrLabel.setHorizontalAlignment(SwingConstants.LEFT);
        attrEncAttrLabel.setText("Attributes to encode:");
        attrEncAttrLabel.setHorizontalTextPosition(SwingConstants.LEFT);
        attrEncAttrLabel.setPreferredSize(new Dimension(400, 16));
        attrEncPanel.add(attrEncAttrLabel);
        
        for (int i = 0; i < attributes.size(); i++) {
        	JCheckBox checkBox = attrEncCheckBoxes[i];
        	String attr = attributes.get(i);
        	checkBox.setText(attr);
        	checkBox.setEnabled(false);
        	checkBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					attrCheckBoxItemStateChanged(e);
				}
			});
        	attrEncPanel.add(checkBox);
        }

        timeSinceMidnightCheckBox.setText("Time since midnight");

        timeSinceFDoWCheckBox.setText("Time since first day of the week");

        timeSincePrevEventCheckBox.setText("Time since previous event");

        encodingScrollPane.setViewportView(encodingPanel);

        cancelButton.setText("Cancel");
        cancelButton.setSize(new Dimension(100, 29));
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        saveButton.setText("Save");
        saveButton.setMaximumSize(new Dimension(100, 29));
        saveButton.setMinimumSize(new Dimension(100, 29));
        saveButton.setPreferredSize(new Dimension(100, 29));
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        GroupLayout modelPanelLayout = new GroupLayout(modelPanel);
        modelPanel.setLayout(modelPanelLayout);
        modelPanelLayout.setHorizontalGroup(
            modelPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(modelPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(modelLabel, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modelComboBox, GroupLayout.PREFERRED_SIZE, 371, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        modelPanelLayout.setVerticalGroup(
            modelPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, modelPanelLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(modelPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(modelLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(modelComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        
        GroupLayout encodingPanelLayout = new GroupLayout(encodingPanel);
        encodingPanel.setLayout(encodingPanelLayout);
        encodingPanelLayout.setHorizontalGroup(
            encodingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(encodingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(encodingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(timeSinceFDoWCheckBox)
                    .addComponent(timeSincePrevEventCheckBox)
                    .addComponent(oneHotEncCheckBox)
                    .addComponent(timeSinceMidnightCheckBox)
                    .addComponent(attributeEncCheckBox)
                    .addGroup(encodingPanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(encodingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(attrEncPanel, GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                            .addComponent(oneHotPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        encodingPanelLayout.setVerticalGroup(
            encodingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(encodingPanelLayout.createSequentialGroup()
                .addComponent(oneHotEncCheckBox)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(oneHotPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(attributeEncCheckBox)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(attrEncPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timeSinceMidnightCheckBox)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timeSinceFDoWCheckBox)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timeSincePrevEventCheckBox)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(modelPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(encodingScrollPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(modelPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(encodingScrollPane, GroupLayout.PREFERRED_SIZE, 339, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(cancelButton)
                    .addComponent(saveButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    
    ////////////////////////////////////////////////
    // the listeners 
    ////////////////////////////////////////////////    
    
    private void cancelButtonActionPerformed(ActionEvent evt) {                                             
    
    	this.setVisible(false);
    	this.dispose();
    }                                            

    /**
     * Listener for the Save Button
     * Check if the OneHot and Attribute check box is selected,
     * subsequently check if at least one attribute of each is selected.
     * Prepare the EncodingType and the attribute for OH and A. 
     * @param evt
     */
    private void saveButtonActionPerformed(ActionEvent evt) {
    	// check first if the attr enc or the one hot is active but no attribute is selected
    	boolean oneHotIsActive = false;
    	boolean attrIsActive = false;
    	
    	ArrayList<String> oneHotAttr = new ArrayList<String>();
    	ArrayList<String> attrAttr = new ArrayList<String>();
    	
    	if (oneHotEncCheckBox.isSelected()) {
    		for (JCheckBox cb : oneHotCheckBoxes) {
    			if (cb.isSelected()) {
    				oneHotAttr.add(cb.getText());
    				oneHotIsActive = true;
    			}
    		}
    		if (!oneHotIsActive) {
    			//custom title, error icon
    			JOptionPane.showMessageDialog(this,
    			    "Please select at least an attribute for the one hot encoding",
    			    "Attribute missing",
    			    JOptionPane.ERROR_MESSAGE);
    			return;
    		}
    	}
    	if (attributeEncCheckBox.isSelected()) {
    		for (JCheckBox cb : attrEncCheckBoxes) {
    			if (cb.isSelected()) {
    				attrAttr.add(cb.getText());
    				attrIsActive = true;
    			}
    		}
    		if (!attrIsActive) {
    			//custom title, error icon
    			JOptionPane.showMessageDialog(this,
    			    "Please select at least an attribute for the attribute encoding",
    			    "Attribute missing",
    			    JOptionPane.ERROR_MESSAGE);
    			return;
    		}
    	}
    	
    	if (oneHotEncCheckBox.isSelected())
    		encTypes.add(EncodingType.OneHotV2);
    	if (attributeEncCheckBox.isSelected())
    		encTypes.add(EncodingType.AttEnc);
    	if (timeSinceMidnightCheckBox.isSelected())
    		encTypes.add(EncodingType.TimeSinceMidnight);
    	if (timeSinceFDoWCheckBox.isSelected())
    		encTypes.add(EncodingType.TimeSinceFDoW);
    	if (timeSincePrevEventCheckBox.isSelected())
    		encTypes.add(EncodingType.TimeDiffBefore);
    	
    	if (oneHotIsActive) {
    		oneHotEncodingV2Info = new OneHotEncodingV2Info[oneHotAttr.size()];
    		for (int i = 0; i < oneHotAttr.size(); i++) {
    			oneHotEncodingV2Info[i] = new OneHotEncodingV2Info(oneHotAttr.get(i), xlog);
    		}
    	}
    	
    	if (attrIsActive) {
    		attEncodingInfo = new AttributeEncodingInfo[attrAttr.size()];
    		for (int i = 0; i < attrAttr.size(); i++) {
    			attEncodingInfo[i] = new AttributeEncodingInfo(attrAttr.get(i), xlog);
    		}
    	}
    	
    	for (String oh : oneHotAttr) {
    		System.out.println("OE: "+oh);
    	}
    	System.out.println("---");
    	for (String a : attrAttr) {
    		System.out.println("AE: "+a);
    	}
    	
    	EncodingType[] encType = new EncodingType[this.encTypes.size()];
    	for(int ii = 0; ii < this.encTypes.size(); ii++){
    		encType[ii] = this.encTypes.get(ii);
    	}
    	
    	String selectedModel = (String) modelComboBox.getSelectedItem();
    	if (selectedModel.equals(DECISION_TREE_STR))
    		modelType = PredictorModelType.DecisionTree;
    	else if (selectedModel.equals(RANDOM_FOREST_STR))
    		modelType = PredictorModelType.RandomForest;
    	else 
    		modelType = PredictorModelType.LinearRegression;
    	
    	this.predPanel.updateConfig(encType, oneHotEncodingV2Info, attEncodingInfo, this.modelType);
    	this.setVisible(false);
    	this.dispose();
    }                                          

    /**
     * Listener for the one hot and attribute encoding check box
     * (the main check box, not the attributes)
     * @param evt
     */
    private void checkBoxItemStateChanged(ItemEvent evt) {
    	Object source = evt.getItemSelectable();
    	int selected = evt.getStateChange();
    	if (source == oneHotEncCheckBox && selected == ItemEvent.SELECTED) {
    		for (JCheckBox cb : oneHotCheckBoxes) {
    			cb.setEnabled(true);
    		}
    	} else if (source == oneHotEncCheckBox && selected == ItemEvent.DESELECTED) {
    		for (JCheckBox cb : oneHotCheckBoxes) {
    			cb.setEnabled(false);
    		}
    	} else if (source == attributeEncCheckBox && selected == ItemEvent.SELECTED) {
    		for (JCheckBox cb : attrEncCheckBoxes) {
    			cb.setEnabled(true);
    		}
    	} else if (source == attributeEncCheckBox && selected == ItemEvent.DESELECTED) {
    		for (JCheckBox cb : attrEncCheckBoxes) {
    			cb.setEnabled(false);
    		}
    	}
    }
    
    /**
     * The listener for the attributes check boxes of
     * the one hot and attribute encoding. Currently done nothing.
     * @param evt
     */
    private void attrCheckBoxItemStateChanged(ItemEvent evt) {
//    	JCheckBox source = (JCheckBox) evt.getItemSelectable();
//    	if (source.getParent() == oneHotPanel) {
//    		boolean isActive = false;
//    		for (JCheckBox cb : oneHotCheckBoxes) {
//    			if (cb.isSelected()) {
//    				isActive = true;
//    			}
//    		}
//    		if (!isActive) {
//    			oneHotEncCheckBox.setSelected(false);
//    		}
//    	} else if (source.getParent() == attrEncPanel) {
//    		boolean isActive = false;
//    		for (JCheckBox cb : attrEncCheckBoxes) {
//    			if (cb.isSelected()) {
//    				isActive = true;
//    			}
//    		}
//    		if (!isActive) {
//    			attributeEncCheckBox.setSelected(false);
//    		}
//    	}
    	
    }
    	
    // GUI declaration - do not modify                     
    private JLabel attrEncAttrLabel;
    private JCheckBox [] attrEncCheckBoxes;
    private JPanel attrEncPanel;
    private JCheckBox attributeEncCheckBox;
    private JButton cancelButton;
    private JPanel encodingPanel;
    private JScrollPane encodingScrollPane;
    private JComboBox<String> modelComboBox;
    private JLabel modelLabel;
    private JPanel modelPanel;
    private JCheckBox [] oneHotCheckBoxes;
    private JLabel oneHotAttrLabel;
    private JCheckBox oneHotEncCheckBox;
    private JPanel oneHotPanel;
    private JButton saveButton;
    private JCheckBox timeSinceFDoWCheckBox;
    private JCheckBox timeSinceMidnightCheckBox;
    private JCheckBox timeSincePrevEventCheckBox;
    // End of variables declaration        

	public ArrayList<EncodingType> getEncTypes() {
		return encTypes;
	}

	public PredictorModelType getModelType() {
		return modelType;
	}
	
}
