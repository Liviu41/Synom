package gui;

import dsc.Dispersion;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Optimize {

    public static void optimize() throws FileNotFoundException, IOException, InterruptedException {
        // creates the toBeClustered file
        Dispersion.secondDataSet();

        // execute the python script to predict if the process should be killed
        Process python = Runtime.getRuntime().exec("python3 /home/liviu/Synom/Synom/K_Means/K_Means_Windows.py");
        while (python.isAlive() == true) {
            Thread.sleep(50);
        }
        // label set to finish as soon as the pids have been clustered
        if (python.isAlive() == false) {
            GraphicalUI.okLabel.setText("Status: Finished");
        }

        // array of Strings to be killed
        String[] toBeKilled = new String[10];
        for (int i = 0; i < toBeKilled.length; i++) {
            toBeKilled[i] = "-1";
        }

        int cnt = 0;
        // cnt2 tracks if all the to be killed processes have been
        // approved by admin and if yes kill them.
        // A single not killed process from the list would stop the
        // killing of any processes. To be implemented later.
        int cnt2 = 0;
        FileReader fr = new FileReader("/home/liviu/Synom/Synom/Data_Sets/toBeKilled.txt");
        Scanner sc = new Scanner(fr);
        // get PIDs from file
        while (sc.hasNextLine()) {
            toBeKilled[cnt] = sc.nextLine();
            cnt++;
        }
        
        StringBuilder tbk = new StringBuilder();
        
        for(int i = 0; i < cnt; i++){
            StringBuilder cop = new StringBuilder(toBeKilled[i]);
            tbk = tbk.append(cop + ",");
        }
        tbk.deleteCharAt(tbk.length()-1);
        tbk.append("?");
        
        System.out.println("AAAAAAAAA");
        System.out.println(tbk);
        System.out.println("AAAAAAAAA");

        Object[] options = {"Yes",
            "No"};
        
        if (toBeKilled[0].equals("-1") != true) // option pane to choose if process should be killed
        {
//            for (int i = 0; i < toBeKilled.length; i++) {
//
//                if (toBeKilled[i].equals("-1")) {
//                    break;
//                }
//
//                int n = JOptionPane.showOptionDialog(GraphicalUI.okLabel,
//                        "Kill PID =  " + toBeKilled[i],
//                        "Killer",
//                        JOptionPane.YES_NO_CANCEL_OPTION,
//                        JOptionPane.QUESTION_MESSAGE,
//                        null,
//                        options,
//                        options[1]);
//
//                // n is user's input
//                // 0 = true
//                // 1 = false
//                if (n == 0) {
//                    cnt2++;
//                }
           
                    
                int n = JOptionPane.showOptionDialog(GraphicalUI.okLabel,
                        "Kill PIDS: " + tbk,
                        "Killer",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[1]);

                // n is user's input
                // 0 = true
                // 1 = false
                if (n == 0) {
                    cnt2 = cnt;
                }

            if (cnt2 == cnt) {
                String ready = "true";
                // write to boolean.txt true or false
                // if it is true then the client would kill the processes
            PrintWriter out = new PrintWriter("/home/liviu/Synom/Synom/Data_Sets/boolean.txt");
            out.println(ready);
                out.close();
            }
        }
    }
}
