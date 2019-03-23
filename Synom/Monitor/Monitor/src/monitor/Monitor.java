package monitor;

import com.sun.management.OperatingSystemMXBean;
import java.io.File;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

public class Monitor {

    public static OperatingSystemMXBean mbean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    public static double memorySize, totalRAM, cpuLoad, freeStorage, totalStorage;
    public static Double cpuLoadText, totalRAMText, ramText, freeStorageText, totalStorageText;

    /* monitor() return an array of Strings as follows:
        status[0] = now.toString();
        status[1] = operatingSystem;
        status[2] = cpuLoadText.toString();
        status[3] = ramText.toString();
        status[4] = totalRAMText.toString();
        status[5] = totalStorageText.toString();
        status[6] = freeStorageText.toString();
        status[7] = inetAddress;
     */
    public String[] monitor() throws InterruptedException, UnknownHostException {
        String operatingSystem_uncut = System.getProperty("os.name");
        String operatingSystem = operatingSystem_uncut.replaceAll("\\s+", "");

        cpuLoad = mbean.getSystemCpuLoad();
        // getSystemCpuLoad() returns -1 at the first execution
        Thread.sleep(320);
        cpuLoad = mbean.getSystemCpuLoad();
        cpuLoadText = cpuLoad * 100;

        memorySize = mbean.getFreePhysicalMemorySize();
        ramText = memorySize / 1024 / 1024;

        totalRAM = mbean.getTotalPhysicalMemorySize();
        totalRAMText = totalRAM / 1024 / 1024;

        LocalDateTime now = LocalDateTime.now();

        File file = null;
        if (operatingSystem.equals("Windows10")) {
            file = new File("c:");
        } else {
            file = new File("/home");
        }
        freeStorage = file.getFreeSpace();
        totalStorage = file.getTotalSpace();


        freeStorageText = freeStorage / 1024 / 1024;
        totalStorageText = totalStorage / 1024 / 1024;

        String inetAddress = InetAddress.getLocalHost().getHostAddress();

        String[] status = new String[10];

        status[0] = now.toString();
        status[1] = operatingSystem;
        status[2] = cpuLoadText.toString();
        status[3] = ramText.toString();
        status[4] = totalRAMText.toString();
        status[5] = totalStorageText.toString();
        status[6] = freeStorageText.toString();
        status[7] = inetAddress;
        return status;
    }

    public static void main(String[] args) throws InterruptedException, UnknownHostException {
        Monitor a = new Monitor();
        String[] s = a.monitor();
        System.out.println(s[1]);
    }

}
