package dsc;

public class Second_Pipe {

    static int noOfCrtProcs = 5;
    static int noOfTimeCaptures = 12;
    static String matrix[][][] = new String[600][10][2];
    static String s = new String();
    static double[][] mem = new double[noOfCrtProcs][noOfTimeCaptures + 1];
    static double[] pid = new double[noOfCrtProcs];


    static void secondDataSet() {
        matrix = First_Pipe.firstDataSet();

        /* These lines create an array string with the following form:
        pid1 ramNow ramNow-1 ramNow-2...ramNow-noOfTimeCaptures
        pid2 ramNow ramNow-1 ramNow-2...ramNow-noOfTimeCaptures
        .
        .
        pid_noOfCrtProcs ramNow ramNow-1...ramNow-noOfTimeCaptures
         */
        for (int j = 0; j < noOfCrtProcs; j++) {
            if (s.isEmpty() == true) {
                s = matrix[0][0][0] + " " + matrix[0][0][1];
                pid[0] = Double.parseDouble(matrix[0][0][0]);
                mem[0][0] = Double.parseDouble(matrix[0][0][1]);
            } else {
                s = s + matrix[0][j][0] + " " + matrix[0][j][1];
                pid[j] = Double.parseDouble(matrix[0][j][0]);
                mem[j][0] = Double.parseDouble(matrix[0][j][1]);
            }
            for (int i = 1; i < noOfTimeCaptures; i++) {
                if (matrix[i][j][0].equals(matrix[0][j][0])) {
                    s = s + " " + matrix[i][j][1];
                    mem[j][i] = Double.parseDouble(matrix[i][j][1]);
                }
            }
            s = s + "\n";
        }
        System.out.println(s);
        System.out.println("");

        // compute min and max mem of each pid
        double[] max = new double[100];
        double[] min = new double[100];

        // initialize min with first value and max with 0
        for (int i = 0; i < 12; i++) {
            max[i] = 0;
        }

        for (int i = 0; i < noOfCrtProcs; i++) {
            min[i] = mem[i][0];
        }

        // compute min and max
        for (int j = 0; j < noOfCrtProcs; j++) {
            for (int i = 0; i < noOfTimeCaptures; i++) {
                if (min[j] >= mem[j][i]) {
                    min[j] = mem[j][i];
                }
            }
        }

        for (int j = 0; j < noOfCrtProcs; j++) {
            for (int i = 0; i < noOfTimeCaptures; i++) {
                if (max[j] <= mem[j][i]) {
                    max[j] = mem[j][i];
                }
            }
        }

        double sum[] = new double[100];
        double mean[] = new double[100];

        //sum of all values for each PID
        for (int j = 0; j < noOfCrtProcs; j++) {
            for (int i = 0; mem[0][i] != 0.0; i++) {
                sum[j] += mem[j][i];
            }
        }

        //compute mean of each process
        for (int i = 0; i < noOfCrtProcs; i++) {
            mean[i] = sum[i] / noOfTimeCaptures;
        }

        double variance[] = new double[100];

        for (int i = 0; i < noOfCrtProcs; i++) {
            variance[i] = 0;
        }

        // compute variance as:
        // sigma = sum((x - mean)^2)
        for (int i = 0; i < noOfCrtProcs; i++) {
            for (int j = 0; j < noOfTimeCaptures; j++) {
                variance[i] = variance[i] + Math.pow(mem[i][j] - mean[i], 2);
            }
        }

        double dispersion[] = new double[100];

        for (int i = 0; i < noOfCrtProcs; i++) {
            dispersion[i] = 0;
        }

        // dispersion: d = sqrt(sigma)
        for (int i = 0; i < noOfCrtProcs; i++) {
            dispersion[i] = Math.sqrt(variance[i]);
        }

        for (int i = 0; i < noOfCrtProcs; i++) {
            System.out.println(dispersion[i]);
        }
    }

}
