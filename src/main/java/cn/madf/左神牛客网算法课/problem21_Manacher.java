package cn.madf.左神牛客网算法课;

/**
 * 最长回文子串的O(N)解法
 * <p>
 * 思路：
 * ......预处理：偶数情况会存在虚回文中心，基于此，我们可以将字符串每个字符间填充固定字符："aabaacddc"-->"#a#a#b#a#a#c#d#d#c#"
 * ......(1)    用一个数组记录各个对应位向外扩张时所能达到的最大回文半径长度，遍历过程中也就是利用这个数组进行加速
 * ......(2)    最右回文右边界R和对应的回文中心C
 *
 * @author 烛影鸾书
 * @date 2020/6/5
 * @copyright© 2020
 */
public class problem21_Manacher {

    public String longestPlalindromeString2(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }

        String string = preprocess(str);
        int[] radius = new int[string.length()]; // 回文半径数组
        int R = -1;  // 最右回文右边界
        int C = -1;  // R对应的回文中心
        int resRadius = Integer.MIN_VALUE;
        int resCenter = 0;

        int index = 1;
        while (index < string.length()) {
            /* 回文半径数组的初始化 */
            int mirror = 2 * C - index;
            radius[index] = index >= R ? 0 : Math.min(radius[mirror], R - index);
            /* 然后开始向两边扩展，直到不能扩时停下 */
            int nextRight = index + radius[index] + 1;
            int nextLeft = index - radius[index] - 1;
            while (nextLeft > -1 && nextRight < string.length() && string.charAt(nextLeft) == string.charAt(nextRight)) {
                radius[index]++;
                nextLeft--;
                nextRight++;
            }
            /* 扩完需要更新R和C */
            if (index + radius[index] > R) {
                R = index + radius[index];
                C = index;
            }

            if (radius[index] > resRadius) {
                resRadius = radius[index];
                resCenter = index;
            }
            index++;
        }

        int start = (resCenter - resRadius) / 2;
        int len = resRadius;
        return str.substring(start, start + len);
    }

    public String longestPlalindromeString(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }

        String string = preprocess(str);
        int[] radius = new int[string.length()]; // 回文半径数组
        int R = -1;  // 最右回文右边界
        int C = -1;  // R对应的回文中心
        int resRadius = Integer.MIN_VALUE;
        int resCenter = 0;

        int index = 1;  // 遍历索引
        while (index < string.length()) {
            /* 第一种情况，index位于R或R右边时，暴力扩 */
            if (index >= R) {
                radius[index] = 0;
                int nextRight = index + radius[index] + 1;
                int nextLeft = index - radius[index] - 1;
                while (nextRight < string.length() && nextLeft > -1 && string.charAt(nextRight) == string.charAt(nextLeft)) {
                    radius[index]++;
                    nextLeft--;
                    nextRight++;
                }
                if (index + radius[index] > R) {
                    R = index + radius[index];
                    C = index;
                }
            }
            /* 第二种情况，index位于R左边 */
            else {
                int L = 2 * C - R;
                int mirror = 2 * C - index;
                int mirrorL = mirror - radius[mirror];
                /* 子情况1和2：当index的镜像位置index'的回文左边界在C的回文左边界外和内部时 */
                if (mirrorL != L) {
                    radius[index] = mirrorL > L ? radius[mirror] : R - index;
                }
                /* 子情况3： 当index的镜像位置index'的回文左边界在C的回文左边界上时 mirrorL==L，暴力扩 */
                else {
                    radius[index] = R - index;
                    int nextRight = index + radius[index] + 1;
                    int nextLeft = index - radius[index] - 1;
                    while (nextRight < string.length() && nextLeft > -1 && string.charAt(nextRight) == string.charAt(nextLeft)) {
                        radius[index]++;
                        nextLeft--;
                        nextRight++;
                    }
                    if (index + radius[index] > R) {
                        R = index + radius[index];
                        C = index;
                    }
                    /* 写到这里就可以发现代码重复了，说明是可以优化的，当然不是指编译器提示那种新建一个函数来优化
                     * 我们可以从四种情况综合考虑优化：
                     * 情况1和情况2-3都需要暴力扩，情况2-1和2-2不需要暴力扩是因为我们通过证明了解到是没必要扩，但是并不意味不能暴力扩，
                     * 此时如果强行暴力扩张，就会触发string.charAt(right) != str.charAt(left)而终止循环，因此我们只要处理好边界条件
                     * 就可以将四种子情况归到一起。 */
                }
            }

            if (radius[index] > resRadius) {
                resRadius = radius[index];
                resCenter = index;
            }

            index++;
        }

        StringBuilder res = new StringBuilder();
        for (int i = resCenter - resRadius + 1; i <= resCenter + resRadius - 1; i += 2) {
            res.append(string.charAt(i));
        }
        return res.toString();
    }

    private String preprocess(String string) {
        StringBuilder newString = new StringBuilder("#");
        for (int i = 0; i < string.length(); i++) {
            newString.append(string.charAt(i));
            newString.append("#");
        }
        return newString.toString();
    }

    public static void main(String[] args) {
        problem21_Manacher manacher = new problem21_Manacher();
        String str = "abbd";
        System.out.println(manacher.longestPlalindromeString2(str));

    }
}
