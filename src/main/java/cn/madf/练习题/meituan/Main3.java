package cn.madf.练习题.meituan;

import java.util.Scanner;

/**
 * @author 烛影鸾书
 * @date 2020/9/20 11:09
 * @copyright© 2020
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        String s = sc.next();
        String t = sc.next();
        int sum = subsequence(s.toCharArray(), t.toCharArray());
        if (sum == 0) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
            System.out.println(sum);
        }
    }

    private static int subsequence(char[] s, char[] t) {
        if (t.length == 0) {
            return 0;
        }
        if (s.length < t.length) {
            return subsequence(t, s);
        }
        int sum = 0;
        int i = 0;
        int j = 0;
        while (i < s.length && j < t.length) {
            if (s[i] == t[j]) {
                j++;
                sum += (i + 1);
            }
            i++;
        }
        if (j == t.length) {
            return sum;
        } else {
            return 0;
        }
    }

}
