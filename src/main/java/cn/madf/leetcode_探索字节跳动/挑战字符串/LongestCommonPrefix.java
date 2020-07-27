package cn.madf.leetcode_探索字节跳动.挑战字符串;

import java.util.HashSet;
import java.util.Set;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * <p>
 * 所有输入只包含小写字母 a-z 。
 *
 * @author 烛影鸾书
 * @date 2020/7/27 16:58
 * @copyright© 2020
 */
public class LongestCommonPrefix {

    /**
     * 指针 或者 前缀树
     *
     * @param strs 字符串数组
     * @return 前缀字符串
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        int minLen = Integer.MAX_VALUE;
        for (String str : strs) {
            minLen = Math.min(minLen, str.length());
        }

        int len = 0;
        for (int i = 0; i < minLen; i++) {
            if (isSameChar(strs, i)) {
                len = i + 1;
            } else {
                break;
            }
        }
        return strs[0].substring(0, len);
    }

    private boolean isSameChar(String[] strs, int i) {
        Set<Character> set = new HashSet<>();
        for (String str : strs) {
            set.add(str.charAt(i));
            if (set.size() > 1) {
                return false;
            }
        }
        return set.size() == 1;
    }
}
