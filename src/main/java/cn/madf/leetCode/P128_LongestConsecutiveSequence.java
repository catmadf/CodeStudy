package cn.madf.leetCode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * 要求算法的时间复杂度为 O(n)。
 * <p>
 * 示例:
 * <p>
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-consecutive-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解法： 可以用hashset来存放一个数，遍历一个数时，我们一直去查后一个数是否存在即可。但是要保证O(N)复杂度，这就意味着重复操作需要做优化
 * ......否则就会是O(N^2)
 * <p>
 * leetcode里有个人说还可以用并查集来解
 *
 * @author 烛影鸾书
 * @date 2020/6/6
 * @copyright© 2020
 */
public class P128_LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int lcs = 0;
        for (Integer integer : set) {
            /* 如果integer-1包含在set内，说明integer在遍历到integer-1时已经做过统计了 */
            if (!set.contains(integer - 1)) {
                int num = integer;
                int ccs = 1;
                while (set.contains(num + 1)) {
                    num++;
                    ccs++;
                }
                lcs = Math.max(lcs, ccs);
            }
        }
        return lcs;
    }

    public static void main(String[] args) {
        int[] a = new int[]{2147483646, -2147483647, 0, 2, 2147483644, -2147483645, 2147483645};
        P128_LongestConsecutiveSequence p128 = new P128_LongestConsecutiveSequence();
        System.out.println(p128.longestConsecutive(a));
    }
}
