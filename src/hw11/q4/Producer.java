package hw11.q4;

import java.util.ArrayList;
import java.util.List;

public class Producer extends Thread {
    List<Integer> list;
    private static ArrayList<Integer> randNumbers;

    public Producer(List<Integer> list) {
        this.list = list;
        randNumbers = new ArrayList<>();
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            synchronized (list) {
                Integer randomNumber = numberGenerator();
                System.out.println(randomNumber + " added");
                list.add(randomNumber);
                list.notify();
            }
        }
    }

    public static int numberGenerator() {
        int upperBound = 200;
        int lowerBound = 0;
        int number = (int) (Math.random() * (upperBound - lowerBound)) + lowerBound;
        if (!randNumbers.contains(number)) {
            randNumbers.add(number);
            return number;
        } else numberGenerator();
        return number;
    }
}
