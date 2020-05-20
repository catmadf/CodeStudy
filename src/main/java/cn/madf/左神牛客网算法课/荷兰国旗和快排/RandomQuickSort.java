package cn.madf.左神牛客网算法课.荷兰国旗和快排;

import cn.madf.SmallSum.TestDetector;

import java.util.Arrays;

/**
 * @author 烛影鸾书
 * @date 2020/4/9
 * @copyright© 2020
 */
public class RandomQuickSort {

    public static void solution(int[] arr) {
        randomQuickSort(arr, 0, arr.length);
    }

    private static void randomQuickSort(int[] arr, int l, int r) {
        if (l < r) {
            int[] p = partition(arr, l, r);  // 额外的空间，一共logN层，因此空间复杂度为logN
            randomQuickSort(arr, l, p[0]+1);
            randomQuickSort(arr, p[1], r);
        }
    }

    private static int[] partition(int[] arr, int l, int r) {
        int less = l - 1;
        int more = r;
        int num = arr[l + (int) (Math.random() * (r - l))];  // 随机选取的一个数
        while (l < more) {
            if (arr[l] < num) {
                swap(arr, ++less, l++);
            } else if (arr[l] > num) {
                swap(arr, l, --more);
            } else {
                l++;
            }
        }
        return new int[]{less, more};
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = TestDetector.generateIntArray(25, 30);
        System.out.println("排序前: "+Arrays.toString(arr));
        solution(arr);
        System.out.println("排序后: "+Arrays.toString(arr));
    }
}
