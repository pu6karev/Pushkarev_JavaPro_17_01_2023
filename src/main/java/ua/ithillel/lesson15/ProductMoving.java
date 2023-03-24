package ua.ithillel.lesson15;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ProductMoving {

    private final List<Product> list = new ArrayList<>();

    public void add(Product product){
        list.add(product);
    }

    public List<Product> getAll(){
        return list;
    }

    public List<Product> getCategoryForPrice(Category category, double minPrice) {

        List<Product> categoryAndPrice = list.stream()
                .filter(s -> s.getCategory() == category && s.getPrice() > minPrice)
                .toList();

        return categoryAndPrice;
    }

    public List<Product> getCategoryWithDiscount(Category category, double discountPercent) {

        List<Product> categoryAndDiscount = list.stream()
                .filter(s -> (s.getCategory() == category && s.isDiscount()))
                .peek(s -> s.setDiscount(discountPercent))
                .toList();

        return categoryAndDiscount;
    }

    public Product getCheapest() {

        Product minPriceProd = list.stream()
                .min((s1,s2) -> Double.compare(s1.getPrice(), s2.getPrice()))
                .orElseThrow(IllegalStateException::new);

        return minPriceProd;
    }

    // --- получим список объектов с последними добавленными продуктами
    public List<Product> getLatestProducts(int numbOfLastProducts) {

        List<Product> listLatest = list.stream()
                .sorted(Comparator.comparing(Product::getAddingDate))
                .skip(list.size() - numbOfLastProducts)
                .toList();

        return listLatest;
    }

    // --- получим общую сумму продуктов, начиная с указанной даты, нужной категории и цене, не выше заданной
    public double getSumByCriterion(LocalDate startDate, Category category, double maxPrice) {

        double listSum = list.stream()
                .filter(s -> s.getAddingDate().isAfter(startDate) && s.getCategory()==category && s.getPrice() <= maxPrice)
                .mapToDouble(Product::getPrice)
                .sum();

        return listSum;
    }


    // --- группируем по категории и собираем в List соответсвующие объекты Product
    public Map<Category, List<Product>> getMapList(){

        Map<Category, List<Product>> map = list.stream().collect(Collectors.groupingBy(Product::getCategory));

        return map;
    }

    // --- группируем по категории и собираем в строку соответсвующие названия продуктов через запятую
    public Map<Category, String> getMapString(){

        Map<Category, String> map = list.stream().collect(Collectors.groupingBy(Product::getCategory,
                Collectors.mapping(Product::getName, Collectors.joining(", "))));

        return map;
    }


}
