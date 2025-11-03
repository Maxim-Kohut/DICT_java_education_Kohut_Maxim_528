package MatrixProcessing;

import java.util.*;

public class MatrixMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            // Главное меню
            System.out.println("\nMatrix Operations:");
            System.out.println("1. Add matrices");
            System.out.println("2. Multiply matrix by a constant");
            System.out.println("3. Multiply matrices");
            System.out.println("4. Transpose matrix");
            System.out.println("5. Calculate a determinant");
            System.out.println("6. Inverse matrix");
            System.out.println("0. Exit");
            System.out.print("> ");

            String option = sc.next();

            switch (option) {
                case "1" -> { // сложение
                    double[][] a = MatrixProcessing.inputMatrix(sc, "first");
                    double[][] b = MatrixProcessing.inputMatrix(sc, "second");
                    MatrixProcessing.printMatrix(MatrixProcessing.add(a, b));
                }
                case "2" -> { // умножение на константу
                    double[][] a = MatrixProcessing.inputMatrix(sc, "target");
                    System.out.print("Enter constant: ");
                    double k = sc.nextDouble();
                    MatrixProcessing.printMatrix(MatrixProcessing.multiplyByConst(a, k));
                }
                case "3" -> { // умножение двух матриц
                    double[][] a = MatrixProcessing.inputMatrix(sc, "first");
                    double[][] b = MatrixProcessing.inputMatrix(sc, "second");
                    MatrixProcessing.printMatrix(MatrixProcessing.multiply(a, b));
                }
                case "4" -> { // транспонирование
                    System.out.println("1. Main diagonal");
                    System.out.println("2. Side diagonal");
                    System.out.println("3. Vertical line");
                    System.out.println("4. Horizontal line");
                    System.out.print("Your choice: ");
                    String t = sc.next();

                    double[][] a = MatrixProcessing.inputMatrix(sc, "original");

                    switch (t) {
                        case "1" -> MatrixProcessing.printMatrix(MatrixProcessing.transposeMain(a));
                        case "2" -> MatrixProcessing.printMatrix(MatrixProcessing.transposeSide(a));
                        case "3" -> MatrixProcessing.printMatrix(MatrixProcessing.transposeVertical(a));
                        case "4" -> MatrixProcessing.printMatrix(MatrixProcessing.transposeHorizontal(a));
                        default -> System.out.println("Invalid choice!");
                    }
                }
                case "5" -> { // детерминант
                    double[][] a = MatrixProcessing.inputMatrix(sc, "target");
                    if (a.length != a[0].length) {
                        System.out.println("Determinant only for square matrix!");
                    } else {
                        System.out.println("The result is:");
                        System.out.println(MatrixProcessing.determinant(a));
                    }
                }
                case "6" -> { // обратная матрица
                    double[][] a = MatrixProcessing.inputMatrix(sc, "target");
                    MatrixProcessing.printMatrix(MatrixProcessing.inverse(a));
                }
                case "0" -> { // выход
                    System.out.println("Exit!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again!");
            }
        }
    }
}
