package cn.madf.leetCode;

import org.ejml.alg.dense.mult.MatrixMatrixMult;

/**
 * 给定一个整数矩阵，找出最长递增路径的长度。
 * <p>
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums =
 * [
 * [9,9,4],
 * [6,6,8],
 * [2,1,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径为 [1, 2, 6, 9]。
 * 示例 2:
 * <p>
 * 输入: nums =
 * [
 * [3,4,5],
 * [3,2,6],
 * [2,2,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 烛影鸾书
 * @date 2020/7/26 20:49
 * @copyright© 2020
 */
public class P329_LongestIncreasePathInMatrix {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        /* 用来存储已搜索的路径的最大值 */
        int[][] path = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                deepSearchPath(matrix, i, j, path);
            }
        }
        int tempMax = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                tempMax = Math.max(path[i][j], tempMax);
            }
        }
        return tempMax;
    }

    private int deepSearchPath(int[][] matrix, int i, int j, int[][] path) {
        System.out.println(i + "  " + j);
        if (path[i][j] != 0) {
            return path[i][j];
        }
        int up, down, left, right;
        if (i - 1 > -1 && matrix[i][j] < matrix[i - 1][j]) {
            up = deepSearchPath(matrix, i - 1, j, path) + 1;
        } else {
            up = 1;
        }

        if (i + 1 < matrix.length && matrix[i][j] < matrix[i + 1][j]) {
            down = deepSearchPath(matrix, i + 1, j, path) + 1;
        } else {
            down = 1;
        }

        if (j - 1 > -1 && matrix[i][j] < matrix[i][j - 1]) {
            left = deepSearchPath(matrix, i, j - 1, path) + 1;
        } else {
            left = 1;
        }

        if (j + 1 < matrix[0].length && matrix[i][j] < matrix[i][j + 1]) {
            right = deepSearchPath(matrix, i, j + 1, path) + 1;
        } else {
            right = 1;
        }
        int curPath = Math.max(Math.max(up, down), Math.max(left, right));
        path[i][j] = curPath;
        return curPath;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{3, 4, 5}, {3, 2, 6}, {2, 2, 1}};
        P329_LongestIncreasePathInMatrix p329 = new P329_LongestIncreasePathInMatrix();
        int longestPath = p329.longestIncreasingPath(matrix);
        System.out.println(longestPath);
    }
}
