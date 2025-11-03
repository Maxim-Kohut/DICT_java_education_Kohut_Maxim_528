public class Math_and_logic {

    //======================== diff pay ============================
    public void raschet_diff(double principal, int srok_mes, double procent_god) {
        // мес процент из годового
        double procent_mes = procent_god / 1200.0;
        long vsego_plat = 0; // общая сумма выпл

        // крутим по месам, пока не погасим
        for (int mesyac = 1; mesyac <= srok_mes; mesyac++) {
            // основа = тело кредита делим на кол-во мес
            double osnovnaya_chast = principal / srok_mes;
            // остаток долга (чем дальше, тем меньше)
            double ostatok_dolga = principal - (principal * (mesyac - 1)) / srok_mes;
            // % за этот мес
            double procent_chast = ostatok_dolga * procent_mes;
            // итого платёж (округляем вверх чтоб не остались копейки)
            long platezh = (long) Math.ceil(osnovnaya_chast + procent_chast);

            vsego_plat += platezh; // прибавляем к общ сумме
            System.out.printf("Month %d: payment is %d%n", mesyac, platezh);
        }

        long pereplata = vsego_plat - Math.round(principal);
        System.out.printf("Overpayment = %d%n", pereplata);
    }

    //======================== annuity fix pay ============================
    public void raschet_annuitet(double principal, int srok_mes, double procent_god) {
        // мес % из годового
        double i = procent_god / 1200.0;
        // считаем по формуле аннуитета
        double numerator = i * Math.pow(1 + i, srok_mes);
        double denominator = Math.pow(1 + i, srok_mes) - 1;
        long platezh = (long) Math.ceil(principal * numerator / denominator);

        long vsego = platezh * srok_mes;
        long pereplata = vsego - Math.round(principal);

        System.out.printf("Your annuity payment = %d!%n", platezh);
        System.out.printf("Overpayment = %d%n", pereplata);
    }

    //======================== find principal ============================
    public void raschet_principal(double platezh, int srok_mes, double procent_god) {
        double i = procent_god / 1200.0;
        // обратная формула (ищем principal по известным A, i, n)
        double numerator = i * Math.pow(1 + i, srok_mes);
        double denominator = Math.pow(1 + i, srok_mes) - 1;
        long principal = (long) Math.floor(platezh / (numerator / denominator));

        long vsego = Math.round(platezh) * srok_mes;
        long pereplata = vsego - principal;

        System.out.printf("Your loan principal = %d!%n", principal);
        System.out.printf("Overpayment = %d%n", pereplata);
    }

    //======================== find time (months/years) ============================
    public void raschet_sroka(double principal, double platezh, double procent_god) {
        double i = procent_god / 1200.0;
        double nReal;
        try {
            // формула с логарифмом, жесть но работает
            nReal = Math.log(platezh / (platezh - i * principal)) / Math.log(1 + i);
        } catch (Exception e) {
            System.out.println("Incorrect parameters"); // типо ошибка если что не так
            return;
        }

        int n = (int) Math.ceil(nReal); // округляем до след меса
        int y = n / 12;
        int m = n % 12;

        // делаем красиво для вывода срока
        StringBuilder s = new StringBuilder();
        if (y > 0) s.append(y).append(" year").append(y != 1 ? "s" : "");
        if (m > 0) {
            if (s.length() > 0) s.append(" and ");
            s.append(m).append(" month").append(m != 1 ? "s" : "");
        }

        long vsego = Math.round(platezh) * n;
        long pereplata = vsego - Math.round(principal);

        System.out.printf("It will take %s to repay this loan!%n", s);
        System.out.printf("Overpayment = %d%n", pereplata);
    }
}