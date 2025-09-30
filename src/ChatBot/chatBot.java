import java.util.Scanner;

public class chatBot {
    public static void main (String[] args)
    {
        System.out.println("Hello! I'm ChatBot. My name is Andrew.");
        System.out.println("I was created in 2025");
        System.out.println("Pls remind your name");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("Great name " + name);
    }
}