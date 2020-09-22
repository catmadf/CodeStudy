package cn.madf.练习题.mihayou;

import java.util.Scanner;

/**
 * @author 烛影鸾书
 * @date 2020/9/19 20:59
 * @copyright© 2020
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        String p = sc.next();
        System.out.println(match(s.toCharArray(), 0, p.toCharArray(), 0));
    }

    private static boolean match(char[] s, int i, char[] p, int j) {

        /* 匹配结束 */
        if (i == s.length - 1 && j == p.length - 1) {
            return s[i] == p[j];
        }
        if (i < s.length && j >= p.length) {
            return false;
        }

        if ((j + 1) < p.length && p[j + 1] == '*') {
            if (p[j] == s[i] || (p[j] == '.' && i < s.length - 1)) {
                /* 匹配多次 */
                return match(s, i + 1, p, j) || match(s, i + 1, p, j + 2) || match(s, i, p, j + 2);
            } else {
                /* 匹配0次 */
                return match(s, i, p, j + 2);
            }
        } else if ((j + 1) < p.length && p[j + 1] == '+') {
            if (p[j] == s[i] || (p[j] == '.' && i < s.length - 1)) {
                /* 匹配1次或多次 */
                return match(s, i + 1, p, j) || match(s, i + 1, p, j + 2) || match(s, i, p, j + 2);
            } else {
                /* + 前一个数未匹配成功 */
                return false;
            }
        }

        if (s[i] == p[j] || (p[j] == '.' && i < s.length - 1)) {
            return match(s, i + 1, p, j + 1);
        }
        return false;
    }

}
