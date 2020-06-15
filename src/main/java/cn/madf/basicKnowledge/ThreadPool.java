package cn.madf.basicKnowledge;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @author 烛影鸾书
 * @date 2020/6/15
 * @copyright© 2020
 */
public class ThreadPool {
    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 20;
    private static final long KEEP_ALIVE_TIME = 2L;

    private static final int N_THREAD = 26;

    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < N_THREAD; i++) {
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " Start Time = " + new Date());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " End Time = " + new Date());
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {

        }
        System.out.println("end.");
    }
}
