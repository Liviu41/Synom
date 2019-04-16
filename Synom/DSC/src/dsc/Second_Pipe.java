/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author liviu
 */
public class Second_Pipe {

    static String matrix[][][] = new String[600][10][2];
    static String s = new String();
    static double[][] mem = new double[20][20];
    static double[] pid = new double[20];
    static double dispersion = 0;

    static void secondDataSet() {
        matrix = First_Pipe.firstDataSet();

        for (int j = 0; j < 5; j++) {
            if (s.isEmpty() == true) {
                s = matrix[0][0][0] + " " + matrix[0][0][1];
                pid[0] = Double.parseDouble(matrix[0][0][0]);
                mem[0][0] = Double.parseDouble(matrix[0][0][1]);
            } else {
                s = s + matrix[0][j][0] + " " + matrix[0][j][1];
                pid[j] = Double.parseDouble(matrix[0][j][0]);
                mem[j][0] = Double.parseDouble(matrix[0][j][1]);
            }
            for (int i = 1; i < 12; i++) {
                if (matrix[i][j][0].equals(matrix[0][j][0])) {
                    s = s + " " + matrix[i][j][1];
                    mem[j][i] = Double.parseDouble(matrix[i][j][1]);
                }
            }
            s = s + "\n";
        }
        System.out.println(s);
        System.out.println("");

        double[] max = new double[100];
        double[] min = new double[100];

        for (int i = 0; i < 12; i++) {
            max[i] = 0;
        }

        for (int i = 0; i < 5; i++) {
            min[i] = mem[i][0];
        }

        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 12; i++) {
                if (min[j] >= mem[j][i]) {
                    min[j] = mem[j][i];
                }
            }
        }

        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 12; i++) {
                if (max[j] <= mem[j][i]) {
                    max[j] = mem[j][i];
                }
            }
        }

        double sum[] = new double[100];
        double mean[] = new double[100];

        //sum of all values from each PID
        for (int j = 0; j < 5; j++) {
            for (int i = 0; mem[0][i] != 0.0; i++) {
                sum[j] += mem[j][i];
            }
        }

        //calculate mean of each process
        for (int i = 0; i < 5; i++) {
            mean[i] = sum[i] / 5;
        }
        
        double disperion[] = new double[100];

    }

}
