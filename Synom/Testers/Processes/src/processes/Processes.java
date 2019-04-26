package processes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Processes {

    public static void main(String[] args) throws IOException {
//        Process process = Runtime.getRuntime().exec("tasklist.exe");
//        Scanner scanner = new Scanner(new InputStreamReader(process.getInputStream()));
//        while (scanner.hasNext()) {
//            System.out.println(scanner.nextLine());
//        }
//        scanner.close();
        linux();
    }

    public static void windows() throws IOException {
        String[] s = new String[500];
        Process process = Runtime.getRuntime().exec("tasklist.exe /FI \"CPUTIME gt 00:00:10\" "
                + "/FI \"SESSION eq 1\" /FI \"STATUS eq RUNNING\" /FI \"MEMUSAGE gt 50000\"");
        Scanner scanner = new Scanner(new InputStreamReader(process.getInputStream()));
        for (int i = 0; scanner.hasNext() != false; ++i) {
            s[i] = scanner.nextLine();
        }
        scanner.close();

        String s2 = new String();
        for (int i = 0; i < s.length; i++) {
            if (s[i] != null) {
                s2 = s2 + s[i] + "\n";
            } else {
                break;
            }
        }

        int k = 0, cnt = 0;
        for (int i = 0; i < s2.length(); i++) {
            if (s2.charAt(i) == '\n') {
                cnt++;
            }
            if (cnt == 5) {
                k = i;
                break;
            }
        }

        System.out.println(s2);
    }

    public static void linux() throws IOException {
        Process process = Runtime.getRuntime().exec("ps axo user,pid,%cpu,%mem,cmd --sort -rss");
        String[] s = new String[500];
        Scanner scanner = new Scanner(new InputStreamReader(process.getInputStream()));
        for (int i = 0; scanner.hasNext() != false; ++i) {
            s[i] = scanner.nextLine();
        }
        scanner.close();

        int cnt = 0;
        String s2 = new String();
        for (int i = 0; i < s.length; i++) {
            if ((s[i] != null) && (cnt <= 20)) {
                s2 = s2 + s[i] + "\n";
                cnt++;
            } else {
                break;
            }
        }

        System.out.println(s2);
    }
}
