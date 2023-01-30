package ua.ithillel.lesson3.BaseHomework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestMain {

    StringMethods methods = new StringMethods();
    // --- Тест задания 2: сколько раз встречается символ в строке-источнике
    @Test
    public void testFindSymbols(){

        int countChar = methods.findSymbolOccurance("We are the", 'e');
        Assertions.assertEquals(3, countChar);
    }

    // --- Тест задания 3: выясняем является подстрока target частью строки-источника
    @Test
    public void testFindWordPosition(){

        int idPosition = methods.findWordPosition("Attraction", "trac");
        Assertions.assertEquals(2, idPosition);
    }

    // --- Тест задания 4: передаем строку и возвращаем строку-реверс
    @Test
    public void testStringReverse(){

        String strReverse = methods.stringReverse("Plant");
        Assertions.assertEquals("tnalP", strReverse);
    }

    // --- Тест задания 5: передаем слово и проверяем является ли оно полиндромом
    @Test
    public void testIsPalindrome(){

        boolean isRight =  methods.isPalindrome("ARARA");
        Assertions.assertTrue(isRight);
    }

}
