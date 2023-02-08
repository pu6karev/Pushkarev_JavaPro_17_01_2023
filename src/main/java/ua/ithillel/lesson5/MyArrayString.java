package ua.ithillel.lesson5;

public class MyArrayString implements HillelList{

    public static String[] baseArray;
    @Override
    public void add(String item) {

        int newSize = (baseArray == null) ? 1 : baseArray.length + 1;
        String[] ar = new String[newSize];

        for (int i = 0; i < newSize-1; i++) {
            ar[i] = baseArray[i];
        }

        ar[newSize-1] = item;
        baseArray = ar;
    }

    @Override
    public String remove(int index) {
        return null;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        if(baseArray == null) return  -1;

        for (int i = 0; i < baseArray.length; ++i) {
            if(baseArray[i].equals(item)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return (baseArray == null) ? 0 : baseArray.length;
    }

    @Override
    public String get(int index) {
        return (baseArray == null) ? null : baseArray[index];
    }

    @Override
    public String[] getAll() {
        return (baseArray == null) ? null : baseArray;
    }
}
