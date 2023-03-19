package ua.ithillel.lesson14.item1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class SimpleCalculator {

    public int squareSum(int first, int second) {
        Supplier<Integer> firstSquare = new SquareSupplier(first);
        CompletableFuture<Integer> firstSquareFuture = CompletableFuture.supplyAsync(firstSquare);

        Supplier<Integer> secondSquare = new SquareSupplier(second);
        CompletableFuture<Integer> secondSquareFuture = CompletableFuture.supplyAsync(secondSquare);

        BiFunction<Integer, Integer, Integer> combineSquares = new CombineSquares();
        // --- получим сумму квадратов двух чисел
        CompletableFuture<Integer> sumFuture = firstSquareFuture.thenCombine(secondSquareFuture, combineSquares);

        try {
            return sumFuture.get();
        }  catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }
}