package executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorEx {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        for (int i = 1; i <= 4; i++) {
            Task task = new Task();
            service.submit(task);
        }
        service.shutdown();
    }
}


