package org.cryptoclient.gui.dialogs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author kothieringer
 */
public class OptionsDialog extends JDialog implements OptionsView, ItemListener {

    private OptionsViewController controller;

    private int type = 1;
    private String key = "";

    private JRadioButton type1Button;
    private JRadioButton type2Button;
    private JRadioButton type3Button;
    
    private JTextField keyField;
    
    public OptionsDialog(OptionsViewController controller) {
        setOptionsViewController(controller);
        setLayout(new GridBagLayout());

        type1Button = new JRadioButton("Caesar Chiffé", true);
        type1Button.setActionCommand("1");
        type2Button = new JRadioButton("One Time Pad", false);
        type2Button.setActionCommand("2");
        type3Button = new JRadioButton("No Encryptions", false);
        type3Button.setActionCommand("3");

        ButtonGroup group = new ButtonGroup();
        group.add(type1Button);
        group.add(type2Button);
        group.add(type3Button);

        type1Button.addItemListener(this);
        type2Button.addItemListener(this);
        type3Button.addItemListener(this);
        
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        
        add(type1Button, c);
        c.gridy = 1;
        add(type2Button, c);
        c.gridy = 2;
        add(type3Button, c);
        
        keyField = new JTextField(5);
        keyField.setHorizontalAlignment(JTextField.CENTER);
        c.gridx = 1;
        c.gridy = 0;
        add(new JLabel("Encryption Key:"), c);
        c.gridy = 1;
        add(keyField, c);
        
        JButton okButton = new JButton("okey");
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        add(okButton, c);
        
        okButton.addActionListener((ActionEvent e) -> {
            getOptionsViewController().encryptionDidChange(this);
            dispose();
        });
        
        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public int getEncryptionType() {
        return type;
    }

    @Override
    public String getEncryptionKey() {
        return key;
    }
    
    @Override
    public void setOptionsViewController(OptionsViewController controller) {
        this.controller = controller;
    }
    
    public OptionsViewController getOptionsViewController() {
        return controller;
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (e.getItem() == type1Button) {
                type = 1;
                keyField.setEnabled(true);
            } else if (e.getItem() == type2Button) {
                type = 2;
                keyField.setEnabled(true);
            } else if (e.getItem() == type3Button) {
                type = 3;
                keyField.setEnabled(false);
            }
        }
    }

}
