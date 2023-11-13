package DecouverteMultithreading.strassenWithForkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class Strassen extends RecursiveTask<int[][]> {
    private int[][] a;
    private int[][] b;
    private int[][] result;

    private int N;

    ForkJoinPool pool = new ForkJoinPool();


    public Strassen(int[][] a, int[][] b, int n) {
        this.a = a;
        this.b = b;
        N = n;
    }

    // Addition de 2 matrices
    public int[][] add(int[][] A, int[][] B) {
        int n = A.length;

        int[][] C = new int[n][n];

        // Parcourir les lignes
        for (int i = 0; i < n; i++) {
            System.arraycopy(A[i], 0, C[i], 0, n);
            // Parcourir les colonnes
            for (int j = 0; j < n; j++) {
                // Additionner les éléments correspondants
                C[i][j] += B[i][j];
            }
        }

        return C;
    }

    // Soustraction de 2 matrices
    public int[][] sub(int[][] A, int[][] B) {
        int n = A.length;

        int[][] C = new int[n][n];

        // Parcourir les lignes
        for (int i = 0; i < n; i++) {
            System.arraycopy(A[i], 0, C[i], 0, n);

            // Parcourir les colonnes
            for (int j = 0; j < n; j++) {
                // Soustraire les éléments correspondants
                C[i][j] -= B[i][j];
            }
        }

        return C;
    }

    // Diviser une matrice en 4 sous-matrices
    public void split(int[][] P, int[][] C, int iB, int jB) {
        // Parcourir les lignes
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++) {
            // Parcourir les colonnes
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++) {
                // Copier les éléments correspondants
                C[i1][j1] = P[i2][j2];
            }
        }
    }

    // Joindre 4 sous-matrices en une seule
    public void join(int[][] C, int[][] P, int iB, int jB) {
        // Parcourir les lignes
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++) {
            // Parcourir les colonnes
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++) {
                // Copier les éléments correspondants
                P[i2][j2] = C[i1][j1];
            }
        }
    }



    @Override
    protected int[][] compute() {
        int[][] R = new int[N][N];

        if (N == 1) {
            R[0][0] = a[0][0] * b[0][0];
        } else {
            int[][] A11 = new int[N / 2][N / 2];
            int[][] A12 = new int[N / 2][N / 2];
            int[][] A21 = new int[N / 2][N / 2];
            int[][] A22 = new int[N / 2][N / 2];

            int[][] B11 = new int[N / 2][N / 2];
            int[][] B12 = new int[N / 2][N / 2];
            int[][] B21 = new int[N / 2][N / 2];
            int[][] B22 = new int[N / 2][N / 2];

            split(a, A11, 0, 0);
            split(a, A12, 0, N / 2);
            split(a, A21, N / 2, 0);
            split(a, A22, N / 2, N / 2);

            split(b, B11, 0, 0);
            split(b, B12, 0, N / 2);
            split(b, B21, N / 2, 0);
            split(b, B22, N / 2, N / 2);

            Strassen m1 = new Strassen(add(A11, A22), add(B11, B22), N / 2);
            Strassen m2 = new Strassen(add(A21, A22), B11, N / 2);
            Strassen m3 = new Strassen(A11, sub(B12, B22), N / 2);
            Strassen m4 = new Strassen(A22, sub(B21, B11), N / 2);
            Strassen m5 = new Strassen(add(A11, A12), B22, N / 2);
            Strassen m6 = new Strassen(sub(A21, A11), add(B11, B12), N / 2);
            Strassen m7 = new Strassen(sub(A12, A22), add(B21, B22), N / 2);

            m1.fork();
            m2.fork();
            m3.fork();
            m4.fork();
            m5.fork();
            m6.fork();
            m7.fork();

            int[][] C11 = add(sub(add(m1.join(), m4.join()), m5.join()), m7.join());
            int[][] C12 = add(m3.join(), m5.join());
            int[][] C21 = add(m2.join(), m4.join());
            int[][] C22 = add(sub(add(m1.join(), m3.join()), m2.join()), m6.join());

            join(C11, R, 0, 0);
            join(C12, R, 0, N / 2);
            join(C21, R, N / 2, 0);
            join(C22, R, N / 2, N / 2);
        }

        return R;
    }



}