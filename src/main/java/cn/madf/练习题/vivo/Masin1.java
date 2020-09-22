package cn.madf.练习题.vivo;

import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] start = new int[2];
        start[0] = sc.nextInt();
        start[1] = sc.nextInt();
        int[] end = new int[2];
        end[0] = sc.nextInt();
        end[1] = sc.nextInt();
        char[][] map = new char[n][n];
        for (int i = 0; i < n; i++) {
            String str = sc.next();
            map[i] = str.toCharArray();
        }
        System.out.println(bfs(map, start, end));
    }

    private static int bfs(char[][] map, int[] start, int[] end) {
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        int[][] matrix = new int[map.length][map[0].length];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = Integer.MAX_VALUE;
            }
        }
        queue.add(start);
        matrix[start[0]][start[1]] = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == end[0] && cur[1] == end[1]) {
                break;
            }
            for (int i = 0; i < 4; i++) {
                int y = cur[0] + dy[i];
                int x = cur[1] + dx[i];
                if (y >= 0 && y < matrix.length && x >= 0 && x < matrix[0].length && map[y][x] != '#' && map[y][x] != '@' && matrix[y][x] == Integer.MAX_VALUE) {
                    matrix[y][x] = matrix[cur[0]][cur[1]] + 1;
                    int[] c = {y, x};
                    queue.offer(c);
                }
            }
        }
        return matrix[end[0]][end[1]];
    }

}