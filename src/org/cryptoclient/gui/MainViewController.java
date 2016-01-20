/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cryptoclient.gui;

import org.cryptoclient.gui.dialogs.OptionsView;
import org.cryptoclient.gui.login.LoginView;

/**
 *
 * @author da
 */
public interface MainViewController {
        
    public void connectionWasRequested(LoginView view);
    public void sendMessage(String message);
    
    public void encryptionDidChange(OptionsView view);
}
