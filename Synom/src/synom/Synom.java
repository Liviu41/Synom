package synom;

import com.sun.management.OperatingSystemMXBean;
import java.lang.management.*;

public class Synom {

    public static void main(String[] args) {
        System.out.println("Made it!");

        OperatingSystemMXBean mbean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        double load;
        for (int i = 0; i < 10; i++) {
            load = mbean.getSystemCpuLoad();
            System.out.println(load);
            if ((load < 0.0 || load > 1.0) && load != -1.0) {
                throw new RuntimeException("getSystemCpuLoad() returns " + load
                        + " which is not in the [0.0,1.0] interval");
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long memorySize = ((com.sun.management.OperatingSystemMXBean) ManagementFactory
        .getOperatingSystemMXBean()).getFreePhysicalMemorySize();
        Object a = memorySize;
        System.out.println(Long.parseLong(a.toString())/1000000-200);
    }

}
