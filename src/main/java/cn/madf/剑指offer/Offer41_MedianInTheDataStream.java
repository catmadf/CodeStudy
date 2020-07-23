package cn.madf.剑指offer;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * <p>
 * 例如，
 * <p>
 * [2,3,4] 的中位数是 3
 * <p>
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * <p>
 * 设计一个支持以下两种操作的数据结构：
 * <p>
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 示例 1：
 * <p>
 * 输入：
 * ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
 * [[],[1],[2],[],[3],[]]
 * 输出：[null,null,null,1.50000,null,2.00000]
 * 示例 2：
 * <p>
 * 输入：
 * ["MedianFinder","addNum","findMedian","addNum","findMedian"]
 * [[],[2],[],[3],[]]
 * 输出：[null,null,2.00000,null,2.50000]
 *  
 * <p>
 * 限制：
 * <p>
 * 最多会对 addNum、findMedia进行 50000 次调用。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * MedianFinder 是构造函数，在本地测试改成了Offer41_MedianInTheDataStream .
 *
 * @author 烛影鸾书
 * @date 2020/7/23 20:14
 * @copyright© 2020
 */
public class Offer41_MedianInTheDataStream {

    /**
     * 最大堆
     */
    private PriorityQueue<Integer> maxHeap;
    /**
     * 最小堆
     */
    private PriorityQueue<Integer> minHeap;

    /**
     * initialize your data structure here.
     */
    public Offer41_MedianInTheDataStream() {
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        maxHeap.add(num);

        while (minHeap.size() != 0 && maxHeap.size() != 0 && maxHeap.peek() > minHeap.peek()) {
            minHeap.add(maxHeap.poll());
        }

        while (Math.abs(maxHeap.size() - minHeap.size()) > 1) {
            if (maxHeap.size() > minHeap.size()) {
                minHeap.add(maxHeap.poll());
            }
            if (maxHeap.size() < minHeap.size()) {
                maxHeap.add(minHeap.poll());
            }
        }
    }

    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        } else if (maxHeap.size() != 0) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }

        return -1;
    }

    public static void main(String[] args) {
        Offer41_MedianInTheDataStream offer41 = new Offer41_MedianInTheDataStream();
        String[] orders = new String[]{"MedianFinder", "addNum", "findMedian", "addNum", "findMedian", "addNum",
                "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum",
                "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian"};
        int[] nums = new int[]{-1, 1, -1, 2, -1, 3, -1, 4, -1, 5, -1, 6, -1, 7, -1, 8, -1, 9, -1, 10, -1};

        for (int i = 0; i < orders.length; i++) {
            if ("addNum".equals(orders[i])) {
                offer41.addNum(nums[i]);
            }
            if ("findMedian".equals(orders[i])) {
                System.out.println(offer41.findMedian());
            }
        }
    }
}
