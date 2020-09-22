package cn.madf.练习题.meituan;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author 烛影鸾书
 * @date 2020/9/20 10:09
 * @copyright© 2020
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        System.out.println(recuSearch(m, n));
    }

    private static int recuSearch(int m, int n) {
        if (m < 100000) {
            return recuSearch(100000, n);
        }
        if (n > 999999) {
            return recuSearch(m, 999999);
        }
        int count = 0;
        for (int i = m; i <= n; i++) {
            char[] s = String.valueOf(i).toCharArray();
            int a = s[0] - '0';
            int b = s[1] - '0';
            int c = s[2] - '0';
            int d = s[3] - '0';
            int e = s[4] - '0';
            int f = s[5] - '0';
            if (containZero(s)) {
                continue;
            }
            if (!isUnique(s)) {
                continue;
            }
            if (10 * a + b + 10 * c + d == 10 * e + f) {
                count++;
            }
        }
        return count;
    }

    private static boolean isUnique(char[] arr) {
        Set<Character> set = new HashSet<>();
        for (char c : arr) {
            set.add(c);
        }
        return set.size() == arr.length;
    }

    private static boolean containZero(char[] arr) {
        if (arr.length < 6) {
            return true;
        }
        for (char c : arr) {
            if (c == '0') {
                return true;
            }
        }
        return false;
    }

}
