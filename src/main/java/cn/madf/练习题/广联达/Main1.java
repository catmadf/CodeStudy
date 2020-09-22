package cn.madf.练习题.广联达;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author 烛影鸾书
 * @date 2020/9/16 21:32
 * @copyright© 2020
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int x = sc.nextInt();
        Queue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            heap.offer(sc.nextInt());
        }

        int i = 0;
        while (i < m && !heap.isEmpty()) {
            heap.offer(heap.poll() + x);
            i++;
        }
        System.out.println(heap.peek());
    }

}
