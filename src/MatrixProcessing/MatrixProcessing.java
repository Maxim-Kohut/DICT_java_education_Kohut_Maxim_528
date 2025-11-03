package MatrixProcessing;

import java.util.*;

public class MatrixProcessing {

    // === Общие методы ===

    // Чтение матрицы от пользователя
    public static double[][] inputMatrix(Scanner sc, String label) {
        while (true) {
            try {
                System.out.printf("Enter size of %s matrix (rows cols): ", label);
                //% указывает методу куда вставить стринг в вывод
                int rows = sc.nextInt();
                int cols = sc.nextInt();

                if (rows <= 0 || cols <= 0) {
                    System.out.println("Matrix dimensions must be positive!");
                    continue;
                }

                double[][] matrix = new double[rows][cols];
                System.out.printf("Enter %d rows with %d elements each:\n", rows, cols);

                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        matrix[i][j] = sc.nextDouble(); // вводим число
                    }
                }
                return matrix;

            } catch (Exception e) {
                System.out.println("Invalid input! Try again.");
                sc.nextLine(); // чистим буфер, если введено не число
            }
        }
    }

    // Вывод матрицы
    public static void printMatrix(double[][] matrix) {
        if (matrix == null) {
            System.out.println("Operation cannot be performed.");
            return;
        }
        System.out.println("The result is:");
        for (double[] row : matrix) {
            for (double value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    // === Операции с матрицами ===

    // St.1 Сложение матриц
    public static double[][] add(double[][] a, double[][] b) {
        int rowsA = a.length, colsA = a[0].length;
        int rowsB = b.length, colsB = b[0].length;

        if (rowsA != rowsB || colsA != colsB) {
            System.out.println("The operation cannot be performed.");
            return null;
        }

        double[][] res = new double[rowsA][colsA];
        for (int i = 0; i < rowsA; i++)
            for (int j = 0; j < colsA; j++)
                res[i][j] = a[i][j] + b[i][j]; // складываем поэлементно
        return res;
    }

    // St.2  Умножение матрицы на константу
    public static double[][] multiplyByConst(double[][] a, double k) {
        int rows = a.length, cols = a[0].length;
        double[][] res = new double[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                res[i][j] = a[i][j] * k; // каждый элемент * константу
        return res;
    }

    // St. 3 умнож двух матриц
    public static double[][] multiply(double[][] a, double[][] b) {
        int rowsA = a.length, colsA = a[0].length;
        int rowsB = b.length, colsB = b[0].length;

        if (colsA != rowsB) {
            System.out.println("The operation cannot be performed.");
            return null;
        }

        double[][] res = new double[rowsA][colsB];
        for (int i = 0; i < rowsA; i++)
            for (int j = 0; j < colsB; j++)
                for (int k = 0; k < colsA; k++)
                    res[i][j] += a[i][k] * b[k][j]; // умножаем строки на столбцы
        return res;
    }

    // Стад. 4 Транспон.

    // Главное диагональ
    public static double[][] transposeMain(double[][] a) {
        int rows = a.length, cols = a[0].length;
        double[][] res = new double[cols][rows];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                res[j][i] = a[i][j]; // меняем строки со столбцами
        return res;
    }

    // Побочная диагональ
    public static double[][] transposeSide(double[][] a) {
        int rows = a.length, cols = a[0].length;
        double[][] res = new double[cols][rows];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                res[cols - 1 - j][rows - 1 - i] = a[i][j]; // зеркалим по побочной диагонали
        return res;
    }

    // Отражение по вертикали
    public static double[][] transposeVertical(double[][] a) {
        int rows = a.length, cols = a[0].length;
        double[][] res = new double[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                res[i][cols - 1 - j] = a[i][j]; // зеркалим строки по вертикали
        return res;
    }

    // Отражение по горизонтали
    public static double[][] transposeHorizontal(double[][] a) {
        int rows = a.length, cols = a[0].length;
        double[][] res = new double[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                res[rows - 1 - i][j] = a[i][j]; // зеркалим строки по горизонтали
        return res;
    }

    // St.4 Определитель

    public static double determinant(double[][] a) {
        int n = a.length;
        if (n == 1) return a[0][0];
        if (n == 2) return a[0][0] * a[1][1] - a[0][1] * a[1][0];

        double det = 0;
        for (int col = 0; col < n; col++) {
            det += Math.pow(-1, col) * a[0][col] * determinant(minor(a, 0, col));
        }
        return det;
    }

    private static double[][] minor(double[][] a, int row, int col) {
        int n = a.length;
        double[][] m = new double[n - 1][n - 1];
        int r = 0;
        for (int i = 0; i < n; i++) {
            if (i == row) continue;
            int c = 0;
            for (int j = 0; j < n; j++) {
                if (j == col) continue;
                m[r][c++] = a[i][j];
            }
            r++;
        }
        return m;
    }

    //  Обратная матр

    public static double[][] inverse(double[][] a) {
        int n = a.length;
        if (n != a[0].length) {
            System.out.println("Inverse matrix only for square matrices!");
            return null;
        }

        double det = determinant(a);
        if (det == 0) {
            System.out.println("This matrix doesn't have an inverse.");
            return null;
        }

        double[][] cof = new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                cof[i][j] = Math.pow(-1, i + j) * determinant(minor(a, i, j)); // минор * знак

        double[][] adj = transposeMain(cof); // транспонируем дополнения
        double[][] inv = new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                inv[i][j] = adj[i][j] / det; // делим на определитель

        return inv;
    }
}
