package ua.ithillel.lesson12;

import java.util.Arrays;

public class ArrayInitializer {
    private double[] array1;
    private double[] array2;

    // --- геттеры для проверки в тестах
    public double[] getArray1() {
        return array1;
    }

    public double[] getArray2() {
        return array2;
    }

    public void init(double[] array){

        int range1 = array.length/2;
        // --- скопируем оригинальный массив, разбив его на две части
        array1 = Arrays.copyOf(array, range1);
        array2 = Arrays.copyOfRange(array, range1, array.length);

        MyThread myThread1 = new MyThread(array1);
        MyThread myThread2 = new MyThread(array2);

        myThread1.start();
        myThread2.start();

        try {
            myThread1.join();
            myThread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
