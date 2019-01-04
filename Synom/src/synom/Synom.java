/*

*/

package synom;

import com.sun.management.OperatingSystemMXBean;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.management.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Synom {

    public static GUI gui = new GUI();
    public static OperatingSystemMXBean mbean = (com.sun.management.OperatingSystemMXBean) 
            ManagementFactory.getOperatingSystemMXBean();
    public static double memorySize, totalRAM;
    public static double[] cpuLoad = new double[100];
    public static Double cpuLoadText, totalRAMText, ramText;
    public static final Chart chartCPU = new Chart("CPU Chart", "cpu");
    public static final Chart chartRAM = new Chart("RAM Chart", "ram");

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        gui.setLocation(200, 200);
        gui.setVisible(true);

        String operatingSystem = System.getProperty("os.name");
        gui.osLabel.setText("Current operating system is " + operatingSystem);
        operatingSystem = operatingSystem.subSequence(0, 5).toString();
        System.out.println("Current operating system: " + operatingSystem);

        PrintWriter writer;
        if (operatingSystem.equals("Windo")) {
            writer = new PrintWriter("C:\\Users\\Liviu\\Desktop\\Logs.txt", "UTF-8");
        } else {
            writer = new PrintWriter("~/Desktop/Logs.txt");
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        
        for (int i = 0; i < 10; ++i) {
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

            gui.cpuLoad.setText("CPU = " + String.format("%.2f", Synom.cpuLoadText) + "%");
            gui.ram.setText("RAM = " + String.format("%.2f", Synom.ramText) + " MB");
            gui.totalRAM.setText("Total RAM = " + String.format("%.2f", Synom.totalRAMText) + " MB");
            chartCPU.button.doClick();
            chartRAM.button.doClick();

            LocalDateTime now = LocalDateTime.now();

            writer.println("[" + dtf.format(now) + "]\t CPU_Load = " + String.format("%.2f", Synom.cpuLoadText) + "%\t"
                    + " RAM_Load = " + String.format("%.2f", Synom.ramText) + "MB");
        }
        writer.close();
        System.exit(0);
    }

}
