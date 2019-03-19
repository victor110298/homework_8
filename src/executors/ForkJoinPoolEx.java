package executors;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import static executors.ForkJoinPoolEx.countOfOperation;
import static executors.ForkJoinPoolEx.numberOfThread;

public class ForkJoinPoolEx {
    public static int numberOfThread = Runtime.getRuntime().availableProcessors();
    private static ForkJoinPool fjp = new ForkJoinPool(numberOfThread);
    public static long countOfOperation = 1000L;

    public static void main(String[] args) {
        System.out.println("Count of thread " + numberOfThread);
        System.out.println("Start!");
        System.out.println(fjp.invoke(new Constructor(0, countOfOperation)));
        System.out.println("End!");
    }
}

class Constructor extends RecursiveTask<Long> {
    private long from;
    private long to;

    public Constructor(long from, long to) {
        this.from = from;
        this.to = to;
    }

    @Override
    protected Long compute() {
        if ((to - from) <= (countOfOperation / numberOfThread)) {
            long result = 0;
            for (long i = from; i < to; i++) {
                result += i;
            }
            return result;
        } else {
            long middle = (to + from) / 2;
            Constructor firstHalf = new Constructor(from, middle);
            firstHalf.fork();
            Constructor secondtHalf = new Constructor(middle + 1, to);
            long secondValue = secondtHalf.compute();
            return firstHalf.join() + secondValue;
        }
    }
}
