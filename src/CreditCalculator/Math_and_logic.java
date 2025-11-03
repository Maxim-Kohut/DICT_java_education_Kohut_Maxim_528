import java.util.Scanner;

public class Math_and_logic {

    // St3: аннуит форм с %
    public void calcAnnuity() {
        Scanner sc = new Scanner(System.in);

        System.out.println("What do you want to calculate?");
        System.out.println("type \"n\" for number of monthly payments,");
        System.out.println("type \"a\" for annuity monthly payment amount,");
        System.out.println("type \"p\" for loan principal:");
        String mode = sc.nextLine().trim();

        if ("n".equals(mode)) {
            System.out.println("Enter the loan principal:");
            double P = Double.parseDouble(sc.nextLine().trim());
            System.out.println("Enter the monthly payment:");
            double A = Double.parseDouble(sc.nextLine().trim());
            System.out.println("Enter the loan interest:");
            double interest = Double.parseDouble(sc.nextLine().trim());

            double i = interest / 1200.0; // месячная ставка
            double nReal = Math.log(A / (A - i * P)) / Math.log(1 + i);
            int n = (int) Math.ceil(nReal);

            int years = n / 12;
            int months = n % 12;

            StringBuilder text = new StringBuilder("It will take ");
            if (years > 0) text.append(years).append(" year").append(years > 1 ? "s" : "");
            if (months > 0) {
                if (years > 0) text.append(" and ");
                text.append(months).append(" month").append(months > 1 ? "s" : "");
            }
            text.append(" to repay this loan!");
            System.out.println(text);

        } else if ("a".equals(mode)) {
            System.out.println("Enter the loan principal:");
            double P = Double.parseDouble(sc.nextLine().trim());
            System.out.println("Enter the number of periods:");
            int n = Integer.parseInt(sc.nextLine().trim());
            System.out.println("Enter the loan interest:");
            double interest = Double.parseDouble(sc.nextLine().trim());

            double i = interest / 1200.0;
            double numerator = i * Math.pow(1 + i, n);
            double denominator = Math.pow(1 + i, n) - 1;
            long A = (long) Math.ceil(P * numerator / denominator);

            System.out.printf("Your annuity payment = %d!%n", A);

        } else if ("p".equals(mode)) {
            System.out.println("Enter the annuity payment:");
            double A = Double.parseDouble(sc.nextLine().trim());
            System.out.println("Enter the number of periods:");
            int n = Integer.parseInt(sc.nextLine().trim());
            System.out.println("Enter the loan interest:");
            double interest = Double.parseDouble(sc.nextLine().trim());

            double i = interest / 1200.0;
            double numerator = i * Math.pow(1 + i, n);
            double denominator = Math.pow(1 + i, n) - 1;
            long P = (long) Math.floor(A / (numerator / denominator));

            System.out.printf("Your loan principal = %d!%n", P);
        } else {
            System.out.println("Incorrect option");
        }
    }
}
