package ua.ithillel.lesson16;

public class QuickSort {

    public static void sorting(int[] array, int first, int last){

        if(first < last){

            int left = first;
            int right = last;
            int middle = (first + last)/2;

            do{
                while (array[left] < array[middle]) left++;     // обнаружив, значение ячейки справа больше, выход с индексом
                while (array[right] > array[middle]) right--;   // обнаружив, значение ячейки слева меньше,  выход с индексом

                if(left <= right){                              // пока индексы не встретились посередине
                    int temp = array[left];                     // swap
                    array[left] = array[right];                 // swap
                    array[right] = temp;                        // swap
                    left++;                                     // уходим с тек.индекса правее
                    right--;                                    // уходим с тек.индекса левее
                }
            } while (left < right);

            sorting(array, first, right);                       // рекурсивно передаем индексы сначала до новой середки
            sorting(array, left, last);                         // рекурсивно передаем индексы с новой середки до конца
        }
    }
}
