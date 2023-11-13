package DecouverteMultithreading.decouverteCallable;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Additionneur implements Callable<Integer> {

    public Additionneur(int n) {
        this.n = n;
    }

    private int n;

    @Override
    public Integer call() throws Exception {
        int res = 0;
        System.out.println("Debut de l'additionneur : " + n + " - thread : " + Thread.currentThread().getName());

        for (int i = 0; i <= n; i++) {
            System.out.println("\t\t\tAdditionneur : " + n + " - i = " + i);

            res += i;

            TimeUnit.MILLISECONDS.sleep(333);
        }

        System.out.println("Fin de l'additionneur : " + n + " - les resultat est : " + res);

        return res;
    }
}