package cn.madf.左神牛客网算法课;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 1. 用队列实现栈
 * 2. 用栈实现队列
 *
 * @author 烛影鸾书
 * @date 2020/5/14
 * @copyright© 2020
 */
public class problem3_QueueStackRecTransform {
    public static class QueueToStack {
        private Queue<Integer> data;
        private Queue<Integer> help;

        public QueueToStack() {
            data = new LinkedList<>();
            help = new LinkedList<>();
        }

        public void push(int item) {
            data.add(item);
        }

        public int pop() {
            if (data.isEmpty()) {
                throw new ArrayIndexOutOfBoundsException("stack is empty");
            }
            while (data.size() > 1) {
                help.add(data.poll());
            }
            int res = data.poll();
            swap();
            return res;
        }

        private void swap() {
            Queue<Integer> tmp = help;
            help = data;
            data = tmp;
        }
    }

    public static class StackToQueue {
        /**
         * 用两个栈来实现，一个栈用来push，一个栈用来pop
         * 约束：
         * 1、pop栈里有数据时push栈的数据不往pop栈里倒。
         * 2、push栈往pop栈里倒数据时一次倒完。
         */
        private Stack<Integer> push;
        private Stack<Integer> pop;

        public StackToQueue() {
            push = new Stack<>();
            pop = new Stack<>();
        }

        public void add(int item) {
            push.add(item);
        }

        public int poll() {
            if (push.size() + pop.size() == 0) {
                throw new ArrayIndexOutOfBoundsException("queue is empty");
            }

            if (pop.size() == 0) {
                while (push.size()!=0){
                    pop.push(push.pop());
                }
            }
            return pop.pop();
        }
    }

    public static void main(String[] args) {
        QueueToStack stack = new QueueToStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.pop();
        stack.push(4);

        StackToQueue queue = new StackToQueue();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.poll();
        queue.poll();
        queue.poll();
        queue.add(4);
        queue.add(5);
        queue.add(6);
        queue.poll();
        queue.add(7);
        queue.poll();
    }
}
