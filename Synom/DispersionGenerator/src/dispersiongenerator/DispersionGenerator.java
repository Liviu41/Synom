package DispersionGenerator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class DispersionGenerator {

    public static void main(String[] args) throws FileNotFoundException {
        Random rand = new Random();
        Double arr[] = new Double[2000];
        for (int i = 0; i < 500; i++) {
            arr[i] = rand.nextDouble();
            arr[i] = arr[i] * 1000;
            if (arr[i] < 50) {
                arr[i] = 0.0;
            }
            if (arr[i] < 150 && arr[i] > arr[i]) {
                arr[i] = 200.0;
            }
        }

        String ready = "";
        for (int i = 0; i < 500; i++) {
            ready += arr[i];
            ready += "\n";
        }

        //System.out.println(ready);
        PrintWriter out = new PrintWriter("..\\DataSet.txt");
        out.println(ready);
        out.close();
    }

}
