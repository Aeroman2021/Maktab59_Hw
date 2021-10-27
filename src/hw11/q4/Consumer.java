package hw11.q4;

import java.util.ArrayList;
import java.util.List;

public class Consumer extends Thread {

    List<Integer> list;

    public Consumer(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            synchronized (list) {
                while (list.size() == 0) {
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Integer number = list.remove(0);
                System.out.println(number + " removed");
            }

        }

    }







}
