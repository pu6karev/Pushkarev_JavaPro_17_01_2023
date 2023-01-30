package ua.ithillel.lesson2.part3;

public class Main {
    public static void main(String[] args) {

        Area[] shapes = new Area[5];

        shapes[0] = new Circle(2);
        shapes[1] = new Square(5.5);
        shapes[2] = new Triangle(3, 5);
        shapes[3] = new Square(10);
        shapes[4] = new Circle(7.8);

        double sum = sumArea(shapes);

        System.out.println("Общая площадь всех фигур в массиве = " + sum);
    }

    // --- метод для суммирования площадей из разных фигур
    static  double sumArea(Area[] arrayShape){
        double sum = 0;

        for (Area shape: arrayShape) {
            sum += shape.calculateArea();
        }
        return sum;
    }
}
