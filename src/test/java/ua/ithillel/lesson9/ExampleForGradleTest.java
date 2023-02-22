package ua.ithillel.lesson9;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class ExampleForGradleTest {


    @Test
    void shouldArrayAvg() {
        ExampleForGradle example = new ExampleForGradle();
        int[] array = {5, 10, 12, 18, 36};

        assertEquals(16.2, example.arrayAvg(array));
    }

    @Test
    void shouldArrayEmpty() {
        ExampleForGradle example = new ExampleForGradle();
        int[] array = new int[0];

        assertThrows(ArithmeticException.class, new Executable() {
                    @Override
                    public void execute() throws Throwable {
                        assertEquals(0, example.arrayAvg(array));
                    }
                });

    }
}