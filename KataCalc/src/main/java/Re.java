import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Re {
    public static void main(String[] args) throws Exception {
        System.out.println("Введите выражение [2+2] или два римских числа от I до X:[V+V] + Enter ");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        int result;
        int num1;
        int num2;
        char operator = ' ';
        for (char symbol : input.toCharArray()) {
            if ((symbol == '+') || (symbol == '-') || (symbol == '*') || (symbol == '/')) {
                operator = symbol;
            }
        }
        String[] values = input.split("[*/+-]");
        if (isRoman(values[0]) && isRoman(values[1])) {
            num1 = romanToNumber(values[0]);
            num2 = romanToNumber(values[1]);
            if ((num1 > num2) || (operator == '+') || (operator == '*')){
                result = calc(num1, num2, operator);
                System.out.println(numToRoman(result));
            } else throw new Exception("при вычитании/делении первое число должно быть больше второго");

        } else if (isNumeric(values[0]) && isNumeric(values[1])) {
            num1 = Integer.parseInt(values[0]);
            num2 = Integer.parseInt(values[1]);
            if (validateNum(num1) && validateNum(num2)) {
                System.out.println(calc(num1, num2, operator));


            } else throw new Exception("Число должно быть от 1 до 10");

        } else {
            throw new NumberFormatException("Оба числа должны быть в одном формате: римские или арабские") {
            };
        }


    }

    private static String numToRoman(int num) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        return roman[num];
    }

    static boolean isRoman(String input) {
        Pattern pattern = Pattern.compile("^(?=[MDCLXVI])M*(C[MD]|D?C*)(X[CL]|L?X*)(I[XV]|V?I*)$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    static boolean isNumeric(String input) {
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static int calc(int num1, int num2, char op) {
        switch (op) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
        }
        return 0;
    }

    private static int romanToNumber(String roman) {
        try {
            switch (roman) {
                case "I":
                    return 1;
                case "II":
                    return 2;
                case "III":
                    return 3;
                case "IV":
                    return 4;
                case "V":
                    return 5;
                case "VI":
                    return 6;
                case "VII":
                    return 7;
                case "VIII":
                    return 8;
                case "IX":
                    return 9;
                case "X":
                    return 10;
                default:
                    throw new Exception("Число должно быть от I до X");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static boolean validateNum(int val1) {
        if ((val1 >= 1) && (val1 <= 10)) return true;
        return false;
    }
}
