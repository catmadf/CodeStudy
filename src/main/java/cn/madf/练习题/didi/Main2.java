package cn.madf.练习题.didi;

import java.util.*;

/**
 * @author 烛影鸾书
 * @date 2020/9/13 19:55
 * @copyright© 2020
 */
public class Main2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int k = sc.nextInt();
            UnionFindSet unionFindSet = new UnionFindSet();
            for (int j = 0; j < n; j++) {
                unionFindSet.put(j+1);
            }
            for (int j = 0; j < m; j++) {
                int p = sc.nextInt();
                int q = sc.nextInt();
                int w = sc.nextInt();
                if (w <= k) {
                    unionFindSet.union(p, q);
                }
            }
            boolean flag = bridge(unionFindSet, n);
            if (flag) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }

        }
    }

    private static boolean bridge(UnionFindSet set, int n) {
        for (int i = 0; i < n; i++) {
            if (set.setSize(i+1) == n) {
                return true;
            }
        }
        return false;
    }


    static class UnionFindSet {
        private Map<Integer, Integer> fatherMap;
        private Map<Integer, Integer> sizeMap;

        public UnionFindSet() {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public void put(Integer i) {
            fatherMap.put(i, i);
            sizeMap.put(i, 1);
        }

        /* 查找代表节点 */
        public Integer findHead(Integer i) {
            Integer father = fatherMap.get(i);
            if (!father.equals(i)) {
                father = findHead(father);
            }
            fatherMap.put(i, father);
            return father;
        }

        public void union(Integer a, Integer b) {
            if (a == null || b == null) {
                return;
            }
            Integer aHead = findHead(a);
            Integer bHead = findHead(b);
            if (!aHead.equals(bHead)) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                if (aSetSize <= bSetSize) {
                    fatherMap.put(aHead, bHead);
                    sizeMap.put(bHead, aSetSize + bSetSize);
                } else {
                    fatherMap.put(bHead, aHead);
                    sizeMap.put(aHead, aSetSize + bSetSize);
                }
            }
        }

        public int setSize(Integer i) {
            return sizeMap.get(i);
        }
    }

}
