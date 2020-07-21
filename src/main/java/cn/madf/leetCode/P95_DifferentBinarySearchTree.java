package cn.madf.leetCode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 烛影鸾书
 * @date 2020/7/21
 * @copyright© 2020
 */
public class P95_DifferentBinarySearchTree {
    private static class TreeNode {
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

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<>();
        }

        return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> allSubTree = new LinkedList<>();

        if (start > end) {
            allSubTree.add(null);
            return allSubTree;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> leftSubTrees = generateTrees(start, i - 1);
            List<TreeNode> rightSubTrees = generateTrees(i + 1, end);

            for (TreeNode leftSubTree : leftSubTrees) {
                for (TreeNode rightSubTree : rightSubTrees) {
                    TreeNode rootNode = new TreeNode(i);
                    rootNode.left = leftSubTree;
                    rootNode.right = rightSubTree;
                    allSubTree.add(rootNode);
                }
            }
        }

        return allSubTree;
    }
}
