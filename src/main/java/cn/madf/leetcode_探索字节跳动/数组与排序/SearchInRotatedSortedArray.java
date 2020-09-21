package cn.madf.leetcode_探索字节跳动.数组与排序;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * <p>
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * <p>
 * 你可以假设数组中不存在重复的元素。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 *
 * @author 烛影鸾书
 * @date 2020/8/15 15:06
 * @copyright© 2020
 */
public class SearchInRotatedSortedArray {
    public int searchInRotatedSortedArray(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        /* 二分查找找到“断点” */
        int left = 0;
        int right = nums.length - 1;
        while (right - left > 1) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] >= nums[left]) {
                left = mid;
            } else if (nums[mid] <= nums[right]) {
                right = mid;
            }
        }

        if (nums[0] <= target && target <= nums[left]) {
            return binarySearch(nums, target, 0, left);
        } else if (left < nums.length - 1 && nums[left + 1] <= target && target <= nums[nums.length - 1]) {
            return binarySearch(nums, target, left + 1, nums.length - 1);
        } else {
            return -1;
        }

    }

    private int binarySearch(int[] arr, int target, int left, int right) {
        if (right < left) {
            return -1;
        }
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (arr[mid] > target) {
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1};
        SearchInRotatedSortedArray test = new SearchInRotatedSortedArray();
        System.out.println(test.searchInRotatedSortedArray(arr, 0));
    }
}
