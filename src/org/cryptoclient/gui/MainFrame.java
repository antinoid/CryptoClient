package org.cryptoclient.gui;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import org.cryptoclient.gui.chat.ChatPane;
import org.cryptoclient.gui.chat.ChatViewController;
import org.cryptoclient.gui.dialogs.OptionsDialog;
import org.cryptoclient.gui.dialogs.OptionsView;
import org.cryptoclient.gui.dialogs.OptionsViewController;
import org.cryptoclient.gui.login.LoginPane;
import org.cryptoclient.gui.login.LoginView;
import org.cryptoclient.gui.login.LoginViewController;
import org.cryptoclient.gui.menu.MenuBar;
import org.cryptoclient.gui.menu.MenuView;
import org.cryptoclient.gui.menu.MenuViewController;

/**
 *
 * @author da
 */
public class MainFrame implements MainView, LoginViewController, MenuViewController, ChatViewController, OptionsViewController {

    private MainViewController controller;
    
    private JFrame frame;
    private LoginPane loginPane;
    private ChatPane chatPane;
    
    public MainFrame(MainViewController controller) {
        setMainViewController(controller);
                
        frame = new JFrame("CryptClient");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setJMenuBar(new MenuBar(this));
        
        loginPane = new LoginPane(this);
        chatPane = new ChatPane(this);
        frame.add(loginPane);
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void connectionWasRequested(LoginView view) {
        getMainViewController().connectionWasRequested(view);
    }

    @Override
    public void connectionWasCancelled(LoginView view) {
    }

    @Override
    public void saveLog(MenuView view) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void showOptions(MenuView view) {
        new OptionsDialog(this).setVisible(true);
    }

    @Override
    public void showHelp(MenuView view) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void showAbout(MenuView view) {
        JDialog dialog = new JDialog(frame, "about", true);
        JTextArea textArea = new JTextArea();
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        textArea.setEditable(false);
        textArea.append("Crypto Chat\n");
        textArea.append("by:\n");
        textArea.append("Daniel Kothieringer\n");
        textArea.append("Darian Gajgic\n");
        textArea.append("Dominik Mayer\n");
        textArea.append("Julia Zachskorn\n");
        textArea.append("Samuel Wirth\n");
        dialog.add(textArea);
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

    @Override
    public void showChatPane() {
        frame.remove(loginPane);
        frame.add(chatPane);
        frame.revalidate();
        frame.repaint();
        frame.pack();
    }

    @Override
    public ChatPane getChatView() {
        return chatPane;
    }

    @Override
    public void setMainViewController(MainViewController controller) {
        this.controller = controller;
    }

    @Override
    public MainViewController getMainViewController() {
        return controller;
    }

    @Override
    public void sendMessage(String message) {
        getMainViewController().sendMessage(message);
    }

    @Override
    public void encryptionDidChange(OptionsView view) {
        getMainViewController().encryptionDidChange(view);
    }
}
