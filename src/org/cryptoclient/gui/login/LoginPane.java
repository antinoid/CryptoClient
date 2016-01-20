package org.cryptoclient.gui.login;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.cryptoclient.gui.login.credentials.CredentialsPane;
import org.cryptoclient.gui.login.credentials.CredentialsView;
import org.cryptoclient.gui.login.credentials.CredentialsViewController;

/**
 *
 * @author da
 */
public class LoginPane extends JPanel implements LoginView, CredentialsViewController {

    private final InetAddressValidator validator = InetAddressValidator.getInstance();
    
    private LoginViewController controller;
    private CredentialsPane credentialsView;
    
    private JButton connectButton;
    private JButton cancelButton;
    
    private boolean isConnected;
    
    public LoginPane(LoginViewController controller) {
        setLoginViewController(controller);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        
        credentialsView = new CredentialsPane(this);
        add(credentialsView, BorderLayout.CENTER);
        
        connectButton = new JButton("Connect");
        cancelButton = new JButton("Cancel");
        
        connectButton.addActionListener((ActionEvent e) -> {
            getLoginViewController().connectionWasRequested(this);
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(connectButton);
        buttonPanel.add(cancelButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
        validateCredentials();
    }
    
    public boolean isConnected() {
        return isConnected;
    }
    
    @Override
    public CredentialsView getCredentialsView() {    
        return credentialsView;
    }

    @Override
    public void willConntect() {
        getCredentialsView().willConnect();
        connectButton.setEnabled(false);
    }

    @Override
    public void connectionFailed() {
        JOptionPane.showMessageDialog(this, "Couldn't conntect to server", "Error", JOptionPane.ERROR_MESSAGE);
        getCredentialsView().connectingFailed();
        validateCredentials();
        isConnected = false;
    }

    @Override
    public void connectionSucceeded() {
        isConnected = true;
    }
    
    @Override
    public LoginViewController getLoginViewController() {
        return controller;
    }

    @Override
    public void setLoginViewController(LoginViewController controller) {
        this.controller = controller;
    }

    @Override
    public void credentialsDidChange(CredentialsView view) {
        validateCredentials();
    }
    
    private void validateCredentials() {
        
        CredentialsView view = getCredentialsView();
        String address = view.getAddress();
        int port = view.getPort();
        
        if (port != 0 && (validator.isValid(address) || "localhost".equals(address))) {
            connectButton.setEnabled(true);
        } else {
            connectButton.setEnabled(false);
        }
        
    }
}
