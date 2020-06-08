package cn.madf.leetCode;

import java.util.HashMap;
import java.util.Map;
/**
 * tag：unionSet
 * 给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或 "a!=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。
 * 只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。
 * <p>
 * 示例 1：
 * 输入：["a==b","b!=a"]
 * 输出：false
 * 解释：如果我们指定，a = 1 且 b = 1，那么可以满足第一个方程，但无法满足第二个方程。没有办法分配变量同时满足这两个方程。
 * <p>
 * 示例 2：
 * 输出：["b==a","a==b"]
 * 输入：true
 * 解释：我们可以指定 a = 1 且 b = 1 以满足满足这两个方程。
 * <p>
 * 示例 3：
 * 输入：["a==b","b==c","a==c"]
 * 输出：true
 * <p>
 * 示例 4：
 * 输入：["a==b","b!=c","c==a"]
 * 输出：false
 * <p>
 * 示例 5：
 * 输入：["c==c","b==d","x!=z"]
 * 输出：true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/satisfiability-of-equality-equations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 我们可以将"=="符号视为将左右两边添加到一个集合中，遇到"!="时就判断左右两边是否在同一个集合内，如果在返回false，不在判断下一个。
 * 由此思路很明确，并查集可以查找两个元素是否属于同一集合
 *
 * @author 烛影鸾书
 * @date 2020/6/8
 * @copyright© 2020
 */
public class P990_EquationsPossible {

    private Map<Character, Character> fatherMap = new HashMap<>();
    private Map<Character, Integer> sizeMap = new HashMap<>();

    public boolean equationsPossible(String[] equations) {
        for (String equation : equations) {
            fatherMap.put(equation.charAt(0), equation.charAt(0));
            sizeMap.put(equation.charAt(0), 1);
            fatherMap.put(equation.charAt(3), equation.charAt(3));
            sizeMap.put(equation.charAt(3), 1);
        }

        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                union(equation.charAt(0), equation.charAt(3));
            }
        }

        for (String equation : equations) {
            if (equation.charAt(1) == '!') {
                if (isSameSet(equation.charAt(0), equation.charAt(3))) {
                    return false;
                }
            }
        }
        return true;
    }

    private char findHead(char c) {
        Character father = fatherMap.get(c);
        if (father != c) {
            father = findHead(father);
        }
        fatherMap.put(c, father);
        return father;
    }

    private boolean isSameSet(char c1, char c2) {
        return findHead(c1) == findHead(c2);
    }

    private void union(char c1, char c2) {
        char c1Head = findHead(c1);
        char c2Head = findHead(c2);
        if (c1Head != c2Head) {
            int c1Size = sizeMap.get(c1);
            int c2Size = sizeMap.get(c2);
            if (c1Size <= c2Size) {
                fatherMap.put(c1Head, c2Head);
                sizeMap.put(c2Head, c1Size + c2Size);
            } else {
                fatherMap.put(c2Head, c1Head);
                sizeMap.put(c1Head, c1Size + c2Size);
            }
        }
    }

    public static void main(String[] args) {
        String[] str = new String[]{"a==z","a==b","b==c","c==d","b==y","c==x","d==w","g==h","h==i","i==j","a==g","j!=y"};
        P990_EquationsPossible p990 = new P990_EquationsPossible();
        System.out.println(p990.equationsPossible(str));

    }


}
