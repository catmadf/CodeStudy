package cn.madf.leetCode;

/**
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * <p>
 * 示例:
 * <p>
 * s = "3[a]2[bc]", 返回 "aaabcbc".
 * s = "3[a2[c]]", 返回 "accaccacc".
 * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
 *
 * @author 烛影鸾书
 * @date 2020/5/28
 * @copyright© 2020
 */
public class P394_DecodeString_TODO {
    public String decodeString(String s) {
        int bracketMatch = 0;
        int n = s.length();
        boolean startDecode = false;

        StringBuilder res = new StringBuilder();

        int subStart = 0;
        int subEnd = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '[') {
                if (bracketMatch == 0) {
                    subStart = i - 1;
                    startDecode = false;
                }
                bracketMatch++;
            }
            if (s.charAt(i) == ']') {
                bracketMatch--;
                if (bracketMatch == 0) {
                    subEnd = i;
                    startDecode = true;
                }
            }
            if (startDecode) {
                res.append(decodeSubString(1, ""));
            }
        }

        return "";

    }

    private String decodeSubString(int num, String s) {
        return "";
    }

    private String decode(int num, String item) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < num; i++) {
            res.append(item);
        }
        return res.toString();
    }
}
