package cn.madf.剑指offer;

import com.sun.media.sound.ModelStandardIndexedDirector;

import javax.net.ssl.CertPathTrustManagerParameters;
import java.util.Arrays;

/**
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,5,6,4]
 * 输出: 5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 提时：归并排序
 *
 * @author 烛影鸾书
 * @date 2020/7/18
 * @copyright© 2020
 */
public class Offer51_ReversePairs {
    public int reversePairs(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int[] tmp = Arrays.copyOf(nums, nums.length);
        return splitProcess(nums, left, right, tmp);
    }

    private static int splitProcess(int[] nums, int left, int right, int[] tmp) {
        if (left >= right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        int leftCount = splitProcess(nums, left, mid, tmp);
        int rightCount = splitProcess(nums, mid + 1, right, tmp);
        int acrossCount = mergeCount(nums, left, right, tmp);
        return leftCount + rightCount + acrossCount;
    }

    private static int mergeCount(int[] nums, int left, int right, int[] tmp) {
        if (left >= right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        System.arraycopy(nums, left, tmp, left, right - left + 1);
        int i = left;
        int j = mid + 1;
        int count = 0;
        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {
                nums[k] = tmp[j++];
            } else if (j == right + 1) {
                nums[k] = tmp[i++];
            } else if (tmp[i] > tmp[j]) {
                nums[k] = tmp[j++];    // 该循环 i 不会变， i...mid的所有数都会和j构成一个逆序对
                count += mid + 1 - i;
            } else {
                nums[k] = tmp[i++];
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Offer51_ReversePairs offer51 = new Offer51_ReversePairs();
        int[] nums = new int[]{7, 5, 6, 4};
        System.out.println(offer51.reversePairs(nums));
    }

}
