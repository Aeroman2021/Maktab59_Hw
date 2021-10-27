package hw11.q2;

public class Runner {

    public static void main(String[] args) {
        Thread hello = new Thread(new Hello());
        hello.start();



        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        hello.interrupt();

        System.out.println("Printing stopped....");

    }

}
