package ua.ithillel.lesson2.part2;

public class Animal {

    protected String name;

     // --- конструктор
    public Animal(String name) {
        this.name = name;
    }

     // --- методы
    public void run(double distance){

        System.out.println(name + " was running " + distance + " metres");
    }

    public void swim(double distance){

        System.out.println(name + " was swimming " + distance + " metres");
    }

}
