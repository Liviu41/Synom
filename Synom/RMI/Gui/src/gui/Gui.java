package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gui {

    public static Visual gui = new Visual();
    static String s = null;

    public static void main(String[] args) throws SQLException {

        gui.setLocation(200, 200);
        gui.setVisible(true);

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        String user = "root";
        String pass = "fenderice9";

        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vault", user, pass);
            myStmt = myConn.createStatement();

            while (true) {
// Client 1 --------------------------------------------------------------------------------------------------------------------
                String query1 = "select cpu_usage from resources_shadowfax order by idresources desc limit 1,1";
                ResultSet rs = myStmt.executeQuery(query1);

            String s2 = null;
                while (rs.next()) {
                    s = rs.getString("cpu_usage");
                    int dot = 0;
                    for (int i = 0; i < s.length(); ++i) {
                        if (Character.isDigit(s.charAt(i)) == false) {
                            dot = i;
                            break;
                        }
                    }
                    s2 = s.substring(0, dot + 3) + "%";
                }

            gui.l1.setText(s2);

                String query2 = "select ram_usage from resources_shadowfax order by idresources desc limit 1,1";
                rs = myStmt.executeQuery(query2);

                while (rs.next()) {
                    s = rs.getString("ram_usage");
                    int dot = 0;
                    for (int i = 0; i < s.length(); ++i) {
                        if (Character.isDigit(s.charAt(i)) == false) {
                            dot = i;
                            break;
                        }
                    }
                    s2 = s.substring(0, dot + 3) + "MB";
                }

            gui.l2.setText(s2);
//-----------------------------------------------------------------------------------------------------------------------------

// Client 2--------------------------------------------------------------------------------------------------------------------
            String query3 = "select cpu_usage from resources_lightfax order by idresources desc limit 1,1";
            rs = myStmt.executeQuery(query3);

            while (rs.next()) {
                s = rs.getString("cpu_usage");
                int dot = 0;
                for (int i = 0; i < s.length(); ++i) {
                    if (Character.isDigit(s.charAt(i)) == false) {
                        dot = i;
                        break;
                    }
                }
                s2 = s.substring(0, dot + 3) + "%";
            }

            gui.l3.setText(s2);

            String query4 = "select ram_usage from resources_shadowfax order by idresources desc limit 1,1";
            rs = myStmt.executeQuery(query4);

            while (rs.next()) {
                s = rs.getString("ram_usage");
                int dot = 0;
                for (int i = 0; i < s.length(); ++i) {
                    if (Character.isDigit(s.charAt(i)) == false) {
                        dot = i;
                        break;
                    }
                }
                s2 = s.substring(0, dot + 3) + "MB";
            }

            gui.l4.setText(s2);

//-----------------------------------------------------------------------------------------------------------------------------
              }
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (myRs != null) {
                try {
                    myRs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (myStmt != null) {
                try {
                    myStmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
