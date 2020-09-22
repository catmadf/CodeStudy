package cn.madf.练习题.weipinhui;

import java.util.Scanner;

/**
 * @author 烛影鸾书
 * @date 2020/9/18 20:57
 * @copyright© 2020
 */
public class Main2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(solution(n));
    }

    private static long solution(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        long[] cow = new long[n + 1];
        cow[0] = 0;
        cow[1] = 1;
        cow[2] = 2;
        cow[3] = 3;
        int i = 4;
        while (i <= n) {
            cow[i] = cow[i - 1] + cow[i - 3];
            i++;
        }
        return cow[n];
    }
}
