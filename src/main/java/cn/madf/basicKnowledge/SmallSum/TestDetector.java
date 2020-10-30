package cn.madf.basicKnowledge.SmallSum;

import java.util.Arrays;

/**
 * @author 烛影鸾书
 * @date 2020/4/8
 * @copyright© 2020
 */
public class TestDetector {

    public static int[] generateIntArray(int maxSize, int maxItem) {
        int length = (int) (Math.random() * maxSize);
        int[] array = new int[length];

        for (int i = 0; i < length; i++) {
            array[i] = (int) (Math.random() * maxItem);
        }

        return array;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int[] arr = generateIntArray(30, 100);
            int res1 = SmallSum.solutionA(arr);
            int res2 = SmallSum.solutionB(arr);
            if (res1 != res2) {
                System.out.println(Arrays.toString(arr));
                System.out.println(res1 + "\t" + res2);
            }
        }
        System.out.println("test pass!");
    }
}
