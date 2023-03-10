package ua.ithillel.lesson12;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ArrayInitializerTest {
    double[] array = new double[15];
    ArrayInitializer initializer = new ArrayInitializer();

    @BeforeEach
    public void setIni(){
        Arrays.fill(array, 7.7);
        initializer.init(array);
    }

    @Test
    public void shouldArraysSize(){
        // --- получим размеры первого и второго массива
        assertEquals(7, initializer.getArray1().length);
        assertEquals(8, initializer.getArray2().length);
    }

    @Test
    public void shouldInitArrayPart1(){

        double[] arrayGet1 = initializer.getArray1();
        // --- проверим правильность расчетов первой части массива
        for (int i = 0; i < arrayGet1.length; i++) {
            array[i] = (array[i] * Math.sin(0.2 + i / 5.0) * Math.cos(0.2 + i / 5.0) * Math.cos(0.4 + i / 2.0));
            assertEquals(arrayGet1[i], array[i]);
        }
    }

    @Test
    public void shouldInitArrayPart2(){
        // --- проверим правильность расчетов второй части массива
        double[] arrayGet2 = initializer.getArray2();
        for (int i = array.length/2, j=0; i < array.length; i++, j++) {
            array[i] = (array[i] * Math.sin(0.2 + j / 5.0) * Math.cos(0.2 + j / 5.0) * Math.cos(0.4 + j / 2.0));
            assertEquals(arrayGet2[j], array[i]);
        }
    }
}