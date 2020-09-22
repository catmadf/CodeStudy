package cn.madf.练习题.meituan;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author 烛影鸾书
 * @date 2020/9/20 10:43
 * @copyright© 2020
 */
public class Main2 {
    private static int p;
    private static int q;

    private static int point = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        p = sc.nextInt();
        q = sc.nextInt();
        int curx = 0;
        int cury = 0;
        char[][] matrix = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = sc.next();
            for (int j = 0; j < m; j++) {
                char c = line.charAt(j);
                matrix[i][j] = c;
                if (c == 'S') {
                    curx = i;
                    cury = j;
                    matrix[i][j] = '+';
                }
            }
        }
        Queue<Character> queue = new LinkedList<>();
        String line = sc.next();
        for (int i = 0; i < line.length(); i++) {
            queue.add(line.charAt(i));
        }

        while (!queue.isEmpty()) {
            char c = queue.poll();
            if (c == 'W') {
                if (!(curx == 0 || matrix[curx - 1][cury] == '#')) {
                    curx = curx - 1;
                }
                if (matrix[curx][cury] == 'X') {
                    point -= q;
                    matrix[curx][cury] = '+';
                }
                if (matrix[curx][cury] == 'O') {
                    point += p;
                    matrix[curx][cury] = '+';
                }
            }

            if (c == 'S') {
                if (!(curx == n-1 || matrix[curx + 1][cury] == '#')) {
                    curx = curx + 1;
                }
                if (matrix[curx][cury] == 'X') {
                    point -= q;
                    matrix[curx][cury] = '+';
                }
                if (matrix[curx][cury] == 'O') {
                    point += p;
                    matrix[curx][cury] = '+';
                }
            }

            if (c == 'A') {
                if (!(cury == 0 || matrix[curx][cury - 1] == '#')) {
                    cury = cury - 1;
                }
                if (matrix[curx][cury] == 'X') {
                    point -= q;
                    matrix[curx][cury] = '+';
                }
                if (matrix[curx][cury] == 'O') {
                    point += p;
                    matrix[curx][cury] = '+';
                }
            }

            if (c == 'D') {
                if (!(cury == m - 1 || matrix[curx][cury + 1] == '#')) {
                    cury = cury + 1;
                }
                if (matrix[curx][cury] == 'X') {
                    point -= q;
                    matrix[curx][cury] = '+';
                }
                if (matrix[curx][cury] == 'O') {
                    point += p;
                    matrix[curx][cury] = '+';
                }
            }
        }
        System.out.println(point);
    }

}
