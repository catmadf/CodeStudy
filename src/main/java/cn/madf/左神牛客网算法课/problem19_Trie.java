package cn.madf.左神牛客网算法课;

/**
 * 前缀树
 *
 * @author 烛影鸾书
 * @date 2020/5/30
 * @copyright© 2020
 */
public class problem19_Trie {

    private static class TrieNode {
        int pass;  // 在添加字符串进来时路过该节点几次
        int end;  // 在添加字符串进来时有多少次是以该节点结尾
        //也可以用HashMap<Character, TrieNode>实现路
        TrieNode[] nexts;  // 路，如果要判断有没有 'a' 这条路，只需要计算nexts['a'-'a']是否为空即可，次数索引即为抽象的路

        public TrieNode() {
            pass = 0;
            end = 0;
            nexts = new TrieNode[26];
        }
    }

    private TrieNode root;

    private final char INDEX0 = 'a';

    public problem19_Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        if (word == null) {
            return;
        }
        TrieNode node = root;
        int index;
        char[] chars = word.toCharArray();
        for (char aChar : chars) {
            index = aChar - INDEX0;
            if (node.nexts[index] == null) {
                node.nexts[index] = new TrieNode();
            }
            node = node.nexts[index];
            node.pass++;
        }
        node.end++;
    }

    public void insertAll(String... words) {
        for (String word : words) {
            insert(word);
        }
    }

    public void delete(String word) {
        if (word == null) {
            return;
        }
        if (search(word) == 0) return;
        TrieNode node = root;
        int index;
        char[] chars = word.toCharArray();
        for (char aChar : chars) {
            index = aChar - INDEX0;
            if ((--node.nexts[index].pass) == 0) {
                node.nexts[index] = null;
                return;
            }
            node = node.nexts[index];
        }
        node.end--;
    }

    public int search(String word) {
        TrieNode node = searchNode(word);
        return node == null ? 0 : node.end;
    }

    public int prefixNumber(String pre) {
        TrieNode node = searchNode(pre);
        return node == null ? 0 : node.pass;
    }

    private TrieNode searchNode(String word) {
        if (word == null) {
            return null;
        }
        TrieNode node = root;
        int index;
        char[] chars = word.toCharArray();
        for (char aChar : chars) {
            index = aChar - INDEX0;
            if (node.nexts[index] == null) {
                return null;
            }
            node = node.nexts[index];
        }
        return node;
    }

    public static void main(String[] args) {
        problem19_Trie problem19Trie = new problem19_Trie();
        System.out.println(problem19Trie.search("zuo"));
        problem19Trie.insert("zuo");
        System.out.println(problem19Trie.search("zuo"));
        problem19Trie.delete("zuo");
        System.out.println(problem19Trie.search("zuo"));
        problem19Trie.insert("zuo");
        problem19Trie.insert("zuo");
        problem19Trie.delete("zuo");
        System.out.println(problem19Trie.search("zuo"));
        problem19Trie.delete("zuo");
        System.out.println(problem19Trie.search("zuo"));
        problem19Trie.insert("zuoa");
        problem19Trie.insert("zuoac");
        problem19Trie.insert("zuoab");
        problem19Trie.insert("zuoad");
        problem19Trie.delete("zuoa");
        System.out.println(problem19Trie.search("zuoa"));
        System.out.println(problem19Trie.prefixNumber("zuo"));
    }
}
