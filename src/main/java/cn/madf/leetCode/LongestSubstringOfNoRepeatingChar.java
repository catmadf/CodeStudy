package cn.madf.leetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 烛影鸾书
 * @date 2020/5/21
 * @copyright© 2020
 */
public class LongestSubstringOfNoRepeatingChar {

    public static int lengthOfLongestSubstring(String s) {
        if (s == null) return 0;
        if (s.length() < 2) return s.length();

        Set<Character> set = new HashSet<>();
        int res = 1;
        int i = 0, j = 1;
        set.add(s.charAt(i));
        while (i != s.length() - 1) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j));
                res = Math.max(res, j-i+1);
                j = j == s.length() - 1 ? s.length() - 1 : j + 1;
            } else {
                set.remove(s.charAt(i));
                i++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int a = lengthOfLongestSubstring("abcabcbb");
        System.out.println(a);
    }
}
