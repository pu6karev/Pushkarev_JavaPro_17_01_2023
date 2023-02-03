package ua.ithillel.lesson2.part2;

// --- создание абстрактного родительского класса для потомков Cat и Dog
abstract public class Animal {

    protected String name;

     // --- конструктор
    public Animal(String name) {
        this.name = name;
    }

     // --- методы
    abstract public void run(double distance);

    abstract public void swim(double distance);

}
