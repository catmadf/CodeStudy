package cn.madf.leetcode_探索字节跳动.挑战字符串;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * <p>
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 * <p>
 * 示例1:
 * <p>
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 * <p>
 * <p>
 * 示例2:
 * <p>
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 * <p>
 * <p>
 * 注意：
 * <p>
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 *
 * @author 烛影鸾书
 * @date 2020/7/27 19:26
 * @copyright© 2020
 */
public class ArrangementOfStrings {

    /**
     * 滑动窗口，固定大小的窗口
     *
     * @param s1 字符串
     * @param s2 字符串
     * @return boolean
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        if (s1.length() == 0) {
            return true;
        }
        if (s1.length() == 1) {
            return s2.contains(s1);
        }
        /* 窗口 */
        int left = 0;
        int right = s1.length() + left - 1;
        /* map记录s1中元素 */
        Map<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            charMap.put(s1.charAt(i), charMap.getOrDefault(s1.charAt(i), 0) + 1);
        }
        int matchStart = left;
        while (right < s2.length()) {
            matchStart = winContainsSeq(charMap, s2, matchStart, right);
            if (matchStart > right) {
                return true;
            } else {
                char c = s2.charAt(left);
                if (charMap.containsKey(c)) {
                    charMap.put(c, charMap.get(c) + 1);
                }
                left++;
                right++;
                matchStart = matchStart < left ? left : matchStart;
            }
        }
        return false;
    }

    /**
     * @param map   s1中的元素
     * @param s     s2
     * @param start 窗口左边界
     * @param end   窗口右边界
     * @return 如果匹配不成功，则返回下次应该匹配的匹配起点(相当于记录下了该窗口上次已匹配的信息)
     */
    private int winContainsSeq(Map<Character, Integer> map, String s, int start, int end) {
        for (int k = start; k <= end; k++) {
            char c = s.charAt(k);
            int ccount;
            if ((ccount = map.getOrDefault(c, 0)) != 0) {
                map.put(c, ccount - 1);
            } else {
                return k;
            }
        }
        return end + 1;
    }

    public static void main(String[] args) {
        ArrangementOfStrings test = new ArrangementOfStrings();
        System.out.println(test.checkInclusion("ab", "eidbaooo"));
    }

}
