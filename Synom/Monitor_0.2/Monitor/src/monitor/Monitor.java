package monitor;

import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;
import java.time.LocalDateTime;

public class Monitor {

    public static OperatingSystemMXBean mbean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    public static double memorySize, totalRAM, cpuLoad;
    public static Double cpuLoadText, totalRAMText, ramText;

    /* monitor() return an array of Strings as follows:
    status[0] = time of execution
    status[1] = percent of CPU usage
    status[2] = RAM usage in MB
    status[3] = Total RAM in MB
    */
    public String[] monitor() throws InterruptedException {
        cpuLoad = mbean.getSystemCpuLoad();
        // getSystemCpuLoad() returns -1 at the first execution
        Thread.sleep(250);
        cpuLoad = mbean.getSystemCpuLoad();
        cpuLoadText = cpuLoad * 100;

        memorySize = mbean.getFreePhysicalMemorySize();
        ramText = memorySize / 1024 / 1024;

        totalRAM = mbean.getTotalPhysicalMemorySize();
        totalRAMText = totalRAM / 1024 / 1024;

        LocalDateTime now = LocalDateTime.now();

        String[] status = new String[4];
        status[0] = now.toString();
        status[1] = cpuLoadText.toString();
        status[2] = ramText.toString();
        status[3] = totalRAMText.toString();
        return status;
    }

    public static void main(String[] args) throws InterruptedException {
        Monitor a = new Monitor();
        String[] d = a.monitor();
        System.out.println("CPU = " + d[3] + "%");
    }

}
