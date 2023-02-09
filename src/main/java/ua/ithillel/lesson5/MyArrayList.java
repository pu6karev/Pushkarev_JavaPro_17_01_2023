package ua.ithillel.lesson5;

public class MyArrayList implements HillelList{

    private String[] baseArray;
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
        if (baseArray == null) return null;
        if (index >= baseArray.length) return null;

        String[] ar = new String[baseArray.length-1];

        if(index > 0) {
            System.arraycopy(baseArray, 0, ar, 0, index);
        }
        System.arraycopy(baseArray, index+1, ar, index, baseArray.length-index-1);

        String value = baseArray[index];
        baseArray = ar;

        return value;
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
        return (baseArray == null || index >= baseArray.length) ? null : baseArray[index];
    }

    @Override
    public String[] getAll() {
        return (baseArray == null) ? null : baseArray;
    }
}
