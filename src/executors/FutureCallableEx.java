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

class Hipot implements Callable<Double> {
    private double firstSide;
    private double secondSide;

    public Hipot(double firstSide, double secondSide) {
        this.firstSide = firstSide;
        this.secondSide = secondSide;
    }

    @Override
    public Double call() throws Exception {
        double hip = (firstSide * firstSide) + (secondSide * secondSide);
        return Math.sqrt(hip);
    }
}
