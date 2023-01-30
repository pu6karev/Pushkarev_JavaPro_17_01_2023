package ua.ithillel.lesson2.part3;

public class Square implements Area{

    private double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public double calculateArea() {
        double area = side*side;
        System.out.println("Площадь квадрата = " + area);
        return area;
    }
}
