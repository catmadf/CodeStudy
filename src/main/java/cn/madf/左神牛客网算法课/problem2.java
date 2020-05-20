package cn.madf.左神牛客网算法课;

import java.util.Arrays;

/**
 * 1.给定固定大小的数组，用它来分别实现栈和队列
 * 栈：很简单
 * 队列：提示：数组可以循环利用，可以将数组想象成头尾相连的，用两个指针，一个代表队列头，一个代表队列尾，再定义一个size变量用来约束两个指针
 *
 * 2.实现一个栈，满足基本功能，并且实现getMin()方法获得当前栈的最小值，要求复杂度为O(1).
 * 提示：额外建立一个min栈，跟原栈同步push和pop，使其栈顶永远为原栈当前状态的最小值。
 * @author 烛影鸾书
 * @date 2020/5/13
 * @copyright© 2020
 */
public class problem2 {
    public static class ArrayStack {

        private int[] data;
        private int index;

        public ArrayStack(int initialSize) {
            if (initialSize < 0) {
                throw new IllegalArgumentException("this init size is illegal, \"initialSize < 0\"");
            }
            data = new int[initialSize];
            index = 0;
        }

        public void push(int item) {
            if (index == data.length) {
                throw new IndexOutOfBoundsException("the index is out of bounds:" + index);
            }
            data[index++] = item;
        }

        public int pop() {
            if (index == 0) {
                throw new IndexOutOfBoundsException("the stack is empty.");
            }
            return data[--index];
        }

        public Integer peek() {
            if (index == 0) {
                return null;
            }
            return data[index - 1];
        }

        public int[] elements() {
            int[] ret = new int[index];
            System.arraycopy(data, 0, ret, 0, index);
            return ret;
        }
    }

    public static class ArrayQueue {
        private int[] data;
        private int size;
        private int first;
        private int last;

        public ArrayQueue(int initialSize) {
            if (initialSize < 0) {
                throw new IllegalArgumentException("this init size is illegal, \"initialSize < 0\"");
            }
            data = new int[initialSize];
            size = 0;
            first = 0;
            last = 0;
        }

        public void push(int item) {
            if (size == data.length) {
                throw new ArrayIndexOutOfBoundsException("queue is full");
            }
            size++;
            data[last] = item;
            last = last + 1 == data.length ? 0 : last + 1;
        }

        public int poll() {
            if (size == 0) {
                throw new ArrayIndexOutOfBoundsException("queue is empty");
            }

            size--;
            int tmp = first;
            first = first + 1 == data.length ? 0 : first + 1;
            return data[tmp];
        }

        public int[] elements() {
            int[] ret = new int[size];
            int i = 0;
            int j = first;
            while (i != size) {
                ret[i++] = data[j];
                j = j + 1 == data.length ? 0 : j + 1;
            }
            return ret;
        }
    }

    public static class MinStack {
        private ArrayStack stack;
        private ArrayStack min;

        public MinStack(int initialSize) {
            if (initialSize < 0) {
                throw new IllegalArgumentException("this init size is illegal, \"initialSize < 0\"");
            }
            stack = new ArrayStack(initialSize);
            min = new ArrayStack(initialSize);
        }

        public void push(int item) {
            stack.push(item);
            if (min.peek() == null) {
                 min.push(item);
            } else {
                min.push(Math.min(min.peek(), item));
            }
        }

        public int pop() {
            min.pop();
            return stack.pop();
        }

        public int getMin() {
            return min.peek();
        }
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        queue.push(2);
        queue.push(3);
        System.out.println(Arrays.toString(queue.elements()));
        queue.poll();
        System.out.println(Arrays.toString(queue.elements()));
        queue.push(1);
        queue.push(4);
        System.out.println(Arrays.toString(queue.elements()));
    }
}
