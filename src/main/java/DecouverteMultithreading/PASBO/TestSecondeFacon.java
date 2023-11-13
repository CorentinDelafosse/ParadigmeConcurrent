package DecouverteMultithreading.PASBO;

import java.util.concurrent.TimeUnit;

public class TestSecondeFacon {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Debut TestSecondeFacon");

        MonThread premierMonThread = new MonThread("abcdef");
        MonThread secondMonThread = new MonThread("     GHIJK");

        //premierMonThread.start(); // IMPOSSIBLE !!!
        Runnable r1 = premierMonThread;
        Runnable r2 = secondMonThread;

        //r1.start();

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();

        TimeUnit.SECONDS.sleep(4);

        //t1.mettreFin(); //NE PAS DONNER AU THREAD !!!
        premierMonThread.mettreFin();
        secondMonThread.mettreFin();

        System.out.println("Fin TestSecondeFacon");
    }
}
