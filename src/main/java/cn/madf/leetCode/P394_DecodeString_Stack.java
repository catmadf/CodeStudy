package cn.madf.leetCode;

import java.util.Stack;

/**
 * @author 烛影鸾书
 * @date 2020/5/28
 * @copyright© 2020
 */
public class P394_DecodeString_Stack {
    public String decodeString(String s) {
        if (s.length() == 0) {
            return "";
        }

        Stack<Integer> numStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();

        int numPush = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                numPush = numPush *10 + Character.getNumericValue(c);
            } else if (c != ']') {
                stringStack.push(String.valueOf(c));
                if (c == '[') {
                    numStack.push(numPush);
                    numPush = 0;
                }
            } else {
                StringBuilder substring = new StringBuilder();
                String popItem;
                while (!(popItem = stringStack.pop()).equals("[")) {
                    substring.append(popItem);
                }
                String item = getString(numStack.pop(), substring.toString());
                stringStack.push(item);
            }
        }
        StringBuilder res = new StringBuilder();
        while (!stringStack.isEmpty()) {
            res.append(stringStack.pop());
        }
        return res.reverse().toString();
    }

    private String getString(int num, String item) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < num; i++) {
            res.append(item);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        P394_DecodeString_Stack p394 = new P394_DecodeString_Stack();

        String a = "3[a]2[bc]";
        String b = "3[a2[c]]";
        String c = "2[abc]3[cd]ef";
        String d = "100[leetcode]";

        System.out.println(p394.decodeString(a));
        System.out.println(p394.decodeString(b));
        System.out.println(p394.decodeString(c));
        System.out.println(p394.decodeString(d));
    }
}
