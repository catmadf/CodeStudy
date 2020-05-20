package cn.madf.左神牛客网算法课;

/**
 * 深拷贝一个带有随机指针的链表
 * 解法1：hashmap：key为原链表结点 value为对应复制结点
 * 解法2：先不管random指针，将原链表复制成如下：
 * 1 --> 1' --> 2 --> 2' ...... -->null
 *
 * @author 烛影鸾书
 * @date 2020/5/16
 * @copyright© 2020
 */
public class problem9_CopyLinkedListWithRandomPointer {
    private static class Node {
        Integer item;
        Node next;
        Node random;

        Node() {
            this.item = null;
            next = null;
            random = null;
        }

        Node(Integer item) {
            this.item = item;
            next = null;
            random = null;
        }

        Node(Integer item, Node next) {
            this.item = item;
            this.next = next;
            this.random = null;
        }

        Node(Integer item, Node next, Node random) {
            this.item = item;
            this.next = next;
            this.random = random;
        }
    }

    public static Node solution(Node head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new Node(head.item, null, head.random);
        }
        Node n1 = head;
        while (n1 != null) {
            n1.next = new Node(n1.item, n1.next);
            n1 = n1.next.next;
        }

        /* 下一步，复制random指针 */
        n1 = head;
        while (n1 != null) {
//            n1.next.random = n1.random.next;  // n1.random可能造成空指针异常
//            n1 = n1.next.next;

            n1.next.random = n1.random == null ? null : n1.random.next;
            n1 = n1.next.next;
        }

        /* 分离两个链表 */
        n1 = head;
        Node n2 = n1.next;
        Node res = n2;
        while (true) {
            n1.next = n2.next;
            n1 = n1.next;
            if (n1 == null) {
                break;
            }
            n2.next = n1.next;
            n2 = n2.next;
        }
        return res;
    }

    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.item + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.random == null ? "- " : cur.random.item + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = null;
        Node res1 = null;
        Node res2 = null;
        printRandLinkedList(head);
        res1 = solution(head);
        printRandLinkedList(res1);
        res2 = solution(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.random = head.next.next.next.next.next; // 1 -> 6
        head.next.random = head.next.next.next.next.next; // 2 -> 6
        head.next.next.random = head.next.next.next.next; // 3 -> 5
        head.next.next.next.random = head.next.next; // 4 -> 3
        head.next.next.next.next.random = null; // 5 -> null
        head.next.next.next.next.next.random = head.next.next.next; // 6 -> 4

        printRandLinkedList(head);
        res1 = solution(head);
        printRandLinkedList(res1);
        res2 = solution(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");
    }
}
