import org.junit.Test;

import java.util.*;

/**
 * @author 烛影鸾书
 * @date 2020/6/9
 * @copyright© 2020
 */
public class Test2Power {

    @Test
    public void test1() {

        /* 求num的最近的2的幂次方 */
        int num = 97;
        num--;
        num |= num >>> 1;
        num |= num >>> 2;
        num |= num >>> 4;
        num |= num >>> 8;
        num |= num >>> 16;
        System.out.println(num);

        /* 求模 */
        int p = num % 16;
        int q = num & (16-1);
        System.out.println(p == q);

        HashSet<Integer> set = new HashSet<>();

    }

    @Test
    public void test2() {
        int[] arr = new int[] {1, 5, 7, -2, 4, 63, -41, 25};
        List<Integer> list = new LinkedList<>();
        for (int i : arr) {
            list.add(i);
        }
        System.out.println(list);
        list.sort((a, b)->b-a);
        System.out.println(list);
    }

    public static void main(String[] args) {
    }
}
