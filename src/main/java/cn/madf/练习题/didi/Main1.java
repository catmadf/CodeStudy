package cn.madf.练习题.didi;

import java.util.Scanner;

/**
 * @author 烛影鸾书
 * @date 2020/9/13 19:33
 * @copyright© 2020
 */
public class Main1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        String s = sc.nextLine();
        char[] sequence = s.toCharArray();
        resolve(sequence, t);
        System.out.println(new String(sequence));
    }

    private static void resolve(char[] s, int t) {
        if (s.length <= t) {
            reverse(s, 0, s.length - 1);
            return;
        }
        int n = s.length;
        int slice = n / t;
        for (int i = 0; i < slice; i++) {
            reverse(s, t * i, t * (i + 1) - 1);
        }

        if (n % t != 0) {
            reverse(s, slice * t, n - 1);
        }

    }

    private static void reverse(char[] s, int start, int end) {
        while (start <= end) {
            swap(s, start++, end--);
        }
    }

    private static void swap(char[] s, int i, int j) {
        char tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }

}
