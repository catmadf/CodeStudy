package cn.madf.左神牛客网算法课;

/**
 * 给定一个链表和一个数num，将链表调整为左边的数小于num，中间的等于num，右边的大于num。
 * 要求时间复杂度O(N)，空间复杂度O(1)，并且保证调整后的链表的三部分中各自部分中的结点前后顺序与原链表相同（即要求稳定性）
 *
 * @author 烛影鸾书
 * @date 2020/5/16
 * @copyright© 2020
 */
public class problem8_LinkedLisDutchFlag {
    private static class Node {
        Integer item;
        Node next;

        Node() {
            this.item = null;
            next = null;
        }

        Node(Integer item) {
            this.item = item;
            this.next = null;
        }

        Node(Integer item, Node next) {
            this.item = item;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node node = this.next;
            sb.append(item);
            while (node != null) {
                sb.append(" --> ");
                sb.append(node.item);
                node = node.next;
            }
            sb.append(" --> null");
            return sb.toString();
        }
    }

    public static void solution(Node head, int num) {
        if (head == null || head.next == null) {
            return;
        }
        Node n = head;
        Node less = new Node();
        Node eq = new Node();
        Node more = new Node();
        Node n1 = less;
        Node n2 = eq;
        Node n3 = more;
        while (n != null) {
            if (n.item < num) {
                n1.next = n;
                n1 = n1.next;
            } else if (n.item == num) {
                n2.next = n;
                n2 = n2.next;
            } else {
                n3.next = n;
                n3 = n3.next;
            }
            n = n.next;
        }
        if (eq.next != null) {
            n1.next = eq.next;
            n2.next = more.next;
        } else {
            n1.next = more.next;
        }
    }

    public static void main(String[] args) {
        Node a = new Node(1, new Node(9, new Node(4, new Node(3, new Node(6, new Node(5, null))))));
        System.out.println(a);
        solution(a, 4);
        System.out.println(a);
        Node b = new Node(5, new Node(8, new Node(9, new Node(6, null))));
        System.out.println(b);
        solution(b, 4);
        System.out.println(b);
        Node c = new Node(3, new Node(2, new Node(0, new Node(1, null))));
        System.out.println(c);
        solution(c, 4);
        System.out.println(c);
    }

}
