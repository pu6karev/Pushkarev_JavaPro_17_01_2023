package ua.ithillel.lesson4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.*;


public class ArrayValueCalculatorTest {

    @Test
    public void testNotNumericSymbol() {
        ArrayValueCalculator arrayValueCalculator = new ArrayValueCalculator();

        String[][] arrayString = {
                {"1", "2", "3", "4"},
                {"11", "12", "b", "14"},
                {"21", "22", "23", "24"},
                {"31", "32", "33", "34"},
        };

        assertThrows(ArrayDataException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                arrayValueCalculator.doCalc(arrayString);
            }
        });

    }


    @Test
    public void testArrayLess() {
        ArrayValueCalculator arrayValueCalculator = new ArrayValueCalculator();

        String[][] arrayString = {
                {"1", "2", "3"},
                {"11", "12", "13"},
                {"21", "22", "23", "24"},
                {"31", "32", "33", "34"},
        };

        assertThrows(ArraySizeException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                arrayValueCalculator.doCalc(arrayString);
            }
        });
    }

    @Test
    public void testArrayLess34() {
        ArrayValueCalculator arrayValueCalculator = new ArrayValueCalculator();

        String[][] arrayString = {
                {"1",   "2",  "3",  "4"},
                {"11", "12", "13", "14"},
                {"21", "22", "23", "24"},
        };

        assertThrows(ArraySizeException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                arrayValueCalculator.doCalc(arrayString);
            }
        });
    }

    @Test
    public void shouldDoCalc() {
        ArrayValueCalculator arrayValueCalculator = new ArrayValueCalculator();

        String[][] arrayString = {
                {"1", "2", "3", "4"},
                {"11", "12", "13", "14"},
                {"21", "22", "23", "24"},
                {"31", "32", "33", "34"},
        };

        assertEquals(280, arrayValueCalculator.doCalc(arrayString));
    }
}
