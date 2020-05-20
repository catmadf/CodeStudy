package cn.madf.basicKnowledge.AQS;

import java.util.concurrent.*;

/**
 * @author 烛影鸾书
 * @date 2020/5/11
 * @copyright© 2020
 */
public class CyclicBarrierExample {

    public static void main(String[] args) {
        final int threadNum = 10;
        ConcurrentHashMap<String, String> runnableMap = new ConcurrentHashMap<>(threadNum);
        CyclicBarrier cb = new CyclicBarrier(threadNum);
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < threadNum; i++) {
            int finalI = i;
            pool.execute(() -> {
                String runnableName = "runnable-"+(finalI +1);
                System.out.println(runnableName+" is running");
                runnableMap.put(runnableName, runnableName);
                try {
                    cb.await();  // 执行一次await，计数减1，减到0，所有等待的线程被唤醒
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(runnableMap.size());
            });
        }
        pool.shutdown();

    }
}
