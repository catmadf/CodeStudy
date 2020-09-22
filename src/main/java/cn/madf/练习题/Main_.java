package cn.madf.练习题;

import java.math.BigInteger;
import java.util.*;

public class Main_ {
    static BigInteger bi = new BigInteger("0");
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int i=0;i<T;i++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int k = sc.nextInt();
            int[][] arr = new int[m][k];
            for(int j=0;j<m;j++) {
                for(int p=0;p<k;p++) {
                    arr[j][p] = sc.nextInt();
                }
            }
            int[] temp = new int[n];
            helper(0,arr,temp,m,n,k);
            bi = bi.mod(new BigInteger("1000000007"));
            System.out.println(bi.intValue());
        }
        sc.close();
    }

    public static void helper(int index,int[][] arr,int[] temp,int m,int n,int k) {
        if(index == n) {
            bi = bi.add(new BigInteger("1"));
            return;
        }

        if(index == 0) {
            for(int i=0;i<m;i++) {
                temp[index] = i+1;
                helper(index+1,arr,temp,m,n,k);
            }
        }else {
            for(int i=0;i<m;i++) {
                boolean flag = true;
                for(int j=0;j<k;j++) {
                    if(i+1 == arr[index-1][j]) {
                        flag = false;
                        break;
                    }
                }
                if(flag) {
                    temp[index] = i+1;
                    helper(index+1,arr,temp,m,n,k);
                }
            }
        }


    }


}