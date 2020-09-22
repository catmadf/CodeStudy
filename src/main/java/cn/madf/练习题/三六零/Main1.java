package cn.madf.练习题.三六零;

import com.sun.org.apache.xml.internal.security.signature.reference.ReferenceOctetStreamData;

import java.util.Scanner;

/**
 * @author 烛影鸾书
 * @date 2020/9/11 20:44
 * @copyright© 2020
 */
public class Main1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] records = new int[n];   // 0代表默认无记录，1代表一条记录，2代表两条记录
        int start = 0;
        int end = 0;
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            if (b == 1) {
                records[a - 1]++;
            } else {
                records[a - 1]++;
            }
            if (i == 0) {
                start = a - 1;
            }
            if (i == m - 1) {
                end = a - 1;
            }
        }
        for (int i = 0; i < records.length; i++) {
            if (i == start && records[i] == 2) {
                System.out.print(i + 1);
            }
            if (records[i] == 0) {
                System.out.print(i + 1);
            }
        }
        System.out.println();
    }

}
