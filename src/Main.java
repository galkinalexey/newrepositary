import java.util.Scanner;
import java.util.regex.Pattern;
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        System.out.println("Привет! Это калькулятор.");
        System.out.println("Он может складывать (+), вычитать (-), умножать (*) и делить (/) римские цифры (от I до X) и арабские цифры (от 1 до 10).");
        System.out.println("Обратите внимание, что калькулятор может проводить операции только с двумя целыми числами.");
        System.out.println("Пришлите пример, который нужно решить. Например, I + IV или 8 - 2.");
        String str = s.nextLine();

        // Проверяем с помощью регулярного выражения соответствие примера заданным условиям
        // Если не соответствует сообщаем об этом

        String regexp_arab = "^(([1-9]{1}|10)\s?[+\\-*\\/]{1}\s?([1-9]{1}|10))$";
        String regexp_roman = "^((I{1,3}|IV|V{1}|VI{1,3}|X{1}|IX)\s?[+\\-*\\/]{1}\s?(I{1,3}|IV|V{1}|VI{1,3}|X{1}|IX))$";
        boolean found_arab = Pattern.matches(regexp_arab,str);
        boolean found_roman = Pattern.matches(regexp_roman,str);

        if(!found_arab && !found_roman) throw new Exception("Ошибка! Введенный пример не соответствует условиям.");

        // Получаем операнды

        String [] operands = str.split("[+\\-*/]");
        String [] symbol_mass = str.split("");
        String sign = "";
        for(String symb: symbol_mass) {
            switch (symb) {
                case "+":
                case "-":
                case "*":
                case "/":
                    sign = symb;
                    break;
            }
        }

        int a,b;

        if(found_arab){
            a = Integer.parseInt(operands[0]);
            b = Integer.parseInt(operands[1]);
            System.out.println(calc(a,b,sign));
        }
        else if (found_roman){
            RomanNum romanConst1,romanConst2;
            romanConst1 = RomanNum.valueOf(operands[0].trim());
            romanConst2 = RomanNum.valueOf(operands[1].trim());
            a = romanConst1.getNum();
            b = romanConst2.getNum();
            int res = (calc(a,b,sign));
            if (res>0) {
                for(RomanNum rn: RomanNum.values()){
                    if(rn.ordinal()==res-1) System.out.println(rn);
                }
            } else {
                throw new Exception("Результат операций над римскими цифрами не может быть отрицательным или равняться нулю.");
            }

        }

    }

    // Метод калькулятора, который выполняет основные действия: сложение, вычитание, умножение, деление
    static int calc(int a, int b, String sign) {
        switch (sign) {
            case "+" -> {
                return a + b;
            }
            case "-" -> {
                return a - b;
            }
            case "*" -> {
                return a * b;
            }
            case "/" -> {
                return a / b;
            }
            default -> {
                return -1000;
            }
        }
    }

    // Создаем перечисление для римских чисел
    enum RomanNum {
        I(1),II(2),III(3),IV(4),V(5),VI(6),VII(7),VIII(8),IX(9),X(10),
        XI(11),XII(12),XIII(13),XIV(14),XV(15),XVI(16),XVII(17),XVIII(18),XIX(19),XX(20),
        XXI(21),XXII(22),XXIII(23),XXIV(24),XXV(25),XXVI(26),XXVII(27),XXVIII(28),XXIX(29),XXX(30),
        XXXI(31),XXXII(32),XXXIII(33), XXXIV(34), XXXV(35), XXXVI(36), XXXVII(37), XXXVIII(38), XXXIX(39), XL(40),
        XLI(41), XLII(42), XLIII(43), XLIV(44), XLV(45), XLVI(46), XLVII(47), XLVIII(48), XLIX(49), L(50),
        LI(51), LII(52), LIII(53), LIV(54), LV(55), LVI(56), LVII(57), LVIII(58), LIX(59), LX(60),
        LXI(61), LXII(62), LXIII(63), LXIV(64), LXV(65), LXVI(66), LXVII(67), LXVIII(68), LXIX(69), LXX(70),
        LXXI(71), LXXII(72), LXXIII(73), LXXIV(74), LXXV(75), LXXVI(76), LXXVII(77), LXXVIII(78), LXXIX(79), LXXX(80),
        LXXXI(81), LXXXII(82), LXXXIII(83), LXXXIV(84), LXXXV(85), LXXXVI(86), LXXXVII(87), LXXXVIII(88), LXXXIX(89), XC(90),
        XCI(91), XCII(92), XCIII(93), XCIV(94), XCV(95), XCVI(96), XCVII(97), XCVIII(98), XCIX(99), C(100);
        final int num;
        RomanNum(int num) {
            this.num = num;
        }

        int getNum() {
            return num;
        }
    }
}