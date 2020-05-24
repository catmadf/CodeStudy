package cn.madf.leetCode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * tag: string
 * <p>
 * 返回 a 中包含 b 所有字符的子串
 *
 * @author 烛影鸾书
 * @date 2020/5/23
 * @copyright© 2020
 */
public class P76_SmallestOverwrittenSubstring {

    public String solution(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        Map<Character, Integer> bCharMap = new HashMap<>();
        Map<Character, Integer> windowCharMap = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            bCharMap.put(c, bCharMap.getOrDefault(c, 0) + 1);
        }
        int i = 0;
        int j = -1;  // [i, j] 即为窗口包含位置

        int start = 0;
        int end = -1;
        int len = Integer.MAX_VALUE;

        while (j < s.length()) {
            j++;
            if (j < s.length() && bCharMap.containsKey(s.charAt(j))) {
                char c = s.charAt(j);
                windowCharMap.put(c, windowCharMap.getOrDefault(c, 0) + 1);
            }
            while (isContainAll(bCharMap, windowCharMap) && i <= j) {
                if (j - i + 1 < len) {
                    start = i;
                    end = j;
                    len = j - i + 1;
                }
                char c = s.charAt(i);
                if (bCharMap.containsKey(c)) windowCharMap.put(c, windowCharMap.getOrDefault(c, 0) - 1);
                i++;
            }
        }
        return (end - start + 1) > 0 ? s.substring(start, end + 1) : "";
    }

    private boolean isContainAll(Map<Character, Integer> bCharMap, Map<Character, Integer> windowCharMap) {
        Iterator iter = bCharMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Character key = (Character) entry.getKey();
            Integer val = (Integer) entry.getValue();
            if (windowCharMap.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        P76_SmallestOverwrittenSubstring t = new P76_SmallestOverwrittenSubstring();
        String a = "ADOBECODEBANC";
        String b = "ABC";
        System.out.println(t.solution(a, b));
    }
}
