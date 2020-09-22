package cn.madf.练习题.jingdong;

import java.util.Scanner;

/**
 * @author 烛影鸾书
 * @date 2020/9/17 20:08
 * @copyright© 2020
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[] start = new int[2];
            int[] end = new int[2];
            char[][] matrix = new char[n][m];
            boolean[][] isVisited = new boolean[n][m];
            for (int j = 0; j < n; j++) {
                String line = sc.next();
                for (int k = 0; k < line.length(); k++) {
                    char c = line.charAt(k);
                    if (c == 'S') {
                        start[0] = j;
                        start[1] = k;
                    }
                    matrix[j][k] = c;
                }
            }
            if (isReached(matrix, start[0], start[1], isVisited)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static boolean isReached(char[][] matrix, int i, int j, boolean[][] isVisited) {
        int n = matrix.length;
        int m = matrix[0].length;
        if (matrix[i][j] == 'E') {
            return true;
        }
        boolean flag = false;
        isVisited[i][j] = true;
        if (i > 0 && !isVisited[i - 1][j] && matrix[i - 1][j] != '#') {
            flag = isReached(matrix, i - 1, j, isVisited);
        }
        if (flag) {
            return true;
        }
        if (i < n - 1 && !isVisited[i + 1][j] && matrix[i + 1][j] != '#') {
            flag = isReached(matrix, i + 1, j, isVisited);
        }
        if (flag) {
            return true;
        }
        if (j > 0 && !isVisited[i][j - 1] && matrix[i][j - 1] != '#') {
            flag = isReached(matrix, i, j - 1, isVisited);
        }
        if (flag) {
            return true;
        }
        if (j < m - 1 && !isVisited[i][j + 1] && matrix[i][j + 1] != '#') {
            flag = isReached(matrix, i, j + 1, isVisited);
        }
        if (flag) {
            return true;
        }
        isVisited[i][j] = false;
        return flag;
    }

}
