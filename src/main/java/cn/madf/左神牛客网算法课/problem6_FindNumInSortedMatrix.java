package cn.madf.左神牛客网算法课;

/**
 * 给定一个n*m的矩阵，每一行每一列都是排好序的，给定一个数num，返回num是否在该矩阵中，要求时间复杂度O(N+M)，额外空间复杂度O(1)
 *
 * @author 烛影鸾书
 * @date 2020/5/16
 * @copyright© 2020
 */
public class problem6_FindNumInSortedMatrix {
    public static boolean solution(int[][] m, int num) {
        if (m.length == 0 || m[0].length == 0) {
            return false;
        }
        if (num > m[m.length - 1][m[0].length - 1]) {
            return false;
        }
        int row = m.length;
        int col = m[0].length;
        /* 取出右上角的数 */
        int i = 0;
        int j = col - 1;
        while (i < row && j >= 0) {
            if (m[i][j] == num) {
                return true;
            } else if (m[i][j] > num) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{{1, 2, 3, 6}, {4, 5, 6, 8}, {7, 8, 9, 12}};
        System.out.println(solution(a, 7));
    }
}
