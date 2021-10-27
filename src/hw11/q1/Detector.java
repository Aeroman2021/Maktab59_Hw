package hw11.q1;

import java.util.ArrayList;

public class Detector {
    int counter = 0;
    private int number;
    private ArrayList<Integer> numberList;

    public Detector(int number,ArrayList<Integer> list) {
        this.number = number;
        this.numberList = list;
    }

    public ArrayList<Integer> getNumberList() {
        return numberList;
    }

    public void printOddNumber() {
        synchronized (this) {
            while (counter < number) {
                while (counter % 2 == 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(counter + " ");
                counter++;
                notify();
            }
        }
    }


    public void printEvenNumber() {
        synchronized (this) {
            while (counter < number) {
                while (counter % 2 == 1) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(counter + " ");
                counter++;
                notify();
            }
        }
    }
}
