package rmi_interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends Remote {

    public int greet(String s) throws RemoteException;

    public String getMessage(String text) throws RemoteException;

    public void sendMessage(String text) throws RemoteException;
}
