package cn.madf.练习题.浪潮;

import org.junit.Test;
import sun.util.locale.provider.FallbackLocaleProviderAdapter;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 烛影鸾书
 * @date 2020/9/11 10:51
 * @copyright© 2020
 */
public class Main2 {

    public static void main(String[] args) {
        int treeNum = 100;
        int[] nums = new int[treeNum + 1];
        Arrays.fill(nums, 1);
        nums[treeNum] = 0;

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n == 100) {
            System.out.println(0 + " " + 0);
            return;
        }
        for (int i = 0; i < n; i++) {
            int cut = sc.nextInt();
            if (cut % 2 == 1) {
                // 左侧
                nums[cut / 2] = 0;
            } else {
                nums[49 + cut / 2] = 0;
            }
        }

        boolean flag = false;
        int resStart = 0;
        int resEnd = 0;
        int start = 0;
        int end = 0;
        for (int i = 0; i < 50; i++) {
            if (nums[i] == 1 && !flag) {
                start = i;
                flag = true;
            }
            if (nums[i] == 1 && flag) {
                end = i;
            }
            if (nums[i] == 0) {
                flag = false;
            }
            if (end - start > resEnd - resStart) {
                resStart = start;
                resEnd = end;
            }
        }
        start = 0;
        end = 0;
        flag = false;
        for (int i = 50; i < 101; i++) {
            if (nums[i] == 1 && !flag) {
                start = i;
                flag = true;
            }
            if (nums[i] == 1 && flag) {
                end = i;
            }
            if (nums[i] == 0) {
                flag = false;
            }
            if (end - start > resEnd - resStart) {
                resStart = start;
                resEnd = end;
            }
        }
        if (resStart < 50) {
            System.out.println((resStart * 2 + 1) + " " + (resEnd - resStart + 1));
        } else {
            System.out.println(((resStart - 49) * 2) + " " + (resEnd - resStart + 1));
        }
    }

    @Test
    public void test() {
        int treeNum = 100;
        int[] nums = new int[treeNum + 1];
        Arrays.fill(nums, 1);
        nums[treeNum] = 0;

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n == 100) {
            System.out.println(0 + " " + 0);
            return;
        }
        for (int i = 0; i < n; i++) {
            int cut = sc.nextInt();
            if (cut % 2 == 1) {
                // 左侧
                nums[cut / 2] = 0;
            } else {
                nums[49 + cut / 2] = 0;
            }
        }

        boolean flag = false;
        int resStart = 0;
        int resEnd = 0;
        int start = 0;
        int end = 0;
        for (int i = 0; i < 50; i++) {
            if (nums[i] == 1 && !flag) {
                start = i;
                flag = true;
            }
            if (nums[i] == 1 && flag) {
                end = i;
            }
            if (nums[i] == 0) {
                flag = false;
            }
            if (end - start > resEnd - resStart) {
                resStart = start;
                resEnd = end;
            }
        }
        for (int i = 50; i < 101; i++) {
            if (nums[i] == 1 && !flag) {
                start = i;
                flag = true;
            }
            if (nums[i] == 1 && flag) {
                end = i;
            }
            if (nums[i] == 0) {
                flag = false;
            }
            if (end - start > resEnd - resStart) {
                resStart = start;
                resEnd = end;
            }
        }
        if (resStart < 50) {
            System.out.println((resStart * 2 + 1) + " " + (resEnd - resStart + 1));
        } else {
            System.out.println(((resStart - 49) * 2) + " " + (resEnd - resStart + 1));
        }
    }

}
