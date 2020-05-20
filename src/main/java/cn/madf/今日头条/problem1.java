package cn.madf.今日头条;

import java.util.*;

/**
 * 给定一个数组arr，以及一个三元组(l, r, num), 计算arr中索引l到r中num的个数；
 * 现有一批的这种元组[(...),(...),...,(...)]，请输出一个数组，数组中的值即输入元组执行上述计算后得结果，一一对应。
 *
 * @author 烛影鸾书
 * @date 2020/5/6
 * @copyright© 2020
 */
public class problem1 {

    public static int[] solution(int[] array, List<int[]> triples) {
        /* 预处理：用一个map来存预处理后的结果，map中的key为array中的数，对应的value为list，list中放key在array中的索引 */
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            int finalI = i;
            map.compute(array[i], (k, v) -> {
                if (v == null) {
                    v = new ArrayList<>();
                    v.add(finalI);
                } else {
                    v.add(finalI);
                }
                return v;
            });
        }
        /* 接下来只需要对输入中的三元组遍历，从map中查询获取结果 */
        int[] res = new int[triples.size()];
        int i = 0;
        for (int[] triple : triples) {
            res[i++] = process(triple, map);
        }
        return res;
    }

    private static int process(int[] arr, Map<Integer, ArrayList<Integer>> map) {
        int left = arr[0];
        int right = arr[1]+1;
        int num = arr[2];
        ArrayList<Integer> numIx = map.get(num);

        /* 在numIx中找到left,right的位置,以此计算指定索引段num的个数 */
        int a = compute(numIx, left);
        int b = compute(numIx, right);
        return b-a;
    }

    private static int compute(ArrayList<Integer> numIx, int n) {
        int l = 0;
        int r = numIx.size() - 1;
        if (r == -1) {
            return 0;
        }
        int flag = -1;
        /* 找到numIx中小于n的数的最大的数的索引 */
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (numIx.get(mid) < n) {
                flag = mid;
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return flag + 1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 1, 2, 4, 1, 1, 2, 4, 6, 7, 4, 5, 6, 3, 1, 3, 7, 8, 9, 0, 0, 1, 2, 3};
        List<int[]> list = new LinkedList<>();
        list.add(new int[]{0, 6, 0});
        System.out.println(Arrays.toString(solution(arr, list)));
    }
}
