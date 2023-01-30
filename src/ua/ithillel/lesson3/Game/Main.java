package ua.ithillel.lesson3.Game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Guess guess = new Guess();
        String compWord = guess.getRandomWord();        // случайное слово, загаданное компьютером
        char[] arrayWord = compWord.toCharArray();      // слово переводим в массив символов
        int sizeWord = arrayWord.length;
        char[] guessWord = new char[15];                // массив с открываемыми буквами
        int numbOpen = 1;                               // кол-во открытых букв в массиве

        Scanner scanner = new Scanner(System.in);

        System.out.println("Привет. Я загадал простое английское слово. Попробуй его угадать. " + compWord);

        while (true){

            for (int i = 0; i < guessWord.length; i++) {
                guessWord[i] = '#';                         // заполняем весь массив символом #
                if(i < numbOpen && numbOpen < sizeWord){    // кол-во разрешенных к открытию букв
                    guessWord[i] = arrayWord[i];            // открываем буквы из слова-источника
                }
            }

            String charsOut = new String(guessWord);        // массив с частью открытых букв отправляем в строку
            System.out.println(charsOut);

            String userWord = scanner.nextLine();           // принимаем слово из консоли от пользователя
            System.out.println("Вы ввели слово " + userWord);

            // --- если слово совпало, выходим из цикла, иначе просим попытаться еще
            if(compWord.equals(userWord)){
                System.out.println("Поздравляю, это верное слово, угадано за " + numbOpen + " попытки(ок).");
                break;
            } else {
                System.out.println("Это неверное слово, попробуйте еще раз");
            }

            ++numbOpen;         // счетчик, для открытия следующей буквы в слове
        }
    }
}
