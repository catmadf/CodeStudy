package cn.madf.练习题.jingdong;

import cn.madf.小工具.PrintBinaryTree;

import java.util.Scanner;

/**
 * @author 烛影鸾书
 * @date 2020/9/17 18:49
 * @copyright© 2020
 */
public class Main1 {
    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        searchYear(str);
        System.out.println(sb.substring(0, sb.length() - 1));
    }

    public static void searchYear(String str) {
        String[] strs = str.split("[^0-9]");
        for (String s : strs) {
            char[] c = s.toCharArray();
            if (c.length == 4 && (c[0] >= '1' && c[0] <= '3') && (Character.isDigit(c[1])) && (Character.isDigit(c[2])) && (Character.isDigit(c[3]))) {
                addYear(s);
            }
        }
    }

    public static void print(String str) {
        if (count == 0) {
            System.out.print(str);
            count = 1;
        } else {
            System.out.print(" " + str);
        }
    }

    private static StringBuilder sb = new StringBuilder();

    private static boolean isDigits(String s) {
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i++);
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return i != 0;
    }

    private static void addYear(String year) {
        sb.append(year).append(" ");
    }
}