package cn.madf.左神牛客网算法课;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 并查集
 * <p>
 * 这里并查集的实现采用hashmap，key是节点，value是该节点相连的节点
 *
 * @author 烛影鸾书
 * @date 2020/5/29
 * @copyright© 2020
 */
public class problem17_UnionFindSet {

    public static class Node {
        /* 此处省略存放的结构细节 */
    }

    public static class UnionFindSet {
        private Map<Node, Node> fatherMap;
        private Map<Node, Integer> sizeMap;

        public UnionFindSet(List<Node> nodes) {
            makeSets(nodes);
        }

        private void makeSets(List<Node> nodes) {
            fatherMap = new HashMap<Node, Node>();
            sizeMap = new HashMap<Node, Integer>();

            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        /* 查找代表节点 */
        public Node findHead(Node node) {
            Node father = fatherMap.get(node);
            if (father != node) {
                father = findHead(father);
            }
            fatherMap.put(node, father);
            return father;
        }

        public boolean isSameSet(Node a, Node b) {
            return findHead(a) == findHead(b);
        }

        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            Node aHead = findHead(a);
            Node bHead = findHead(b);
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                if (aSetSize <= bSetSize) {
                    fatherMap.put(aHead, bHead);
                    sizeMap.put(bHead, aSetSize + bSetSize);
                } else {
                    fatherMap.put(bHead, aHead);
                    sizeMap.put(aHead, aSetSize + bSetSize);
                }
            }
        }
    }
}

