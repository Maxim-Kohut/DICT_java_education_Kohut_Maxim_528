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
        System.out.println("Let me guess your age");
        System.out.println("Enter remainders of dividing your age by 3, 5 and 7");
        int input1 = scanner.nextInt();
        int input2 = scanner.nextInt();
        int input3 = scanner.nextInt();
        int age = (input1 * 70 + input2 * 21 + input3 * 15) % 105;
        System.out.println("You age is " + age + " that's a good time to start programming!");
        System.out.println("Now I will prove to you that I can count to any number you want!");
        int input4 = scanner.nextInt();
        for (int i=0; i<=input4; i++) {
            System.out.println(i + " !");
        }
    }
}