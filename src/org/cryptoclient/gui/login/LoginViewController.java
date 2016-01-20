/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cryptoclient.gui.login;

/**
 *
 * @author da
 */
public interface LoginViewController {
    
    public void connectionWasRequested(LoginView view);
    public void connectionWasCancelled(LoginView view);
}
