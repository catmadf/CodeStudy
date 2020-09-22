package cn.madf.练习题;

import com.sun.org.apache.regexp.internal.REUtil;

import java.util.Scanner;

/**
 * @author 烛影鸾书
 * @date 2020/9/5 19:40
 * @copyright© 2020
 */
public class Main_b {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] cost = new int[2 * n];
        int[] income = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            cost[i] = sc.nextInt();
        }
        for (int i = 0; i < 2 * n; i++) {
            income[i] = sc.nextInt();
        }

        System.out.println(solution(cost, income, n));
    }

    private static int solution(int[] cost, int[] income, int n) {
        int lp = n - 1;
        int rp = n;
        int lpq = lp;  // 收益相等时准备去kill的指针
        int rpq = rp;
        int blood = 0;
        while (lp >= 0 && rp < 2 * n) {
            int leftBlood = income[lp] - cost[lp];
            int rightBlood = income[rp] - cost[rp];
            if (leftBlood > rightBlood) {
                lp--;
                blood += leftBlood;
            } else if (leftBlood < rightBlood) {
                rp++;
                blood += rightBlood;
            } else {
                lpq = lp;
                rpq = rp;
                while (cost[lpq] == cost[rpq] && income[lpq] == income[rpq] && leftBlood == rightBlood && (lpq - 1) >= 0 && (rpq + 1) <= (2 * n - 1)) {
                    lpq--;
                    rpq++;
                    leftBlood += (income[lpq] - cost[lpq]);
                    rightBlood += (income[rpq] - cost[rpq]);
                }
                if (leftBlood < rightBlood) {
                    blood += rightBlood;
                    rp = rpq + 1;
                } else if (leftBlood > rightBlood) {
                    blood += leftBlood;
                    lp = lpq - 1;
                } else {
                    if (cost[lpq] < cost[rpq]) {
                        blood += leftBlood;
                        lp = lpq - 1;
                    } else {
                        blood += rightBlood;
                        rp = rpq + 1;
                    }
                }
            }
        }

        while (lp >= 0) {
            int leftBlood = income[lp] - cost[lp];
            blood += leftBlood;
            lp--;
        }
        while (rp < 2 * n) {
            int rightBlood = income[rp] - cost[rp];
            blood += rightBlood;
            rp++;
        }
        if (blood >= 0) {
            return 1;
        } else {
            return 1 - blood;
        }
    }
}
