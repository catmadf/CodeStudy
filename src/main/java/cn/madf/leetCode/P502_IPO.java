package cn.madf.leetCode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 假设 力扣（LeetCode）即将开始其 IPO。为了以更高的价格将股票卖给风险投资公司，力扣 希望在 IPO 之前开展一些项目以增加其资本。 由于资源有限，它只能在 IPO 之前完成最多 k 个不同的项目。帮助 力扣 设计完成最多 k 个不同项目后得到最大总资本的方式。
 * 给定若干个项目。对于每个项目 i，它都有一个纯利润 Pi，并且需要最小的资本 Ci 来启动相应的项目。最初，你有 W 资本。当你完成一个项目时，你将获得纯利润，且利润将被添加到你的总资本中。
 * 总而言之，从给定项目中选择最多 k 个不同项目的列表，以最大化最终资本，并输出最终可获得的最多资本。
 * <p>
 * 示例 1:
 * <p>
 * 输入: k=2, W=0, Profits=[1,2,3], Capital=[0,1,1].
 * 输出: 4
 * <p>
 * 解释:
 * 由于你的初始资本为 0，你尽可以从 0 号项目开始。
 * 在完成后，你将获得 1 的利润，你的总资本将变为 1。
 * 此时你可以选择开始 1 号或 2 号项目。
 * 由于你最多可以选择两个项目，所以你需要完成 2 号项目以获得最大的资本。
 * 因此，输出最后最大化的资本，为 0 + 1 + 3 = 4。
 * <p>
 * 注意:
 * 假设所有输入数字都是非负整数。
 * 表示利润和资本的数组的长度不超过 50000。
 * 答案保证在 32 位有符号整数范围内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ipo
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 首先要保证每次选项目时资金充足，即 w>=c ；其次，在满足第一个条件基础上保证 p 最大。
 * 相当于每次选出能够做的项目，在这些项目中找到收益最多的。细心的话可以发现堆能够满足我们的需求。
 * <p>
 * 看了官方题解后发现可以加速代码：即会存在一种情况，第一次从小根堆取出所有能做的项目时，此时项目个数就已经大于等于K
 *
 * @author 烛影鸾书
 * @date 2020/6/2
 * @copyright© 2020
 */
public class P502_IPO {

    private class Node {
        int profit;
        int cost;

        Node(int profit, int cost) {
            this.profit = profit;
            this.cost = cost;
        }
    }

    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        boolean speed = true;
        for (int c : Capital) {
            if (W < c) {
                speed = false;
                break;
            }
        }
        /* 用一个小根堆来存放项目，每次pop出cost最小的 */
        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        /* 用一个大根堆来存放项目，接收从上面那个pop出的项目，然后pop出收益最大的项目来做 */
        PriorityQueue<Node> maxHeap = new PriorityQueue<>((node1, node2) -> node2.profit - node1.profit);

        /* 若所有项目都可做，则只需要选最大利润的k个即可 */
        if (speed) {
            for (int i = 0; i < Profits.length; i++) {
                maxHeap.add(new Node(Profits[i], Capital[i]));
            }
            for (int i = 0; i < k; i++) {
                W+=maxHeap.poll().profit;
            }
            return W;
        }

        for (int i = 0; i < Profits.length; i++) {
            minHeap.add(new Node(Profits[i], Capital[i]));
        }

        int doneProjects = 0;
        while (doneProjects < k) {
            /* 每次做项目时抽一下小根堆是否解锁新的项目，把解锁的项目全放进大根堆 */
            while (!minHeap.isEmpty() && minHeap.peek().cost <= W) {
                maxHeap.add(minHeap.poll());
            }
            if (maxHeap.isEmpty()) {
                return W;
            }
            W += maxHeap.poll().profit;
            k++;
        }

        return W;
    }
}
