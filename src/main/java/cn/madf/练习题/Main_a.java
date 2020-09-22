package cn.madf.练习题;

import java.util.Scanner;

/**
 * @author 烛影鸾书
 * @date 2020/9/3 20:16
 * @copyright© 2020
 */
public class Main_a {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testNum = sc.nextInt();
        for (int i = 0; i < testNum; i++) {
            int n = sc.nextInt();
            int[] counts = new int[n + 1];
            int m = sc.nextInt();
            for (int j = 0; j < m; j++) {
                int k = sc.nextInt();
                for (int l = 0; l < k; l++) {
                    int left = sc.nextInt();
                    int right = sc.nextInt();
                    for (int o = left; o <= right; o++) {
                        counts[o]++;
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            int c = 0;
            for (int j = 1; j < n + 1; j++) {
                if (counts[j] >= m) {
                    sb.append(j).append(" ");
                    c++;
                }
            }

            System.out.println(c);
            System.out.println(sb.substring(0, sb.length() - 1));
        }
    }
}
