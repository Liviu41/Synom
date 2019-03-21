package rmi_servermodule;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi_interfacemodule.RMI_InterfaceModule;

public class RMI_Methods implements RMI_InterfaceModule {

    public static String receivedMessage;

    @Override
    public String getMessage(String Text) throws RemoteException {
        return "Your message is: " + Text;
    }

    @Override
    public void sendMessage(String text) throws RemoteException {
        FileWriter writer = null;
        try {
            writer = new FileWriter("C:\\Users\\Liviu\\Desktop\\Logs.txt", true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RMI_ServerModule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RMI_ServerModule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RMI_ServerModule.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            writer.write(text);
        } catch (IOException ex) {
            Logger.getLogger(RMI_ServerModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(RMI_ServerModule.class.getName()).log(Level.SEVERE, null, ex);
        }

        receivedMessage = text;
        System.out.println(receivedMessage);
    }
}
