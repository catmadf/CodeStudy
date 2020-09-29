package cn.madf.剑指offer;

import java.util.List;

/**
 * @author 烛影鸾书
 * @date 2020/9/29 16:24
 * @copyright© 2020
 */
public class Offer24_ReverseList {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /* 头插法翻转链表 */
    public ListNode reverseList1(ListNode head) {
        /* 伪头节点 */
        ListNode fakeHead = new ListNode(-1);
        ListNode cur = head;
        ListNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = fakeHead.next;
            fakeHead.next = cur;
            cur = next;
        }
        return fakeHead.next;
    }

    /* 就地翻转 */
    public ListNode reverseList2(ListNode head) {
        if (head == null) {
            return null;
        }
        /* 伪头节点 */
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;
        ListNode last = fakeHead.next;
        ListNode cur = head.next;
        while (cur != null) {
            last.next = cur.next;
            cur.next = fakeHead.next;
            fakeHead.next = cur;
            cur = last.next;
        }
        return fakeHead.next;
    }

}
