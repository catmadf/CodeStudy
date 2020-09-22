package cn.madf.练习题.weipinhui;

import java.util.Scanner;

/**
 * @author 烛影鸾书
 * @date 2020/9/18 20:35
 * @copyright© 2020
 */
public class Main1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        System.out.println(calculate(a, b, c));
    }

    private static int max3(int i, int j, int k) {
        return Math.max(i, Math.max(j, k));
    }

    private static String calculate(int a, int b, int c) {
        int x = max3(a + b, b, c);
        int y = max3(a, b + c, c) + max3(a, b, b + c);
        if (y == 0) {
            return "ERROR";
        }
        float m = x * 1.0f / y;

        String[] value = String.valueOf(m).split("\\.");

        StringBuilder sb = new StringBuilder();
        sb.append(value[0]).append(".");
        String decimals = value[1];
        if (decimals.length() <= 2) {
            for (int i = 0; i < 2; i++) {
                if (i < decimals.length()) {
                    sb.append(decimals.charAt(i));
                } else {
                    sb.append("0");
                }
            }
            return sb.toString();
        }
        return String.valueOf((float) (Math.round(m * 100)) / 100);
    }

}
