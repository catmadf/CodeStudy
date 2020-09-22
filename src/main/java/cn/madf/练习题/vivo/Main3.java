package cn.madf.练习题.vivo;

import java.util.*;


public class Main3 {

    class Node {
        Node pre;  // 依赖
        List<Node> nexts = new LinkedList<>();
        int num;
        Node(int num) {
            this.num = num;
        }

        @Override
        public String toString() {
            return String.valueOf(num);
        }
    }
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 编译顺序
     * @param input string字符串 
     * @return string字符串
     */
    public String compileSeq (String input) {
        // write code here
        String[] item = input.split(",");
        int[] arr = new int[item.length];
        HashMap<Integer, Node> nodeHashMap = new HashMap<>();
        for (int i = 0; i < item.length; i++) {
            arr[i] = Integer.parseInt(item[i].trim());
            nodeHashMap.put(i, new Node(i));
        }

        int start = -1;
        System.out.println(nodeHashMap);
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1) {
                start = i;
                continue;
            }
            nodeHashMap.get(i).pre = nodeHashMap.get(arr[i]);
            nodeHashMap.get(arr[i]).nexts.add(nodeHashMap.get(i));
        }

        System.out.println(dump(nodeHashMap.get(start)));

        return "";
    }

    private static String dump(Node node) {
        if (node.nexts.size() == 0) {
            return String.valueOf(node.num);
        }
        node.nexts.sort((o1, o2) -> o2.num - o1.num);
        List<String> res = new LinkedList<>();
        for (Node next : node.nexts) {
            res.add(dump(next));
        }
        res.add(String.valueOf(node.num));
        Collections.reverse(res);
        StringBuilder sb = new StringBuilder();
        for (String re : res) {
            sb.append(re).append(",");
        }

        return sb.substring(0, sb.length() - 1);
    }

    public static void main(String[] args) {
        Main3 m = new Main3();
        String s = "1,2,-1,1,0,0,0,1,2,3,3";
        m.compileSeq(s);
    }
}