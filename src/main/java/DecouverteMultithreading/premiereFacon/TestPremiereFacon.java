package DecouverteMultithreading.premiereFacon;

import java.util.concurrent.TimeUnit;

public class TestPremiereFacon {

    public static void main(String[] args) {
        System.out.println("Début test première façon");

        MonThread premierMonThread = new MonThread("abcdef");
        MonThread secondMonThread = new MonThread("    GHIJK");

        //premierMonThread.run(); //HORREUR !!!!!!!
        //secondMonThread.run(); //HORREUR !!!!!!!

        premierMonThread.start();
        secondMonThread.start();

        try {
            Thread.currentThread().sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //TimeUnit.SECONDS.sleep(2);

        //premierMonThread.stop() // DEPRECATED !!!!

        premierMonThread.mettreFin();

        System.out.println("Fin test première façon");
    }
}