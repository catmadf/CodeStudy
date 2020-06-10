package cn.madf.左神牛客网算法课;

import java.util.LinkedList;

/**
 * 给定一个数组，请返回所有最大值和最小值差小于等于指定数num的子数组个数
 * <p>
 * 移动窗口
 * 双端队列保存窗口最大值或最小值
 * <p>
 * 提示：如果一个子数组满足要求，那么内部任何一个子数组都达标；
 * ......如果一个子数组不满足要求，那么再扩大数组也不可能使任何子数组满足要求
 *
 * @author 烛影鸾书
 * @date 2020/6/8
 * @copyright© 2020
 */
public class problem23_CountSubArrayWithMinGap {

    public int solution(int[] arr, int num) {
        if (arr == null) {
            return 0;
        }

        int res = 0;
        int lp = 0;
        int rp = 0;
        LinkedList<Integer> maxList = new LinkedList<>();  // 保存当前窗口的最大值
        LinkedList<Integer> minList = new LinkedList<>();  // 保存当前窗口的最小值
        while (lp < arr.length) {
            while (rp < arr.length) {
                /* 维护两个双端链表 */
                while (!maxList.isEmpty() && arr[maxList.peekLast()] <= arr[rp]) {
                    maxList.pollLast();
                }
                maxList.addLast(rp);
                while (!minList.isEmpty() && arr[minList.peekLast()] >= arr[rp]) {
                    minList.pollLast();
                }
                minList.addLast(rp);
                if (arr[maxList.getFirst()] - arr[minList.getFirst()] > num) {
                    break;
                }
                rp++;
            }
            /* 判断双端链表下标过期没有 */
            if (maxList.peekFirst() == lp) {
                maxList.pollFirst();
            }
            if (minList.peekFirst() == lp) {
                minList.pollFirst();
            }

            res += rp - lp;  // (lp, ..., rp-1)位置的所有子数组都满足要求
            lp++;
        }
        return res;
    }

    public static void main(String[] args) {
        problem23_CountSubArrayWithMinGap p23 = new problem23_CountSubArrayWithMinGap();
        int[] a = new int[]{1, 2, 3, 5, 3};
        System.out.println(p23.solution(a, 3));
    }

}
