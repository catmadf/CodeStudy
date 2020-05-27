package cn.madf.左神牛客网算法课;

import java.util.LinkedList;
import java.util.Queue;

/**
 * tag: tree, traverse by level
 * 1：使用层序遍历方式来序列化tree，当然也可以用先序中序等，关键是要将null节点一并打印
 * 2：使用层序遍历来判断一颗树是否是完全二叉树
 *
 * @author 烛影鸾书
 * @date 2020/5/26
 * @copyright© 2020
 */
public class problem14_HierarchyTraversalOfTree {
    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int v) {
            value = v;
        }
    }

    public static String serializeTreeByLevel(Node head) {
        if (head == null) {
            return "#_";
        }
        StringBuilder res = new StringBuilder(head.value + "_");
        /* 使用一个queue来保存同级的其他节点 */
        Queue<Node> queue = new LinkedList<>();
        Node node = head;
        queue.offer(node);
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node.left != null) {
                res.append(node.left.value).append("_");
                queue.offer(node.left);
            } else {
                res.append("#_");
            }
            if (node.right != null) {
                res.append(node.right.value).append("_");
                queue.offer(node.right);
            } else {
                res.append("#_");
            }
        }
        return String.valueOf(res);
    }

    public static Node deserializeTreeByString(String s) {
        String[] values = s.split("_");
        int index = 0;
        Node head = generateNode(values[index++]);
        Queue<Node> queue = new LinkedList<>();
        if (head != null) {
            queue.offer(head);
        }
        Node node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = generateNode(values[index++]);
            node.right = generateNode(values[index++]);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return head;
    }

    private static Node generateNode(String v) {
        if (v.equals("#")) {
            return null;
        }
        return new Node(Integer.parseInt(v));
    }

    public static boolean isCompleteBinaryTree(Node head) {
        if (head == null) {
            return true;
        }
        Node node = head;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        Node l, r;
        boolean leaf = false;
        /* 层序遍历 */
        while (!queue.isEmpty()) {
            node = queue.poll();
            l = node.left;
            r = node.right;
//            if (leaf) {
//                if (l != null || r != null) {
//                    return false;
//                }
//            } else {
//                if (l == null && r != null) {
//                    return false;
//                }
//            }
            if ((leaf && (l != null || r != null)) || (l == null && r != null)) {
                return false;
            }

            if (l != null) {
                queue.offer(l);
            }
            if (r != null) {
                queue.offer(r);
            } else {
                leaf = true;
            }
        }
        return true;
    }

}
