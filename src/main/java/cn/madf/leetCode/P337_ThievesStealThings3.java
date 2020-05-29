package cn.madf.leetCode;

import sun.reflect.generics.tree.Tree;

import java.util.HashMap;
import java.util.Map;

/**
 * tag: tree, tree dp, 树形动规
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 * <p>
 * 示例 1:
 * 输入: [3,2,3,null,3,null,1]
 * <p>
 * 3
 * / \
 * 2   3
 * \   \
 * 3   1
 * <p>
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * <p>
 * 示例 2:
 * 输入: [3,4,5,1,3,null,1]
 * <p>
 * 3
 * / \
 * 4   5
 * / \   \
 * 1   3   1
 * <p>
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 * <p>
 * 考虑一个最优子结构：
 * 1
 * /   \
 * 1     9
 * / \    / \
 * 9   9  1   1
 * 爷爷节点构成的子树所能偷到的钱一定是[两个儿子构成的子树偷到的钱]和[四个孙子偷到的钱加上爷爷自身的钱]，两者中的最大值。
 * 注意定义，我们定义的是儿子这颗子树偷到的钱，已经包含了除去自身只偷两个孙子的情况
 * 或者再观察上述3层满二叉树，可以将孙子节点下的null视为0节点，在左儿子子树中偷到的钱为9+9=18，在右儿子子树中偷到的钱为
 * 0+0+0+0+9=9，实际上[两个儿子子树]的情况包含了[一边两个孙子一边一个儿子]的情况
 * <p>
 * 显然求子树的解的过程是一个递归过程，但是这个递归过程存在重复子结构，想象一下斐波拉契数列的递归解法。
 * 有很多节点的解重复计算了。在第一道小偷偷钱的问题中，我们最开始用一个数组来记录求解过程，后来发现我们每次只会用到最近
 * 的两次求解过程。同样，本题中节点的解求出来后可以将其保存下来，避免掉重复计算子问题，性能上有不少提升。
 *
 * @author 烛影鸾书
 * @date 2020/5/29
 * @copyright© 2020
 */
public class P337_ThievesStealThings3 {
    private Map<TreeNode, Integer> map = new HashMap<>();

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int rob(TreeNode root) {
        return stealSubTree(root);
    }

    private int stealSubTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (map.containsKey(root)) {
            return map.get(root);
        }

        int money = root.val;
        /* 1.计算两个儿子子树的解
        *  2.计算四个孙子子树的解（需要注意空指针问题，即儿子存在时才有孙子） */
        if (root.left!=null) {
            money += stealSubTree(root.left.left) + stealSubTree(root.left.right);
        }
        if (root.right!=null) {
            money += stealSubTree(root.right.left) + stealSubTree(root.right.right);
        }
        money = Math.max(money, stealSubTree(root.left)+stealSubTree(root.right));
        map.put(root, money);
        return money;
    }

}
