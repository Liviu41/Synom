package rmi_servermodule;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi_interfacemodule.RMI_InterfaceModule;
import java.sql.*;

public class RMI_Methods implements RMI_InterfaceModule {

    public static String[] receivedMessage;

    @Override
    public String getMessage(String Text) throws RemoteException {
        return "Your message is: " + Text;
    }

    @Override
    public void sendMessage(String[] text) throws RemoteException {

        /* Local DB -------------------------------------------------------------------------------------------------------------
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
*/

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        String user = "root";
        String pass = "fenderice9";

        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vault", user, pass);
            myStmt = myConn.createStatement();

            if (text[7].equals("192.168.1.167")) {
                myStmt.executeUpdate("INSERT INTO `vault`.`resources_lightfax` "
                        + "(`time`, `os_type`, `cpu_usage`, `ram_usage`, `ram_total`, `storage_total`, `storage_free`, `ip`) "
                        + "VALUES ('" + text[0] + "', '" + text[1] + "', '" + text[2] + "', '" + text[3]
                        + "', '" + text[4] + "', '" + text[5] + "', '" + text[6] + "', '" + text[7] + "');");
            } else if (text[7].equals("127.0.1.1")){
                myStmt.executeUpdate("INSERT INTO `vault`.`resources_shadowfax` "
                        + "(`time`, `os_type`, `cpu_usage`, `ram_usage`, `ram_total`, `storage_total`, `storage_free`, `ip`) "
                        + "VALUES ('" + text[0] + "', '" + text[1] + "', '" + text[2] + "', '" + text[3]
                        + "', '" + text[4] + "', '" + text[5] + "', '" + text[6] + "', '" + text[7] + "');");
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (myRs != null) {
                try {
                    myRs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RMI_Methods.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (myStmt != null) {
                try {
                    myStmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RMI_Methods.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        receivedMessage = text;
        System.out.println(receivedMessage);
    }
}
