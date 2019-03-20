package monitor;

import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Monitor {

    public static OperatingSystemMXBean mbean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    public static double memorySize, totalRAM;
    public static double[] cpuLoad = new double[100];
    public static Double cpuLoadText, totalRAMText, ramText;

    public static void main(String[] args) {
        String operatingSystem = System.getProperty("os.name");
        System.out.println("Current operating system: " + operatingSystem);

        operatingSystem = operatingSystem.subSequence(0, 5).toString();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        int i = 0;

        while (true) {
            cpuLoad[i] = mbean.getSystemCpuLoad();

            if ((cpuLoad[i] < 0.0 || cpuLoad[i] > 1.0) && cpuLoad[i] != -1.0) {
                throw new RuntimeException("getSystemCpuLoad() returns " + cpuLoad[i]
                        + " which is not in the [0.0,1.0] interval");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cpuLoadText = cpuLoad[i] * 100;
            memorySize = mbean.getFreePhysicalMemorySize();
            ramText = memorySize / 1024 / 1024;
            totalRAM = mbean.getTotalPhysicalMemorySize();
            totalRAMText = totalRAM / 1024 / 1024;

            LocalDateTime now = LocalDateTime.now();

            if (i == 0) {
                i++;
                continue;
            }
            System.out.println("[" + dtf.format(now) + "]\t CPU_Load = " + String.format("%.2f", cpuLoadText) + "%\t"
                    + " RAM_Load = " + String.format("%.2f", ramText) + "MB");
        }
    }

}
