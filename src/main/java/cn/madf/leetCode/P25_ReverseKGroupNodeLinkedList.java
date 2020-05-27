package cn.madf.leetCode;

import java.awt.*;
import java.util.List;

/**
 * tag: linkedList,
 * <p>
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 示例：
 * <p>
 * 给你这个链表：1->2->3->4->5
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * <p>
 * 说明：
 * <p>
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * @author 烛影鸾书
 * @date 2020/5/27
 * @copyright© 2020
 */
public class P25_ReverseKGroupNodeLinkedList {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) {
            return head;
        }
        int p = 0;
        /* 伪双指针 */
        ListNode lp = new ListNode(0);
        ListNode rp = new ListNode(0);
        lp.next = head;
        rp.next = head;

        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;

        ListNode lastTail = fakeHead;

        while (rp.next != null) {
            if (rp.val % k == k - 1) {
                lastTail = reverseSubList(lp, rp, lastTail);

                lp.next = lastTail.next;
                rp.next = lp.next;
                rp.val = rp.val + 1;
                lp.val = rp.val;
            } else {
                rp.next = rp.next.next;
                rp.val++;
            }
        }

        return fakeHead.next;
    }

    /* 将lp.next指向的节点到rp.next指向的节点翻转, 连接上一个和下一个子链表，同时将翻转后的子链表尾返回 */
    private ListNode reverseSubList(ListNode lp, ListNode rp, ListNode lastTail) {
        ListNode n1 = lp.next;
        ListNode n2 = rp.next;

        ListNode nextHead = n2.next;  // 将下一个子链表头记下来

        n2.next = null;
        n2 = n1.next;
        n1.next = nextHead;
        ListNode n3 = null;

        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }

        /* 将上一个子链表尾连在翻转后的子链表头 */
        lastTail.next = n1;

        return lp.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        P25_ReverseKGroupNodeLinkedList p25 = new P25_ReverseKGroupNodeLinkedList();
        head = p25.reverseKGroup(head, 2);
        System.out.println();
    }


}
