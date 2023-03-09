package ua.ithillel.lesson12_1;

import java.util.Arrays;

public class MyThread extends Thread {

    private final double[] array;

    public MyThread(double[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length; i++) {
            array[i] = (array[i] * Math.sin(0.2 + i / 5.0) * Math.cos(0.2 + i / 5.0) * Math.cos(0.4 + i / 2.0));
        }
        System.out.println(Arrays.toString(array));
    }
}
