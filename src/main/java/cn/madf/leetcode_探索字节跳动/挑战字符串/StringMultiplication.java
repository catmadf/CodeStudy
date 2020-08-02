package cn.madf.leetcode_探索字节跳动.挑战字符串;

import com.sun.xml.internal.ws.client.sei.ValueSetter;

import java.util.Arrays;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * <p>
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * <p>
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 * @author 烛影鸾书
 * @date 2020/7/28 16:50
 * @copyright© 2020
 */
public class StringMultiplication {

    public static String multiply(String num1, String num2) {
        if (num1.charAt(0) == '0' || num2.charAt(0) == '0') {
            return "0";
        }
        /* 两数相乘结果的位数一不大于两数位数和 */
        int[] nums = new int[num1.length() + num2.length()];

        for (int i = num1.length() - 1; i > -1; i--) {
            int a = num1.charAt(i) - '0';
            int[] tmp = new int[num2.length() + 1];
            for (int j = num2.length() - 1; j > -1; j--) {
                int b = num2.charAt(j) - '0';
                int base = a * b % 10;
                int carry = a * b / 10;
                tmp[j + 1] += base;
                tmp[j] += carry;
            }
            addArray(nums, tmp, num1.length() - 1 - i);
        }

        char[] values = new char[nums.length];
        for (int i = 0; i < nums.length; i++) {
            values[i] = (char) (nums[i] + '0');
        }
        String res = new String(values);
        return res.charAt(0) == '0' ? res.substring(1) : res;
    }

    private static void addArray(int[] nums, int[] tmp, int shiftCarry) {
        int p1 = nums.length - 1 - shiftCarry;
        int p2 = tmp.length - 1;
        while (p1 >= 0 && p2 >= 0) {
            int sum = nums[p1] + tmp[p2];
            nums[p1--] = sum % 10;
            if (sum / 10 != 0) {
                nums[p1] += (sum / 10);
            }
            p2--;
        }
    }

    public static void main(String[] args) {
        System.out.println(multiply("2", "3"));
    }
}
