package cn.madf.左神牛客网算法课;

/**
 * tag: tree
 * 计算一个完全二叉树的节点数
 * 提示：满二叉树的结点数为2^h-1，h为树深；
 * <p>
 * 遍历根节点左子树左边界获取树深
 * 可以遍历根节点的右子树的左边界，如果能到最后一层，说明左子树是满的；如果不能到最后一层，根据平衡性，右子树是满的
 * 可以递归求解整个过程
 *
 * 算法时间复杂度为O((logN)^2)
 *
 * @author 烛影鸾书
 * @date 2020/5/27
 * @copyright© 2020
 */
public class problem15_CountNodeInCBT {
    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int v) {
            value = v;
        }
    }

    public int countNode(Node head) {
        if (head == null) {
            return 0;
        }
        return countNodeSubtree(head, 1, leftDepth(head));
    }

    private int countNodeSubtree(Node node, int level, int depth) {
        if (level == depth) {  // 等价于node.left==null&&node.right==null;
            return 1;
        }
        /* 右子树左边界是否到底 */
        if (leftDepth(node.left) == depth - level) {
            /* 左子树为满二叉树 */
            return (1 << (depth - level)) - 1 + 1 + countNodeSubtree(node.right, level + 1, depth);
        } else {
            /* 右子树为满二叉树，但是叶子节点在整个大树的倒数第二层（基于完全二叉树推导） */
            return countNodeSubtree(node.left, level + 1, depth) + (1 << (depth - level - 1)) - 1 + 1;
        }
    }

    private int leftDepth(Node node) {
        /* 沿着左边界计算node节点为根的子树的深度 */
        int depth = 1;
        while (node != null) {
            node = node.left;
            depth++;
        }
        return depth - 1;
    }
}
