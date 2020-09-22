package cn.madf.练习题.aiqiyi;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author 烛影鸾书
 * @date 2020/9/13 15:26
 * @copyright© 2020
 */
public class Main1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();

    }

    private static int lenOfLongestNoRepeat(String s) {
        if (s.length() < 2) {
            return s.length();
        }
        Set<Character> set = new HashSet<>();
        int res = 1;
        int i = 0, j = 1;
        set.add(s.charAt(i));
        while (i < s.length()) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j));
                res = Math.max(res, j - i + 1);
                j = j == s.length() - 1 ? s.length() - 1 : j + 1;
            } else {
                set.remove(s.charAt(i));
                i++;
            }
        }
        return res;
    }

}
