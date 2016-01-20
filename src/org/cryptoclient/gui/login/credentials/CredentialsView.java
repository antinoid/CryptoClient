/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cryptoclient.gui.login.credentials;

/**
 *
 * @author da
 */
public interface CredentialsView {
    
    public String getAddress();
    public int getPort();
    
    public void willConnect();
    public void connectingFailed();
    public void connectingSucceeded();
    
    public void setCredentialsViewController(CredentialsViewController controller);
    public CredentialsViewController getCredentialsViewController();
}
