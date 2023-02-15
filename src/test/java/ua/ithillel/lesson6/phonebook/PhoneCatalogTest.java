package ua.ithillel.lesson6.phonebook;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PhoneCatalogTest {
    @Test
    public void shouldFind(){

        PhoneCatalog phoneCatalog = new PhoneCatalog();
        phoneCatalog.addRecord("Vasiliy Pushkarev", "(093)1115511");
        phoneCatalog.addRecord("Svetlana Petrova", "(096)1185634");
        phoneCatalog.addRecord("Oleg Shkolnik", "(093)93845784");
        phoneCatalog.addRecord("Viktor Trend", "(050)2109090");
        phoneCatalog.addRecord("Boris Jonson", "(097)17657651");
        phoneCatalog.addRecord("Vasiliy Pushkarev", "(050)87874332");
        phoneCatalog.addRecord("Dmitriy Veselov", "(098)2323872");
        phoneCatalog.addRecord("Svetlana Petrova", "(093)1267776");


        Record getOne = phoneCatalog.find("Vasiliy Pushkarev");
        Record rec = new Record("Vasiliy Pushkarev", "(093)1115511");
        assertEquals(rec, getOne);

        Record getTwo = phoneCatalog.find("Boris Jonson");
        Record rec2 = new Record("Boris Jonson", "(097)17657651");
        assertEquals(rec2, getTwo);
    }

    @Test
    public void shouldFindAll(){

        PhoneCatalog phoneCatalog = new PhoneCatalog();
        phoneCatalog.addRecord("Vasiliy Pushkarev", "(093)1115511");
        phoneCatalog.addRecord("Svetlana Petrova", "(096)1185634");
        phoneCatalog.addRecord("Oleg Shkolnik", "(093)93845784");
        phoneCatalog.addRecord("Viktor Trend", "(050)2109090");
        phoneCatalog.addRecord("Boris Jonson", "(097)17657651");
        phoneCatalog.addRecord("Vasiliy Pushkarev", "(050)87874332");
        phoneCatalog.addRecord("Dmitriy Veselov", "(098)2323872");
        phoneCatalog.addRecord("Svetlana Petrova", "(093)1267776");
        ArrayList<Record> fullOne = phoneCatalog.findAll("Vasiliy Pushkarev");

        ArrayList<Record> testList = new ArrayList<>();
        testList.add( new Record("Vasiliy Pushkarev", "(093)1115511") );
        testList.add( new Record("Vasiliy Pushkarev", "(050)87874332") );

        for (int i = 0; i < fullOne.size(); i++) {
            Record rec = fullOne.get(i);
            assertEquals(rec, testList.get(i));
        }

    }
}