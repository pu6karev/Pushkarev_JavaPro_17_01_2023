package ua.ithillel.lesson14.item1;

import java.util.function.Supplier;

public class SquareSupplier implements Supplier<Integer> {
    private final int value;
    public SquareSupplier(int value) {
        this.value = value;
    }
    public Integer get() {
        return value * value;
    }
}
