package cn.madf.leetCode;

import java.util.Arrays;

/**
 * @author 烛影鸾书
 * @date 2020/10/28 17:07
 * @copyright© 2020
 */
public class P451_SortByFrequencyOfCharacterOccurrence {

    public String frequencySort(String s) {
        int[][] count = new int[128][2];
        for (int i = 0; i < count.length; i++) {
            count[i][0] = i;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            count[c][1]++;
        }

        Arrays.sort(count, (o1, o2) -> o2[1] - o1[1]);
        StringBuilder sb = new StringBuilder();
        for (int[] ints : count) {
            if (ints[1] == 0) {
                break;
            }
            for (int i = 0; i < ints[1]; i++) {
                sb.append((char) (ints[0]));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        P451_SortByFrequencyOfCharacterOccurrence p451 = new P451_SortByFrequencyOfCharacterOccurrence();
        p451.frequencySort("tree");
    }

}
