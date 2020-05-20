package cn.madf.左神牛客网算法课;

/**
 * 判断一个链表是否是回文链表
 * 解法1：应用栈先进后出的特性，将链表压进栈，然后从头开始遍历，每遍历一个同时出栈一个比较
 * 解法2：优化解法1，实际上并不需要比较全部链表，只需要将后半部分链表压进栈即可
 * 解法3：在原链表上操作，用快慢指针，将后半部分逆序，然后指针从两头分别向中间走，比较即可。该解法O(1)时间复杂度。
 *
 * @author 烛影鸾书
 * @date 2020/5/16
 * @copyright© 2020
 */
public class problem7_PalindromeList {
    private static class Node {
        int item;
        Node next = null;
    }

    public static boolean solution(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node n1 = head;
        Node n2 = head;
        /* 先找到中间结点，n1走一步，n2走两步 */
        while (n2.next != null && n2.next.next != null) {
            /* 不管奇偶，n1最后都会在中间结点（奇数）或中间前一个（偶数） */
            n1 = n1.next;
            n2 = n2.next.next;
        }
        /* 将后半部分链表原地逆序 */
        n2 = n1.next;
        n1.next = null;
        Node n3 = null;
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }  // 此时n1指向链表未变动时的最后一个结点，n2=n3=null
        /* 将n2指向头结点 */
        n3 = n1;   // 存档，后续用来恢复顺序
        n2 = head;
        boolean res = true;
        while (n2 != null && n1 != null) {
            if (n2.item != n1.item) {
                res = false;  // 此时不能返回，因为链表右半部分逆序的，要先恢复原状
                break;
            }
            n2 = n2.next;
            n1 = n1.next;
        }
        n1 = n3.next;
        n3.next = null;
        while (n1!=null){
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
    }

    public static void main(String[] args) {
        Node a = new Node();
    }


}
