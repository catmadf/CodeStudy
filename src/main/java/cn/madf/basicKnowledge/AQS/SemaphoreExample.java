package cn.madf.basicKnowledge.AQS;

import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author 烛影鸾书
 * @date 2020/5/11
 * @copyright© 2020
 */
public class SemaphoreExample {
    public static void main(String[] args) {
        final int permit = 3;
        final int threadNum = 10;
        Semaphore semaphore = new Semaphore(permit);
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < threadNum; i++) {
            int finalI = i;
            pool.execute(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(finalI +"-"+semaphore.availablePermits());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
        }
        pool.shutdown();
    }
}
