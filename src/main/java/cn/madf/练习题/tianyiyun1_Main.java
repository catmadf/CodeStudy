package cn.madf.练习题;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author 烛影鸾书
 * @date 2020/9/9 21:00
 * @copyright© 2020
 */
public class tianyiyun1_Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(nthUglyNum(n));
    }

    private static long nthUglyNum(int n) {
        if (n <= 1) {
            return n;
        }
        PriorityQueue<Long> minNumHeap = new PriorityQueue<>();
        HashSet<Long> createdNums = new HashSet<>();
        long[] uglyNums = new long[n];
        int count = 0;
        minNumHeap.add(1L);
        createdNums.add(1L);
        while (count < n) {
            long cthUglyNum = minNumHeap.poll();
            uglyNums[count++] = cthUglyNum;
            long newNum;
            if (!createdNums.contains(newNum = cthUglyNum * 2)) {
                createdNums.add(newNum);
                minNumHeap.add(newNum);
            }
            if (!createdNums.contains(newNum = cthUglyNum * 3)) {
                createdNums.add(newNum);
                minNumHeap.add(newNum);
            }
            if (!createdNums.contains(newNum = cthUglyNum * 5)) {
                createdNums.add(newNum);
                minNumHeap.add(newNum);
            }
        }
        return uglyNums[n-1];
    }

}
