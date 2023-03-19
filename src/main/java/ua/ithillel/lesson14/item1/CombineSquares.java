package ua.ithillel.lesson14.item1;
import java.util.function.BiFunction;

// --- третий параметр Integer является результатом выполнения ф-ии apply()
public class CombineSquares implements BiFunction<Integer, Integer, Integer> {
    public Integer apply(Integer firstSquare, Integer secondSquare) {
        return firstSquare + secondSquare;
    }
}
