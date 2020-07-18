package cn.madf.leetCode;

/**
 * @author 烛影鸾书
 * @date 2020/7/18 14:18
 * @copyright© 2019
 */
public class P35_SearchInsert {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int insertIx = nums.length;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                insertIx = mid;
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return insertIx;
    }

    public static void main(String[] args) {
        P35_SearchInsert p35 = new P35_SearchInsert();
        System.out.println(p35.searchInsert(new int[]{1, 3, 5, 6}, 1));
    }

}
