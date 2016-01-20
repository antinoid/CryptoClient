/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cryptoclient.gui.menu;

/**
 *
 * @author da
 */
public interface MenuViewController {
    
    public void saveLog(MenuView view);
    public void showOptions(MenuView view);
    public void showHelp(MenuView view);
    public void showAbout(MenuView view);
}
