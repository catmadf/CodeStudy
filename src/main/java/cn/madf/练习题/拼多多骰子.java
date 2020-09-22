package cn.madf.练习题;

import lombok.ToString;

import java.io.PushbackInputStream;
import java.util.*;

/**
 * @author 烛影鸾书
 * @date 2020/8/2 19:48
 * @copyright© 2020
 */
public class 拼多多骰子 {

    private static class OppTuple {
        int a;
        int b;

        public OppTuple(int i, int j) {
            a = i;
            b = j;
        }

        @Override
        public String toString() {
            return a + " " + b;
        }
    }

    private static class Dice {
        OppTuple ud;
        OppTuple lr;
        OppTuple fb;

        public Dice(String line) {
            String[] item = line.trim().split(" ");
            int[] nums = new int[6];
            for (int i = 0; i < item.length; i++) {
                nums[i] = Integer.parseInt(item[i]);
            }
            ud = new OppTuple(nums[0], nums[1]);
            lr = new OppTuple(nums[2], nums[3]);
            fb = new OppTuple(nums[4], nums[5]);
        }

        public String getLabel() {
            int[][] pad = new int[3][4];
            pad[1][1] = 1;
            pad[1][3] = findOpp(1);
            return ud.a + "" + ud.b + "" + lr.a + "" + lr.b;
        }

        private int findOpp(int i) {
            if (ud.a == i || ud.b == i) {
                return ud.a == i ? ud.b : ud.a;
            } else if (lr.a == i || lr.b == i) {
                return lr.a == i ? lr.b : lr.a;
            } else {
                return fb.a == i ? fb.b : fb.a;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Dice> diceList = new LinkedList<>();
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            diceList.add(new Dice(s));
        }

        Map<String, Integer> labelCount = new HashMap<>();
        for (Dice dice : diceList) {
            String key = dice.getLabel();
            labelCount.put(key, labelCount.getOrDefault(key, 0) + 1);
        }

        int[] count = new int[labelCount.size()];
        int i = 0;
        for (String s : labelCount.keySet()) {
            count[i++] = labelCount.get(s);
        }
        Arrays.sort(count);

        System.out.println(count.length);
        for (int j = count.length - 1; j > 0; j--) {
            System.out.print(count[j] + " ");
        }
        System.out.print(count[0]);
    }

}
