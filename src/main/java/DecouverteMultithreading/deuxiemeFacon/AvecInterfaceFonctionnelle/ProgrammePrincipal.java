package DecouverteMultithreading.deuxiemeFacon.AvecInterfaceFonctionnelle;

import java.util.concurrent.TimeUnit;

public class ProgrammePrincipal {
    public static void main(String[] args) {

        Runnable ecrivainGauche;

        ecrivainGauche=()->{

            for(int i = 0; i < 50; i++){
                System.out.println("ecrivainGauche - " + i);

                try {
                    TimeUnit.MILLISECONDS.sleep(750);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        };

        Runnable ecrivainDroit=()->{
            for(int i=0; i<50; i++){
                System.out.println("ecrivainDroite - " + i);

                try {
                    TimeUnit.MILLISECONDS.sleep(750);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread t1 = new Thread(ecrivainGauche);
        t1.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Thread t2 = new Thread(ecrivainDroit);
        t2.start();


        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(()->{
            for(int i=0; i<50; i++){
                System.out.println("\t\t\tecrivainThread - " + i);

                try {
                    TimeUnit.MILLISECONDS.sleep(600);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
