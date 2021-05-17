package hrynkevych.serhii.homework7_2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static final String [] upToTwenty = {"нуль", "один", "два", "три", "чотири", "п'ять", "шість", "сім",
            "вісім", "девять", "десять", "одинадцять", "дванадцять", "тринадцять", "чотирнадцять", "п'ятнадцять",
            "шістнадцять", "сімнадцять", "вісімнадцять", "дев'ятнадцять"};
    public static final String [] tens = {"десять", "двадцять", "тридцять", "сорок", "п'ятдесят", "шістдесят",
            "сімдесят", "вісімдесят", "дев'яносто"};
    public static final String [] hundreds = {"сто", "двісті", "триста", "чотириста", "п'ятсот", "шістсот", "сімсот",
            "вісімсот", "дев'ятсот"};
    public static final String [] thousand = {"одна тисяча", "дві тисячі", "три тисячі", "чотири тисячі", "п'ять тисяч",
            "шість тисяч", "сім тисяч", "вісім тисяч", "дев'ять тисяч"};

    public static void main(String[] args) {
        Integer inputNumber; //объявляем переменную для введеного числа
        String result; //объявляем переменную для текстового результата

        inputNumber = inputData(); //заполняем введенное число вызвал функцию

        if (inputNumber == null) { //проверяем на ошибку ввода - если null, выводим сообщение и останавливаемся
            System.out.println("Щось пішло не так(");
            return;
        }

        if (inputNumber < 0) { //если значение меньше нуля - выводим сообщение функцией
            outOfRange();
        }

        if (inputNumber >= 10000) { //если значение больше 9999 - выводим сообщение функцией
            outOfRange();
        }

        if (inputNumber >= 0 && inputNumber <= 99) { //если значение от 0вкл до 99вкл (десятки)
            result = outputTens(inputNumber);
            System.out.println(result);
        }

        if (inputNumber >= 100 && inputNumber <= 999) { //если значение от 100вкл до 999вкл (сотни)
            result = outputHundreds(inputNumber);
            System.out.println(result);
        }

        if (inputNumber >= 1000 && inputNumber <= 9999) { //если значение от 1000вкл до 9999вкл (тысячи)
            result = outputThousand(inputNumber);
            System.out.println(result);
        }

    }

    public static Integer inputData() { //функция ввода с консоли
        Scanner scanner = new Scanner(System.in);
        Integer inputNumber = null; //по умолчанию переменную заполняем null

        try { //отловливаем ошибки ввода
            inputNumber = scanner.nextInt(); //если нет - заполняем
        } catch (InputMismatchException e) { //если ловим ошибку
            return inputNumber; //возвращаем значение переменной null
        }
        return inputNumber; //если нет ошибок возвращаем введенное значение
    }

    public static void outOfRange() { //функция сообщения вне диапазона
        System.out.println("Введене число поза діапазоном підтримуємих чисел");
    }

    public static String outputTens(int inputNumber) { //функция вывода результата десятков (ну тут долго описывать действия мои)))
        String resultTens = "";

        if (inputNumber < 20) {
            resultTens = upToTwenty[inputNumber];
        } else if (inputNumber < 100) {
            int tenth = inputNumber / 10;
            int one = inputNumber % 10;
            resultTens = tens[tenth-1];
            if (one != 0 ) {
                resultTens = resultTens + " " + upToTwenty[one];
            }
        }
        return resultTens;
    }

    public static String outputHundreds(int inputNumber) { //функция вывода результата сотен
        int hundred = inputNumber / 100;
        int tenth = (inputNumber % 100) / 10;
        int one = inputNumber % 10;
        String resultHundreds = hundreds[hundred-1];

        if (tenth != 0 && one == 0) {
            resultHundreds = resultHundreds + " " + outputTens(inputNumber - (hundred * 100));
        }

        if (tenth == 0 && one != 0) {
            resultHundreds = resultHundreds + " " + outputTens(inputNumber - (hundred * 100));
        }

        if (tenth != 0 && one != 0) {
            resultHundreds = resultHundreds + " " + outputTens(inputNumber - (hundred * 100));
        }
        return resultHundreds;
    }

    public static String outputThousand(int inputNumber) { //функция вывода результата тысяч
        int oneThousand = inputNumber / 1000;
        int hundred = (inputNumber % 1000) / 100;
        int tenth = ((inputNumber - (oneThousand * 1000)) % 100) / 10;
        int one = (inputNumber - (oneThousand * 1000) - (tenth * 100)) % 10;
        String resultThousand = thousand[oneThousand-1];

        if (hundred == 0 && tenth != 0 && one != 0) {
            resultThousand = resultThousand + " " + outputTens(inputNumber - (oneThousand * 1000));
        } else {
            resultThousand = resultThousand + " " + outputHundreds(inputNumber - (oneThousand * 1000));
        }
        return resultThousand;
    }
}