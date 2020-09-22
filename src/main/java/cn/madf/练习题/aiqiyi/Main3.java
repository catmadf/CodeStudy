package cn.madf.练习题.aiqiyi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author 烛影鸾书
 * @date 2020/9/13 15:35
 * @copyright© 2020
 */
public class Main3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] sArr = s.split(" ");
        int[] arr = new int[sArr.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(sArr[i]);
        }
        List<Integer[]> res = threeNum(arr);
        for (Integer[] re : res) {
            for (int i = 0; i < re.length; i++) {
                if (i == 0) {
                    System.out.print(re[i]);
                } else {
                    System.out.print(" " + re[i]);
                }
            }
            System.out.println();
        }
    }

    private static List<Integer[]> threeNum(int[] nums) {
        Arrays.sort(nums);
        List<Integer[]> res = new ArrayList<>();
        for (int left = 0; left < nums.length; left++) {
            if (left > 0 && nums[left - 1] == nums[left]) {
                continue;
            }
            int right = nums.length - 1;

            for (int mid = left + 1; mid < nums.length; mid++) {
                if (mid > left + 1 && nums[mid - 1] == nums[mid]) {
                    continue;
                }
                while (mid < right && nums[left] + nums[mid] + nums[right] > 0) {
                    right--;
                }
                if (mid == right) {
                    break;
                }
                if (nums[left] + nums[mid] + nums[right] == 0) {
                    res.add(new Integer[]{nums[left], nums[mid], nums[right]});
                }
            }
        }
        return res;
    }

}
