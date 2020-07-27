package cn.madf.leetcode_探索字节跳动.挑战字符串;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * @author 烛影鸾书
 * @date 2020/7/27 16:11
 * @copyright© 2020
 */
public class TheLongestStringWithoutRepeatingCharacters {

    /**
     * 移窗思想
     * 维护一个窗口，窗口右边界扩张，扩张过程中遇到重复字符则记录下此时的长度，左边界向右收缩；
     * 用一个hashmap保存窗口内的字符以及其所在的位置；
     *
     * @param s 字符串
     * @return 无重复字符的最长子串长度
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() < 2) {
            return s.length();
        }
        int left = 0;
        int right = 0;
        int winLen = 1;
        Map<Character, Integer> winCharMap = new HashMap<>();
        while (right < s.length()) {
            char curChar = s.charAt(right);
            if (!winCharMap.containsKey(curChar)) {
                winCharMap.put(curChar, right++);
            } else {
                /* 将窗口移动过程中丢弃的char从winCharMap中移除 */
                int newLeft = winCharMap.get(curChar) + 1;
                for (int i = left; i < newLeft; i++) {
                    winCharMap.remove(s.charAt(i));
                }
                left = newLeft;
            }
            winLen = Math.max(winLen, right - left);
        }
        return winLen;
    }

    public static void main(String[] args) {
        String s = "abba";
        TheLongestStringWithoutRepeatingCharacters test = new TheLongestStringWithoutRepeatingCharacters();
        System.out.println(test.lengthOfLongestSubstring(s));
    }

}
