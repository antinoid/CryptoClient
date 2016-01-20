package org.cryptoclient;

import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;
import org.cryptoclient.crypt.Crypt;
import org.cryptoclient.crypt.CryptModule;
import org.cryptoclient.gui.MainFrame;
import org.cryptoclient.gui.MainView;
import org.cryptoclient.gui.MainViewController;
import org.cryptoclient.gui.dialogs.OptionsView;
import org.cryptoclient.gui.dialogs.OptionsViewController;
import org.cryptoclient.gui.login.LoginView;
import org.cryptoclient.network.Client;

/**
 *
 * @author da
 */
public class MainController implements MainViewController, OptionsViewController, Observer {

    private MainView mainView;
    private Client client;
    private CryptModule cryptModule;

    private int cryptType = 1;

    public MainController() {
        mainView = new MainFrame(this);
        cryptModule = Crypt.getModule(cryptType);
    }

    @Override
    public void connectionWasRequested(LoginView view) {
        String address = view.getCredentialsView().getAddress();
        int port = view.getCredentialsView().getPort();
        client = new Client(address, port);
        client.addObserver(this);
        client.connect();

        if (!client.isConnected()) {
            view.connectionFailed();
        } else {
            mainView.showChatPane();
        }
    }

    @Override
    public void sendMessage(String message) {
        if (message.startsWith("#")) {
            client.send(message);
        } else {
            client.send(cryptModule.encrypt(message));
        }
    }

    @Override
    public void encryptionDidChange(OptionsView view) {
        int type = view.getEncryptionType();
        cryptModule = Crypt.getModule(type);
    }

    @Override
    public void update(Observable o, Object arg) {
        String msg = (String) arg;
        if (msg.startsWith("#")) {
            mainView.getChatView().println(msg.substring(1));
        } else {
            String[] msgData = msg.split(":");
            String name = msgData[0];
            StringBuilder message = new StringBuilder();
            for (int i = 1; i < msgData.length; i++) {
                message.append(msgData[i]);
            }
            mainView.getChatView().println(name + ":" + cryptModule.encrypt(message.toString()));
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new MainController();
        });
    }
}
