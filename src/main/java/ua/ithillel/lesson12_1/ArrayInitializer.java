package ua.ithillel.lesson12_1;

import java.util.Arrays;

public class ArrayInitializer {
    public static void init(double[] array){

        int range1 = array.length/2;
        // --- скопируем оригинальный массив, разбив его на две части
        double[] array1 = Arrays.copyOf(array, range1);
        double[] array2 = Arrays.copyOfRange(array, range1, array.length);

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

    public static void main(String[] args) {
        double[] arr = new double[20];
        Arrays.fill(arr, 7.0);
        System.out.println(Arrays.toString(arr));
        init(arr);
    }
}
