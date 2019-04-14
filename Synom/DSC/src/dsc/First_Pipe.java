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

public class First_Pipe {

    public static String[][][] firstDataSet() {

        /*
        dataSet =
            dimension 1 = id of process list
            dimension 2 = entry nr in processes
            dimension 3 = PID/Mem Usage
         */
        String[][][] dataSet = new String[600][10][2];

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        String user = "root";
        String pass = "fenderice9";

        //int x = 0;
        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vault", user, pass);
            myStmt = myConn.createStatement();

            for (int x = 0; x < 553; x++) {
                String query1 = "SELECT processes FROM resources_shadowfax ORDER BY idresources DESC LIMIT "
                        + Integer.toString(x) + ",1;";
                ResultSet rs = myStmt.executeQuery(query1);

                String s = null;
                while (rs.next()) {
                    s = rs.getString("processes");
                }
                s = s.substring(155, s.length() - 1);
                s = s.replace(' ', '_');
                StringBuilder se = new StringBuilder(s);

                for(int k=0;k<se.length();k++){
                    if(se.charAt(k)==',')
                        se.replace(k, k+1, ".");
                }
                
                for (int k = 0; k < 4; k++) {
                    for (int i = se.indexOf("_"); se.charAt(i) == '_'; i++) {
                        se.deleteCharAt(i);
                    }
                }

                for (int k = 0; k < 5; k++) {
                    for (int i = 0; i < se.length() - 1; i++) {
                        if (se.charAt(i) == se.charAt(i + 1) && se.charAt(i) == '_') {
                            se.deleteCharAt(i);
                        }
                    }
                }

                for (int i = 0; i < se.length() - 3; i++) {
                    if (se.charAt(i) == '_' && se.charAt(i + 1) == 'K' && se.charAt(i + 2) == '\n') {
                        se.deleteCharAt(i);
                        se.deleteCharAt(i);
                    }
                }

                int numberOfProcesses = 0;

                for (int i = 0; i < se.length(); i++) {
                    if (se.charAt(i) == '\n') {
                        numberOfProcesses++;
                    }
                }

                int index, index2 = 0;

                StringBuilder cutted = new StringBuilder(se);

                for (int i = 0; i <= numberOfProcesses; i++) {
                    index = cutted.indexOf("_");
                    dataSet[x][i][0] = cutted.substring(index + 1, cutted.indexOf("_", index + 1));
                    for (int h = 0; h < 4; h++) {
                        cutted.delete(0, index + 1);
                        index = cutted.indexOf("_");
                    }
                    for (int p = 0; p < cutted.length(); p++) {
                        if (cutted.charAt(p) == '\n') {
                            index2 = p;
                            break;
                        }
                    }
                    dataSet[x][i][1] = cutted.substring(0, index2);
                    cutted.delete(0, index2 + 1);
                }
            }

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
        return dataSet;
    }

}
