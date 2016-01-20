/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cryptoclient.gui.dialogs;

/**
 *
 * @author kothieringer
 */
public interface OptionsView {
    
    public int getEncryptionType();
    public String getEncryptionKey();
    
    public void setOptionsViewController(OptionsViewController controller);
}
