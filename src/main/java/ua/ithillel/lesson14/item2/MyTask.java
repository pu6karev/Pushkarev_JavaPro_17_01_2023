package ua.ithillel.lesson14.item2;

import java.util.concurrent.RecursiveAction;

public class MyTask extends RecursiveAction {

    private final double[] array;
    private final int start;
    private final int end;
    private final int numOfThreads;


    public MyTask(double[] array, int start, int end, int numOfThreads) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.numOfThreads = numOfThreads;
    }

    @Override
    protected void compute() {
        if(end - start <= array.length / numOfThreads){
            for (int i = start; i <= end; i++) {
                array[i] = (array[i] * Math.sin(0.2 + i / 5.0) * Math.cos(0.2 + i / 5.0) * Math.cos(0.4 + i / 2.0));
                //System.out.println("i=" + i + " array[i]=" + array[i]);
            }
        } else {

            int middle = (start + end) / 2;
            var left = new MyTask(array, start, middle, numOfThreads);
            var right = new MyTask(array, middle+1, end, numOfThreads);

            left.fork();
            right.fork();

            left.join();
            right.join();
        }
    }
}
