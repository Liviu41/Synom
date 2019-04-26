package dsc;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Second_Pipe {

    static int noOfTimeCaptures = 5;
    static int noOfEntries = 1;
    static String matrix[][][] = new String[600][10][2];
    static String s = new String();

    static void secondDataSet() throws FileNotFoundException {
        matrix = First_Pipe.firstDataSet();

        int noOfCrtProcs = matrix[0].length;
        double[][][] mem = new double[noOfEntries][noOfCrtProcs][noOfTimeCaptures + 1];
        int[][] pid = new int[noOfEntries][noOfCrtProcs];

        /* These lines create an array string with the following form:
        pid1 ramNow ramNow-1 ramNow-2...ramNow-noOfTimeCaptures
        pid2 ramNow ramNow-1 ramNow-2...ramNow-noOfTimeCaptures
        .
        .
        pid_noOfCrtProcs ramNow ramNow-1...ramNow-noOfTimeCaptures
         */
        for (int ct = 0; ct < noOfEntries; ct++) {
            for (int j = 0; j < noOfCrtProcs; j++) {
                if (s.isEmpty() == true) {
                    s = matrix[0][0][0] + " " + matrix[ct][0][1];
                    pid[ct][0] = Integer.parseInt(matrix[ct][0][0]);
                    mem[ct][0][0] = Double.parseDouble(matrix[ct][0][1]);
                } else {
                    s = s + matrix[0][j][0] + " " + matrix[ct][j][1];
                    pid[ct][j] = Integer.parseInt(matrix[ct][j][0]);
                    mem[ct][j][0] = Double.parseDouble(matrix[ct][j][1]);
                }
                for (int i = ct + 1; i < noOfTimeCaptures + ct; i++) {
                    if (matrix[i][j][0].equals(matrix[ct][j][0])) {
                        s = s + " " + matrix[i][j][1];
                        mem[ct][j][i - ct] = Double.parseDouble(matrix[i][j][1]);
                    }
                }
                s = s + "\n";
            }
            s = s + "\n";
        }

        // compute min and max mem of each pid
        double[][] max = new double[noOfEntries][100];
        double[][] min = new double[noOfEntries][100];

        // initialize min with first value and max with 0
        for (int ct = 0; ct < noOfEntries; ct++) {
            for (int i = 0; i < 12; i++) {
                max[ct][i] = 0;
            }
        }

        for (int ct = 0; ct < noOfEntries; ct++) {
            for (int i = 0; i < noOfCrtProcs; i++) {
                min[ct][i] = mem[ct][i][0];
            }
        }

        // compute min and max
        for (int ct = 0; ct < noOfEntries; ct++) {
            for (int j = 0; j < noOfCrtProcs; j++) {
                for (int i = 0; i < noOfTimeCaptures; i++) {
                    if (min[ct][j] >= mem[ct][j][i]) {
                        min[ct][j] = mem[ct][j][i];
                    }
                }
            }
        }

        for (int ct = 0; ct < noOfEntries; ct++) {
            for (int j = 0; j < noOfCrtProcs; j++) {
                for (int i = 0; i < noOfTimeCaptures; i++) {
                    if (max[ct][j] <= mem[ct][j][i]) {
                        max[ct][j] = mem[ct][j][i];
                    }
                }
            }
        }

        double sum[][] = new double[1000][100];
        double mean[][] = new double[1000][100];

        //sum of all values for each PID
        for (int ct = 0; ct < noOfEntries; ct++) {
            for (int j = 0; j < noOfCrtProcs; j++) {
                for (int i = 0; mem[ct][0][i] != 0.0; i++) {
                    sum[ct][j] += mem[ct][j][i];
                }
            }
        }

        //compute mean of each process
        for (int ct = 0; ct < noOfEntries; ct++) {
            for (int i = 0; i < noOfCrtProcs; i++) {
                mean[ct][i] = sum[ct][i] / noOfTimeCaptures;
            }
        }

        double variance[][] = new double[1000][100];

        for (int ct = 0; ct < noOfEntries; ct++) {
            for (int i = 0; i < noOfCrtProcs; i++) {
                variance[ct][i] = 0;
            }
        }

        // compute variance as:
        // sigma = sum((x - mean)^2)
        for (int ct = 0; ct < noOfEntries; ct++) {
            for (int i = 0; i < noOfCrtProcs; i++) {
                for (int j = 0; j < noOfTimeCaptures; j++) {
                    variance[ct][i] = variance[ct][i] + Math.pow(mem[ct][i][j] - mean[ct][i], 2);
                }
            }
        }

        double dispersion[][] = new double[1000][100];

        for (int ct = 0; ct < noOfEntries; ct++) {
            for (int i = 0; i < noOfCrtProcs; i++) {
                dispersion[ct][i] = 0;
            }
        }

        // dispersion: d = sqrt(sigma)
        for (int ct = 0; ct < noOfEntries; ct++) {
            for (int i = 0; i < noOfCrtProcs; i++) {
                dispersion[ct][i] = Math.sqrt(variance[ct][i]);
                if (dispersion[ct][i] > 0.5) {
                    dispersion[ct][i] = 0.2;
                }
                if (dispersion[ct][i] < 0.1 && dispersion[ct][i] > 0.03) {
                    dispersion[ct][i] = 0.2;
                }
            }
        }

        String ready = null;
        int testPID[] = new int[3000];

        for (int i = 0; i < 3000; i++) {
            testPID[i] = i;
        }

        // real scenario
//        for (int ct = 0; ct < noOfEntries; ct++) {
//            for (int i = 0; i < noOfCrtProcs; i++) {
//                if (ready == null) {
//                    ready = pid[ct][i] + " " + dispersion[ct][i];
//                    ready += "\n";
//                } else {
//                    ready += pid[ct][i] + " " + dispersion[ct][i];
//                    ready += "\n";
//                }
//            }
//        }
        // needed in order to set the precision of double
        Double formatDispersion[][] = new Double[1000][100];
        for (int ct = 0; ct < noOfEntries; ct++) {
            for (int i = 0; i < noOfCrtProcs; i++) {
                formatDispersion[ct][i] = Double.parseDouble(String.format("%.10f", dispersion[ct][i])) * 1000;
            }
        }

        // test scenario
        int j = 0; // id for testPID
        for (int ct = 0; ct < noOfEntries; ct++) {
            for (int i = 0; i < noOfCrtProcs; i++) {
                if (ready == null) {
                    ready = formatDispersion[ct][i].toString();
                    ready += "\n";
                    j++;
                } else {
                    ready += formatDispersion[ct][i].toString();
                    ready += "\n";
                    j++;
                }
            }
        }

        System.out.println(ready);

        PrintWriter out = new PrintWriter("..\\Data_Sets\\DataSet.txt");
        out.println(ready);
        out.close();
    }

}
