package monitor;

import com.sun.management.OperatingSystemMXBean;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.Scanner;

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
        status[8] = processes;
     */
    public String[] monitor() throws InterruptedException, UnknownHostException, IOException {
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

        Process process = null;
        String processes = null;
        
        if (operatingSystem.equals("Windows10")) {
            // Code for getting the list of processes FOR WINDOWS OS!!
            String[] initial_list = new String[500];
            process = Runtime.getRuntime().exec("tasklist.exe /FI \"CPUTIME gt 00:00:02\" "
                    + "/FI \"SESSION eq 1\" /FI \"STATUS eq RUNNING\" /FI \"MEMUSAGE gt 50000\"");
            Scanner scanner = new Scanner(new InputStreamReader(process.getInputStream()));
            for (int i = 0; scanner.hasNext() != false; ++i) {
                initial_list[i] = scanner.nextLine();
            }
            scanner.close();
            processes = new String();
            for (int i = 0; i < initial_list.length; i++) {
                if (initial_list[i] != null) {
                    processes = processes + initial_list[i] + "\n";
                } else {
                    break;
                }
            }
        } else {

            process = Runtime.getRuntime().exec("ps axo user,pid,%cpu,%mem,cmd --sort -rss");
            String[] s = new String[500];
            Scanner scanner = new Scanner(new InputStreamReader(process.getInputStream()));
            for (int i = 0; scanner.hasNext() != false; ++i) {
                s[i] = scanner.nextLine();
            }
            scanner.close();

            int cnt = 0;
            processes = new String();
            for (int i = 0; i < s.length; i++) {
                if ((s[i] != null) && (cnt <= 20)) {
                    processes = processes + s[i] + "\n";
                    cnt++;
                } else {
                    break;
                }
            }
        }

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
        status[8] = processes;
        return status;
    }

    public static void main(String[] args) throws InterruptedException, UnknownHostException, IOException {
        Monitor a = new Monitor();
        String[] s = a.monitor();
        System.out.println(s[7]);
    }

}
