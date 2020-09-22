package cn.madf.练习题.vivo;

import java.util.Scanner;

/**
 * @author 烛影鸾书
 * @date 2020/9/12 20:01
 * @copyright© 2020
 */
public class Main2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();

        System.out.println(solution(str));
    }


    private static String solution(String s) {
        int lp = 0;
        int rp = s.length() - 1;
        while (lp < rp && s.charAt(lp) == s.charAt(rp)) {
            lp++;
            rp--;
        }
        if (lp == rp) {
            return s;
        }
        if (lp == rp - 1) {
            return s.substring(0, lp) + s.substring(lp + 1);
        } else {
            if (isPalindrome(s, lp + 1, rp)) {
                // 删掉lp
                return s.substring(0, lp) + s.substring(lp + 1);
            } else if (isPalindrome(s, lp, rp - 1)) {
                // 删掉rp
                return s.substring(0, rp) + s.substring(rp + 1);
            }
            return "false";
        }
    }

    private static boolean isPalindrome(String s, int start, int end) {

        while (start < end) {
            if (s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
            } else {
                return false;
            }
        }
        return true;
    }

}
