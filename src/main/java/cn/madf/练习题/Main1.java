package cn.madf.练习题;

import jdk.nashorn.internal.ir.ReturnNode;

import java.util.*;

public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] arrIn = sc.nextLine().split(" ");
        int n = arrIn.length;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(arrIn[i]);
        }

    }

    private static int exchangeNeighbourBit(int num) {
        int res = 0xFFFFFFFF;
        int start = 3;
        int left = 2;
        int right = 1;
        for (int i = 0; i < 15; i++) {
            start = (start << (i * 2));
            left = (left << (i * 2));
            right = (right << (i * 2));

        }
        return 0;
    }
}