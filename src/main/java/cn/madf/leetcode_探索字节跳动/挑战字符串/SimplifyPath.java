package cn.madf.leetcode_探索字节跳动.挑战字符串;

import jdk.nashorn.internal.ir.IfNode;

import java.util.Arrays;

/**
 * 以 Unix 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。
 * <p>
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。更多信息请参阅：Linux / Unix中的绝对路径 vs 相对路径
 * <p>
 * 请注意，返回的规范路径必须始终以斜杠 / 开头，并且两个目录名之间必须只有一个斜杠 /。最后一个目录名（如果存在）不能以 / 结尾。此外，规范路径必须是表示绝对路径的最短字符串。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入："/home/"
 * 输出："/home"
 * 解释：注意，最后一个目录名后面没有斜杠。
 * 示例 2：
 * <p>
 * 输入："/../"
 * 输出："/"
 * 解释：从根目录向上一级是不可行的，因为根是你可以到达的最高级。
 * 示例 3：
 * <p>
 * 输入："/home//foo/"
 * 输出："/home/foo"
 * 解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。
 * 示例 4：
 * <p>
 * 输入："/a/./b/../../c/"
 * 输出："/c"
 * 示例 5：
 * <p>
 * 输入："/a/../../b/../c//.//"
 * 输出："/c"
 * 示例 6：
 * <p>
 * 输入："/a//b////c/d//././/.."
 * 输出："/a/b/c"
 *
 * @author 烛影鸾书
 * @date 2020/8/3 14:04
 * @copyright© 2020
 */
public class SimplifyPath {

    private class Node {
        String pathName;
        Node pre;
        Node next;

        Node(String s) {
            pathName = s;
        }
    }

    public String simplifyPath(String path) {
        Node root = new Node("/");
        Node cur = root;

        String[] pathArr = path.split("/");
        for (String s : pathArr) {
            if (s.length() == 0 || s.equals(".")) {
                continue;
            }
            if (s.equals("..")) {
                Node pre = cur.pre;
                if (pre != null) {
                    pre.next = null;
                    cur.pre = null;
                    cur = pre;
                }
            } else {
                Node cdNode = new Node(s);
                cur.next = cdNode;
                cdNode.pre = cur;
                cur = cdNode;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("/");
        cur = root.next;
        while (cur != null) {
            sb.append(cur.pathName).append("/");
            cur = cur.next;
        }

        return sb.length() == 1 ? sb.toString() : sb.substring(0, sb.length() - 1);
    }

    public static void main(String[] args) {
        SimplifyPath test = new SimplifyPath();
        String path = "/a//b////c/d//././/..";
        System.out.println(test.simplifyPath(path));
    }

}
