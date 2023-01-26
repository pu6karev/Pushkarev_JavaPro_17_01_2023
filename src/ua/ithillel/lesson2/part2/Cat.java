package ua.ithillel.lesson2.part2;

public class Cat extends Animal{

    private final double MAX_DIST_RUN = 200;

    public Cat(String name) {
        super(name);
    }

    @Override
    public void run(double distance) {
        System.out.println();

        if(distance > MAX_DIST_RUN) {
            System.out.println(name + " has limit for his running and can't run " + distance + " metres");
            distance = MAX_DIST_RUN;
        }

        System.out.println(name + " has run " + distance + " metres");
    }

    @Override
    public void swim(double distance) {
        System.out.println();
        System.out.println(name + " can't swim at all. ");
    }
}
