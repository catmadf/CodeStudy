package cn.madf.leetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author 烛影鸾书
 * @date 2020/10/28 16:47
 * @copyright© 2020
 */
public class P1207_UniqueOccurrences {

    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer integer : arr) {
            map.compute(integer, (k, v)->{
                if (v == null) {
                    return 1;
                }
                return v+1;
            });
        }
        Set<Integer> set = new HashSet<>(map.values());
        return set.size() == map.size();
    }

}
