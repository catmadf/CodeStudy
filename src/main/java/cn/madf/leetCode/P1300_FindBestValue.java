package cn.madf.leetCode;

import java.util.Arrays;

/**
 * 给你一个整数数组 arr 和一个目标值 target ，请你返回一个整数 value ，使得将数组中所有大于 value 的值变成 value 后，数组的和最接近  target （最接近表示两者之差的绝对值最小）。
 * 如果有多种使得和最接近 target 的方案，请你返回这些整数中的最小值。
 * 请注意，答案不一定是 arr 中的数字。
 * <p>
 * 示例 1：
 * 输入：arr = [4,9,3], target = 10
 * 输出：3
 * 解释：当选择 value 为 3 时，数组会变成 [3, 3, 3]，和为 9 ，这是最接近 target 的方案。
 * <p>
 * 示例 2：
 * 输入：arr = [2,3,5], target = 10
 * 输出：5
 * <p>
 * 示例 3：
 * 输入：arr = [60864,25176,27249,21296,20204], target = 56803
 * 输出：11361
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 10^4
 * 1 <= arr[i], target <= 10^5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-mutated-array-closest-to-target
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 首先，排序
 * 然后，我们在还没遍历数组中任何一个数时可以初步假设所有数比都比最终value大，那么value就是 num =  target/arr.length
 * 我们可以通过遍历，将对逼近target贡献最小的数踢出，target相应也缩小范围。
 * 遍历第一个数：如果arr[0]比num小或相等，继续遍历第二个数，num = (target-arr[0])/(arr.length-1)
 * .............如果arr[0]比num大，那么最后取的数不是num就是num+1（注意：num是整除得到的），取能保证使得后边的和与target差绝对值最小即可
 *
 * @author 烛影鸾书
 * @date 2020/6/15
 * @copyright© 2020
 */
public class P1300_FindBestValue {

    public int findBestValue(int[] arr, int target) {
        if (arr == null) {
            return 0;
        }

        Arrays.sort(arr);

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            int newTarget = target - sum;
            int newLen = arr.length - i;
            int t = newTarget / newLen;
            if (arr[i] > t) {
                return Math.abs(newTarget - t * newLen) <= Math.abs(newTarget - (t + 1) * newLen) ? t : t + 1;
            }
            sum += arr[i];
        }

        return arr[arr.length - 1];
    }
}
