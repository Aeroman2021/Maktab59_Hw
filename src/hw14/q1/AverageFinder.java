package hw14.q1;

import java.util.function.BiFunction;

public class AverageFinder {
    public static void main(String[] args) {

        BiFunction<Integer, Integer, Double> findAverage = (a, b) -> 0.5 * (a + b);
        System.out.println(findAverage.apply(2, 8));

    }
}
