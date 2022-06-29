import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int number1, number2;
    static char operation;
    static int result;

    public static void main(String[] args) throws Exception {
        System.out.println("Введите выражение [2+2] или два римских числа от I до X:[V+V] + Enter ");
//      Считываем строку userInput которую ввёл пользователь
        String userInput = scanner.nextLine();
//
        char[] charArr = new char[10];
//
        for (int i = 0; i < userInput.length(); i++) {
            charArr[i] = userInput.charAt(i);
            if (charArr[i] == '+') {
                operation = '+';
            } else if (charArr[i] == '-') {
                operation = '-';
            } else if (charArr[i] == '*') {
                operation = '*';
            } else if (charArr[i] == '/') {
                operation = '/';
            } 
        }
        String under_charString = String.valueOf(charArr).trim();
        String[] blocks = under_charString.split("[+-/*]");
        String value00 = blocks[0];
        String value01 = blocks[1];

        if(isRoman(value00)&&isRoman(value01)) {
            number1 = romanToNumber(value00);
            number2 = romanToNumber(value01);

        if (number1 < 0 && number2 < 0) {
            result = 0;
        } else {
            result = calculated(number1, number2, operation);
            if (result<1) throw new NumberFormatException("римские числа не бывают меньше I");
            System.out.println("---Результат для римских цифр----");
            String resultRoman = convertNumToRoman(result);
            System.out.println(value00 + " " + operation + " " + value01 + " = " + resultRoman);
        }
        } else if(isNumeric(value00)&&isNumeric(value01)){
            number1 = Integer.parseInt(value00);
            number2 = Integer.parseInt(value01);
            result = calculated(number1, number2, operation);
            System.out.println("--Результат для арабских цифр----");
            System.out.println(number1 + " " + operation + " " + number2 + " = " + result);
        } else {
            throw new NumberFormatException("Введены неверные данные, только римские и арабские числа!");
        }
    }



    static boolean isRoman(String input){
        Pattern pattern = Pattern.compile("^(?=[MDCLXVI])M*(C[MD]|D?C*)(X[CL]|L?X*)(I[XV]|V?I*)$");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()){
            return true;
        }
        return false;
    }

    static boolean isNumeric(String input){
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()){
            return true;
        }
        return false;
    }
    private static String convertNumToRoman(int numArabian) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        final String s = roman[numArabian];
        return s;
    }


    private static int romanToNumber(String roman) {
        /*"^M{0,3}" + ///от 0 до 3 M
                "(CM|CD|D?C{0,3})?" +
                "(XC|XL|L?X{0,3})?" +
                "(IX|IV|V?I{0,3})?$'"*/
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
            }
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Неверный формат данных");
        }
        return -1;
    }

    public static int calculated(int num1, int num2, char op) {
        int result = 0;
        switch (op) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                try {
                    result = num1 / num2;
                } catch (ArithmeticException | InputMismatchException e) {
                    System.out.println("Exception : " + e);
                    System.out.println("Only integer non-zero parameters allowed");

                    break;
                }
                break;
            default:
                throw new IllegalArgumentException("Не верный знак операции");
        }
        return result;
    }
}
