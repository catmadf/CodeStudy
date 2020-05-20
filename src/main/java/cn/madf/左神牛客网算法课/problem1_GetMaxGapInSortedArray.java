package cn.madf.左神牛客网算法课;

import cn.madf.SmallSum.TestDetector;

import java.util.Arrays;

/**
 * 给一个数组，求该数组排好序后相邻两数差值的最大值，要求使用非比较的排序，要求时间复杂度为O(N)
 * 例如：
 * input: [1, 8, 9, 5, 4, 6, 7]
 * output: 3
 * 提示：应用桶排序思想，将N个数用N+1个桶来装
 *
 * @author 烛影鸾书
 * @date 2020/5/13
 * @copyright© 2020
 */
public class problem1_GetMaxGapInSortedArray {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int[] array = TestDetector.generateIntArray(10000, 999);
            int res1 = solution(array);
            int res2 = detectorSol(array);
            if (res1 != res2) {
                System.out.println(Arrays.toString(array));
                System.out.println(res1 + "  " + res2);
            }
        }
    }

    public static int solution(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }
        if (min == max) return 0;
        /* 桶中记录三个信息：最小值，最大值，是否有数进来过 */
        int[] bucketMin = new int[arr.length + 1];
        int[] bucketMax = new int[arr.length + 1];
        boolean[] bucketBool = new boolean[arr.length + 1];

        for (int i : arr) {
            int ix = (i - min) * arr.length / (max - min);
            bucketMin[ix] = bucketBool[ix] ? Math.min(bucketMin[ix], i) : i;
            bucketMax[ix] = bucketBool[ix] ? Math.max(bucketMax[ix], i) : i;
            bucketBool[ix] = true;
        }
        /* 对桶遍历，每遇到一个非空桶，就计算该桶最小值与前一个非空桶最大值的差值 */
        int maxGap = 0;
        int lastMax = bucketMax[0];

        for (int i = 1; i < arr.length + 1; i++) {
            if (bucketBool[i]) {
                maxGap = Math.max(maxGap, bucketMin[i] - lastMax);
                lastMax = bucketMax[i];
            }
        }
        return maxGap;
    }

    public static int detectorSol(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        Arrays.sort(arr);
        int maxGap = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            maxGap = Math.max(maxGap, arr[i + 1] - arr[i]);
        }
        return maxGap;
    }
}
