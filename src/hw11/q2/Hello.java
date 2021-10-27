package hw11.q2;

public class Hello implements Runnable {

    @Override
    public void run() {

        System.out.println("Start printing hello...");
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            System.out.println("Hello");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
            if (Thread.currentThread().isInterrupted()) return;


        }

    }
}
