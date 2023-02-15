package ua.ithillel.lesson6.phonebook;

import java.util.ArrayList;

public class PhoneCatalog {

    private final ArrayList<Record> recordList = new ArrayList<>();

    public void addRecord(String name, String number){
        Record rec = new Record(name, number);
        recordList.add(rec);
    }

    public Record find(String name){

        for (Record rec : recordList) {

            if(name.equals(rec.getName()) ){
                return rec;
            }
        }

        return null;
    }

    public ArrayList<Record> findAll(String name){

        ArrayList<Record> nameList = new ArrayList<>();

        for (Record rec : recordList) {

            if(name.equals(rec.getName()) ){
                nameList.add(rec);
            }
        }

        return nameList;
    }
}
