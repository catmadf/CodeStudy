package cn.madf.leetCode;

import java.net.DatagramPacket;

/**
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 * <p>
 * 提示：
 * 0 <= num < 231
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解：很明显的动态规划
 * 我们考虑位数大于2的数字
 * f(i)很明显与f(i-1)和f(i-2)有关
 * 当我们把i-1位和i位合并翻译时，就有f(i-1)种情况
 * 当我们打算将i-1位和i位单独翻译，如果这两位组成的数字不允许单独翻译，则f(i) = f(i-1)，如果允许，则f(i) = f(i-1)+f(i-2)
 *
 * @author 烛影鸾书
 * @date 2020/6/9
 * @copyright© 2020
 */
public class P46_TranslateNum {
    public int translateNum(int num) {
        String str = Integer.toString(num);
        if (str.length() == 1) return 1;
        if (str.length() == 2) return num > 25 ? 1 : 2;

        /* dp数组优化成三个变量 */
        int first = 1;
        int second = Integer.parseInt(str.substring(0, 2)) > 25 ? 1 : 2;
        int res = 0;
        for (int i = 2; i < str.length(); i++) {
            int it = Integer.parseInt(str.substring(i - 1, i + 1));
            int coeff = (it > 9 && it < 26) ? 1 : 0;
            res = first * coeff + second;
            first = second;
            second = res;
        }

        return res;
    }

    public static void main(String[] args) {
        P46_TranslateNum p46 = new P46_TranslateNum();
        int num = 12258;
        System.out.println(p46.translateNum(num));
    }
}
