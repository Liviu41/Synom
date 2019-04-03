package rmi_clientmodule;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import monitor.Monitor;
import rmi_interfacemodule.RMI_InterfaceModule;

public class RMI_ClientModule {

    public static void main(String[] args) throws InterruptedException, UnknownHostException, IOException {
        String text = "RMI Test Message";
        RMI_InterfaceModule rmi = null;

        while (true) {
            try {
                Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
                rmi = (RMI_InterfaceModule) registry.lookup("server");
                System.out.println("Connected to Server");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (rmi != null) {
                try {
                    Monitor m = new Monitor();
                    String[] s = m.monitor();
                    String message = "Date: " + s[0] + "\n"
                            + "CPU Usage = " + s[1] + "%\n"
                            + "RAM Usage = " + s[2] + "MB\n"
                            + "Total RAM = " + s[3] + "MB\n"
                            + "----------------------------------------------\n";
                    rmi.sendMessage(s);
                    System.out.println(rmi.getMessage(text));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                System.out.println("Operation status: Passed");
            }
            Thread.sleep(1000);
        }
    }

}
