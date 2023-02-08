package ua.ithillel.lesson5;

public class Main {
    public static void main(String[] args) {

        MyArrayString myArrayString = new MyArrayString();

        myArrayString.add("elem1");
        myArrayString.add("elem2");
        myArrayString.add("elem3");

        for (String elem: MyArrayString.baseArray) {
            System.out.println(elem);
        }

        System.out.println("indexOf=" + myArrayString.indexOf("elem2"));
        System.out.println("indexOf=" + myArrayString.indexOf("elem3"));
        System.out.println("indexOf=" + myArrayString.indexOf("elem4"));
    }
}
