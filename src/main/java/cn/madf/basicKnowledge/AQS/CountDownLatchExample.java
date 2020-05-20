package cn.madf.basicKnowledge.AQS;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 烛影鸾书
 * @date 2020/5/11
 * @copyright© 2020
 */
public class CountDownLatchExample {

    public static void main(String[] args) throws InterruptedException {
        final int threadNum = 100;
        CountDownLatch latch = new CountDownLatch(threadNum);

        AtomicInteger cnt = new AtomicInteger();
        for (int i = 0; i < threadNum; i++) {
            new Thread(()->{
                cnt.incrementAndGet();
                latch.countDown();
            }).start();
        }
        latch.await();
        System.out.println(cnt.get());
    }
}
