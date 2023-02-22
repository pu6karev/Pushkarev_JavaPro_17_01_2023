package ua.ithillel.lesson9;

public class ExampleForGradle {

    public double arrayAvg(int[] array){
        double sum = 0;

        if(array.length == 0){
            throw new ArithmeticException("Вы передали пустой массив");
        }

        for (int elem: array) {
            sum += elem;
        }

        return (sum / array.length);
    }

}
