package rmi_server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi_interface.RMIInterface;
import monitor.Monitor;

public class RMI_Server implements RMIInterface {

//        try {
//            Registry reg = LocateRegistry.createRegistry(1099);
//            Greet s = new Greet();
//            reg.rebind("greet", s);
//            System.out.println("Server ready");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
    @Override
    public void sendMessage(String s) throws RemoteException {
//        Monitor m = new Monitor();
//        String d[] = null;
//        try {
//            d = m.monitor();
//        } catch (InterruptedException ex) {
//            Logger.getLogger(RMI_Server.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println("Date = " + d[0]);
//        System.out.println("CPU Usage = " + d[1]);
//        System.out.println("RAM Usage = " + d[2]);
//        System.out.println("Total RAM = " + d[3]);
        System.out.println(s);
    }

    @Override
    public String getMessage(String text) throws RemoteException {
        return "Your message is: " + text;
    }

    public static void main(String[] args) {
        Registry reg = null;
        try {
            reg = LocateRegistry.createRegistry(1099);
        } catch (Exception e) {
            System.out.println("ERROR: Could not create the registry.");
            e.printStackTrace();
        }
        RMI_Server serverObject = new RMI_Server();
        System.out.println("Waiting...");
        try {
            reg.rebind("server", (RMIInterface) UnicastRemoteObject.exportObject(serverObject, 0));
        } catch (Exception e) {
            System.out.println("ERROR: Failed to register the server object.");
            e.printStackTrace();
        }
    }

    @Override
    public int greet(String s) throws RemoteException {
        return 0;
    }

}
