package cn.madf.leetCode;

/**
 * tag: 动态规划
 * 在198的基础上，房子改成环形排列
 * <p>
 * 解法：实际上还是动规
 * 1.   第1间房不偷那就是 dp[1:]的最大值
 * 2.   第n间房不偷那就是 dp[:n-1]的最大值
 * 3.   头尾两间房都不偷，实际上这里会有一个点可以注意到，少掉一间可供偷取的对象后最终并不会使偷到的钱增加，而是减少或不变
 * 即 max(dp[2, 3, 4,..., n-1]) <= [max(dp[1, 2, 3, ..., n-1]), max(dp[2, 3, ..., n-1, n])].max = max(dp[1, 2, 3, ..., n-1, n])
 * 最后一个相等是因为1和n只能选一个或者都不选。
 * 因此只需要讨论1和2即可
 *
 * @author 烛影鸾书
 * @date 2020/5/29
 * @copyright© 2020
 */
public class P213_ThievesStealThings2 {

    public int rob(int[] nums) {
        if (nums.length < 2) {
            return nums.length == 0 ? 0 : nums[0];
        }

        return Math.max(stealMoney(nums, 0, nums.length-1), stealMoney(nums, 1, nums.length));

    }

    private int stealMoney(int[] nums, int start, int end) {
        if (end - start == 1) {
            return nums[start];
        }

        int first = nums[start];
        int second = Math.max(nums[start], nums[start + 1]);
        int temp = 0;
        int i = start + 2;
        while (i < end) {
            temp = Math.max(first + nums[i], second);
            i++;

            first = second;
            second = temp;
        }
        return second;
    }

    public static void main(String[] args) {
        P213_ThievesStealThings2 p213 = new P213_ThievesStealThings2();

        int[] a = new int[]{2, 3, 2};
        System.out.println(p213.rob(a));
        int[] b = new int[]{2, 7, 9, 3, 1};
        System.out.println(p213.rob(b));
        int[] c = new int[]{1, 1, 9, 1, 1, 9};
        System.out.println(p213.rob(c));
    }
}
