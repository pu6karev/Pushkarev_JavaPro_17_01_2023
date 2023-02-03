package ua.ithillel.lesson3.BaseHomework;

public class StringMethods {

    // --- Задание 2: сколько раз встречается символ в строке-источнике
    public int findSymbolOccurance(String aString, char aChar){

        int countSymbols = 0;
        char[] array = aString.toCharArray();

        for (char ch: array) {
            if(ch == aChar) ++countSymbols;
        }

        return countSymbols;
    }

    // --- Задание 3: выясняем является подстрока target частью строки-источника
    public int  findWordPosition(String source, String target){
        return source.indexOf(target);
    }

    // --- Задание 4: передаем строку и возвращаем строку-реверс
    public String stringReverse(String aString){

        StringBuilder s = new StringBuilder(aString);
        s.reverse();

        return s.toString();
    }

    // --- Задание 5: передаем слово и проверяем является ли оно полиндромом
    public  boolean isPalindrome(String aString){

        String s = stringReverse(aString);          // используем функцию-реверс из задания 4

        return  aString.equals(s);
    }
}
