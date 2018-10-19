package synom;

import com.sun.management.OperatingSystemMXBean;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.management.*;
import javax.swing.Action;
import org.jfree.ui.RefineryUtilities;

public class Synom {

    public static GUI gui = new GUI();
    public static OperatingSystemMXBean mbean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    public static double memorySize, totalRAM;
    ;
    public static double[] cpuLoad = new double[100];
    public static Double cpuLoadText, totalRAMText, ramText;

    public static void main(String[] args) {

        gui.setVisible(true);

        final org.jfree.chart.demo.DynamicDataDemo demo = new org.jfree.chart.demo.DynamicDataDemo("Dynamic Data Demo");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
        demo.button.setVisible(false);
        for (int i = 0; i < 100; ++i) {
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
            gui.ram.setText("RAM = " + String.format("%.2f", Synom.ramText) + " GB");
            gui.totalRAM.setText("Total RAM = " + String.format("%.2f", Synom.totalRAMText) + " GB");
            demo.button.doClick();
        }

        Chart chart = new Chart("CPU usage", "CPU usage");
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }

}
