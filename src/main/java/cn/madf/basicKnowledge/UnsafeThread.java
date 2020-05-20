package cn.madf.basicKnowledge;

import lombok.Data;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 烛影鸾书
 * @date 2020/5/1
 * @copyright© 2020
 */
@Data
public class UnsafeThread {
    private int cnt = 0;

    public void add() {
        cnt++;
    }

    public static void main(String[] args) throws InterruptedException {
        final int threadSize = 1000;
        UnsafeThread unsafeThread = new UnsafeThread();
        ExecutorService pool = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            pool.execute(() -> {
                unsafeThread.add();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        pool.shutdown();
        System.out.println(unsafeThread.getCnt());
    }
}
