package cn.madf.leetCode;

/**
 * tag: tree
 * 根据前序遍历和中序遍历构造二叉树，假设没有重复元素。
 *
 * 解：根据先序遍历特性，先序遍历数组的第一个数即为以该先序遍历数组所有数为结点的二叉树的根节点；
 *     根据中序遍历特性，中序遍历数组的根节点左边为左子树的所有结点，右边为右子树的所有结点。
 *
 * @author 烛影鸾书
 * @date 2020/5/22
 * @copyright© 2020
 */
public class P105_ConstructTreeByPreOrderAndInOrder {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static TreeNode solution(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return subtree(preorder, inorder, 0, 0, preorder.length);
    }

    /**
     * @param preorder 先序遍历
     * @param inorder  中序遍历
     * @param preStart 构建的子树包含的结点位于先序遍历的段-起点
     * @param inStart  构建的子树包含的结点位于中序遍历的段-起点
     * @param len      构建的子树包含的结点数
     * @return 子树
     */
    private static TreeNode subtree(int[] preorder, int[] inorder, int preStart, int inStart, int len) {
        if (len == 0) {
            return null;
        }
        /* 根据先序遍历确定根节点：先序遍历第一个数即为根节点 */
        TreeNode root = new TreeNode(preorder[preStart]);
        /* 根据中序遍历确定左右子树段：中序遍历中根节点左边即为左子树，右边即为右子树 */
        for (int i = inStart; i < inStart + len; i++) {
            if (inorder[i] == preorder[preStart]) {
                root.left = subtree(preorder, inorder, preStart + 1, inStart, i - inStart);
                root.right = subtree(preorder, inorder, preStart + 1 + i - inStart, i + 1, inStart+len-i-1);
                break;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        TreeNode tree = solution(preorder, inorder);
    }
}
