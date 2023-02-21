package ua.ithillel.lesson8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class FileNavigatorTest {

    FileData fileFamily1 = new FileData("/home/family/","MyJob", 35_000);
    FileData fileFamily2 = new FileData("/home/family/","Photos", 1_200_235);
    FileData fileFamily3 = new FileData("/home/family/","Finance", 8_050);
    FileData fileFun1 = new FileData("/home/entertainment/","Games", 10_050_127);
    FileData fileFun2 = new FileData("/home/entertainment/","Photos", 2_200_000);
    FileData fileFun3 = new FileData("/home/entertainment/","Movies", 35_185_020);
    FileData fileFun4 = new FileData("/home/entertainment/","Music", 7_185_000);

    FileNavigator fileNavigator = new FileNavigator();

    @BeforeEach
    void initAdd(){
        fileNavigator.add(fileFamily1);
        fileNavigator.add(fileFamily2);
        fileNavigator.add(fileFamily3);
        fileNavigator.add(fileFun1);
        fileNavigator.add(fileFun2);
        fileNavigator.add(fileFun3);
        fileNavigator.add(fileFun4);
    }


    @Test
    public void shouldAdd() {
        //initAdd();

        Map<String, List<FileData>> map = fileNavigator.getMap();
        // --- сколько ключей содержит карта
        assertEquals(2, map.size());
        // --- из карты по ключу "путь к файлу" вытягиваем List, содержащий 3 файла
        assertEquals(3, map.get("/home/family/").size());
        // --- из карты по ключу "путь к файлу" вытягиваем List, содержащий 4 файла
        assertEquals(4, map.get("/home/entertainment/").size());
    }

    @Test
    public void shouldFind() {
        List<FileData> expextedList = new ArrayList<>();
        expextedList.add(fileFamily1);
        expextedList.add(fileFamily2);
        expextedList.add(fileFamily3);

        List<FileData> checkedList = fileNavigator.find("/home/family/");
        // --- сравнение списков с тремя объектами FileData
        assertEquals(expextedList, checkedList);

        // --- проверка еще одним способом
        Iterable<FileData> al = new ArrayList<>(expextedList);
        assertIterableEquals(al, checkedList);
    }

    @Test
    public void shouldFilterBySize() {
        List<FileData>  checkedList1 = fileNavigator.filterBySize(1_000_000);
        // --- два файла размером меньше 1 млн.
        assertEquals(2, checkedList1.size());


        List<FileData>  checkedList2 = fileNavigator.filterBySize(10_000_000);
        // --- 4 файла размером меньше 10 млн.
        assertEquals(5, checkedList2.size());
    }

    @Test
    public void shouldRemove() {
        Map<String, List<FileData>> map = fileNavigator.getMap();
        assertEquals(2, map.size());

        map.remove("/home/family/");
        assertEquals(1, map.size());
    }

    @Test
    public void shouldSortBySize() {
        List<FileData> sortedList = fileNavigator.sortBySize();

        FileData fileDataFirst = sortedList.get(0);
        int len = sortedList.size();
        FileData fileDataLast =sortedList.get(len-1);

        // --- сравним размеры самомго маленького и самого большого файла
        if(fileDataFirst != null) assertEquals(8_050, fileDataFirst.getSize());
        if(fileDataLast != null) assertEquals(35_185_020, fileDataLast.getSize());
    }

    @Test
    public void shouldAddWithException(){
        // --- после действи ф-ии init(), добавим путь с расхождением
        FileData fileFamily4 = new FileData("/home/family/","School", 8_050);

        assertThrows(WrongPathException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                fileNavigator.add("/home/familyWrong/", fileFamily4);
            }
        });

        // --- добавление с правильным путем и проверка
        fileNavigator.add("/home/family/", fileFamily4);

        // ---
        Map<String, List<FileData>> map = fileNavigator.getMap();
        // --- сколько ключей содержит карта
        assertEquals(2, map.size());
        // --- из карты по ключу "путь к файлу" вытягиваем List, содержащий 3 файла
        assertEquals(4, map.get("/home/family/").size());
    }

}