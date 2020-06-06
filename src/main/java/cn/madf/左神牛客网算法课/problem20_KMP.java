package cn.madf.左神牛客网算法课;

/**
 * KMP算法
 *
 * @author 烛影鸾书
 * @date 2020/6/5
 * @copyright© 2020
 */
public class problem20_KMP {

    public int getIndexOf(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return -1;
        }
        /* 用一个数组记录预处理结果，help[i]即表示str2中前0~i-1的子串中最大前缀和后缀相等的长度，前后缀都不能等于整个子串 */
        int[] help = preprocess(str2);
        int p1 = 0;
        int p2 = 0;
        while (p1 < str1.length() && p2 < str2.length()) {
            if (str1.charAt(p1) == str2.charAt(p2)) {
                p1++;
                p2++;
            } else if (help[p2] == -1){
                p1++;
            } else {
                p2 = help[p2];
            }
        }

        return p2 == str2.length() ? p1 - p2 : -1;

    }

    private int[] preprocess(String str2) {
        if (str2.length() == 1) {
            return new int[]{-1};
        }
        int[] res = new int[str2.length()];
        res[0] = -1;
        res[1] = 0;
        int p = 2;
        while (p < str2.length()) {
            search(str2, res, p);
            p++;
        }
        return res;
    }

    private void search(String str, int[] res, int p) {
        int springboard = p - 1;
        while (springboard != 0) {
            if (str.charAt(p - 1) == str.charAt(res[springboard])) {
                res[p] = res[springboard] + 1;
                break;
            } else {
                springboard = res[springboard];
            }
        }
    }

    public static void main(String[] args) {
        problem20_KMP kmp = new problem20_KMP();
        String str1 = "aasdn iwhainfwaiwijfje[oowaomlwmfwawkgw5787q3gq3gag";
        String str2 = "oowaomlwm";
        int index = kmp.getIndexOf(str1, str2);
        System.out.println(index);

    }
}
