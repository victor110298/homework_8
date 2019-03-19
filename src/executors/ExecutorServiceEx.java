package executors;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceEx {
    SimpleDateFormat sdf = null;
    private final int COUNT = 5;

    ExecutorServiceEx() {
        sdf = new SimpleDateFormat("HH:mm:ss.S");
        CountDownLatch cdl1 = new CountDownLatch(COUNT);
        CountDownLatch cdl2 = new CountDownLatch(COUNT);
        CountDownLatch cdl3 = new CountDownLatch(COUNT);
        CountDownLatch cdl4 = new CountDownLatch(COUNT);
        ExecutorService executor;
        executor = Executors.newFixedThreadPool(2);
        System.out.println("Start");
        executor.execute(new MyThread(cdl1, "Thread.1"));
        executor.execute(new MyThread(cdl2, "Thread.2"));
        executor.execute(new MyThread(cdl3, "Thread.3"));
        executor.execute(new MyThread(cdl4, "Thread.4"));
        try {
            cdl1.await();
            cdl2.await();
            cdl3.await();
            cdl4.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        System.out.println("End!");
    }

    void printMessage(final String templ) {
        String text = sdf.format(new Date()) + " : " + templ;
        System.out.println(text);
    }

    class MyThread implements Runnable {
        String name;
        CountDownLatch latch;

        MyThread(CountDownLatch c, String n) {
            latch = c;
            name = n;
            new Thread(this);
        }

        public void run() {
            try {
                for (int i = 0; i < COUNT; i++) {
                    printMessage(name + " - " + i);
                    latch.countDown();
                    Thread.sleep((long) (Math.random() * 1500));
                }
                printMessage(name + " completed");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        new ExecutorServiceEx();
    }
}
