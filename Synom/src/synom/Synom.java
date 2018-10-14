package synom;

import com.sun.management.OperatingSystemMXBean;
import java.lang.management.*;

public class Synom {

    public static GUI gui = new GUI();
    public static OperatingSystemMXBean mbean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    public static double memorySize;
    public static double[] cpuLoad = new double[100];
    public static Double cpuLoadText;
    public static Double ramText;

    public static void main(String[] args) {

        gui.setVisible(true);
        int i = 0;
        memorySize = mbean.getFreePhysicalMemorySize();
        ramText = memorySize/1000000;
        while (true) {
            cpuLoad[i] = mbean.getSystemCpuLoad();

            if ((cpuLoad[i] < 0.0 || cpuLoad[i] > 1.0) && cpuLoad[i] != -1.0) {
                throw new RuntimeException("getSystemCpuLoad() returns " + cpuLoad[i]
                        + " which is not in the [0.0,1.0] interval");
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cpuLoadText = cpuLoad[0] * 100;
        }
    }

}
