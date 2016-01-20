package org.cryptoclient.gui.login.credentials;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author da
 */
public class CredentialsPane extends JPanel implements CredentialsView {

    private CredentialsViewController controller;
    
    private JTextField addressField;
    private JTextField portField;
    
    public CredentialsPane(CredentialsViewController controller) {
        setCredentialsViewController(controller);
        setLayout(new GridBagLayout());
        
        addressField = new JTextField(10);
        addressField.setHorizontalAlignment(JTextField.CENTER);
        portField = new JTextField(10);
        portField.setHorizontalAlignment(JTextField.CENTER);
        
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2, 2, 2, 2);
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.EAST;
        add(new JLabel("Address:"), c);
        
        c.gridy = 1;
        add(new JLabel("Port:"), c);
        
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(addressField, c);
        
        c.gridy = 1;
        add(portField, c);
        
        DocumentListener listener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                getCredentialsViewController().credentialsDidChange(CredentialsPane.this);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                getCredentialsViewController().credentialsDidChange(CredentialsPane.this);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                getCredentialsViewController().credentialsDidChange(CredentialsPane.this);
            }
        };
        
        addressField.getDocument().addDocumentListener(listener);
        portField.getDocument().addDocumentListener(listener);
    }

    @Override
    public String getAddress() {
        return addressField.getText();
    }

    @Override
    public int getPort() {
        try {
            return Integer.parseInt(portField.getText());
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public void willConnect() {
        addressField.setEnabled(false);
        portField.setEnabled(false);
    }

    @Override
    public void connectingFailed() {
        addressField.setEnabled(true);
        portField.setEnabled(true);
    }

    @Override
    public void connectingSucceeded() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void setCredentialsViewController(CredentialsViewController controller) {
        this.controller = controller;
    }

    @Override
    public CredentialsViewController getCredentialsViewController() {
        return controller;
    }
}
