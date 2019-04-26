/*

    This class creates a txt file with pseudo-random generated
    dispersions for the Windows K-Means clustering.

 */
package DispersionGenerator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.ArrayList;

public class DispersionGenerator {

    public static void main(String[] args) throws FileNotFoundException {
        Random rand = new Random();
        ArrayList<Double> arr = new ArrayList<Double>();

        for (int i = 0; i < 300; i++) {
            arr.add(Double.NaN);
            arr.set(i, rand.nextDouble() * 1000);
            if (arr.get(i) <= 100) {
                arr.set(i, 0.0);
            }
            if (arr.get(i) < 150 && arr.get(i) > 100) {
                // decrement the counter so the index of the list will point to
                // the next empty element
                arr.remove(i);
                i--;
            }
            if (arr.get(i) > 400) {
                arr.remove(i);
                i--;
            }
        }

        String ready = "";
        for (int i = 0; i < 300; i++) {
            ready += arr.get(i);
            ready += "\n";
        }

        //System.out.println(ready);
        PrintWriter out = new PrintWriter("..\\Data_Sets\\DataSet_Artificial_Windows.txt");
        out.println(ready);
        out.close();
    }

}
