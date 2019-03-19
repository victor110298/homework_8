package executors;

import java.util.concurrent.Callable;

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
