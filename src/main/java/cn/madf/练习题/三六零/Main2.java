package cn.madf.练习题.三六零;

import java.util.Scanner;

/**
 * @author 烛影鸾书
 * @date 2020/9/11 20:55
 * @copyright© 2020
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String password = sc.next();
            System.out.println(isPwLegal(password));
        }
    }

    private static String isPwLegal(String pw) {
        if (pw.length() < 8) {
            return "Irregular password";
        }
        boolean hasNumber = false;
        boolean hasUpLetter = false;
        boolean hasLowLetter = false;
        boolean hasSpecialSymbol = false;

        for (int i = 0; i < pw.length(); i++) {
            char c = pw.charAt(i);
            if (c >= '0' && c <= '9') {
                hasNumber = true;
            } else if (c >= 'a' && c <= 'z') {
                hasLowLetter = true;
            } else if (c >= 'A' && c <= 'Z') {
                hasUpLetter = true;
            } else {
                hasSpecialSymbol = true;
            }
        }
        if (hasNumber && hasLowLetter && hasUpLetter && hasSpecialSymbol) {
            return "Ok";
        } else {
            return "Irregular password";
        }
    }
}
