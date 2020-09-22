package cn.madf.练习题.广联达;

import org.yaml.snakeyaml.nodes.ScalarNode;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 烛影鸾书
 * @date 2020/9/16 21:44
 * @copyright© 2020
 */
public class Main2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long[] arr = new long[4];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextLong();
        }
        while (!(arr[0] == arr[1] && arr[1] == arr[2] && arr[2] == arr[3])) {
            Arrays.sort(arr);
            if (arr[3] - arr[2] > 2) {
                long time = (arr[3] - arr[2]) / 3;
                arr[3] -= time * 2;
                arr[0] += time;
            } else if (arr[3] - arr[2] == 2) {
                arr[3]--;
                arr[3]--;
                arr[0]++;
            } else if (arr[3] - arr[2] == 1) {
                if (arr[0] == arr[1] && arr[1] == arr[2]){
                    arr[3]--;
                }else if (arr[0] == arr[1] && arr[2] - arr[1] > 2){
                    arr[3]--;
                    arr[2]--;
                    arr[0]++;
                }else {
                    arr[3]--;
                    arr[3]--;
                    arr[0]++;
                }
            } else {
                arr[3]--;
                arr[2]--;
                arr[0]++;
            }
        }
        System.out.println(arr[3] * 4);
    }

}
