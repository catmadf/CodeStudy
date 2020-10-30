package cn.madf.basicKnowledge.SmallSum;

/**
 * 小和问题
 * 一个数组的小和等于该数组内任意一个数b左边比它小的数a的累加和，数a可以重复。
 * 例如：1， 2， 5， 4， 7 的小和为 1 +（1 + 2）+ (1 + 2) + (1 + 2 + 5 + 4) = 19
 *
 * @author 烛影鸾书
 * @date 2020/4/8
 * @copyright© 2020
 */
public class SmallSum {

    /**
     * 时间复杂度O(N^2)解法
     *
     * @param arr 数组
     */
    public static int solutionA(int[] arr) {

        if (arr == null || arr.length < 2) {
            return 0;
        }

        int smallSum = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    smallSum += arr[j];
                }
            }
        }
        return smallSum;
    }

    /**
     * 时间复杂度O(N*logN)解法
     * 利用归并排序思想
     *
     * @param arr 数组
     */
    public static int solutionB(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return mergeSmallSum(arr, 0, arr.length - 1);
    }

    private static int mergeSmallSum(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }

        int mid = (left + right) / 2; // 不安全  left+right可能会溢出，稳妥写法是left+(right-left)/2
        int sum1 = mergeSmallSum(arr, left, mid);
        int sum2 = mergeSmallSum(arr, mid + 1, right);

        int sum3 = mergeProcess(arr, left, right);
        return sum1 + sum2 + sum3;
    }

    private static int mergeProcess(int[] arr, int left, int right) {
        int[] temp = new int[right - left + 1];
        int sum = 0;
        int mid = (left + right) / 2;
        int p1 = left, p2 = mid + 1, i = 0;
        while (p1 <= mid && p2 <= right) {
            if (arr[p1] < arr[p2]) {
                sum = sum + arr[p1] * (right - p2 + 1);
            }
            temp[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        /* p1或p2必有一个越界 */
        while (p1 <= mid) {
            temp[i++] = arr[p1++];
        }
        while (p2 <= right) {
            temp[i++] = arr[p2++];
        }

        for (int j = left; j <= right; j++) {
            arr[j] = temp[j - left];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 5, 4, 7};
        System.out.println(solutionA(arr));
        System.out.println(solutionB(arr));
    }
}
