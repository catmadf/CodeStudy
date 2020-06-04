package cn.madf.leetCode;

/**
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 * <p>
 * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/product-of-array-except-self
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 之前还碰到一个不能使用乘法、if-else、switch等来计算阶乘的（通过 && 运算符短路特性或者 俄罗斯农民乘法 计算）
 * 不使用除法运算符计算除法，主要有以下几种方式：
 * .........1.  搜索：计算A除以b，在 [2， A-1]上搜索一个数a*b==A即可，这个过程可以使用二分搜索优化
 * .........2.  还是搜索，只不过这次指定步长，比如我们要计算1200/3，就可以从3开始搜索：
 * .............(1) 3,6,9,12,...1997固定步长顺序搜索
 * .............(2) 3,6,12,24,48,...,768搜索了9次，第10次1536大于1200，接下来比较1200-768=432再重新搜索3,6,12,24,...,384
 * .................搜索了8次，继续比较432-384=48再重新搜索3,6,12,24,48停止，此时总共比较10+9+5=24次比顺序搜索更快，最终
 * .................结果即为2^8+2^7+2^4=400
 * 这种除法了解一下就行了，会超时...
 * <p>
 * 要求时间复杂度为O(N):
 * 那么我们就不能在遍历时通过累积除以该数得到结果，
 * 我们可以这么想：一个数的"累积" = 它左边所有数的累积 + 它右边所有数的累积
 * <p>
 * 空间优化：我们可以直接使用leftProd表示结果，在第二次遍历过程中只需要动态记录右边所有数的累积即可，乘以leftProd[i]即为结果
 *
 * @author 烛影鸾书
 * @date 2020/6/4
 * @copyright© 2020
 */
public class P238_ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {

        int[] products = new int[nums.length];
        products[0] = 1;
        /* 第一次遍历将该数左边数的累积记录在案 */
        for (int i = 1; i < nums.length; i++) {
            products[i] = products[i - 1] * nums[i - 1];
        }
        /* 第二次从尾部遍历时，可以不用另备份右边数累积，直接将其乘到products中即可 */
        int rightProd = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            products[i] = products[i] * rightProd;
            rightProd = rightProd * nums[i];
        }

        return products;
    }

    public int[] productExceptSelf_(int[] nums) {
        /* 用两个数组分别存放i位置的数左边数累积和右边数累积 */
        int[] leftProd = new int[nums.length];
        int[] rightProd = new int[nums.length];
        leftProd[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            leftProd[i] = leftProd[i - 1] * nums[i - 1];
        }
        rightProd[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            rightProd[i] = rightProd[i + 1] * nums[i + 1];
        }

        int[] products = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            products[i] = leftProd[i] * rightProd[i];
        }
        return products;
    }

    public int[] productExceptSelf__(int[] nums) {
        int[] products = new int[nums.length];
        /* 计算nums所有数的累积 */
        int product = 1;
        for (int i = 0; i < nums.length; i++) {
            product *= nums[i];
        }
        /* 除以自身 */
        for (int i = 0; i < nums.length; i++) {
            products[i] = division(product, nums[i]);
        }
        return products;
    }

    private int division(int dividend, int divisor) {
        int res = 0;
        int stepDiv;
        while (dividend >= divisor) {
            int step = 0;
            stepDiv = divisor;

            while ((stepDiv << 1) < dividend) {
                stepDiv = stepDiv << 1;
                step++;
            }

            res += Math.pow(2, step);
            dividend = dividend - stepDiv;
        }
        return res;
    }

    public static void main(String[] args) {
        P238_ProductOfArrayExceptSelf p238 = new P238_ProductOfArrayExceptSelf();
        int a = p238.division(0, 3);
        System.out.println(a);
    }
}
