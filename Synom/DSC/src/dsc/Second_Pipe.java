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

    static void secondDataSet() {
        matrix = First_Pipe.firstDataSet();

        for (int j = 0; j < 5; j++) {
            if (s.isEmpty() == true) {
                s = matrix[0][0][0] + " " + matrix[0][0][1];
            } else {
                s = s + matrix[0][j][0] + " " + matrix[0][j][1];
            }
            for (int i = 1; i < 12; i++) {
                if (matrix[i][j][0].equals(matrix[0][j][0])) {
                    s = s + " " + matrix[i][j][1];
                }
            }
            s = s + "\n";
        }

        System.out.println(s);
    }

}
