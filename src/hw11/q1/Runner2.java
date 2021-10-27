package hw11.q1;

import java.util.ArrayList;
import java.util.Arrays;

public class Runner2 {


    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        Detector oddAndEvenDetector = new Detector(10,numbers);

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                oddAndEvenDetector.printEvenNumber();
            }
        });


        Thread t2 = new Thread(new Runnable() {
            public void run() {
                oddAndEvenDetector.printOddNumber();
            }
        });

        t1.start();
        t2.start();

    }

}
