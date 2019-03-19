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

