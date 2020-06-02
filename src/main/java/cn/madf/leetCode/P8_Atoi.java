package cn.madf.leetCode;

import java.util.Stack;

/**
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
 * 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
 * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
 * 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0 。
 * <p>
 * 提示：
 * 本题中的空白字符只包括空格字符 ' ' 。
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−2^31,  2^31 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "42"
 * 输出: 42
 * <p>
 * 示例 2:
 * <p>
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 * 我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * <p>
 * 示例 3:
 * <p>
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 * <p>
 * 示例 4:
 * <p>
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 * 因此无法执行有效的转换。
 * <p>
 * 示例 5:
 * <p>
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 * 因此返回 INT_MIN (−231) 。
 * <p>
 * 题解：对于这类字符串处理的问题，往往会有很多复杂的情况，想通了实际上就是各个状态的转换
 * 本题可以使用确定性有限状态机（deterministic finite automaton, DFA）来解题
 * leetcode官方解法是个不错的思路：https://leetcode-cn.com/problems/string-to-integer-atoi/solution/zi-fu-chuan-zhuan-huan-zheng-shu-atoi-by-leetcode-/
 *
 * @author 烛影鸾书
 * @date 2020/6/1
 * @copyright© 2020
 */
public class P8_Atoi {

    private static final int START = 0;
    private static final int SIGNED = 1;
    private static final int NUMBER = 2;
    private static final int END = 3;

    private static final int[][] stateTransMatrix = new int[][]{
            {0, 1, 2, 3},
            {3, 3, 2, 3},
            {3, 3, 2, 3},
            {3, 3, 3, 3}
    };

    private int state = START;

    public int myAtoi(String s) {
        if (s == null) {
            return 0;
        }
        int signed = 1;
        long num = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            changeState(c);
            if (state == SIGNED) {
                signed = c == '+' ? 1 : -1;
            } else if (state == NUMBER) {
                num = num * 10 + c - '0';
                num = signed == 1 ? Math.min(num, Integer.MAX_VALUE) : Math.min(num, -(long) Integer.MIN_VALUE);
            } else if (state == END) {
                break;
            }
        }
        return signed * (int) num;
    }

    /**
     * 占位     ' ' 	+/- 	digit 	other
     * start 	start 	signed 	number 	end
     * signed 	end 	end 	number 	end
     * number 	end 	end 	number 	end
     * end 	    end 	end 	end 	end
     * 我们可以用一张表来表示状态转换的过程
     *
     * @param c 字符
     */
    private void changeState(char c) {
        int index;
        if (c == ' ') {
            index = 0;
        } else if (c == '+' || c == '-') {
            index = 1;
        } else if (Character.isDigit(c)) {
            index = 2;
        } else {
            index = 3;
        }
        state = stateTransMatrix[state][index];
    }

    public static void main(String[] args) {
        P8_Atoi p8 = new P8_Atoi();
        String s = "42";
        System.out.println(p8.myAtoi(s));
    }

}
