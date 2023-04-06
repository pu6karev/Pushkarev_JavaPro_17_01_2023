package ua.ithillel.lesson13;

import java.util.Arrays;

public class GenArrayList<T> /*implements GenericHillelList<T>*/{

//    private T[] baseArray =  (T[]) new Object[0];
//
//    @Override
//    public void add(T item) {
//
//        int newSize = (baseArray == null) ? 1 : baseArray.length + 1;
//        T[] ar = (T[]) new Object[newSize];
//        //T[] ar = Arrays.copyOf(baseArray, newSize, (Class<? extends T[]>) baseArray.getClass());
//
//        for (int i = 0; i < newSize-1; i++) {
//            ar[i] = baseArray[i];
//        }
//
//        ar[newSize-1] = item;
//        baseArray = ar;
//    }
//
//    @Override
//    public T remove(int index) {
//
//        if (baseArray == null) return null;
//        if (index >= baseArray.length) return null;
//
//        T[] ar = (T[]) new Object[baseArray.length-1];
//
//        if(index > 0) {
//            System.arraycopy(baseArray, 0, ar, 0, index);
//        }
//        System.arraycopy(baseArray, index+1, ar, index, baseArray.length-index-1);
//
//        T value = baseArray[index];
//        baseArray = ar;
//
//        return value;
//    }
//
//    @Override
//    public boolean contains(T item) {
//        return indexOf(item) != -1;
//    }
//
//    @Override
//    public int indexOf(T item) {
//        if(baseArray == null) return  -1;
//
//        for (int i = 0; i < baseArray.length; ++i) {
//            if(baseArray[i].equals(item)){
//                return i;
//            }
//        }
//        return -1;
//    }
//
//    @Override
//    public int size() {
//        return (baseArray == null) ? 0 : baseArray.length;
//    }
//
//    @Override
//    public T get(int index) {
//        return (baseArray == null || index >= baseArray.length) ? null : baseArray[index];
//    }
//
//    @Override
//    public T[] getAll(T[] type) {
//        return type;
//    }
//
//    @Override
//    public Object[] getAll() {
//        return baseArray;
//    }
}
