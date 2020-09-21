package cn.madf.leetcode_探索字节跳动.数组与排序;

/**
 * 给定一个包含了一些 0 和 1 的非空二维数组 grid 。
 * <p>
 * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 * <p>
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,1,1,0,1,0,0,0,0,0,0,0,0],
 * [0,1,0,0,1,1,0,0,1,0,1,0,0],
 * [0,1,0,0,1,1,0,0,1,1,1,0,0],
 * [0,0,0,0,0,0,0,0,0,0,1,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。
 * <p>
 * 示例 2:
 * <p>
 * [[0,0,0,0,0,0,0,0]]
 * 对于上面这个给定的矩阵, 返回 0。
 * <p>
 * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
 *
 * @author 烛影鸾书
 * @date 2020/8/10 21:40
 * @copyright© 2020
 */
public class TheLargestAreaOfTheIsland {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                maxArea = Math.max(dfs(grid, i, j), maxArea);
            }
        }
        return maxArea;
    }

    private int dfs(int[][] matrix, int i, int j) {
        if (matrix[i][j] != 1) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        matrix[i][j] = -1;
        int up = 0, down = 0, left = 0, right = 0;
        if (i - 1 >= 0) {
            up = dfs(matrix, i - 1, j);
        }
        if (i + 1 < m) {
            down = dfs(matrix, i + 1, j);
        }
        if (j - 1 >= 0) {
            left = dfs(matrix, i, j - 1);
        }
        if (j + 1 < n) {
            right = dfs(matrix, i, j + 1);
        }
        return up + down + left + right + 1;
    }
}
