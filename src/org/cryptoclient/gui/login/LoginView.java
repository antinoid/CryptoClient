/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cryptoclient.gui.login;

import org.cryptoclient.gui.login.credentials.CredentialsView;

/**
 *
 * @author da
 */
public interface LoginView {
    
    public CredentialsView getCredentialsView();
    
    public void willConntect();
    public void connectionFailed();
    public void connectionSucceeded();
    
    public void setLoginViewController(LoginViewController controller);
    public LoginViewController getLoginViewController();
}
