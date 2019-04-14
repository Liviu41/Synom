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

    static void secondDataSet() {
        matrix = First_Pipe.firstDataSet();

        Map hashMap = new HashMap();
        List<Map> list = new ArrayList();

        String mem_usages[] = new String[5];

        /* mapare pid -> ram1 ram2 ram3
        35 - 39 sunt gresite
        */
        
        
        //for (int j = 0; matrix[0][j][0] != null; j++) {
            for (int i = 0; i < 5; ++i) {
                mem_usages[i] = matrix[0][i][1];
            }
        //}

        hashMap.put(matrix[0][0][0], mem_usages);

        for (Object name : hashMap.keySet()) {
            String key = name.toString();
            String[] value = (String[]) hashMap.get(name);
            System.out.println(key + " " + value[0] + " " + value[1] + " " + value[2] + " " + value[3] + " " + value[4]);
        }
    }

}
