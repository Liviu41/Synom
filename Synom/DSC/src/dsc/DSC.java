/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DSC {

    static int[][][] dataSet = new int[100][100][100];

    public static void main(String[] args) {

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        String user = "root";
        String pass = "fenderice9";

        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vault", user, pass);
            myStmt = myConn.createStatement();

            String query1 = "select processes from resources_shadowfax order by idresources desc limit 1";
            ResultSet rs = myStmt.executeQuery(query1);

            String s = null;
            while (rs.next()) {
                s = rs.getString("processes");
            }
            s = s.substring(155, s.length() - 1);
            s = s.replace(' ', '_');
            StringBuilder se = new StringBuilder(s);

            for (int k = 0; k < 4; k++) {
            for (int i = se.indexOf("_"); se.charAt(i) == '_'; i++) {
                se.deleteCharAt(i);
                }
            }

            for (int k = 0; k < 5; k++)
            for (int i = 0; i < se.length() - 1; i++) {
                if (se.charAt(i) == se.charAt(i + 1) && se.charAt(i) == '_') {
                    se.deleteCharAt(i);
                }
                }

            for (int i = 0; i < se.length() - 3; i++) {
                if (se.charAt(i) == '_' && se.charAt(i + 1) == 'K' && se.charAt(i + 2) == '\n') {
                    se.deleteCharAt(i);
                    se.deleteCharAt(i);
                }
            }

            System.out.println(se);

            int getPID;
            //getPID = s.

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (myRs != null) {
                try {
                    myRs.close();

                } catch (SQLException ex) {
                    Logger.getLogger(DSC.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (myStmt != null) {
                try {
                    myStmt.close();

                } catch (SQLException ex) {
                    Logger.getLogger(DSC.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

}
