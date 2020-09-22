package cn.madf.练习题.浪潮;

import java.util.Scanner;

/**
 *
 * n沙滩石头 使其按照从小打到排序 最少移动步数
 * @author 烛影鸾书
 * @date 2020/9/11 10:23
 * @copyright© 2020
 */
public class Main1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(solution(arr));
    }

    private static int solution(int[] arr) {
        int m = 1;
        int[] dp = new int[arr.length + 1];
        for (int value : arr) {
            dp[value] = 1;
            if (dp[value - 1] != 0) {
                dp[value] = dp[value - 1] + 1;
            }
            if (dp[value] > m) {
                m = dp[value];
            }
        }
        return arr.length - m;
    }

}
