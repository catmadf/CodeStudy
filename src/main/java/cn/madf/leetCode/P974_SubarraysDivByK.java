package cn.madf.leetCode;

import sun.print.SunAlternateMedia;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。
 * 示例：
 * 输入：A = [4,5,0,-2,-3,1], K = 5
 * 输出：7
 * 解释：
 * 有 7 个子数组满足其元素之和可被 K = 5 整除：
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 * <p>
 * 提示：
 * 1 <= A.length <= 30000
 * -10000 <= A[i] <= 10000
 * 2 <= K <= 10000
 * <p>
 * 答案提示：同余定理：(a - b) % k == 0  等价于  (a % k == b % k)
 * 子数组的问题一般可以用前缀和解决
 * 余数范围应在[0, k-1]之间，当负数取模时需要纠正
 *
 * @author 烛影鸾书
 * @date 2020/5/27
 * @copyright© 2020
 */
public class P974_SubarraysDivByK {

    public int subarraysDivByK(int[] A, int K) {
        Map<Integer, Integer> modRecord = new HashMap<>();
        modRecord.put(0, 1);
        int sum = 0;
        int res = 0;
        for (int i : A) {
            sum += i;

            int mod = (sum % K + K) % K;
            int subNum = modRecord.getOrDefault(mod, 0);
            modRecord.put(mod, subNum + 1);
            res += subNum;
        }

        return res;
    }

    public static void main(String[] args) {
        P974_SubarraysDivByK p974 = new P974_SubarraysDivByK();

        int[] a = new int[] {4, 5, 0, -2, -3, 1};

        System.out.println(p974.subarraysDivByK(a, 5));
    }
}
