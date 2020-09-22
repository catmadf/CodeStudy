package cn.madf.练习题.神策;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 烛影鸾书
 * @date 2020/9/15 20:40
 * @copyright© 2020
 */
public class Main2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[7];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }
        int target = sc.nextInt();
        solution(arr, target);
    }

    private static void solution(int[] arr, int target) {
        int[] money = new int[]{1, 2, 5, 10, 20, 50, 100};
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += (arr[i] * money[i]);
        }
        if (sum < target) {
            System.out.println("0 0 0 0 0 0 0");
        }
        if (sum == target) {
            System.out.println(Arrays.toString(arr).replace(",", ""));
        }

        for (int i = 0; i <= arr[0]; i++) {
            int sum1 = i * money[0];
            if (sum1 > target) {
                break;
            }
            for (int j = 0; j <= arr[1]; j++) {
                int sum2 = j * money[1];
                if (sum1 + sum2 > target) {
                    break;
                }
                for (int k = 0; k <= arr[2]; k++) {
                    int sum3 = k * money[2];
                    if (sum1 + sum2 + sum3 > target) {
                        break;
                    }
                    for (int l = 0; l <= arr[3]; l++) {
                        int sum4 = l * money[3];
                        if (sum1 + sum2 + sum3 + sum4 > target) {
                            break;
                        }
                        for (int m = 0; m <= arr[4]; m++) {
                            int sum5 = m*money[4];
                            if (sum1 + sum2 + sum3 + sum4 + sum5 > target) {
                                break;
                            }
                            for (int n = 0; n <= arr[5]; n++) {
                                int sum6 = n * money[5];
                                if (sum1 + sum2 + sum3 + sum4 + sum5 + sum6 > target) {
                                    break;
                                }
                                for (int o = 0; o <= arr[6]; o++) {
                                    int sum7 = o * money[6];
                                    int s = sum1 + sum2 + sum3 + sum4 + sum5 + sum6 + sum7;
                                    if (s > target) {
                                        break;
                                    }
                                    if (s == target) {
                                        System.out.println(String.format("%d %d %d %d %d %d %d", i, j, k, l, m, n, o));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
