
package TicTakToe;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Tic-Tac-Toe!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Play");
            System.out.println("2. Exit");
            System.out.print("Choose an option (1 or 2): ");

            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                TicTakToe game = new TicTakToe();
                game.printPole();

                char currentPlayer = 'X';
                while (true) {
                    game.step(currentPlayer);
                    game.printPole();

                    String result = game.checkWin();
                    if (!result.equals("Game not finished")) {
                        System.out.println(result);
                        break;
                    }

                    currentPlayer = (currentPlayer == 'X') ? '0' : 'X';
                }

            } else if (choice.equals("2")) {
                System.out.println("Thanks for playing Tic-Tac-Toe! Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        }

        scanner.close();
    }
}