package cn.madf.左神牛客网算法课;

/**
 * 后继结点：中序遍历中该节点的后一个结点为该节点的后继结点
 * 前驱结点：中序遍历总该节点的前一个结点为该节点的前驱结点
 * <p>
 * 当然可以打印中序遍历方式来找，但这样不高效。
 * 一个节点A的后继节点必然在它的右子树上，如果没有右子树，顺着父节点网上找，找到一个节点B的左子树中最后一个节点是A时
 * B就是A的后继节点
 *
 * @author 烛影鸾书
 * @date 2020/5/25
 * @copyright© 2020
 */
public class problem13_FindSuccessorNodeInBinaryTree {
    private static class Node {
        int value;
        Node parent;  // 我们约定根节点的父结点指向null
        Node left;
        Node right;

        Node(int v) {
            value = v;
        }
    }

    public Node findSuccessorInBinaryTree(Node node) {
        if (node == null) {
            return null;
        }

        if (node.right != null) {
            /* 找右子树中序遍历的第一个节点 */
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        } else {
            Node parent = node.parent;
            while (parent != null) {
                /* node是它父节点的左孩子 */
                if (parent.left == node) {
                    return parent;
                } else {
                    node = parent;
                    parent = parent.parent;
                }
            }
            return null;
        }
    }

    public void inOrderRecur(Node node) {
        if (node == null) {
            return;
        }

        inOrderRecur(node.left);
        System.out.print(node.value + " ");
        inOrderRecur(node.right);
    }

    public static void main(String[] args) {
        problem13_FindSuccessorNodeInBinaryTree p13 = new problem13_FindSuccessorNodeInBinaryTree();

        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;
        p13.inOrderRecur(head);
        System.out.println();

        Node test = head.left.left;
        System.out.println(test.value + " next: " + p13.findSuccessorInBinaryTree(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + p13.findSuccessorInBinaryTree(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + p13.findSuccessorInBinaryTree(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + p13.findSuccessorInBinaryTree(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + p13.findSuccessorInBinaryTree(test).value);
        test = head;
        System.out.println(test.value + " next: " + p13.findSuccessorInBinaryTree(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + p13.findSuccessorInBinaryTree(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + p13.findSuccessorInBinaryTree(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + p13.findSuccessorInBinaryTree(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + p13.findSuccessorInBinaryTree(test));
    }
}
