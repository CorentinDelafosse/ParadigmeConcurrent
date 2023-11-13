package DecouverteMultithreading.strassenWithForkJoin;

import java.util.concurrent.ForkJoinPool;

public class ProgramPrincipal {
    public static void main(String[] args) {
        // Initialisez vos matrices A et B et spécifiez la taille N
        int N = 4; // Remplacez par la taille de vos matrices

        int[][] A = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][] B = {{17, 18, 19, 20}, {21, 22, 23, 24}, {25, 26, 27, 28}, {29, 30, 31, 32}};

        // Créez une instance de la classe Strassen
        Strassen strassen = new Strassen(A, B, N);

        // Créez une instance de ForkJoinPool (vous pouvez également utiliser le pool existant)
        ForkJoinPool pool = new ForkJoinPool();

        // Soumettez la tâche à ForkJoinPool et obtenez le résultat
        int[][] result = pool.invoke(strassen);

        // Affichez le résultat
        System.out.println("Matrice résultante :");
        printMatrix(result);
    }

    // Méthode utilitaire pour imprimer une matrice
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
