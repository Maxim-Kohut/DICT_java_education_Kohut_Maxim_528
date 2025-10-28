package TicTakToe;
import java.util.Scanner;

public class TicTakToe {
    private char[][] pole = new char[3][3];
    private Scanner scanner = new Scanner(System.in);

    public TicTakToe() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                pole[i][j] = '_';
            }
        }
    }

    // ---------------- Print pole ----------------
    public void printPole() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(pole[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    // ---------------- Check win ----------------
    public String checkWin() {
        boolean xWins = false;
        boolean oWins = false;

        // Horizontal and vertical
        for (int i = 0; i < 3; i++) {
            if (pole[i][0] == pole[i][1] && pole[i][1] == pole[i][2] && pole[i][0] != '_') {
                if (pole[i][0] == 'X') xWins = true;
                if (pole[i][0] == '0') oWins = true;
            }
            if (pole[0][i] == pole[1][i] && pole[1][i] == pole[2][i] && pole[0][i] != '_') {
                if (pole[0][i] == 'X') xWins = true;
                if (pole[0][i] == '0') oWins = true;
            }
        }

        // Diagonals
        if (pole[0][0] == pole[1][1] && pole[1][1] == pole[2][2] && pole[0][0] != '_') {
            if (pole[0][0] == 'X') xWins = true;
            if (pole[0][0] == '0') oWins = true;
        }
        if (pole[0][2] == pole[1][1] && pole[1][1] == pole[2][0] && pole[0][2] != '_') {
            if (pole[0][2] == 'X') xWins = true;
            if (pole[0][2] == '0') oWins = true;
        }

        int countX = 0, countO = 0;
        boolean hasEmpty = false;
        for (char[] row : pole) {
            for (char c : row) {
                if (c == 'X') countX++;
                if (c == '0') countO++;
                if (c == '_') hasEmpty = true;
            }
        }

        if (Math.abs(countX - countO) > 1) return "Impossible";
        if (xWins && oWins) return "Impossible";
        if (xWins) return "X win!";
        if (oWins) return "0 win!";
        if (hasEmpty) return "Game not finished";
        return "Draw";
    }
    // ---------------- Check coordinates ----------------
    public boolean checkCoordinate(String coords) {
        String[] parts = coords.trim().split("\\s+");
        if (parts.length != 2) {
            System.out.println("You should enter two numbers!");
            return false;
        }

        int x, y;
        try {
            x = Integer.parseInt(parts[0]);
            y = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            System.out.println("You should enter numbers!");
            return false;
        }

        if (x < 1 || x > 3 || y < 1 || y > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }

        if (pole[x - 1][y - 1] != '_') {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }

        return true;
    }

    // ---------------- Step ----------------
    public void step(char player) {
        while (true) {
            System.out.print("Player " + player + ", enter coordinates (row and col from 1 to 3): ");
            String coords = scanner.nextLine();
            if (checkCoordinate(coords)) {
                String[] parts = coords.split("\\s+");
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                pole[x - 1][y - 1] = player;
                break;
            }
        }
    }
}
