package cn.madf.练习题.神策;

import java.util.*;

/**
 * @author 烛影鸾书
 * @date 2020/9/15 20:27
 * @copyright© 2020
 */
public class Main3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

    }

    private static void pairBracket(char[] arr) {
        Deque<Integer> stack = new LinkedList<>();
        List<Integer[]> list = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') {
                stack.push(i);
            }
            if (arr[i] == ')') {
                int left = stack.pop();
                list.add(new Integer[]{left, i});
            }
        }
        list.sort(Comparator.comparingInt(o -> o[0]));
        System.out.println(list.size());
        for (Integer[] integers : list) {
            System.out.println(integers[0]);
            System.out.println(integers[1]);
        }
    }

}
