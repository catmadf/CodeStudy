package cn.madf.左神牛客网算法课;

import sun.security.action.PutAllAction;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 设计一个随机池
 * put：不重复的放入数据
 * getRandom：等概率的返回某一个数据
 * delete：删除指定数据
 * 要求时间复杂度都为O(1)
 *
 * @author 烛影鸾书
 * @date 2020/5/29
 * @copyright© 2020
 */
public class problem16_RandomPool {

    public static class RandomPool {
        private Map<String, Integer> map1;
        private Map<Integer, String> map2;
        private int size = 0;

        public RandomPool() {
            map1 = new HashMap<>();
            map2 = new HashMap<>();
            size = 0;
        }

        public void put(String s) {
            if (!map1.containsKey(s)) {
                map1.put(s, size);
                map2.put(size, s);
                size++;
            }
        }

        public String getRandom() {
            if (size == 0) {
                return null;
            }
            int index = (int) (Math.random() * size);
            return map2.get(index);
        }

        public void delete(String s) {
            if (!map1.containsKey(s)) {
                return;
            }
            /* 正常删除会使索引中间出现断点，这样随机数就会有问题；删除时可以将最后一个索引的数据补到断点上 */
            int delIndex = map1.remove(s);
            String lastS = map2.get(size - 1);
            map1.put(lastS, delIndex);
            map2.remove(size - 1);
            map2.put(delIndex, lastS);
        }
    }
}
