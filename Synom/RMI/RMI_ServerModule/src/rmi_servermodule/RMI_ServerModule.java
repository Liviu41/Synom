package rmi_servermodule;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import rmi_interfacemodule.RMI_InterfaceModule;
import rmi_servermodule.RMI_Methods;

public class RMI_ServerModule extends RMI_Methods implements RMI_InterfaceModule {

    public static void main(String[] args) {
        Registry reg = null;
        try {
            reg = LocateRegistry.createRegistry(1099);
        } catch (Exception e) {
            System.out.println("ERROR: Could not create the registry.");
            e.printStackTrace();
        }
        RMI_ServerModule serverObject = new RMI_ServerModule();
        System.out.println("Server status: Active");
        try {
            reg.rebind("server", (RMI_InterfaceModule) UnicastRemoteObject.exportObject(serverObject, 0));
        } catch (Exception e) {
            System.out.println("ERROR: Failed to register the server object.");
            e.printStackTrace();
        }
    }

}
