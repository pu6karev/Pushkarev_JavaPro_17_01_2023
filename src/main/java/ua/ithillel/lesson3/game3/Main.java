package ua.ithillel.lesson3.game3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Guess guess = new Guess();
        String compWord = guess.getRandomWord();                // случайное слово, загаданное компьютером
        char[] arrayOutWord = new char[15];                     // массив с открываемыми буквами
        int numbTrying = 1;                                     // кол-во попыток

        Scanner scanner = new Scanner(System.in);

        System.out.println("Привет. Я загадал простое английское слово. Попробуй его угадать. (" + compWord + ")");

        while (true){

            String userWord = scanner.nextLine();               // принимаем слово из консоли от пользователя
            System.out.println("Вы ввели слово " + userWord);

            // ---
            if(compWord.equals(userWord)){
                System.out.println("Поздравляю, это верное слово, угадано за " + numbTrying + " попытки(ок).");
                break;
            } else {

                for (int i = 0; i < arrayOutWord.length; i++) {
                     // --- узнаем меньшую строку, чтобы случайно не выйти за размер одной из них по индексу
                    int minLen = Math.min(compWord.length(), userWord.length());
                     // --- пока не выходим за пределы длины строки и при совпадении символов
                    if (i < minLen && compWord.charAt(i) == userWord.charAt(i)){
                      arrayOutWord[i] = compWord.charAt(i);             // заполняем совпавшую букву в массив для вывода
                    } else {
                      arrayOutWord[i] = '#';                            // заполняем остальной массив символом #
                  }
                }

                System.out.println("Это неверное слово, попробуйте еще раз");
                String charsOut = new String(arrayOutWord);             // массив с частью открытых букв отправляем в строку
                System.out.println(charsOut);
            }

            ++numbTrying;         // счет попыток
        }
    }
}
