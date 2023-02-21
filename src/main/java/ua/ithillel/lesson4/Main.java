package ua.ithillel.lesson4;

public class Main {
    public static void main(String[] args) {

        String[][] arrayString = {
                {"1", "2", "3", "4"},
                {"11", "12", "13", "14"},
                {"21", "22", "23", "24"},
                {"31", "32", "33", "34"},
        };

        ArrayValueCalculator avc = new ArrayValueCalculator();

        int i = avc.doCalc(arrayString);
        System.out.println("i=" + i);
    }
}
