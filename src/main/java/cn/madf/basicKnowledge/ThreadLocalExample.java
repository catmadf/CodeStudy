package cn.madf.basicKnowledge;

import java.net.InetAddress;

/**
 * @author 烛影鸾书
 * @date 2020/5/4
 * @copyright© 2020
 */
public class ThreadLocalExample {

    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal1 = new ThreadLocal<>();
        ThreadLocal<Integer> threadLocal2 = new ThreadLocal<>();

        new Thread(()->{
            threadLocal1.set(1);
            threadLocal2.set(2);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":"+threadLocal1.get());
            System.out.println(Thread.currentThread().getName()+":"+threadLocal2.get());
        }, "thread1").start();

        new Thread(()->{
            threadLocal1.set(3);
            threadLocal2.set(4);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":"+threadLocal1.get());
            System.out.println(Thread.currentThread().getName()+":"+threadLocal2.get());
        }, "thread2").start();
    }
}
