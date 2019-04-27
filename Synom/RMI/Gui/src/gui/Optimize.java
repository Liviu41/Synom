package gui;

import dsc.Dispersion;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Optimize {

    public static void optimize() throws FileNotFoundException, IOException, InterruptedException {
        Dispersion.secondDataSet();

        Process python = Runtime.getRuntime().exec("python E:\\Synom\\Synom\\K_Means\\K_Means_Windows.py");
        while (python.isAlive() == true) {
            Thread.sleep(50);
        }
        if (python.isAlive() == false) {
            GraphicalUI.okLabel.setText("Finished");
        }

        String toBeKilled = null;
        FileReader fr = new FileReader("E:\\Synom\\Synom\\Data_Sets\\toBeKilled.txt");
        Scanner sc = new Scanner(fr);
        while (sc.hasNextLine()) {
            toBeKilled = sc.nextLine();
        }

        Object[] options = {"Yes, please",
            "No, thanks"};

        int n = JOptionPane.showOptionDialog(GraphicalUI.okLabel,
                "Kill PID =  " + toBeKilled,
                "Killer",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);
    }
}
