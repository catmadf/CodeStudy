package cn.madf.左神牛客网算法课.堆;

import cn.madf.SmallSum.TestDetector;

import java.util.Arrays;

/**
 * @author 烛影鸾书
 * @date 2020/4/11
 * @copyright© 2020
 */
public class BigRootHeap {

    private int[] array;
    private int capacity;
    private int heapSize;



    public BigRootHeap(int[] array) {
        this.capacity = array.length;
        this.array = new int[this.capacity];
        System.arraycopy(array, 0, this.array, 0, capacity);

        this.heapSize = 0;
        for (int i = 0; i < this.array.length; i++) {
            heapInsert(this.array, i);
            this.heapSize++;
        }
    }

    private void heapInsert(int[] arr, int index) {
        /* 考虑一个0~i的大根堆，插入一个索引为index数到大根堆时应该与这个数的父节点compare and swap  */
        /* 父节点索引为(index-1)/2 */
        while (this.array[index] > this.array[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private void ensureCapacity() {
        if (heapSize >= capacity) {
            /* 扩容 1.5倍 */
            capacity = capacity + capacity >> 1;
            int[] newArray = new int[capacity];
        }
    }

    public int[] getArray() {
        return array;
    }

    public int getHeapSize() {
        return heapSize;
    }

    public static void main(String[] args) {
        int[] arr = TestDetector.generateIntArray(25, 30);
        System.out.println(Arrays.toString(arr));

        BigRootHeap bigRootHeap = new BigRootHeap(new int[]{4, 6, 1, 2, 5, 7});
        System.out.println(Arrays.toString(bigRootHeap.getArray()));
    }
}
