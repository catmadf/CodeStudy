package cn.madf.leetcode_探索字节跳动.数组与排序;

/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 * <p>
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 * @author 烛影鸾书
 * @date 2020/8/15 17:16
 * @copyright© 2020
 */
public class TheKthLargestElementInArray {
    public int findKthLargest(int[] nums, int k) {
        return find(nums, 0, nums.length - 1, k);
    }

    private int find(int[] nums, int start, int stop, int k) {
        int index = partition(nums, start, stop);
        if (index == k - 1) {
            return nums[index];
        } else {
            return index < k - 1 ? find(nums, index + 1, stop, k) : find(nums, start, index - 1, k);
        }
    }

    private int partition(int[] nums, int start, int stop) {
        int lp = start - 1;  // 左边放比num大的数
        int rp = stop + 1;  // 右边放比num小的数
        int target = nums[start];
        int i = start;
        while (i < rp) {
            if (nums[i] > target) {
                swap(nums, i++, ++lp);
            } else if (nums[i] < target) {
                swap(nums, i, --rp);
            } else {
                i++;
            }
        }
        return lp + 1;   // 返回num在的位置
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 1, 5, 6, 4};
        TheKthLargestElementInArray test = new TheKthLargestElementInArray();
        test.findKthLargest(arr, 2);
    }
}
