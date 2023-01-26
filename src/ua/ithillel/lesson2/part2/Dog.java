package ua.ithillel.lesson2.part2;

public class Dog extends Animal{

    private final double MAX_DIST_RUN = 500;
    private final double MAX_DIST_SWIM = 10;

    public Dog(String name) {

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

        if(distance > MAX_DIST_SWIM) {
            System.out.println(name + " has limit for his swimming and can't swim  " + distance + " metres");
            distance = MAX_DIST_SWIM;
        }

        System.out.println(name + " has swum " + distance + " metres");
    }
}
