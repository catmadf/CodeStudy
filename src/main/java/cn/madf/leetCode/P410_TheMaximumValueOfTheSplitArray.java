package cn.madf.leetCode;

/**
 * 给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。
 *
 * 注意:
 * 数组长度 n 满足以下条件:
 *
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
 * 示例:
 *
 * 输入:
 * nums = [7,2,5,10,8]
 * m = 2
 *
 * 输出:
 * 18
 *
 * 解释:
 * 一共有四种方法将nums分割为2个子数组。
 * 其中最好的方式是将其分为[7,2,5] 和 [10,8]，
 * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-array-largest-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 解法1：动态规划
 *      状态定义：f(i, j) 表示数组前i个数分成j组的每组最大值集合的最小值
 *      状态转变：从 j-1 个分组到 j 个分组的转变 ----> 相当于前 k 个数分成 j-1 个分组，后 i-k 个数为一个分组
 *                这时候状态转换方程为：f(i, j) = Min{f(k, j-1), max(k, i, 1)}
 * @author 烛影鸾书
 * @date 2020/7/25 11:34
 * @copyright© 2020
 */
public class P410_TheMaximumValueOfTheSplitArray {

    public int splitArray(int[] nums, int m) {

    }

}
