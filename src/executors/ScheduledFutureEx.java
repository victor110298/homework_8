package executors;

import java.util.concurrent.*;

public class ScheduledFutureEx {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<Integer> scheduledFuture1 = service.schedule(new MathOperation(3, 6), 3, TimeUnit.SECONDS);
        ScheduledFuture<Integer> scheduledFuture2 = service.schedule(new MathOperation(4, 2), 2, TimeUnit.SECONDS);
        System.out.println("remaining delay thread 1: " + scheduledFuture1.getDelay(TimeUnit.SECONDS));
        System.out.println("remaining delay thread 2: " + scheduledFuture2.getDelay(TimeUnit.SECONDS));
        System.out.println(scheduledFuture1.get());
        System.out.println(scheduledFuture2.get());
        service.shutdown();
    }
}
class MathOperation implements Callable<Integer> {
    private int a;
    private int b;

    public MathOperation(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Integer call() throws Exception {
        return (a + a) * (b + b);
    }
}
