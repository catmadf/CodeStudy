package cn.madf.练习题.VMware;

import java.util.Scanner;

/**
 * @author 烛影鸾书
 * @date 2020/9/21 20:29
 * @copyright© 2020
 */
public class Main1 {
    private static int left = 0;
    private static int down = 1;
    private static int right = 2;
    private static int top = 3;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double n = sc.nextDouble();
        int m = sc.nextInt();
        double r = sc.nextDouble();
        move(0f, n, m, r);
    }

    private static void move(double path, double n, int m, double r) {
        int printCount = 0;
        while (printCount < m) {
            path += r;
            int sideNum = (int) (path / n);
            double remain = path % n;
            if (sideNum % 4 == left) {
                System.out.println(String.format("%.2f %.2f", remain, 0f));
            } else if (sideNum % 4 == down) {
                System.out.println(String.format("%.2f %.2f", n, remain));
            } else if (sideNum % 4 == right) {
                System.out.println(String.format("%.2f %.2f", n - remain, n));
            } else {
                System.out.println(String.format("%.2f %.2f", 0f, n - remain));
            }
            printCount++;
        }
    }

}
