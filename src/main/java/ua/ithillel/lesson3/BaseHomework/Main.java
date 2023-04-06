package ua.ithillel.lesson3.BaseHomework;

public class Main {
    public static void main(String[] args) {

        String source;
        StringMethods test = new StringMethods();

        // --- Задание 2: сколько раз встречается символ в строке-источнике
        char ch = 'a';
        source = "We are the champions";
        int countSymbols = test.findSymbolOccurance(source, ch);

        // --- Задание 3: выясняем является подстрока target частью строки-источника
        // --- 1-й вариант
        source = "Apollo";
        String target = "pollo";
        int index = test.findWordPosition(source, target);

        // --- 2-й вариант
        source = "Apple";
        target = "plant";
        index = test.findWordPosition(source, target);

        // --- Задание 4: передаем строку и возвращаем строку-реверс
        source = "Hello";
        String reverse = test.stringReverse(source);
//        System.out.println("Слово-источник - \"" + source + "\", реверс - \"" + reverse + "\"");

        // --- Задание 5: передаем слово и проверяем является ли оно полиндромом
        source = "заказ";
        boolean isWordPalindrome = test.isPalindrome(source);
//        System.out.println("Слово-источник - \"" + source + "\", является ли полиндромом = " + isWordPalindrome);
    }
}
