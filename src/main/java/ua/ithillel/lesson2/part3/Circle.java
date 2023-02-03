package ua.ithillel.lesson2.part3;

public class Circle implements Area{
    private final static double PI = 3.14159;
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }
    @Override
    public double calculateArea() {
        double area = PI*radius*radius;
        System.out.println("Площадь круга = " + area);
        return area;
    }
}
