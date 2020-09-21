package cn.madf.字节跳动.飞书;

import cn.madf.小工具.PrintBinaryTree;

import java.util.*;

/**
 * 山形数组定义：一维数组，数组元素从左到右逐渐变大，达到某个最大值后开始逐渐减小
 * 例如: 1， 2， 2， 5， 10， 9， 8， 2， 1， 1
 * 要求按照元素从小到大顺序，不重复输出，上面的case输出为
 * 1, 2, 5, 8, 9, 10
 * <p>
 * 要求时间复杂度为O(n) 空间复杂度为O(1)
 *
 * @author 烛影鸾书
 * @date 2020/7/21 12:48
 * @copyright© 2020
 */
public class problem1 {

    public void solution(int[] nums) {
        int i = 1;
        int j = nums.length - 1;
        System.out.print(nums[0]);
        int lastPrint = nums[0];
        while (nums[i - 1] <= nums[i]) {
            System.out.println(i+" "+j+"   "+nums[i]+" "+nums[j]);
            if (nums[i] <= nums[j]) {
                if (nums[i] != lastPrint) {
                    System.out.print("," + nums[i]);
                    lastPrint = nums[i];
                }
                if (nums[i] == nums[j]) {
                    j--;
                }
                i++;
            } else{
                if (nums[j] != lastPrint) {
                    System.out.print("," + nums[j]);
                    lastPrint = nums[j];
                }
                j--;
            }
        }
        System.out.println();
    }

    public void solutionCorrect(int[] nums) {
        Arrays.sort(nums);
        int lastPrint = nums[0];
        System.out.print(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != lastPrint) {
                System.out.print("," + nums[i]);
                lastPrint = nums[i];
            }
        }
    }

    public static void main(String[] args) {
        problem1 problem1 = new problem1();

        ArrayGenerator generator = new ArrayGenerator(100, 10);
        for (int i = 0; i < 10000; i++) {
            int[] nums = generator.generateArray(1, 100);
            System.out.println("原始: "+Arrays.toString(nums));
            problem1.solution(nums);
            System.out.println();
        }

    }

    private static class ArrayGenerator {

        private int maxSize;
        private int minSize;

        ArrayGenerator(int maxSize, int minSize) {
            this.maxSize = maxSize;
            this.minSize = minSize;
        }

        /**
         * 生成山形数组
         *
         * @param maxItem 数组中的最大值
         * @param minItem 数组中的最小值
         * @return 数组
         */
        int[] generateArray(int minItem, int maxItem) {
            Random random = new Random();
            int size1 = random.nextInt(maxSize - minSize) + minSize;
            int size2 = random.nextInt(maxSize - minSize) + minSize;

            /* 生成随机数组 */
            Integer[] arr1 = new Integer[size1];
            for (int i = 0; i < arr1.length; i++) {
                arr1[i] = random.nextInt(maxItem - minItem) + minItem;
            }
            Integer[] arr2 = new Integer[size2];
            for (int i = 0; i < arr2.length; i++) {
                arr2[i] = random.nextInt(maxItem - minItem) + minItem;
            }
            Arrays.sort(arr1);
            Arrays.sort(arr2, Comparator.reverseOrder());

            int[] res = new int[arr1.length + arr2.length];
            for (int i = 0; i < arr1.length; i++) {
                res[i] = arr1[i];
            }
            for (int i = 0; i < arr2.length; i++) {
                res[i + arr1.length] = arr2[i];
            }
            return res;
        }
    }
}
