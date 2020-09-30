package cn.madf.leetCode;

/**
 * @author 烛影鸾书
 * @date 2020/9/30 10:01
 * @copyright© 2020
 */
public class P701_InsertIntoBST {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        if (val < root.val) {
            if (left == null || max(left) < val) {
                root.left = new TreeNode(val);
                root.left.left = left;
                return root;
            } else {
                insertIntoBST(root.left, val);
            }
        }
        if (val > root.val) {
            if (right == null || min(right) > val) {
                root.right = new TreeNode(val);
                root.right.right = right;
                return root;
            } else {
                insertIntoBST(root.right, val);
            }
        }
        return root;
    }

    private int max(TreeNode root) {
        int max = root.val;
        TreeNode cur = root.right;
        while (cur != null) {
            max = cur.val;
            cur = cur.right;
        }
        return max;
    }

    private int min(TreeNode root) {
        int max = root.val;
        TreeNode cur = root.left;
        while (cur != null) {
            max = cur.val;
            cur = cur.left;
        }
        return max;
    }

}
