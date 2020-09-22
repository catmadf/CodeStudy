package cn.madf.练习题.神策;

import java.util.Scanner;

/**
 * @author 烛影鸾书
 * @date 2020/9/15 19:59
 * @copyright© 2020
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int sum = sc.nextInt();
        System.out.println(maxSubstr(s.toCharArray(), sum));
    }

    private static int maxSubstr(char[] arr, int sum) {
        if (arr.length == 0 || sum == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return sum == arr[0] - 'a' + 1 ? 1 : 0;
        }
        int[] preSums = new int[arr.length + 1];
        preSums[0] = 0;
        for (int i = 1; i < preSums.length; i++) {
            preSums[i] = preSums[i - 1] + arr[i - 1] - 'a' + 1;
        }

        int lp = 0;
        int rp = 1;
        int curSum = preSums[1] - preSums[0];
        int maxLen = curSum == sum ? 1 : 0;
        while (rp < preSums.length) {
            curSum = preSums[rp] - preSums[lp];
            if (curSum == sum) {
                maxLen = Math.max(maxLen, rp - lp);
                rp++;
                lp++;
            } else if (curSum > sum) {
                lp++;
            } else {
                rp++;
            }
        }
        return maxLen;
    }

}
