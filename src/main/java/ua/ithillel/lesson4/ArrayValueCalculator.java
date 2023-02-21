package ua.ithillel.lesson4;

public class ArrayValueCalculator {

    public static final int DIMENSION = 4;

    public int doCalc(String[][] array) throws ArraySizeException {

        if(array.length != DIMENSION){
            throw new ArraySizeException("Array must have dimension 4*4");
        }

        int rowCounter = 0;
        for (int i = 0; i < array.length; ++i) {
            rowCounter += doCalc(array[i], i);
        }

        return rowCounter;
    }


    private int doCalc(String[] row, int firstIndex) throws ArraySizeException {

        if(row.length != DIMENSION){
            throw new ArraySizeException("Array must have dimension 4*4");
        }

        int counter = 0;
        for (int i = 0; i < row.length; ++i) {
            try{
                counter += Integer.parseInt(row[i]);
            } catch (NumberFormatException ne){
                throw new ArrayDataException(firstIndex, i);
            }
        }

        return counter;
    }

}
