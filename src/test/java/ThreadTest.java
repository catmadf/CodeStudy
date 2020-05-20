import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import org.junit.Test;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 烛影鸾书
 * @date 2020/4/17
 * @copyright© 2020
 */
public class ThreadTest {
    @FunctionalInterface
    interface TestInterface {
        public abstract void run();
    }

    public void func() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.println(i + "-" + Thread.currentThread().getName());
            }
        }
    }

    private static class MyThread extends Thread {

        @Override
        public void run() {
            int i = 0;
            while (!isInterrupted()) {
                System.out.println(i++);
            }
            System.out.println("end");
        }
    }

    @Test
    public void testInterrupted() throws InterruptedException {
        Thread t = new Thread(() -> {
            int i = 0;
            while (!Thread.interrupted()) {
                System.out.println(i++);
            }
            System.out.println("end");
        });
        t.start();
        t.interrupt();
    }

    @Test
    public void test2() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        ThreadTest t = new ThreadTest();
        TestInterface testInterface = () -> {
            System.out.print(System.currentTimeMillis());
        };
        executorService.execute(t::func);
        executorService.execute(t::func);

    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        System.out.println("main");
        for (int i = 1; i <= 100; i++) {
            list.add(i);
        }
        System.out.println(list.stream().filter(integer -> integer > 90).findAny().get());
    }

}
