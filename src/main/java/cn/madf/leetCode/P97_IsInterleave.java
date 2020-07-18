package cn.madf.leetCode;

/**
 * 交错字符串
 * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 * <p>
 * 示例 1:
 * <p>
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/interleaving-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解法1：动态规划
 * s1的前i个字符串和s2的前j个字符串是否能组成s3的前（i+j）个字符串
 *
 * @author 烛影鸾书
 * @date 2020/7/18 15:30
 * @copyright© 2019
 */
public class P97_IsInterleave {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        boolean[][] dp = new boolean[s1.length()+1][s2.length()+1];
        dp[0][0] = true;

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i > 0) {
                    dp[i][j] |= dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                }
                if (j > 0) {
                    dp[i][j] |= dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    public static void main(String[] args) {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";

        P97_IsInterleave p97 = new P97_IsInterleave();
        System.out.println(p97.isInterleave(s1, s2, s3));
    }
}
