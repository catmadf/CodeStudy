package cn.madf.左神牛客网算法课.荷兰国旗和快排;

import java.util.Arrays;

/**
 * 给定一个num将一个数组中小于num的放左边，大于num的放右边
 * 要求空间O（1） 时间O（N）
 *
 * @author 烛影鸾书
 * @date 2020/4/8
 * @copyright© 2020
 */
public class TwoPartition {

    public static void solution(int[] arr, int num) {
        if (arr == null || arr.length < 2) {
            return;
        }
        /* 设立一个"指针"p，从-1开始，使[0, p]位置的数均比num小 */
        int p = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < num) {
                p++;
                int temp = arr[p];
                arr[p] = arr[i];
                arr[i] = temp;
            }
        }
    }

    public static void solutionA(int[] arr, int num) {
        /* 小的在左，大的在右，等于在中间 */
        int l = -1, r = arr.length;
        int i = 0;
        while (i < r) {
            if (arr[i] < num) {
                swap(arr, ++l, i++);
            } else if (arr[i] > num){
                swap(arr, i, --r);
            } else {
                i++;
            }
        }
    }

    private static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 2, 6, 8, 12, 3, 4, 56, 9};
        int num = 12;
        solutionA(arr, num);
        System.out.println(Arrays.toString(arr));
    }
}
