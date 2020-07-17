package cn.madf.左神牛客网算法课;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1.打印矩阵
 * 1,  2,  3,  4,
 * 5,  6,  7,  8,
 * 9, 10, 11, 12,   ----> 按顺序打印：1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10
 * 13, 14, 15, 16,
 * <p>
 * 2.顺时针90°旋转正方形矩阵
 * 1, 2, 3            7, 4, 1
 * 4, 5, 6     ---->  8, 5, 2
 * 7, 8, 9            9, 6, 3
 *
 * @author 烛影鸾书
 * @date 2020/5/14
 * @copyright© 2020
 */
public class problem5_PrintAndRotateMatrix {
    public static void solution1(int[][] matrix) {
        if (matrix.length == 0) {
            return;
        }
        List<Integer> arrList = new ArrayList<>(matrix.length*matrix[0].length);
        int tR = 0;
        int tC = 0;
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;
        while (dR >= tR && dC >= tC) {
            printEdge(matrix, tR++, tC++, dR--, dC--, arrList);
        }
        System.out.println();
        int[] res = new int[arrList.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = arrList.get(i);
        }
    }

    public static void printEdge(int[][] matrix, int tR, int tC, int dR, int dC, List<Integer> list) {
        int i = tR + 1;
        int j = tC;
        while (j <= dC) {
            System.out.print(matrix[tR][j++] + " ");
            list.add(matrix[tR][j++]);
        }
        while (i <= dR) {
            System.out.print(matrix[i++][dC] + " ");
            list.add(matrix[i++][dC]);
        }
        i = dR - 1;
        j = dC - 1;
        while (j >= tC && tR != dR) {
            System.out.print(matrix[dR][j--] + " ");
            list.add(matrix[dR][j--]);
        }
        while (i > tR && tC != dC) {
            System.out.print(matrix[i--][tC] + " ");
            list.add(matrix[i--][tC]);
        }
    }

    public static void solution2(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        assert matrix.length == matrix[0].length;

        int tR = 0;
        int tC = 0;
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;
        while (dR > tR && dC > tC) {
            rotate(matrix, tR++, tC++, dR--, dC--);
        }
    }

    public static void rotate(int[][] matrix, int tR, int tC, int dR, int dC) {
        int n = dC - tC;
        int i = 0;
        while (i < n) {
            /* 交换四边上相对应的四个数 */
            int tmp = matrix[tR][tC + i];
            matrix[tR][tC + i] = matrix[dR - i][tC];
            matrix[dR - i][tC] = matrix[dR][dC - i];
            matrix[dR][dC - i] = matrix[tR + i][dC];
            matrix[tR + i][dC] = tmp;
            i++;
        }
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        solution1(a);
        solution2(a);
        System.out.println(Arrays.deepToString(a));
    }
}
