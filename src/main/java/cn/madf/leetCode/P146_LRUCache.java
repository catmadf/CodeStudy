package cn.madf.leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 烛影鸾书
 * @date 2020/5/25
 * @copyright© 2020
 */
public class P146_LRUCache {

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

    public P146_LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);

        head = null;
        tail = null;

    }

    public void put(Integer key, Integer value) {
        Node node = new Node(key, value);

        if (cache.containsKey(key)){
            Node oldNode = cache.get(key);
            /* 考虑oldNode是头节点还是尾节点，但是每种情况都必须将新节点放到头部 */
            if (oldNode.pre == null && oldNode.next == null) {
                head = node;
                tail = node;
            } else if (oldNode.pre == null) {
                oldNode.next.pre = node;
                node.next = oldNode.next;
                oldNode.next = null;
                head = node;

                cache.put(key, node);
            } else if (oldNode.next == null) {
                oldNode.pre.next = null;
                tail = oldNode.pre;
                oldNode.pre = null;

                /* 将node放入链表头 */
                head.pre = node;
                node.next = head;
                head = node;
            } else {
                /* 将oldNode从链表中去掉，再将node放入头部 */
                oldNode.pre.next = oldNode.next;
                oldNode.next.pre = oldNode.pre;
                oldNode.pre = null;
                oldNode.next = null;

                head.pre = node;
                node.next = head;
                head = node;
            }
        } else {
            if (tail == null) {
                head = node;
                tail = node;
            } else {
                head.pre = node;
                node.next = head;
                head = node;
            }
        }

        cache.put(key, node);

        if (removeEldestElement()) {
            int removeKey = tail.key;
            cache.remove(removeKey);

            Node newTail = tail.pre;
            if (newTail!=null) {
                newTail.next = null;
                tail.pre = null;
                tail = newTail;
            }else {
                tail = null;
            }
        }
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            /* 首先要在链表上找到该key节点，然后将该节点移到链表头部 */
            Node resNode = cache.get(key);

            /* 本身就是头结点则不动，只有不是头结点才需要将其移动到头部 */
            if (resNode.pre != null) {
                if (resNode.next == null) {
                    tail = resNode.pre;
                    tail.next = null;
                } else {
                    resNode.pre.next = resNode.next;
                    resNode.next.pre = resNode.pre;
                }
                resNode.pre = null;
                head.pre = resNode;
                resNode.next = head;
                head = resNode;
            }
            return resNode.value;
        } else {
            return -1;
        }
    }

    public int size() {
        return cache.size();
    }

    private boolean removeEldestElement() {
        return size() > capacity;
    }

}
