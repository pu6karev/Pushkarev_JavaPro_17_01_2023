package ua.ithillel.lesson3.game3;

public class Guess {
    private final String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado" , "broccoli", "carrot",
                                    "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut",
                                    "olive", " pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

    private final int sizeArray = words.length;                       // узнаем длину массива с набором слов


    // --- вернем случайное слово из нащего массива
    public String getRandomWord(){
        int idRandom = (int)(Math.random()*sizeArray);          // случайное число в пределах нашего массива слов
        return words[idRandom];
    }
}
