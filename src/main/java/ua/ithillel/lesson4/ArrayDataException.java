package ua.ithillel.lesson4;

public class ArrayDataException extends RuntimeException{
    public ArrayDataException(Exception exception) {
        super(exception);
    }

    public ArrayDataException(int idColumn, int idRow) {
        super("Ошибка, в ячейке [" + idColumn + ":" + idRow + "] не числовое значение");
    }

}
