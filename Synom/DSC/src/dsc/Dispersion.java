package dsc;

import static dsc.Second_Pipe.noOfEntries;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/*
        dataSet =
            dimension 1 = id of process list
            dimension 2 = entry nr in processes
            dimension 3 = PID/Mem Usage
 */

public class Dispersion {

    static String matrix[][][] = new String[600][10][2];

    // value to be set if mem usage of PID cannot be found
    // back in time at a certain entry
    static Double specialValue = 32767.0;

    public static void secondDataSet() throws FileNotFoundException {
        matrix = First_Pipe.firstDataSet();

        int noOfProcesses = 5;
        int noOfBack = 5;
        String pid[] = new String[noOfProcesses];
        Double mem[][] = new Double[noOfProcesses][noOfBack];

        // get pids of the last process list
        for (int i = 0; i < noOfProcesses; i++) {
            pid[i] = matrix[0][i][0];
        }

        // get mems of each proc and noOfBack mems back in time
        for (int i = 0; i < noOfBack; i++) {
            for (int j = 0; j < noOfProcesses; j++) {
                if (matrix[i][j][0].equals(matrix[i + 1][j][0])) {
                    mem[j][i] = Double.parseDouble(matrix[i][j][1]);
                    mem[j][i] /= 1000;
                } else {
                    mem[j][i] = specialValue;
                }
            }
        }

//        for (int i = 0; i < noOfBack; i++) {
//            for (int j = 0; j < noOfProcesses; j++) {
//                System.out.println(mem[0][j]);
//            }
//        }

        Double[] sum = new Double[noOfProcesses];
        for (int i = 0; i < noOfProcesses; i++) {
            sum[i] = 0.0;
        }

        for (int i = 0; i < noOfProcesses; i++) {
            for (int j = 0; j < noOfBack; j++) {
                sum[i] += mem[i][j];

            }
        }

        Double[] mean = new Double[noOfProcesses];
        for (int i = 0; i < noOfProcesses; i++) {
            mean[i] = 0.0;
        }

        for (int i = 0; i < noOfProcesses; i++) {
            mean[i] = sum[i] / noOfBack;
        }

        // compute variance as:
        // sigma = sum((x - mean)^2)
        Double[] variance = new Double[noOfProcesses];
        for (int i = 0; i < noOfProcesses; i++) {
            variance[i] = 0.0;
        }

        for (int i = 0; i < noOfProcesses; i++) {
            for (int j = 0; j < noOfBack; j++) {
                if (mem[i][j] == specialValue) {
                    variance[i] = specialValue * specialValue;
                }
            }
        }

        for (int i = 0; i < noOfProcesses; i++) {
            for (int j = 0; j < noOfBack; j++) {
                if (variance[i] != specialValue * specialValue) {
                    variance[i] += ((mem[i][j] - mean[i]) * (mem[i][j] - mean[i]));
                }
            }
        }

        Double dispersion[] = new Double[noOfProcesses];
        for (int i = 0; i < noOfProcesses; i++) {
            dispersion[i] = 0.0;
        }

        for (int i = 0; i < noOfProcesses; i++) {
            dispersion[i] = Math.sqrt(variance[i]);
        }

        Double formatDispersion[] = new Double[noOfProcesses];
        for (int i = 0; i < noOfProcesses; i++) {
            formatDispersion[i] = Double.parseDouble(String.format("%.10f", dispersion[i])) * 1000;
            if (formatDispersion[i] > 80 && formatDispersion[i] < 150) {
                formatDispersion[i] = 200.0;
            } else if (formatDispersion[i] > 400) {
                formatDispersion[i] = 200.0;
            }
        }

        String ready = null;

        for (int i = 0; i < noOfProcesses; i++) {
            if (i == 0) {
                ready = pid[i] + " " + formatDispersion[i] + "\n";
            } else {
            ready += pid[i] + " " + formatDispersion[i] + "\n";
            }
        }

        PrintWriter out = new PrintWriter("..\\Data_Sets\\toBeClustered.txt");
        out.println(ready);
        out.close();
    }
}
