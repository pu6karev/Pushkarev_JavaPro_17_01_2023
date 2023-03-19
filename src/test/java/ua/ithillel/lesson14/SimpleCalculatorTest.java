package ua.ithillel.lesson14;

import org.junit.jupiter.api.Test;
import ua.ithillel.lesson14.item1.SimpleCalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleCalculatorTest {

    @Test
    public void shouldSquareSum(){

        SimpleCalculator simpleCalculator = new SimpleCalculator();

        assertEquals(25, simpleCalculator.squareSum(3, 4));
        assertEquals(325, simpleCalculator.squareSum(10, 15));
    }
}
