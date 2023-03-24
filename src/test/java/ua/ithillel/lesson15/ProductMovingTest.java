package ua.ithillel.lesson15;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProductMovingTest {
    ProductMoving productMoving = new ProductMoving();
    Product[] arrayProduct = new Product[6];

    @BeforeEach
    public void init(){
        LocalDate date = LocalDate.of(2020,12,12);
        arrayProduct[0] = new Product("OOP", Category.BOOK, 420, true, date);
        arrayProduct[1] = new Product("Java", Category.BOOK, 699, false, date.plusYears(1));
        arrayProduct[2] = new Product("Johnson", Category.PEN, 2.5, false, date);
        arrayProduct[3] = new Product("Rainbow", Category.PAINT, 12, false, date.plusMonths(5));
        arrayProduct[4] = new Product("Python", Category.BOOK, 190, true, date.minusYears(1));
        arrayProduct[5] = new Product("Fortran", Category.BOOK, 38, true, date.minusYears(5));
    }

    @Test
    void shouldAdd() {
        for (Product elem : arrayProduct) productMoving.add(elem);      // добавление элементов из массива в список

        //assertArrayEquals(arrayProduct, );
        assertEquals(arrayProduct.length, productMoving.getAll().size());
    }

    @Test
    void shouldGetCategoryForPrice() {
        for (Product elem : arrayProduct) productMoving.add(elem);      // добавление элементов из массива в список

        List<Product> filterList = productMoving.getProductsByCategoryAndPrice(Category.BOOK, 250);

        assertEquals(2, filterList.size());
    }

    @Test
    void shouldGetCategoryWithDiscount() {
        for (Product elem : arrayProduct) productMoving.add(elem);      // добавление элементов из массива в список

        List<Product> filterList = productMoving.getCategoryWithDiscount(Category.BOOK, 10);

        assertEquals(420*0.9, filterList.get(0).getPrice());
        assertEquals(38*0.9, filterList.get(filterList.size()-1).getPrice());
    }

    @Test
    void shouldGetCheapest() {
        for (Product elem : arrayProduct) productMoving.add(elem);      // добавление элементов из массива в список

        Product cheapProduct = productMoving.getCheapest();

        assertEquals(2.5, cheapProduct.getPrice());
    }

    @Test
    void shouldGetNullCheapest() {

        assertThrows(IllegalStateException.class, () -> productMoving.getCheapest());
    }

    @Test
    void shouldGetLatestProducts() {
        for (Product elem : arrayProduct) productMoving.add(elem);      // добавление элементов из массива в список

        List<Product> filterList = productMoving.getLatestProducts(3);

        assertEquals(LocalDate.of(2021,12,12), filterList.get(2).getAddingDate());
    }

    @Test
    void shouldGetSumByCriterion() {
        for (Product elem : arrayProduct) productMoving.add(elem);      // добавление элементов из массива в список

        LocalDate beginYear = LocalDate.of(2020, 1,1);
        double sum = productMoving.getSumByCriterion(beginYear, Category.BOOK, 500);

        assertEquals(420, sum);
    }

    // --- для проверки выведем категорию BOOK и соотв. этой категории список объектов Product, их должно быть 4
    @Test
    void shouldGetMapList() {
        for (Product elem : arrayProduct) productMoving.add(elem);      // добавление элементов из массива в список

        Map<Category, List<Product>> map = productMoving.getMapList();

        assertTrue(map.containsKey(Category.BOOK));
        assertTrue(map.containsKey(Category.PAINT));

        List<Product> listUnion = map.get(Category.BOOK);
        assertEquals(4, listUnion.size());
    }

    // --- для проверки выведем категорию BOOK и соотв. этой категории строку со списком книг через запятую
    @Test
    void shouldGetMapString() {
        for (Product elem : arrayProduct) productMoving.add(elem);      // добавление элементов из массива в список

        Map<Category, String> map = productMoving.getMapString();

        assertTrue(map.containsKey(Category.BOOK));
        assertEquals(map.get(Category.BOOK), "OOP, Java, Python, Fortran");
    }
}