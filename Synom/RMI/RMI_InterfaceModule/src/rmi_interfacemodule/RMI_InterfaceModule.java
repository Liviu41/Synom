package rmi_interfacemodule;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMI_InterfaceModule extends Remote {

    public String getMessage(String Text) throws RemoteException;

    public void sendMessage(String[] text) throws RemoteException;
}
