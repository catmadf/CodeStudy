package cn.madf.练习题.mihayou;


import java.util.*;

/**
 * @author 烛影鸾书
 * @date 2020/9/19 20:43
 * @copyright© 2020
 */
public class Main1 {

    private static char[] chars = new char[26];
    private static int count = 0;

    public static void main(String[] args) {
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (65 + i);
        }
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        char[][] matrix = new char[n][m];
        int tr = 0;
        int tc = 0;
        int dr = n - 1;
        int dc = m - 1;
        while (dr >= tr && dc >= tc) {
            fillMatrix(matrix, tr++, tc++, dr--, dc--);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j == 0) {
                    System.out.print(matrix[i][j]);
                } else {
                    System.out.print(" " + matrix[i][j]);
                }
            }
            System.out.println();
        }
    }

    private static void fillMatrix(char[][] matrix, int tr, int tc, int dr, int dc) {
        int i = tr + 1;
        int j = tc;
        while (j <= dc) {
            matrix[tr][j++] = chars[count % 26];
            count++;
        }
        while (i <= dr) {
            matrix[i++][dc] = chars[count % 26];
            count++;
        }
        i = dr - 1;
        j = dc - 1;
        while (j >= tc && tr != dr) {
            matrix[dr][j--] = chars[count % 26];
            count++;
        }
        while (i > tr && tc != dc) {
            matrix[i--][tc] = chars[count % 26];
            count++;
        }
    }

}
