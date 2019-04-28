package gui;

import dsc.Dispersion;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Optimize {

    public static void optimize() throws FileNotFoundException, IOException, InterruptedException {
        // creates the toBeClustered file
        Dispersion.secondDataSet();

        // execute the python script to predict if the process should be killed
        Process python = Runtime.getRuntime().exec("python E:\\Synom\\Synom\\K_Means\\K_Means_Windows.py");
        while (python.isAlive() == true) {
            Thread.sleep(50);
        }
        // label set to finish as soon as the pids have been clustered
        if (python.isAlive() == false) {
            GraphicalUI.okLabel.setText("Finished");
        }

        // array of Strings to be killed
        String[] toBeKilled = new String[10];
        for (int i = 0; i < toBeKilled.length; i++) {
            toBeKilled[i] = "-1";
        }

        int cnt = 0;
        FileReader fr = new FileReader("E:\\Synom\\Synom\\Data_Sets\\toBeKilled.txt");
        Scanner sc = new Scanner(fr);
        // get PIDs from file
        while (sc.hasNextLine()) {
            toBeKilled[cnt] = sc.nextLine();
            cnt++;
        }

        Object[] options = {"Yes",
            "No"};

        if (toBeKilled[0].equals("-1") != true) // option pane to choose if process should be killed
        {
            for (int i = 0; i < toBeKilled.length; i++) {

                if (toBeKilled[i].equals("-1")) {
                    break;
                }

                int n = JOptionPane.showOptionDialog(GraphicalUI.okLabel,
                        "Kill PID =  " + toBeKilled[i],
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
                    String line;
                    Process kill = Runtime.getRuntime().exec("taskkill /F /PID " + toBeKilled[i]);
                    BufferedReader stdInput = new BufferedReader(new InputStreamReader(kill.getInputStream()));

                    BufferedReader stdError = new BufferedReader(new InputStreamReader(kill.getErrorStream()));
                    // s is cmd's output of the command
                    String s = null;
                    while ((s = stdInput.readLine()) != null) {
                        JOptionPane.showMessageDialog(GraphicalUI.okLabel,
                                s,
                                "Process killed",
                                JOptionPane.INFORMATION_MESSAGE);
                    }

                    while ((s = stdError.readLine()) != null) {
                        JOptionPane.showMessageDialog(GraphicalUI.okLabel,
                                s,
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    stdInput.close();
                }
            }
        }
    }
}
