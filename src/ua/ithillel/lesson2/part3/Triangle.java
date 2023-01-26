package ua.ithillel.lesson2.part3;

public class Triangle implements Area {

    private  double side;
    private  double height;

    public Triangle(double side, double height) {
        this.side = side;
        this.height = height;
    }


    // S = высоту умножить на сторону и поделить на 2
    @Override
    public double calculateArea() {
        double area = (side*height)/2;
        System.out.println("Площадь треугольника = " + area);
        return area;
    }
}
