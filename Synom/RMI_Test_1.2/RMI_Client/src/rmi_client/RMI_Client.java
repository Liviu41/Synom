package rmi_client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi_interface.RMIInterface;
import monitor.Monitor;

public class RMI_Client {

    public static void main(String[] args) throws RemoteException, NotBoundException {
//        try {
//            Registry myReg = LocateRegistry.getRegistry("127.0.0.1", 1099);
//            RMIInterface c = (RMIInterface) myReg.lookup("greet");
//            System.out.println(c.greet("AAA"));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }

        String text = "RMI Test Message";
        RMIInterface rmi = null;

        try {
            Registry registry = LocateRegistry.getRegistry("192.168.1.167", 1099);
            rmi = (RMIInterface) registry.lookup("server");
            System.out.println("Connected to Server");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (rmi != null) {
            try {
                Monitor m = new Monitor();
                String []s = m.monitor();
                rmi.sendMessage(s[3]);
                System.out.println(rmi.getMessage(text));
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (InterruptedException ex) {
                Logger.getLogger(RMI_Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Finished");
        }
    }

}
