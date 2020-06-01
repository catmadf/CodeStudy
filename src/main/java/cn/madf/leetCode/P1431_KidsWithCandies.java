package cn.madf.leetCode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 烛影鸾书
 * @date 2020/6/1
 * @copyright© 2020
 */
public class P1431_KidsWithCandies {

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> res = new LinkedList<>();

        int max = Integer.MIN_VALUE;
        for (int candy : candies) {
            max = Math.max(max, candy);
        }

        for (int candy : candies) {
            if (candy+extraCandies>=max){
                res.add(true);
            } else {
                res.add(false);
            }
        }

        return res;
    }
}
