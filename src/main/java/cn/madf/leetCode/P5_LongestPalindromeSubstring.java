package cn.madf.leetCode;

/**
 * 输入一个字符串，输出其最长子串
 * <p>
 * 中心扩展法：如果我们能找到回文中心，只需要往外扩展，扩展时判断扩张的两个字符是否相同即可。
 * （假定所有字符都有可能是回文中心）
 * 但是需要注意到回文中心有可能是虚的，比如 "aa".
 *
 * @author 烛影鸾书
 * @date 2020/5/21
 * @copyright© 2020
 */
public class P5_LongestPalindromeSubstring {
    public static String solution(String s) {
        if (s == null || s.length() == 1) {
            return s;
        }
        /* 记录最长回文子串的起始结束位置 */
        int start = 0;
        int end = 1;
        /* 假定所有字符都有可能是回文中心, 需要遍历 */
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandFromCenter(s, i, i);  // 实回文中心，即奇数情况
            int len2 = expandFromCenter(s, i, i + 1);  // 虚回文中心
            int len = Math.max(len1, len2);
            if (len > (end - start)) {
                if (len % 2 == 0) {
                    start = i - ((len - 2) >> 1);
                    end = i + 1 + ((len - 2) >> 1) + 1;
                } else {
                    start = i - (len >> 1);
                    end = i + (len >> 1) + 1;
                }
            }
        }
        return s.substring(start, end);
    }

    public static int expandFromCenter(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        return j - i - 1;
    }

    public static void main(String[] args) {
        String s = "ccabbacca";
        System.out.println(solution(s));
    }
}
