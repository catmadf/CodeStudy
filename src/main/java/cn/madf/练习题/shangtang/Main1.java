package cn.madf.练习题.shangtang;

import javafx.scene.transform.MatrixType;
import org.yaml.snakeyaml.nodes.ScalarNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author 烛影鸾书
 * @date 2020/9/18 19:39
 * @copyright© 2020
 */
public class Main1 {

    static class State {
        int x;
        int y;
        int key;

        State(int i, int j, int k) {
            x = i;
            y = j;
            key = k;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            if (n == 0 && m == 0) {
                break;
            }
            char[][] matrix = new char[n][m];
            int[] start = new int[2];
            for (int i = 0; i < n; i++) {
                String line = sc.next();
                for (int j = 0; j < m; j++) {
                    char c = line.charAt(j);
                    if (c == 'S') {
                        start[0] = i;
                        start[1] = j;
                    }
                    matrix[i][j] = c;
                }
            }
            if (bfsSearch(matrix, start[0], start[1])) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static boolean bfsSearch(char[][] matrix, int i, int j) {
        Queue<State> queue = new LinkedList<>();
        int[][] shift = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        boolean[][][] visited = new boolean[matrix.length][matrix[0].length][128];
        State s = new State(i, j, 0);
        queue.add(s);
        visited[i][j][0] = true;

        while (!queue.isEmpty()) {
            s = queue.poll();
            i = s.x;
            j = s.y;
            int k = s.key;
            if (matrix[i][j] == 'G') {
                return true;
            }

            for (int l = 0; l < 4; l++) {
                int iShift = i + shift[l][0];
                int jShift = j + shift[l][1];

                if (iShift < 0 || iShift >= matrix.length || jShift < 0 || jShift >= matrix[0].length) {
                    continue;
                }
                char c = matrix[iShift][jShift];
                if (c == 'X') {
                    continue;
                }
                if (c >= 'A' && c <= 'E' && ((k & (1 << (c - 'A'))) == 0)) {
                    continue;
                }
                int cur = 0;
                if (c >= 'a' && c <= 'e') {
                    cur = 1 << (c - 'a');
                }
                if (!visited[iShift][jShift][k|cur]) {
                    visited[iShift][jShift][k|cur] = true;
                    queue.add(new State(iShift, jShift, k|cur));
                }
            }
        }
        return false;
    }

}
