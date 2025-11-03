import java.util.Scanner;

public class Math_and_logic {

    //St.2 : рассчитываем без процент.
    public void calcNoInterest() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the loan principal:");
        double principal = Double.parseDouble(sc.nextLine().trim());

        System.out.println("What do you want to calculate?");
        System.out.println("type \"m\" – for number of monthly payments,");
        System.out.println("type \"p\" – for the monthly payment:");
        String type = sc.nextLine().trim();

        if ("m".equals(type)) {
            System.out.println("Enter the monthly payment:");
            double payment = Double.parseDouble(sc.nextLine().trim());
            // Считаем колич мес (окр вверх)
            int months = (int) Math.ceil(principal / payment);
            System.out.printf("It will take %d month%s to repay the loan%n",
                    months, months == 1 ? "" : "s");
        } else if ("p".equals(type)) {
            System.out.println("Enter the number of months:");
            int months = Integer.parseInt(sc.nextLine().trim());
            // считаем плат (округляем вверх)
            double payment = Math.ceil(principal / months);
            // считаем последний плат, если не делится ровно
            double last = principal - (months - 1) * payment;
            if (last != payment)
                System.out.printf("Your monthly payment = %.0f and the last payment = %.0f.%n",
                        payment, last);
            else
                System.out.printf("Your monthly payment = %.0f%n", payment);
        } else {
            System.out.println("Incorrect option");
        }
    }
}
