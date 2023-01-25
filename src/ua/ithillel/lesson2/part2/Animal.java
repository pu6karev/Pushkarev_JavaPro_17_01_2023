package ua.ithillel.lesson2.part2;

public class Animal {

    private  String name;
    private  double distance;

     // --- конструктор
    public Animal(String name, double distance) {
        this.name = name;
        this.distance = distance;
    }

     // --- методы
    public void run(){
        System.out.println(name + " running...");
    }

    public void swim(){
        System.out.println(name + " swimming...");
    }

}
