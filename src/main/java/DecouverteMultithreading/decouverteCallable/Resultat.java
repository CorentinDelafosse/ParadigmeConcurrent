package DecouverteMultithreading.decouverteCallable;

import java.util.List;
import java.util.concurrent.*;

public class Resultat {
    public static void main(String[] args) {

        System.out.println("Debut de main - thread : " + Thread.currentThread().getName() + "\n");

        Additionneur additionneur25 = new Additionneur(25);

        Additionneur additionneur12 = new Additionneur(12);

        ExecutorService monExecutorService =
                Executors.newFixedThreadPool(3);

        Future<Integer> future25= monExecutorService.submit(additionneur25);

        try {
            System.out.println("\nAvant get sur future25");
            int res25 = future25.get(3, TimeUnit.SECONDS);
            System.out.println("Apres get sur future25 - res25 = " + res25 + "\n");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            System.out.println("Trop tard pour toi mon gars !");

            future25.cancel(true);
        }

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {

        }

        Future<Integer> future12 = monExecutorService.submit(additionneur12);

        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {

        }

        try {
            System.out.println(future25.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        monExecutorService.shutdown();

        System.out.println("Fin de main");
    }
}