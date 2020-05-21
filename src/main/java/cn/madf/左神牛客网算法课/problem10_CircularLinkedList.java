package cn.madf.左神牛客网算法课;

/**
 * 判断一个链表是否有环，有则返回第一个入环点
 * 解法1：hashSet缓存
 * 解法2：快慢指针
 *
 * @author 烛影鸾书
 * @date 2020/5/18
 * @copyright© 2020
 */
public class problem10_CircularLinkedList {
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

    public static Node solution(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node fastPointer = head.next.next;
        Node slowPointer = head.next;
        /* 第一次相遇，判断是否有环 */
//        while (fastPointer.next != null && fastPointer.next.next != null) {
//            fastPointer = fastPointer.next.next;
//            slowPointer = slowPointer.next;
//            if (fastPointer == slowPointer) {
//                isCircular = true;
//                break;
//            }
//        }
        while (fastPointer != slowPointer) {
            if (fastPointer.next == null || slowPointer.next == null) {
                return null;
            }
            fastPointer = fastPointer.next.next;
            slowPointer = slowPointer.next;
        }

        /* 到这儿说明有环，fastPointer回到头节点，和slowPointer一样速度跑，他们相遇点即第一个入环点 */
        fastPointer = head;
        while (fastPointer != slowPointer) {
            fastPointer = fastPointer.next;
            slowPointer = slowPointer.next;
        }
        return fastPointer;
    }

    public static void main(String[] args) {

    }

}
