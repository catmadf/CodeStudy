package cn.madf.leetCode;

import javax.net.ssl.CertPathTrustManagerParameters;

/**
 * tag：array
 * <p>
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <p>
 * 示例 1:
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * <p>
 * 示例 2:
 * 输入: [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * <p>
 * 题解：动态规划
 * <p>
 * 优化：money数组中前面很大一部分数字都是没必要的，因此可以优化存储使空间复杂度达到O(1)；
 *
 * @author 烛影鸾书
 * @date 2020/5/29
 * @copyright© 2020
 */
public class P198_ThievesStealThings {

    public int rob(int[] nums) {
        if (nums.length < 2) {
            return nums.length == 0 ? 0 : nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        /* money[i]代表走到第i家后(不确定偷不偷)，偷到的金额数 */
        int[] money = new int[nums.length];
        money[0] = nums[0];
        money[1] = Math.max(nums[0], nums[1]);
        int i = 2;
        while (i < nums.length) {
            money[i] = Math.max(money[i - 1], money[i - 2] + nums[i]);
            i++;
        }

        return getMaxMoney(money);

    }

    private int getMaxMoney(int[] money) {

        int n = money.length;
        int max = Integer.MIN_VALUE;
        for (int i = n - 1; i > n - 4; i--) {
            max = Math.max(money[i], max);
        }
        return max;
    }

    /* 空间上优化 */
    public int robOptimize(int[] nums) {
        if (nums.length < 2) {
            return nums.length == 0 ? 0 : nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int first = nums[0];
        int second = Math.max(nums[0], nums[1]);
        int temp = 0;
        int i = 2;
        while (i < nums.length) {
            temp = Math.max(first+nums[i], second);
            i++;

            first = second;
            second = temp;
        }
        return temp;
    }

    public static void main(String[] args) {
        P198_ThievesStealThings p198 = new P198_ThievesStealThings();

        int[] a = new int[]{1, 1, 3, 1};
        System.out.println(p198.rob(a));
        int[] b = new int[]{2, 7, 9, 3, 1};
        System.out.println(p198.rob(b));
        int[] c = new int[]{1, 1, 9, 1, 1, 9};
        System.out.println(p198.rob(c));
    }
}
