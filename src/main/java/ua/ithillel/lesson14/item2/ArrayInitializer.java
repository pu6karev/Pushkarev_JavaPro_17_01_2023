package ua.ithillel.lesson14.item2;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class ArrayInitializer {
    public static void main(String[] args) {
        int numberTreads = 4;                   // кол-во потоков, на которые разобъем задачу
        double[] arr = new double[20];
        Arrays.fill(arr, 7.0);

        System.out.println("before=" + Arrays.toString(arr));

        var pool = new ForkJoinPool(numberTreads);
        MyTask myTask = new MyTask(arr, 0, arr.length-1, numberTreads);
        pool.invoke(myTask);

        System.out.println("after=" + Arrays.toString(arr));
    }
}
