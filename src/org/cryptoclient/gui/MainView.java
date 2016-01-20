/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cryptoclient.gui;

import org.cryptoclient.gui.chat.ChatView;

/**
 *
 * @author da
 */
public interface MainView {
    
    public void showChatPane();
    
    public ChatView getChatView();
    public void setMainViewController(MainViewController controller);
    public MainViewController getMainViewController();
}
