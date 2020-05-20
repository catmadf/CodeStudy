package cn.madf.左神牛客网算法课;

import java.util.Stack;

/**
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

  public static void inOrderNotRecur(Node node) {
    System.out.print("in_order: ");
    if (node != null) {}
  }
}
