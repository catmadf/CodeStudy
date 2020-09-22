package cn.madf.练习题;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = sc.next();
        System.out.println(solution(s));
    }

    private static int solution(String s) {
        int n = s.length();
        if (n == 0 || n == 1 || n == 2) {
            return n;
        }
        int minStep = n;
        int lp = 0;   // 不能动
        int rp = 0;
        int lq = 1;
        int rq = 1;
        while (rp < n && rq < n) {
            if (s.charAt(lp) != s.charAt(lq)) {
                lq++;
                rq = lq;
            } else {
                if (s.charAt(rp) == s.charAt(rq)) {
                    rp++;
                    rq++;
                } else {
                    lq = rq;
                    rp = lp;
                }
            }
            if (rp == lq) {
                minStep = Math.min(minStep, n + 1 - (rp - lp));
                lq = rq;
                rp = lp;
            }
        }
        return minStep;
    }
}