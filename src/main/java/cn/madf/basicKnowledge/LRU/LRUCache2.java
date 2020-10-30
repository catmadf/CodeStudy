package cn.madf.basicKnowledge.LRU;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 烛影鸾书
 * @date 2020/3/22
 * @copyright© 2020
 */
public class LRUCache2<K, V> {

    private int maxCacheSize;
    private final float DEFAULT_LOAD_FACTOR = 0.75f;
    private LinkedHashMap cacheMap;

    public LRUCache2(int CacheSize) {
        this.maxCacheSize = CacheSize;
        /* 计算hashmap的capacity，加1是为了保证缓存满后不会触发hashmap的扩容 */
        int capacity = (int) Math.ceil(CacheSize / DEFAULT_LOAD_FACTOR) + 1;
        cacheMap = new LinkedHashMap<K, V>(capacity, DEFAULT_LOAD_FACTOR, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > maxCacheSize;
            }
        };
    }

    /* 调用LinkedHashMap的put,get,remove等方法实现put,get,remove等，但是需要synchronized做线程同步 */
}
