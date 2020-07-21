package cn.madf.字节跳动.今日头条;

import java.util.Arrays;

/**
 * 输入：power = n, arr = [...](约定arr的长度为 2^power ), reverse = [...] (约定reverse中的数均不大于n)
 * 示例: power = 2, arr = [1, 3, 5, 4], reverse = [1, 0, 2]
 * 按reverse中值顺序执行一系列操作：
 * 1 ：将arr按2^1个数为一组，每组组内进行逆序操作，完事后对整个arr（[3, 1, 4, 5]）统计逆序对个数为 1
 * 0 : 将arr按2^0个数为一组，每组组内进行逆序操作，完事后对整个arr（[3, 1, 4, 5]）统计逆序对个数为 1
 * 2 : 将arr按2^2个数为一组，每组组内进行逆序操作，完事后对整个arr（[5, 4, 1, 3]）统计逆序对个数为 5
 * 输出: [1, 1, 5]
 * 提示:
 * 1.  对于一个2^power长度的数组求其逆序对，可以先求按顺序2^1个数为一组(前后各一半划分)组内逆序对个数，
 * 再求2^2个数为一组(前后各一半划分)组内逆序对个数，....，最后求我2^power个数一组(前后各一半划分)组内逆序对个数，累加即可
 * 2^1个数为一组时：[1|3, 5|4]  54为一对逆序对，13为一对升序对
 * 2.  假设reverse中一个数为2^1时，经过逆序操作，它的2个一组的逆序对个数和升序对个数是经过操作前的升序对个数和逆序对个数，4个一组，8个一组等等不变
 *
 * @author 烛影鸾书
 * @date 2020/5/6
 * @copyright© 2020
 */
public class problem2 {
    public static void main(String[] args) {
        solution(3, new int[]{1, 3, 5, 4, 6, 2, 9, 7}, new int[]{1, 0, 2, 3});  // [4, 4, 14, 14]
        solution(2, new int[]{1, 3, 5, 4}, new int[]{1, 0, 2});  // [1, 1, 5]
    }

    public static void solution(int power, int[] arr, int[] reverse) {
        /* 先对arr预处理，计算出2^i个数为一组时的组内逆序对个数 */
        int[] arrCopy = new int[arr.length];
        System.arraycopy(arr, 0, arrCopy, 0, arr.length);
        int[] temp = new int[arr.length];
        /* 第i位表示以2^i个数为一组时组内逆(升)序对个数 */
        int[] descCnt = new int[power + 1];
        int[] ascCnt = new int[power + 1];
        /* 应用归并排序思想求逆序和升序对的个数 */
        reversePairs(arrCopy, 0, arrCopy.length - 1, temp, descCnt, power + 1);
        for (int i = 0; i < arr.length; i++) {
            arrCopy[arr.length - 1 - i] = arr[i];
        }
        reversePairs(arrCopy, 0, arrCopy.length - 1, temp, ascCnt, power + 1);

        int[] res = new int[reverse.length];
        for (int i = 0; i < reverse.length; i++) {
            int optNum = reverse[i];
            int cnt = 0;
            for (int j = 0; j <= optNum; j++) {
                swap(descCnt, ascCnt, j);
            }
            for (int j = 0; j < descCnt.length; j++) {
                cnt += descCnt[j];
            }
            res[i] = cnt;
        }

        System.out.println(Arrays.toString(res));

    }

    private static void reversePairs(int[] nums, int left, int right, int[] temp, int[] cnt, int layer) {
        if (left == right) {
            cnt[layer] += 0;
            return;
        }

        int mid = left + ((right - left) >> 1);

        reversePairs(nums, left, mid, temp, cnt, layer - 1);
        reversePairs(nums, mid + 1, right, temp, cnt, layer - 1);
        meargeAndCount(nums, left, right, temp, cnt, layer - 1);
    }

    private static void meargeAndCount(int[] nums, int left, int right, int[] temp, int[] cnt, int layer) {
        if (right + 1 - left >= 0) System.arraycopy(nums, left, temp, left, right + 1 - left);

        int mid = left + ((right - left) >> 1);
        int i = left;
        int j = mid + 1;
        int count = 0;
        for (int k = left; k <= right; k++) {
            /* i和j的越界判断 */
            if (i == mid + 1) {
                nums[k] = temp[j++];
            } else if (j == right + 1) {
                nums[k] = temp[i++];
            } else if (temp[i] <= temp[j]) {
                nums[k] = temp[i++];
            } else {
                nums[k] = temp[j++];
                /* 此处temp[i] > temp[j]，说明temp[i...mid]所有数均能和temp[j]构成逆序对*/
                count += mid + 1 - i;
            }
        }
        cnt[layer] += count;
    }

    private static void swap(int[] arr1, int[] arr2, int index) {
        int tmp = arr1[index];
        arr1[index] = arr2[index];
        arr2[index] = tmp;
    }
}
