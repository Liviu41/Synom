package rmi_server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMI_Server {

    public static void main(String[] args) throws RemoteException {
        try {
            Registry reg = LocateRegistry.createRegistry(1099);
            Greet s = new Greet();
            reg.rebind("greet", s);
            System.out.println("Server ready");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
