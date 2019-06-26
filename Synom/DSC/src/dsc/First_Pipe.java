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

    // how many entries backward in time to get
    public static int noOfEntries = 6;
    public static boolean type = true;

    public static String[][] firstDataSet() {

        /*
        dataSet =
            dimension 1 = id of process list
            dimension 2 = entry nr in processes
            dimension 3 = PID/Mem Usage
         */
        String[][][] dataSet = new String[600][15][2];
        // needed for double points bug
        String[][][] dataSet2 = new String[600][15][2];
        
        String[][] data = new String[100][100];
        String[][] relevantPID = new String[100][100];
        String[][] relevantMem = new String[100][100];
        
        int noOfRelevantProcesses = 4;

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        String user = "root";
        String pass = "fenderice9";

// Implementare Windows ------------------------------------------------------------------------------------------------------------------------    
    
        if(type == false){
        //int x = 0;
        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vault", user, pass);
            myStmt = myConn.createStatement();

            for (int x = 0; x < noOfEntries; x++) {

                // get each process list from database
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

                // replace "," with "."
                for (int k = 0; k < se.length(); k++) {
                    if (se.charAt(k) == ',') {
                        se.replace(k, k + 1, ".");
                    }
                }

                // leave only one "_" to delimit
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

                // delete the "K" at the end
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

                for (int i = 0; i < se.length(); i++) {
                    if (se.charAt(i) == '.') {
                        se.delete(i, i + 1);
                    }
                }

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

                    // solution for bug which caused last mem string
                    // in case of noOfProc <5 to have a "_" at the end
                    if (cutted.substring(0, index2).contains("_")) {
                        dataSet[x][i][1] = cutted.substring(0, index2 - 1) + "";
                    } else {
                        dataSet[x][i][1] = cutted.substring(0, index2);
                    }
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
        }    }
        
        
        
// IMPLEMENTARE LINUX -----------------------------------------------------------------------------------------------------------------------   
        
        
        String s = null;
        
        String[][] PID = new String[100][100];
        String[][] mem = new String[100][100];
        
        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vault", user, pass);
            myStmt = myConn.createStatement();

            for (int x = 0; x < noOfEntries; x++) {

                // get each process list from database
                String query1 = "SELECT processes FROM resources_lightfax ORDER BY idresources DESC LIMIT "
                        + Integer.toString(x) + ",1;";
                ResultSet rs = myStmt.executeQuery(query1);

                while (rs.next()) {
                    s = rs.getString("processes");
                }
                
                StringBuilder initialString = new StringBuilder(s);
                
                int first = initialString.indexOf("liviu") + 10;

                int firstDel = initialString.indexOf("CMD");
                initialString = initialString.delete(0, firstDel + 3);

                int newLines = 0;
                
                for(int i = 0 ; i < initialString.length();i++)
                    if(initialString.charAt(i)=='\n')
                        newLines++;
                

                int idx1, idx2, idx3, idx4, idxSpecial;
                
                for(int i = 0 ; i < newLines; i++){
                    idxSpecial = initialString.indexOf("liviu");
                    idx1 = initialString.indexOf("/", idxSpecial);
                    if(idx1 != -1){
                        idx2 = initialString.indexOf("\n", idx1);
                        initialString = initialString.delete(idx1, idx2);
                    }
                    
                    idx3 = initialString.indexOf("cinnamon");
                    if(idx3 != -1){
                        idx4 = initialString.indexOf( "\n", idx3);
                        initialString = initialString.delete(idx3, idx4);
                        i++;
                    }
                    
                    idx3 = initialString.indexOf("mint");
                    if(idx3 != -1){
                        idx4 = initialString.indexOf( "\n", idx3);
                        initialString = initialString.delete(idx3, idx4);
                        i++;
                    }
                    
                    idx3 = initialString.indexOf("nemo");
                    if(idx3 != -1){
                        idx4 = initialString.indexOf( "\n", idx3);
                        initialString = initialString.delete(idx3, idx4);
                        i++;
                    }
                                       
                }
                
                for(int i = 0 ; i < initialString.length(); i++){
                    idx1 = initialString.indexOf("  ");
                    if(idx1 != -1)
                        initialString = initialString.deleteCharAt(idx1+1);
                }
                
                //System.out.println(initialString);
                
                StringBuilder str = new StringBuilder(initialString);
                
                int cnt = 0;
                
                for(int i = 0 ; i < initialString.length();i++){
                    idx1 = initialString.indexOf(" ",1);
                    idx2 = initialString.indexOf(" ", idx1 + 1);
                    PID[x][cnt] = initialString.substring(idx1 + 1, idx2);
                    cnt++;
                    idx3 = initialString.indexOf("\n",1);
                    initialString = initialString.delete(0, idx3);
                }
                
                cnt = 0;
                int idxspecial1,idxspecial2;
               // System.out.println(str);
                
                for(int i = 0 ; i < str.length();i++){
                    idxspecial1 = str.indexOf(" ",1);
                    idxspecial2 = str.indexOf(" ",idxspecial1 + 1);
                    idx1 = str.indexOf(" ",idxspecial2 + 1);
                    idx2 = str.indexOf("\n", idx1 + 1);
                    mem[x][cnt]= str.substring(idx1 + 1, idx2);
                    cnt++;
                    str = str.delete(0, idx2);
                }
                
                for(int i = 0; i < noOfRelevantProcesses; i++){
                    relevantPID[x][i] = PID[x][i];
                    relevantMem[x][i] = mem[x][i];
                }
                
            }
            
            for(int i = 0 ; i < data.length; i++)
                for(int j = 0; j < data.length; j++)
                    data[i][j] = "-1";
            
            for(int i = 0 ; i < noOfRelevantProcesses; i++){
                data[i][0] = relevantPID[0][i]; 
            }
            
            int cnt = 1;
            
            for(int i = 0 ; i < noOfRelevantProcesses; i++){
                for(int j = 0 ; j < noOfEntries; j++)
                    for(int k = 0 ; k < noOfRelevantProcesses; k++){
                        if(data[i][0].equals(relevantPID[j][k])){
                            data[i][cnt] = relevantMem[j][k];
                            cnt++;
                        }
                    }
                cnt = 1;
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
        
        
        
        
        return data;
    }

}
