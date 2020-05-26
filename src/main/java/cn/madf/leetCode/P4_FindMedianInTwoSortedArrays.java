package cn.madf.leetCode;

/**
 * tag: array, binary search
 * <p>
 * 给定两个排好序的数组，找出两个数组所有数的中位数，两个数组不会同时为空.要求时间复杂度为O(log(m+n))
 * <p>
 * 解法：中位数：一个排好序的序列中最中间的数，如果序列个数为偶数则取最中间两数的平均值
 * 也就是说中位数左边的数比中位数小，右边的数比中位数大，并且左边数个数和右边数个数一样。
 * 对于两个排好序的数组，且要求时间复杂度O(log(m+n))，很明显归并思想行不通，有个log，基本就和二分扯上关系了
 * 找中位数不一定非要排序，我们可以找到两个数组所有数中第(m+n)/2或者(m+n)/2+1小的数，我们将其命名为k。
 * 由于有两个数组，我们没法确定这个数在哪个数组，因此可以在每个数组上找各自第k/2 - 1小的数，每个数组被分位两部分
 * A[0...k/2-1]和A[k/2...n]、B[0...k/2-1]和B[k/2...m]
 * 将每个数组分成两部分，进行分类讨论，每次可以排除掉A或B中前半部分数组(k/2-1+k/2-1 = k-2, 所以第k小的元素不在这部分即将被排除的数组中)。
 * <p>
 * 注，虽然偶数情况可以一次找出k和k+1的数，但没有意义，这样会使得代码结构很乱。
 * <p>
 * 毫无意义的优化不必进行，刷题不仅仅需要注意时间复杂度、空间复杂度，还需要时刻注意程序结构，保证逻辑清晰、接口完善
 * <p>
 * 本题最优解 ：
 *
 * @author 烛影鸾书
 * @date 2020/5/24
 * @copyright© 2020
 */
public class P4_FindMedianInTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) {
            return nums2.length % 2 == 0 ? (nums2[nums2.length / 2 - 1] + nums2[nums2.length / 2]) / 2.0 : nums2[nums2.length / 2];
        }
        if (nums2 == null || nums2.length == 0) {
            return nums1.length % 2 == 0 ? (nums1[nums1.length / 2 - 1] + nums1[nums1.length / 2]) / 2.0 : nums1[nums1.length / 2];
        }
        int len1 = nums1.length, len2 = nums2.length;

        int k;

        /* 对于奇数情况 */
        if ((len1 + len2) % 2 == 1) {
            k = (len1 + len2) / 2 + 1;
            return getKthSmallestNum(nums1, nums2, k);
        } else {
            k = (len1 + len2) / 2;
            return (getKthSmallestNum(nums1, nums2, k) + getKthSmallestNum(nums1, nums2, k + 1)) / 2.0;
        }

    }

    /**
     * 在两个数中找到第k小的数
     *
     * @param nums1 array
     * @param nums2 array
     * @param k     int
     * @return int
     */
    private int getKthSmallestNum(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length, len2 = nums2.length;
        int offset1 = 0, offset2 = 0;

        while (true) {

            if (offset1 == len1) {
                return nums2[offset2 + k - 1];
            }
            if (offset2 == len2) {
                return nums1[offset1 + k - 1];
            }

            if (k == 1) {
                return Math.min(nums1[offset1], nums2[offset2]);
            }

            int index1 = Math.min(offset1 + (k >> 1) - 1, len1 - 1);
            int index2 = Math.min(offset2 + (k >> 1) - 1, len2 - 1);

            /* 将两个数组[offset, index]的数比较，抛弃其中一组小的 */
            if (nums1[index1] <= nums2[index2]) {
                k = k - (index1 - offset1 + 1);
                offset1 = index1 + 1;
            } else {
                k = k - (index2 - offset2 + 1);
                offset2 = index2 + 1;
            }
        }
    }

    public static void main(String[] args) {
        P4_FindMedianInTwoSortedArrays t = new P4_FindMedianInTwoSortedArrays();
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{1, 2, 3};
        System.out.println(t.findMedianSortedArrays(nums1, nums2));
    }


}
