package cn.madf.练习题.VMware;

import javafx.beans.binding.SetBinding;

import java.util.*;

/**
 * @author 烛影鸾书
 * @date 2020/9/21 21:17
 * @copyright© 2020
 */
public class Main3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        boolean[][] matrix = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            matrix[a - 1][i] = true;
            matrix[b - 1][i] = true;
        }
        int count = 0;
        for (int i = 0; i < n - 1; i++) {
            if (isQualified(matrix, i, k)) {
                count += (n - i - 1);
                continue;
            }
            for (int j = i + 1; j < n; j++) {
                if (isQualified(matrix, i, j, k)) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private static boolean isQualified(boolean[][] matrix, int i, int j, int m) {
        boolean[] a = matrix[i];
        boolean[] b = matrix[j];
        int c = 0;
        for (int k = 0; k < matrix[0].length; k++) {
            if (a[k] || b[k]) {
                c++;
            }
            if (c >= m) {
                return true;
            }
        }
        return false;
    }

    private static boolean isQualified(boolean[][] matrix, int i, int m) {
        boolean[] a = matrix[i];
        int c = 0;
        for (int k = 0; k < matrix[0].length; k++) {
            if (a[k]) {
                c++;
            }
            if (c >= m) {
                return true;
            }
        }
        return false;
    }

}
