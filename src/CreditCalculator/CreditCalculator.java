public class CreditCalculator {

    // быстрая проверка на отриц. ( чтоб не проверять по сто раз)
    private static boolean neg(Double d) { return d != null && d < 0; }
    private static boolean neg(Integer i) { return i != null && i < 0; }

    public static void main(String[] args) {
        Math_and_logic logic = new Math_and_logic(); // создаем объект класса

        // читаем системные проперти (аргументы -Dtype=diff и т.п.)
        String type = System.getProperty("type");
        String principalS = System.getProperty("principal");
        String paymentS   = System.getProperty("payment");
        String periodsS   = System.getProperty("periods");
        String interestS  = System.getProperty("interest");

        // пробуем парсить что передали, если ничего нет то null
        Double principal = principalS != null ? Double.parseDouble(principalS) : null;
        Double payment   = paymentS   != null ? Double.parseDouble(paymentS)   : null;
        Integer periods  = periodsS   != null ? Integer.parseInt(periodsS)     : null;
        Double interest  = interestS  != null ? Double.parseDouble(interestS)  : null;

        //  проверка всего
        // если тип не annuity и не diff  инкорект
        if (type == null || !(type.equals("annuity") || type.equals("diff"))) {
            System.out.println("Incorrect parameters");
            return;
        }

        // без процентов инкорект
        if (interest == null || interest <= 0) {
            System.out.println("Incorrect parameters");
            return;
        }

        // отрицательные инкорект
        if (neg(principal) || neg(payment) || neg(periods)) {
            System.out.println("Incorrect parameters");
            return;
        }

        // если  диф
        if (type.equals("diff")) {
            // нельзя payment в diff инкор
            if (payment != null || principal == null || periods == null) {
                System.out.println("Incorrect parameters");
                return;
            }
            logic.raschet_diff(principal, periods, interest);
            return;
        }

        //  аннуитет
        int known = 0;
        if (principal != null) known++;
        if (payment != null) known++;
        if (periods != null) known++;
        if (known != 2) { //  2 из 3 ато не понятно что искать)
            System.out.println("Incorrect parameters");
            return;
        }

        // теперь выбираем чего не хватает и считаем
        if (principal != null && periods != null) {
            logic.raschet_annuitet(principal, periods, interest);
        } else if (payment != null && periods != null) {
            logic.raschet_principal(payment, periods, interest);
        } else if (principal != null && payment != null) {
            logic.raschet_sroka(principal, payment, interest);
        } else {
            System.out.println("Incorrect parameters");
        }
    }
}