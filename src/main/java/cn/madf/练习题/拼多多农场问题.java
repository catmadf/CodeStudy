package cn.madf.练习题;

import java.util.Scanner;

/**
 * @author 烛影鸾书
 * @date 2020/8/2 19:29
 * @copyright© 2020
 */
public class 拼多多农场问题 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] farm = new char[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                farm[i][j] = sc.next().charAt(0);
            }
        }


    }

    private static int bfs(int[][] matrix, int i, int j) {
        return 0;
    }
}
