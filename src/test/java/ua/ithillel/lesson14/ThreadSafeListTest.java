package ua.ithillel.lesson14;

import org.junit.jupiter.api.Test;
import ua.ithillel.lesson14.item3.ThreadSafeList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThreadSafeListTest {
    List<String> list = new ArrayList<>();
    Thread threads = null;

    @Test
    public  void shouldAdd() throws InterruptedException {

        for (int i = 0; i < 100; i++) {
            String item = "item" + i;

            threads = new Thread(new ThreadSafeList<>(list));
            threads.start();
            list.add(item);
        }
        threads.join();

        assertEquals(100, list.size());
    }

    @Test
    public  void shouldRemove() throws InterruptedException {

        // --- добавим 100 объектов
        shouldAdd();

        // --- удалим 100 объектов
        for (int i = 0; i < 100; i++) {
            threads = new Thread(new ThreadSafeList<>(list));
            threads.start();
            list.remove(0);
        }
        threads.join();

        assertEquals(0, list.size());
    }

    @Test
    public  void shouldGet() throws InterruptedException {

        // --- добавим 100 объектов
        shouldAdd();

        assertEquals("item"+0, list.get(0));
        assertEquals("item"+50, list.get(50));
    }

}
