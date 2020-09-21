package cn.madf.leetcode_探索字节跳动.挑战字符串;

import java.util.*;

/**
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * <p>
 * 有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 *
 * @author 烛影鸾书
 * @date 2020/8/3 19:37
 * @copyright© 2020
 */
public class RestoreIpAddresses {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new LinkedList<>();
        if (s.length() == 0) {
            return res;
        }
        int L = 3;
        for (int i = 1; i <= L; i++) {
            String a = s.substring(0, i);
            if (!isValidNumber(a) || s.length() - i < 3) {
                break;
            }
            if (s.length() - i > 9) {
                continue;
            }
            for (int j = i + 1; j <= i + 1 + L; j++) {
                String b = s.substring(i, j);
                if (!isValidNumber(b) || s.length() - j < 2) {
                    break;
                }
                if (s.length() - j > 6) {
                    continue;
                }
                for (int k = j + 1; k <= j + 1 + L; k++) {
                    String c = s.substring(j, k);
                    if (!isValidNumber(c) || s.length() - k < 1) {
                        break;
                    }
                    if (s.length() - k > 3) {
                        continue;
                    }
                    String d = s.substring(k);
                    if (isValidNumber(d)) {
                        res.add(a + "." + b + "." + c + "." + d);
                    }
                }
            }
        }
        return res;
    }

    private boolean isValidNumber(String t) {
        if (Integer.parseInt(t) > 255) {
            return false;
        }
        return t.length() <= 1 || t.charAt(0) != '0';
    }

    public static void main(String[] args) {
        RestoreIpAddresses test = new RestoreIpAddresses();
        System.out.println(test.restoreIpAddresses("010010"));

        String s = null;
        System.out.println(s);
    }
}
