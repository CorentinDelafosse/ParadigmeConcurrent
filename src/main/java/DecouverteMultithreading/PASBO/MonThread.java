package DecouverteMultithreading.PASBO;

import java.util.concurrent.TimeUnit;

public class MonThread implements Runnable{
    private String message;
    private boolean termine;

    public MonThread(String message) {
        this.message = message;
    }

    public void run(){
        termine = false;

        synchronized(System.out) {

            while (!termine) {
                System.out.println(message + "------------" + Thread.currentThread().getName());

                for (int c = 0; c < message.length(); c++) {

                    System.out.println(message.charAt(c));
                    try {
                        TimeUnit.MICROSECONDS.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                System.out.println();

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {

                }
            }
        }
    }

    public void mettreFin(){
        termine = true;
    }
}
