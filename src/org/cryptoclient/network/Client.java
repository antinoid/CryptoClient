package org.cryptoclient.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Observable;

/**
 *
 * @author da
 */
public class Client extends Observable implements Runnable {

    private String address;
    private int port;
    private boolean isConnected;
    
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public Client(String address, int port) {
        this.address = address;
        this.port = port;
    }
    
    public boolean isConnected() {
        return isConnected;
    }
    
    public void connect() {
        try {
            socket = new Socket(address, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            Thread listen = new Thread(this);
            listen.start();
            isConnected = true;
            send("#" + System.getProperty("user.name"));
        } catch (Exception e) {
            isConnected = false;
        }
    }
    
    public void disconnect() {
        isConnected = false;
        try {
            if (socket != null) {
                socket.close();
                in.close();
                out.close();
            }
        } catch (Exception e) {
        }
    }
    
    public void send(String message) {
        try {
            out.writeUTF(message);
        } catch (Exception e) {
        }
    }
    
    @Override
    public void run() {
        String text;
        while (isConnected) {
            try {
                if ((text = in.readUTF()) != null) {
                    setChanged();
                    notifyObservers(text);
                }
            } catch (Exception e) {
                setChanged();
                notifyObservers("connection lost");
                isConnected = false;
            }
        }
    }
    
    private void notifyView(String message) {
        setChanged();
        notifyObservers(message);
    }
}
