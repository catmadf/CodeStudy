package cn.madf.LRU;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 烛影鸾书
 * @date 2020/3/22
 * @copyright© 2020
 * 继承LinkedHashMap来实现LRU缓存
 *
 */
public class LRUCache1<K, V> extends LinkedHashMap<K, V> {

    /* 缓存的大小 */
    private int maxCacheSize;

    public LRUCache1(int cacheSize) {
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        maxCacheSize = cacheSize;
    }

    /* 重写removeEldestEntry()方法，当大小超出设定的缓存大小值以后返回true, 移除最早的数据 */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxCacheSize;
    }

    public static void main(String[] args) {
        LRUCache1<Integer, Integer> cache = new LRUCache1<Integer, Integer>(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);
        cache.put(2, 3);
        cache.put(3, 3);
        cache.put(4, 4);
    }
}
