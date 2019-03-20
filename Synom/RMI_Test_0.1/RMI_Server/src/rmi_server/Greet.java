package rmi_server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import rmi_interface.RMIInterface;

public class Greet extends UnicastRemoteObject implements RMIInterface {

    public Greet() throws RemoteException {

    }

    @Override
    public int greet(String s) throws RemoteException {
        //System.out.println("Hallo!");
        return 1;
    }

}
