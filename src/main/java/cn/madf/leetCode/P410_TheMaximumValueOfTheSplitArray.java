package cn.madf.leetCode;

/**
 * 给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。
 * <p>
 * 注意:
 * 数组长度 n 满足以下条件:
 * <p>
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
 * 示例:
 * <p>
 * 输入:
 * nums = [7,2,5,10,8]
 * m = 2
 * <p>
 * 输出:
 * 18
 * <p>
 * 解释:
 * 一共有四种方法将nums分割为2个子数组。
 * 其中最好的方式是将其分为[7,2,5] 和 [10,8]，
 * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-array-largest-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解法1：动态规划
 * 状态定义：f(i, j) 表示数组前i个数分成j组的每组最大值集合的最小值
 * 状态转移：从 j-1 个分组到 j 个分组的转变 ----> 相当于前 k 个数分成 j-1 个分组，后 i-k 个数为一个分组
 * 这时候状态转换方程为：f(i, j) = Min{f(i, j), Max{f(k, j-1), sum(k, i, nums)}}(对k遍历)
 * <p>
 * 解法2：二分查找+贪心
 * 最终结果肯定在数组最大值m到数组和s之间
 * 那我们可以用二分查找，在[m, s]之间是否存在一个数x，使得数组被分成m组，每组的和不超过x
 *
 * @author 烛影鸾书
 * @date 2020/7/25 11:34
 * @copyright© 2020
 */
public class P410_TheMaximumValueOfTheSplitArray {

    public int splitArray(int[] nums, int m) {
        int[][] dp = new int[nums.length + 1][m + 1];
        for (int i = 0; i < nums.length + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[0][0] = 0;

        int[] subSum = new int[nums.length + 1];
        for (int i = 0; i < subSum.length - 1; i++) {
            subSum[i + 1] = subSum[i] + nums[i];
        }

        for (int i = 1; i < nums.length + 1; i++) {
            for (int j = 1; j <= Math.min(i, m); j++) {   // 实际上j只能小于等于i，但i和m大小未知，即j<=min(i,m)
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], subSum[i] - subSum[k]));
                }
            }
        }
        return dp[nums.length][m];
    }

    public int splitArraySecond(int[] nums, int m) {
        int left = Integer.MIN_VALUE;
        int right = 0;
        for (int num : nums) {
            left = Math.max(left, num);
            right += num;
        }
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (check(nums, m, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[] nums, int m, int x) {
        int cnt = 1;
        int subSum = 0;
        for (int num : nums) {
            if (subSum + num <= x) {
                subSum += num;
            } else {
                cnt++;
                subSum = num;
            }
        }
        return cnt <= m;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{7, 2, 5, 10, 8};
        P410_TheMaximumValueOfTheSplitArray p410 = new P410_TheMaximumValueOfTheSplitArray();
        System.out.println(p410.splitArraySecond(nums, 2));
    }
}
