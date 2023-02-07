package ua.ithillel.lesson4;

public class ArrayValueCalculator {

    public static final int DIMENSION = 4;

    public int doCalc(String[][] array){

        if(array.length != DIMENSION){
            throw new ArraySizeException("Array must have dimension 4*4");
        }

        int rowCounter = 0;
        for (String[] row : array) {
            rowCounter += doCalc(row);
        }

        return rowCounter;
    }

    public int doCalc(String[] row){

        if(row.length != DIMENSION){
            throw new ArraySizeException("Array must have dimension 4*4");
        }

        int counter = 0;
        for (String element : row) {
            try{
                counter += Integer.parseInt(element);
            } catch (NumberFormatException ne){
                //System.out.println("Attention: row=" + row + " element=" + element);
                throw new ArrayDataException(ne);
            }
        }

        return counter;
    }

}
