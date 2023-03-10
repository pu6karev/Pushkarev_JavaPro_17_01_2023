package ua.ithillel.lesson12_1;

public class MyThread extends Thread {

    private final double[] array;
    private final int idStart;
    private final int idEnd;


    public MyThread(double[] array, int idStart, int idEnd) {
        this.array = array;
        this.idStart = idStart;
        this.idEnd = idEnd;
    }

    @Override
    public void run() {
        for (int i = idStart; i < idEnd; i++) {
            array[i] = (array[i] * Math.sin(0.2 + i / 5.0) * Math.cos(0.2 + i / 5.0) * Math.cos(0.4 + i / 2.0));
        }
    }
}
