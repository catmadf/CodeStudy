package cn.madf.练习题.meituan;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author 烛影鸾书
 * @date 2020/9/20 11:30
 * @copyright© 2020
 */
public class Main4 {
    static class Node {
        int id;
        int score;
        int level;

        Node pre;  // 指向id比自己小的
        Node nxt;  // 指向id比自己大的

        Node(int i, int j) {
            id = i;
            score = j;
        }
    }

    public static void main(String[] args) {
        Map<Integer, Node> map = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int s = sc.nextInt();
            map.put(i + 1, new Node(i + 1, s));
        }


        for (int i = 0; i < n - 1; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            Node min = map.get(Math.min(a, b));
            Node max = map.get(Math.max(a, b));
            min.nxt = max;
            max.pre = min;
        }
        int r = sc.nextInt();
        Node root = map.get(r);

        Node cur = root;
        int l = 0;
        int rMax = 0, rMin = 0;
        while (cur!=null) {
            cur.level = l;
            l++;
            if (l <= k) {
                rMax = Math.max(rMax, cur.score);
                rMin = Math.max(rMin, cur.score);
            }
            cur = cur.nxt;
        }
        l = 0;
        cur = root;
        int lMax = 0, lMin = 0;
        while (cur!=null) {
            cur.level = l;
            l++;
            if (l <= k) {
                lMax = Math.max(lMax, cur.score);
                lMin = Math.max(lMin, cur.score);
            }
            cur = cur.pre;
        }
        int min = Math.min(Math.min(lMax- lMin, rMax-rMin), Math.min(rMax-lMin, lMax - lMin));

        cur = root;
        Node curk = root;
        while (cur != null) {

        }

    }

}
