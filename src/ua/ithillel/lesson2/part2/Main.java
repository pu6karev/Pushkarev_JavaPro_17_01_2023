package ua.ithillel.lesson2.part2;

public class Main {
    public static void main(String[] args) {

        Dog dog = new Dog("Brown");
        Cat cat = new Cat("Barsik");

        dog.run(350);
        dog.run(550);
        cat.run(300);

        dog.swim(12);
        cat.swim(12);
    }
}
