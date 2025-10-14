package CoffeeMachine;
import java.util.*;
//St.1-2-3-4-5-6
// финальный 6
public class CoffeeMachine {
    // что есть на старте
    int water = 400, milk = 540, beans = 120, cups = 9, money = 550;

    // список режимов работы машины
    enum Mode { ACTION, CHOICE, FILL_W, FILL_M, FILL_B, FILL_C, EXIT }
    Mode mode = Mode.ACTION; // начинаем с главного меню

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CoffeeMachine m = new CoffeeMachine();

        // крутимся, пока не напишут exit
        while (m.mode != Mode.EXIT) {
            m.prompt();                 // подсказываем, что сейчас вводить
            if (!sc.hasNextLine()) break; // если ввода нет выход
            String line = sc.nextLine().trim();
            m.handle(line);             // вся логика тут
        }
    }

    // просто печатаем подсказку под текущий режим
    void prompt() {
        switch (mode) {
            case ACTION -> System.out.println("Write action (buy, fill, take, remaining, exit):");
            case CHOICE -> System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back – to main menu:");
            case FILL_W -> System.out.println("Write how many ml of water do you want to add:");
            case FILL_M -> System.out.println("Write how many ml of milk do you want to add:");
            case FILL_B -> System.out.println("Write how many grams of coffee beans do you want to add:");
            case FILL_C -> System.out.println("Write how many disposable cups of coffee do you want to add:");
            default -> {}
        }
    }

    // сюда прилетает строка от пользователя+ делаем действие
    void handle(String s) {
        switch (mode) {
            case ACTION -> { // главное меню
                if ("buy".equals(s)) mode = Mode.CHOICE;           // выбир кофе
                else if ("fill".equals(s)) mode = Mode.FILL_W;     // + заполнение
                else if ("take".equals(s)) {                        // снятьденьги
                    System.out.println("I gave you " + money);
                    money = 0;
                } else if ("remaining".equals(s)) {                 // остатки
                    printState();
                } else if ("exit".equals(s)) {                      // ext
                    mode = Mode.EXIT;
                }
            }
            case CHOICE -> { // выбираем
                if ("back".equals(s)) { mode = Mode.ACTION; return; }

                // ресурсы + цена
                int rw=0, rm=0, rb=0, price=0; // r* = required
                if ("1".equals(s)) { rw=250; rm=0;   rb=16; price=4; }
                if ("2".equals(s)) { rw=350; rm=75;  rb=20; price=7; }
                if ("3".equals(s)) { rw=200; rm=100; rb=12; price=6; }

                // проверяем хватает ли  ресов
                String lack = checkLack(rw, rm, rb);
                if (lack != null) {
                    System.out.println("Sorry, not enough " + lack + "!");
                    mode = Mode.ACTION; // назадв меню
                    return;
                }
                // все ок готовим кофе
                System.out.println("I have enough resources, making you a coffee!");
                water -= rw; milk -= rm; beans -= rb; cups--; money += price;
                mode = Mode.ACTION;
            }
            case FILL_W -> { // + воду
                water += toInt(s);
                mode = Mode.FILL_M; // дальше молоко
            }
            case FILL_M -> { // +молоко
                milk += toInt(s);
                mode = Mode.FILL_B; // дальше зерна
            }
            case FILL_B -> { // +зерна
                beans += toInt(s);
                mode = Mode.FILL_C; // дальше стаканчики
            }
            case FILL_C -> { // +стаканчики
                cups += toInt(s);
                mode = Mode.ACTION; // обратно в меню
            }
            default -> {}
        }
    }
    // чекаем, какого ресурса не хватает
    String checkLack(int rw, int rm, int rb) {
        if (water < rw) return "water";
        if (milk  < rm) return "milk";
        if (beans < rb) return "coffee beans";
        if (cups  < 1)  return "disposable cups";
        return null; // все норм
    }

    // переводим строку в число (если неправильно просто 0)
    int toInt(String s) {
        try { return Integer.parseInt(s); } catch (Exception e) { return 0; }
    }

    // відаем текущие остатки
    void printState() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk  + " of milk");
        System.out.println(beans + " of coffee beans");
        System.out.println(cups  + " of disposable cups");
        System.out.println(money + " of money ");
    }
}
