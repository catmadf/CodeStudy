package cn.madf.leetCode;

/**
 * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * <p>
 * 现在要求你戳破所有的气球。如果你戳破气球 i ，就可以获得 nums[left] * nums[i] * nums[right] 个硬币。 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
 * <p>
 * 求所能获得硬币的最大数量。
 * <p>
 * 说明:
 * <p>
 * 你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * 示例:
 * <p>
 * 输入: [3,1,5,8]
 * 输出: 167
 * 解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *      coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/burst-balloons
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解法：
 * 考虑递归和动态规划来求解（一般这种求最大或最小值都有可能是动态规划）
 * 首先用dp(i, j)表示开区间下即nums[i+1]到nums[j-1]的所有气球的最大硬币数（贪心？）（必须满足i+1 < j, 否则没有气球可戳）
 *
 * @author 烛影鸾书
 * @date 2020/7/19 14:31
 * @copyright© 2019
 */
public class P312_PokeTheBalloon {
    public int maxCoins(int[] nums) {
        int[] numsCopy = new int[nums.length + 2];
        numsCopy[0] = 1;
        numsCopy[numsCopy.length - 1] = 1;
        System.arraycopy(nums, 0, numsCopy, 1, nums.length);
        int[][] dp = new int[numsCopy.length][numsCopy.length];

        return solution(numsCopy, 0, numsCopy.length - 1, dp);

    }

    private int solution(int[] nums, int left, int right, int[][] dp) {
        if (left + 1 >= right) {
            return 0;
        }
        if (dp[left][right] != 0) {
            return dp[left][right];
        }
        /* 对 (left, right) 开区间内所有气球遍历扎一遍，找到最大值，这个过程需要特别注意一下，我们要从递归函数的最底层
        考虑，扎过的气球就不存在了，因此当扎破mid气球时的分数为应该为left*mid*right，这是因为中间气球都在递归过程求最优
        解时被扎过了。或者我们逆向考虑，在left ~ right间放气球使分数最大 */
        int tmpMax = -1;
        for (int mid = left + 1; mid < right; mid++) {
            int cur = nums[left] * nums[mid] * nums[right] + solution(nums, left, mid, dp) + solution(nums, mid, right, dp);
            if (cur > tmpMax) {
                tmpMax = cur;
            }
        }
        dp[left][right] = tmpMax;
        return dp[left][right];
    }
}
