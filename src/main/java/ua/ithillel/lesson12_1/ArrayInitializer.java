package ua.ithillel.lesson12_1;

import java.util.Arrays;

public class ArrayInitializer {
    public static void init(double[] array){

        int idStartNext = array.length/2;
        // --- передаем в потоки оригинальный массив, каждому потоку через индексы даем знать, какой диапазон его
        MyThread myThread1 = new MyThread(array, 0, idStartNext);
        MyThread myThread2 = new MyThread(array, idStartNext, array.length);

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
        System.out.println("before=" + Arrays.toString(arr));
        init(arr);
        System.out.println("after=" + Arrays.toString(arr));
    }
}
