package cn.madf.leetCode;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 * <p>
 * largestRectangleArea_1中的解法为O(N^2)
 * 优化：我们可以注意到对于索引[...k...i...j...]，当我们在向左找第一个比height[j]小的数时(假设这个数是height[k]),
 * 那对于中间的height[i](肯定大于height[j]，因为k才是左边第一个比height[k]小的数)，那么我们肯定可以在j遍历到k的过程中，
 * 遍历到i时，可以通过height[i]的左边界(第一个比它小的数)跳转，从而跳过了中间部分遍历，从而达到加速效果
 *
 * @author 烛影鸾书
 * @date 2020/5/30
 * @copyright© 2020
 */
public class P84_TheLargestRectangleInHistogram {

    public int largestRectangleArea_1(int[] heights) {
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            max = Math.max(getArea(heights, i), max);
        }

        return max;
    }

    public int largestRectangleArea_2(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }
        int max = 0;
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = -1;
        right[n - 1] = n;

        /* 找左边界 */
        for (int i = 0; i < n; i++) {
            int j = i - 1;
            /* 我们在遍历过程中找到一个比height[i]大的数，就可以通过其对应的left边界值进行加速跳转 */
            while (j >= 0 && heights[j] >= heights[i]) {
                j = left[j];
            }
            left[i] = j;
        }
        /* 找右边界就需要从又边开始遍历
         * 假设从左开始遍历，如果要通过height[j](j>i)进行向后跳转，那么说明right[j]是已知的，这明显与从左遍历相矛盾 */
        for (int i = n - 1; i > -1; i--) {
            int j = i + 1;
            /* 同找左边界加速时一样的原理 */
            while (j < n && heights[j] >= heights[i]) {
                j = right[j];
            }
            right[i] = j;
        }

        for (int i = 0; i < n; i++) {
            max = Math.max(max, (right[i] - left[i] - 1) * heights[i]);
        }

        return max;
    }

    private int getArea(int[] heights, int i) {
        int height = heights[i];
        int lp = i - 1;
        int left = i;
        int rp = i + 1;
        int right = i;

        while (lp >= 0) {
            if (heights[lp] >= height) {
                left = lp;
                lp--;
            } else {
                break;
            }
        }
        while (rp < heights.length) {
            if (heights[rp] >= height) {
                right = rp;
                rp++;
            } else {
                break;
            }
        }
        return height * (right - left + 1);
    }

}
