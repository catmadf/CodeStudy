package cn.madf.leetCode;

/**
 * tag: array, binary search, double pointer
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
 * 假设只有一个重复的整数，找出这个重复的数。
 * 1.不能更改原数组（假设数组是只读的）。
 * 2.只能使用额外的 O(1) 的空间。
 * 3.时间复杂度小于 O(n2) 。
 * 4.数组中只有一个重复的数字，但它可能不止重复出现一次。
 *
 * 解法：不能改变原数组且额外空间复杂度为O(1)，就意味着排序不可行。
 *      [2, 3, 4, 1, 5, 2, 6]
 *      [2, nums[2], nums[4] ...]
 *      2 -> 4 -> 5 -> 2
 *      如果将其看成链表，实际上这里就已经成环了，接下来只需要判断链表入环点即可
 *
 * @author 烛影鸾书
 * @date 2020/5/26
 * @copyright© 2020
 */
public class P287_FindDuplicate {

    public int findDuplicate(int[] nums) {
        int fast = 0, slow = 0;
        while (true) {
            fast = nums[nums[fast]];
            slow = nums[slow];
            if (fast == slow) {
                break;
            }
        }
        fast = 0;
        while (true) {
            fast = nums[fast];
            slow = nums[slow];
            if (fast == slow) {
                break;
            }
        }
        return fast;
    }
}
