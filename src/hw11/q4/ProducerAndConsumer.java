package hw11.q4;

import java.util.LinkedList;
import java.util.List;

public class ProducerAndConsumer {

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();

        Thread[] threads = {new Producer(list),new Producer(list),new Consumer(list),new Consumer(list)};

        for (Thread thread : threads){
            thread.start();
        }

        for (Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Finished.");
        System.out.println("The list size is " + list.size());


    }
}
