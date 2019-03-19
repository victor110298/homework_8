package executors;

import java.util.concurrent.*;

public class FutureCallableEx {
    private static ExecutorService service = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<Double> future;
        System.out.println("Start");
        future = service.submit(new Hipot(4, 5));
        System.out.println(future.get());
        service.shutdown();
        System.out.println("End");
    }
}


