package cn.madf.练习题;

import java.util.Scanner;

/**
 * @author 烛影鸾书
 * @date 2020/9/9 21:45
 * @copyright© 2020
 */
public class tianyiyun3_Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        String[] arrInput = input.split(",");
        int[] arr = new int[arrInput.length];
        for (int i = 0; i < arrInput.length; i++) {
            arr[i] = Integer.parseInt(arrInput[i]);
        }
        System.out.println(maxSumDp(arr));
    }

    private static int maxSumDp(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[1], arr[0]);
        for (int i = 2; i < arr.length; i++) {
            dp[i] = Math.max(dp[i - 2] + arr[i], dp[i - 1]);
        }
        return dp[arr.length - 1];
    }

}
