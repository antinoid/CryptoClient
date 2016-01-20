package org.cryptoclient.gui.chat;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author da
 */
public class ChatPane extends JPanel implements ChatView {

    private ChatViewController controller;
    
    private JTextArea chatLogArea;
    private JTextField inputField;
    
    public ChatPane(ChatViewController controller) {
        setChatViewController(controller);
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        chatLogArea = new JTextArea(10, 20);
        chatLogArea.setEditable(false);
        chatLogArea.setLineWrap(true);
        chatLogArea.setWrapStyleWord(true);
        inputField = new JTextField(15);
        
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2, 2, 2, 2);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;        
        add(new JScrollPane(chatLogArea), c);
        
        c.gridy = 1;
        c.gridwidth = 1;
        add(inputField, c);
        
        c.gridx = 1;
        JButton sendButton = new JButton("send");
        
        Action sendAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getChatViewController().sendMessage(getInput());
                inputField.setText("");
            }
        };
        
        add(sendButton, c);
        
        sendButton.addActionListener(sendAction);
        inputField.addActionListener(sendAction);
    }
    
    @Override
    public String[] getChatLog() {
        return chatLogArea.getText().split("\\n");
    }

    @Override
    public String getInput() {
        return inputField.getText();
    }

    @Override
    public void println(String text) {
        chatLogArea.append(text + "\n");
    }

    @Override
    public void setChatViewController(ChatViewController controller) {
        this.controller = controller;
    }

    @Override
    public ChatViewController getChatViewController() {
        return controller;
    }
}
