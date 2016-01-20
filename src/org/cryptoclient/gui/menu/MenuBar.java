package org.cryptoclient.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author da
 */
public class MenuBar extends JMenuBar implements MenuView {

    private MenuViewController controller;
    
    public MenuBar(MenuViewController controller) {
        
        setMenuViewController(controller);
        
        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");
        
        JMenuItem saveLogItem = new JMenuItem("Save Log", 
                new ImageIcon(MenuBar.class.getResource("/images/save.png")));
        JMenuItem optionsItem = new JMenuItem("Options",
                new ImageIcon(MenuBar.class.getResource("/images/options.png")));
        fileMenu.add(saveLogItem);
        fileMenu.add(optionsItem);
        
        JMenuItem helpItem = new JMenuItem("Help",
                new ImageIcon(MenuBar.class.getResource("/images/help.png")));
        JMenuItem aboutItem = new JMenuItem("About",
                new ImageIcon(MenuBar.class.getResource("/images/about.png")));
        helpMenu.add(helpItem);
        helpMenu.add(aboutItem);                       
        
        saveLogItem.addActionListener((ActionEvent e) -> {
            getMenuViewController().saveLog(this);
        });
        optionsItem.addActionListener((ActionEvent e) -> {
            getMenuViewController().showOptions(this);
        });
        helpMenu.addActionListener((ActionEvent e) -> {
            getMenuViewController().showHelp(this);
        });
        aboutItem.addActionListener((ActionEvent e) -> {
            getMenuViewController().showAbout(this);
        });
        
        add(fileMenu);
        add(helpMenu);
    }

    @Override
    public void setMenuViewController(MenuViewController controller) {
        this.controller = controller;
    }

    @Override
    public MenuViewController getMenuViewController() {
        return controller;
    }
}
