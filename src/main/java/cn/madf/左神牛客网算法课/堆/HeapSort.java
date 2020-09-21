package cn.madf.左神牛客网算法课.堆;

import java.util.Arrays;

/**
 * @author 烛影鸾书
 * @date 2020/4/11
 * @copyright© 2020
 */
public class HeapSort {

    public static void solution(int[] arr) {
        heapSort(arr);
    }

    private static void heapSort(int[] arr) {
        int heapSize = 0;
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
            heapSize++;
        }
        swap(arr, 0, --heapSize);
        while (heapSize != 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    private static void heapify(int[] arr, int index, int heapSize) {
        /* 维护index为根节点的子树是一个大根堆，与heapInsert不同，heapify从根往下遍历 */
        int left = (index << 1) + 1; // 左叶节点
        while (left < heapSize) {
            /* 比较左右叶子节点（也有可能不存在右叶子） */
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            /* 再和父节点比较 */
            largest = arr[index] > arr[largest] ? index : largest;
            /* tips：如果largest和index相等意味着不需要再维护了 */
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = (index << 1) + 1;
        }

    }

    private static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 6, 1, 2, 5, 7};
        solution(arr);
        System.out.println(Arrays.toString(arr));

        int[] a = new int[]{0, 3, 4};
        heapify(a, 0, 3);
        System.out.println(Arrays.toString(a));
    }
}
