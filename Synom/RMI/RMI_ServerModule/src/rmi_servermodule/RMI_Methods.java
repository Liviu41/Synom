package rmi_servermodule;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi_interfacemodule.RMI_InterfaceModule;
import java.sql.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

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

            if (text[7].equals("127.0.1.1")) {
                myStmt.executeUpdate("INSERT INTO `vault`.`resources_lightfax` "
                        + "(`time`, `os_type`, `cpu_usage`, `ram_usage`, `ram_total`, `storage_total`, `storage_free`, `ip`, `processes`) "
                        + "VALUES ('" + text[0] + "', '" + text[1] + "', '" + text[2] + "', '" + text[3]
                        + "', '" + text[4] + "', '" + text[5] + "', '" + text[6] + "', '" + text[7] + "', '" + text[8] + "');");
            } else if (text[7].equals("192.168.1.167")) {
                myStmt.executeUpdate("INSERT INTO `vault`.`resources_shadowfax` "
                        + "(`time`, `os_type`, `cpu_usage`, `ram_usage`, `ram_total`, `storage_total`, `storage_free`, `ip`, `processes`) "
                        + "VALUES ('" + text[0] + "', '" + text[1] + "', '" + text[2] + "', '" + text[3]
                        + "', '" + text[4] + "', '" + text[5] + "', '" + text[6] + "', '" + text[7] + "', '" + text[8] + "');");
            } else if (text[7].equals("1.2.3.1")) {
                myStmt.executeUpdate("INSERT INTO `vault`.`resources_darkfax` "
                        + "(`time`, `os_type`, `cpu_usage`, `ram_usage`, `ram_total`, `storage_total`, `storage_free`, `ip`, `processes`) "
                        + "VALUES ('" + text[0] + "', '" + text[1] + "', '" + text[2] + "', '" + text[3]
                        + "', '" + text[4] + "', '" + text[5] + "', '" + text[6] + "', '" + text[7] + "', '" + text[8] + "');");
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

        // originally the code from optimize, now to be run on client
        // array of Strings to be killed
        String[] toBeKilled = new String[10];
        for (int i = 0; i < toBeKilled.length; i++) {
            toBeKilled[i] = "-1";
        }

        int cnt = 0;
        FileReader fr = null;
        try {
            fr = new FileReader("/home/liviu/Synom/Synom/Data_Sets/toBeKilled.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RMI_Methods.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scanner sc = new Scanner(fr);
        // get PIDs from file
        while (sc.hasNextLine()) {
            toBeKilled[cnt] = sc.nextLine();
            cnt++;
        }

        // read the value from boolean.txt in order to determine if
        // the processes should be killed
        FileReader fr2 = null;
        String bool = null;
        try {
            fr2 = new FileReader("/home/liviu/Synom/Synom/Data_Sets/boolean.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RMI_Methods.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scanner sc2 = new Scanner(fr2);
        while (sc2.hasNextLine()) {
            bool = sc2.nextLine();
        }

        if (toBeKilled[0].equals("-1") != true)        {
            for (int i = 0; i < toBeKilled.length; i++) {

                if (toBeKilled[i].equals("-1")) {
                    break;
                }

                if (bool.equals("true")) {
                    String line;
                    Process kill = null;
                    try {
                        kill = Runtime.getRuntime().exec("kill -9 " + toBeKilled[i]);
                    } catch (IOException ex) {
                        Logger.getLogger(RMI_Methods.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

        // write boolean.txt back to false after the processes have been killed
        String ready = "false";
        PrintWriter out = null;
        try {
            out = new PrintWriter("/home/liviu/Synom/Synom/Data_Sets/boolean.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RMI_Methods.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.println(ready);
        out.close();

        receivedMessage = text;
        System.out.println(receivedMessage);
    }
}
