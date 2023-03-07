package ua.ithillel.lesson11;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ua.ithillel.lesson9.ExampleForGradle;

import static org.junit.jupiter.api.Assertions.*;

class ExampleForActionTest {

    @Test
    void arrayAvg() {
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