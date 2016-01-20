/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cryptoclient.gui.chat;

import java.util.Observer;

/**
 *
 * @author da
 */
public interface ChatView {
    
    public String[] getChatLog();
    public String getInput();
    
    public void println(String text);
    
    public void setChatViewController(ChatViewController controller);
    public ChatViewController getChatViewController();
}
