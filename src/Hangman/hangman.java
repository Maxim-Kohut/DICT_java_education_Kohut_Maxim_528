package Hangman;
import java.util.*;

public class hangman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("HANGMAN");

        while (true) {
            System.out.print("Type \"play\" to play the game, \"exit\" to quit: > "); //єкраирование
            String command = scanner.nextLine();

            if (command.equals("exit")) {
                break;
            } else if (!command.equals("play")) {
                // якщо введено щось інше  просимо повторити
                continue;
            }

            // === Початок гри ===
            String[] words = {"python", "java", "javascript", "kotlin"};
            String word = words[random.nextInt(words.length)];
            String probel = "-".repeat(word.length());
            int life = 8; // кількість помилок
            HashSet<Character> usedLetters = new HashSet<>();

            while (life > 0) {
                System.out.println(probel);
                System.out.print("Input a letter: > ");
                String usr_letter = scanner.nextLine();

                // 1️⃣ Перевірка — рівно одна літера
                if (usr_letter.length() != 1) {
                    System.out.println("You should input a single letter");
                    continue;
                }

                char letter = usr_letter.charAt(0);

                // 2️⃣ Перевірка — англійська маленька літера
                if (!Character.isLetter(letter) || !Character.isLowerCase(letter)) {
                    System.out.println("Please enter a lowercase English letter");
                    continue;
                }

                // 3️⃣ Якщо літера вже була введена
                if (usedLetters.contains(letter)) {
                    System.out.println("You've already guessed this letter");
                    continue;
                }

                usedLetters.add(letter);

                // 4️⃣ Якщо літера є у слові
                if (word.indexOf(letter) != -1) {
                    StringBuilder temp = new StringBuilder(probel);
                    for (int i = 0; i < word.length(); i++) {
                        if (word.charAt(i) == letter) {
                            temp.setCharAt(i, letter);
                        }
                    }
                    probel = temp.toString();
                } else {
                    System.out.println("That letter doesn't appear in the word");
                    life--;
                }

                // 5️⃣ Перевірка на виграш
                if (probel.equals(word)) {
                    System.out.println("You guessed the word " + word + "!");
                    System.out.println("You survived!");
                    break;
                }
            }
            // 6️⃣ Якщо життя закінчились
            if (!probel.equals(word)) {
                System.out.println("You lost!");
            }
        }
    }
}
