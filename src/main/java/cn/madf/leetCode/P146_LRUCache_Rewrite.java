package cn.madf.leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 上一个LRUCache解答写得跟屎一样，于是重做该题
 * 对于上次做题的反思：
 * 1：链表头尾表示可以换成两个Node类型的伪指针，这样就可以避免掉很多null的判断
 * 2：注意一下代码的结构，保证接口的完整性
 * <p>
 * 对于put操作，分为插入新数据和更新数据两种情况：插入新数据时，只需将新节点插到链表头即可；更新数据，则需要获取到节点并修改，然后移动到头部
 * 对于get操作，获取不到返回-1，获取到就将该节点移动到头部
 * 插入新数据后要维护一下hashmap，超过capacity，则将链表尾的节点删除即可。
 *
 * @author 烛影鸾书
 * @date 2020/5/25
 * @copyright© 2020
 */
public class P146_LRUCache_Rewrite {

    private class Node {
        int key;
        int value;

        Node pre = null;
        Node next = null;

        Node() {

        }

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private Map<Integer, Node> cache;
    private Node head;
    private Node tail;

    public P146_LRUCache_Rewrite(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);

        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;

    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        /* key不存在于cache中时 */
        if (node == null) {
            Node newNode = new Node(key, value);
            addToHead(newNode);
            cache.put(key, newNode);

            /* 插入新数据需要维护一下LRU的特性 */
            if (removeEldestElement()) {
                int removeKey = removeTail();
                cache.remove(removeKey);
            }
        }
        /* key已存在 */
        else {
            /* 修改oldNode的value */
            node.value = value;
            /* 移动节点到头部 */
            moveToHead(node);
        }
    }

    public int get(int key) {
        Node resNode = cache.get(key);
        if (resNode == null) {
            return -1;
        }

        moveToHead(resNode);

        return resNode.value;
    }

    public void moveToHead(Node node) {
        remove(node);
        addToHead(node);
    }

    private void addToHead(Node node) {
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }

    private int removeTail() {
        Node removeNode = tail.pre;
        remove(removeNode);
        return removeNode.key;
    }

    private void remove(Node removeNode) {
        removeNode.pre.next = removeNode.next;
        removeNode.next.pre = removeNode.pre;
    }

    private boolean removeEldestElement() {
        return size() > capacity;
    }

    public int size() {
        return cache.size();
    }
}
