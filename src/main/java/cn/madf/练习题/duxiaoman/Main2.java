package cn.madf.练习题.duxiaoman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author 烛影鸾书
 * @date 2020/9/13 20:45
 * @copyright© 2020
 */
public class Main2 {

    private static List<Integer> minCost = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[][] matrix = new int[n][n];
        boolean[][] isVisited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            char[] s = sc.next().toCharArray();
            for (int j = 0; j < s.length; j++) {
                if (s[j] == '0') {
                    matrix[i][j] = 1;
                }
                if (s[j] == '1') {
                    matrix[i][j] = -1;
                }
                if (s[j] == '#') {
                    matrix[i][j] = k + 1;
                }
            }
        }
        matrix[0][0] = 0;
        if (dfs(matrix, 0, 0, isVisited, 0)) {
            Collections.sort(minCost);
            System.out.println(minCost.get(0));
        } else {
            System.out.println("No solution");
        }
    }

    private static boolean dfs(int[][] matrix, int i, int j, boolean[][] isVisited, int lastCost) {
        int n = matrix.length;
        if (i == n - 1 && j == n - 1) {
            minCost.add(lastCost + matrix[i][j]);
            return true;
        }

        if (matrix[i][j] == -1) {
            isVisited[i][j] = false;
            return false;
        }
        boolean flag = false;

        isVisited[i][j] = true;
        if (i > 0 && !isVisited[i - 1][j] && matrix[i - 1][j] != -1) {
            flag = dfs(matrix, i - 1, j, isVisited, lastCost + matrix[i][j]);
        }
        if (i < n - 1 && !isVisited[i + 1][j] && matrix[i + 1][j] != -1) {
            flag |= dfs(matrix, i + 1, j, isVisited, lastCost + matrix[i][j]);
        }
        if (j > 0 && !isVisited[i][j - 1] && matrix[i][j - 1] != -1) {
            flag |= dfs(matrix, i, j - 1, isVisited, lastCost + matrix[i][j]);
        }

        if (j < n - 1 && !isVisited[i][j + 1] && matrix[i][j + 1] != -1) {
            flag |= dfs(matrix, i, j + 1, isVisited, lastCost + matrix[i][j]);
        }
        isVisited[i][j] = false;
        return flag;
    }

}
