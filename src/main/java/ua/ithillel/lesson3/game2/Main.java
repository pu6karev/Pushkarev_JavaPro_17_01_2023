package ua.ithillel.lesson3.game2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Guess guess = new Guess();
        String compWord = guess.getRandomWord();            // случайное слово, загаданное компьютером
        char[] arrayCompWord = compWord.toCharArray();      // слово переводим в массив символов
        char[] arrayOutWord = new char[15];                 // массив с открываемыми буквами
        int numbTrying = 1;                                   // кол-во попыток

        Scanner scanner = new Scanner(System.in);

        System.out.println("Привет. Я загадал простое английское слово. Попробуй его угадать. (" + compWord + ")");

        while (true){

            String userWord = scanner.nextLine();               // принимаем слово из консоли от пользователя
            System.out.println("Вы ввели слово " + userWord);
            char[] arrayUserWord = userWord.toCharArray();      // слово переводим в массив символов

            // ---
            if(compWord.equals(userWord)){
                System.out.println("Поздравляю, это верное слово, угадано за " + numbTrying + " попытки(ок).");
                break;
            } else {

                for (int i = 0; i < arrayOutWord.length; i++) {

                     // --- узнаем меньший массив, чтобы случайно не выйти за индекс
                    int minLen = Math.min(arrayCompWord.length, arrayUserWord.length);
                     // --- пока не выходим за пределы массива и при совпадении символов
                    if (i < minLen && arrayCompWord[i] == arrayUserWord[i]){
                      arrayOutWord[i] = arrayCompWord[i];               // заполняем совпавшую букву в массив для вывода
                    } else {
                        arrayOutWord[i] = '#';                          // заполняем остальной массив символом #
                  }
                }

                System.out.println("Это неверное слово, попробуйте еще раз");
                String charsOut = new String(arrayOutWord);        // массив с частью открытых букв отправляем в строку
                System.out.println(charsOut);
            }

            ++numbTrying;         // счет попыток
        }
    }
}
