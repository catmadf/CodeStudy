package cn.madf.左神牛客网算法课;

import sun.plugin.dom.exception.NoModificationAllowedException;


/**
 * 切一根长度为n的金条，需要消耗长度为n的铜块。
 * 先有一根金条要分给m个人，问如何分使铜块消耗最少
 * input: [1, 5, 4, 3, 2], 15
 * output: 33
 * <p>
 * 解法：贪心，哈弗曼编码
 * <p>
 * 可以建一个小根堆，每次pop出两个最小的数，加起来的和insert到小根堆中，重复进行
 *
 * ...注：java中优先级队列 PriorityQueue 就是堆
 *
 * @author 烛影鸾书
 * @date 2020/5/30
 * @copyright© 2020
 */
public class problem18_CutGoldBars {

    private static class SmallHeap {

        private int[] array;
        private int heapSize;

        public SmallHeap(int[] array) {
            this.array = new int[array.length];
            System.arraycopy(array, 0, this.array, 0, array.length);
            this.heapSize = 0;

            for (int i = 0; i < array.length; i++) {
                heapInsert(this.array, i);
            }
        }

        public void heapInsert(int[] array, int i) {
            while (array[i] < array[(i - 1) / 2]) {
                swap(array, i, (i - 1) / 2);
                i = (i - 1) / 2;
            }
            heapSize++;
        }

        public void heapInsert(int i) {
            while (array[i] < array[(i - 1) / 2]) {
                swap(array, i, (i - 1) / 2);
                i = (i - 1) / 2;
            }
            heapSize++;
        }

        public int heapPop() {
            int res = array[0];
            swap(array, 0, --heapSize);
            heapify(array, 0, heapSize);
            return res;
        }

        private void heapify(int[] array, int index, int heapSize) {
            int left = (index << 1) + 1;
            int right = left + 1;
            while (left < heapSize) {
                /* 比较左右子结点 */
                int smallest = right < heapSize && array[right] < array[left] ? right : left;
                /* 比较父节点 */
                smallest = array[index] < array[smallest] ? index : smallest;
                if (smallest == index) {
                    break;
                }
                swap(array, smallest, index);
                index = smallest;
                left = (index << 1) + 1;
            }
        }

        private void swap(int[] array, int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        public void modify(int i, int num) {
            if (i < heapSize) {
                throw new NoModificationAllowedException("the data of heap is not permited to modify");
            }
            this.array[i] = num;
        }

        public int size() {
            return heapSize;
        }
    }

    public int solution(int[] arr) {
        int res = 0;
        SmallHeap heap = new SmallHeap(arr);
        while (heap.size() > 1) {
            int small1 = heap.heapPop();
            int small2 = heap.heapPop();
            int larger = small1 + small2;
            res += larger;
            heap.modify(heap.size(), larger);
            heap.heapInsert(heap.size());
        }
        return res;
    }

    public static void main(String[] args) {
        problem18_CutGoldBars p18 = new problem18_CutGoldBars();

        int[] arr = new int[]{1, 5, 4, 3};

        System.out.println(p18.solution(arr));
    }


}
