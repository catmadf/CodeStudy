package cn.madf.练习题.aiqiyi;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * @author 烛影鸾书
 * @date 2020/9/13 15:35
 * @copyright© 2020
 */
public class Main2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(s);
        String[] sArr = s.split(" ");
        int[] arr = new int[sArr.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(sArr[i]);
        }
        System.out.println(maxCountNumber(arr));
    }

    private static int maxCountNumber(int[] arr) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int value : arr) {
            count.put(value, count.getOrDefault(value, 0) + 1);
        }
        System.out.println(count);

        int res = arr[0];
        int c = count.get(res);

        Set<Integer> keys = count.keySet();

        for (Integer key : keys) {
            if (count.get(key) > c) {
                c = count.get(key);
                res = key;
            }
        }
        return res;
    }

}
