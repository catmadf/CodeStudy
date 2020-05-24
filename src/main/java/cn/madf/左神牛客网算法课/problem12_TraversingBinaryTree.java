package cn.madf.左神牛客网算法课;

import java.util.Stack;

/**
 * tag: tree
 * 二叉树的遍历
 *
 * <p>递归解法和非递归解法 : 本质都是栈
 *
 * <p>为什么要用栈？ 原因，二叉树节点只有从上到下的路径，没办法回到上一层节点，这就需要我们像一个办法让它从从子节点回到父节点，
 * 栈这个结构天然合适，先进的后出，父节点先访问一次进去，等孩子访问完出来后又轮到了父节点
 *
 * @author 烛影鸾书
 * @date 2020/5/19
 * @copyright© 2020
 */
public class problem12_TraversingBinaryTree {
    private static class Node {
        public int data;
        public Node left;
        public Node right;
    }

    public static void preOrderRecur(Node node) {
        if (node == null) {
            return;
        }

        System.out.println(node.data + " ");
        preOrderRecur(node.left);
        preOrderRecur(node.right);
    }

    public static void inOrderRecur(Node node) {
        if (node == null) {
            return;
        }

        preOrderRecur(node.left);
        System.out.println(node.data + " ");
        preOrderRecur(node.right);
    }

    public static void posOrderRecur(Node node) {
        if (node == null) {
            return;
        }

        preOrderRecur(node.left);
        preOrderRecur(node.right);
        System.out.print(node.data + " ");
    }

    public static void preOrderNotRecur(Node node) {
        System.out.print("pre_order: ");
        if (node != null) {
            Stack<Node> stack = new Stack<>();
            Node cur = node;
            stack.add(cur);
            while (!stack.isEmpty()) {
                cur = stack.pop();
                System.out.print(cur.data + " ");
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }
        }
        System.out.println();
    }

    /**
     * 对于一颗树 ：
     * 1
     * 2       3
     * 4    5   6    7
     * -中序遍历输出顺序为：4 2 5 1 6 3 7
     * -根据栈先进后出的特性，可以初步想象一下： 4 应该是后进栈的 ...
     * -但是能不能让 7 最先进栈呢？ 很明显不用递归，当我们遍历到 7 并没有办法回到父结点，因此不行。
     * -考虑一个3结点最小子树的遍历过程，当 cur 存在左孩子和右孩子时，我们可以同时获取到左孩子和右孩子，
     * 并且假设cur即将进行下移，此时若不将cur压栈保留，那cur在移动前指向的结点(父结点)将获取不到。
     * 故而此时应当将cur即父结点压栈，
     * -接下来考虑该压左孩子还是右孩子。以一个具体的例子来设想，假设先前压进栈的是结点2。
     * 如果先压右孩子：cur -> 5, 5进栈后, 下一层没有结点(null), 但是中序遍历要求先输出4, 所以此时又要拿出5的父结点
     * 而5刚进栈又出来, 显然先压右孩子没意义。
     *
     * @param node node
     */
    public static void inOrderNotRecur(Node node) {
        System.out.print("in_order: ");
        Node cur = node;
        Stack<Node> stack = new Stack<>();
        if (node != null) {
            while (!stack.isEmpty() || cur != null) {
                if (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                } else {
                    cur = stack.pop();
                    System.out.print(cur.data + " ");
                    cur = cur.right;
                }
            }
        }
        System.out.println();
    }

    /**
     * 先序遍历是"中左右"
     * 后序遍历是"左右中"
     * 将先序遍历改成"中右左" 再倒序输出
     * @param node node
     */
    public static void posOrderNotRecur(Node node) {
        System.out.print("posOrder: ");
        if (node != null) {
            Stack<Node> stack = new Stack<>();
            Stack<Node> help = new Stack<>();
            Node cur = node;
            stack.add(cur);
            while (!stack.isEmpty()) {
                cur = stack.pop();
                help.push(cur);
                if (cur.left != null) {
                    stack.push(cur.left);
                }
                if (cur.right != null) {
                    stack.push(cur.right);
                }
            }
            while (!help.isEmpty()) {
                System.out.print(help.pop().data + " ");
            }
        }
        System.out.println();
    }
}
