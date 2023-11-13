package DecouverteMultithreading.decouverteCallable;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;



public class UtiliserAdditionneur {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Debut de main - thread : " + Thread.currentThread().getName() + "\n");

        Additionneur additionneur20 = new Additionneur(20);

        Additionneur additionneur12 = new Additionneur(12);

        ExecutorService monExecutorService =
                Executors.newFixedThreadPool(3);

        monExecutorService.submit(additionneur20);

        TimeUnit.SECONDS.sleep(3);

        monExecutorService.submit(additionneur12);

        TimeUnit.SECONDS.sleep(15);

        Additionneur additionneur40 = new Additionneur(40);
        Additionneur additionneur35 = new Additionneur(35);
        Additionneur additionneur45 = new Additionneur(45);
        Additionneur additionneur50 = new Additionneur(50);

        monExecutorService.submit(additionneur40);
        monExecutorService.submit(additionneur35);
        monExecutorService.submit(additionneur45);
        monExecutorService.submit(additionneur50);

        TimeUnit.SECONDS.sleep(20);

        List<Runnable> lr = monExecutorService.shutdownNow();

        System.out.println("Fin de main");
        System.out.println();

        System.out.println(lr);
    }
}