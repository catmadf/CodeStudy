package cn.madf.练习题.duxiaoman;

import java.util.Scanner;

/**
 * @author 烛影鸾书
 * @date 2020/9/13 20:38
 * @copyright© 2020
 */
public class Main1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextInt();
        long m = sc.nextInt();

        System.out.println((1 + 3 * n) * 3 * n / 2 * m);
    }

}
