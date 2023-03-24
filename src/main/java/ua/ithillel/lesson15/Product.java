package ua.ithillel.lesson15;

import java.time.LocalDate;

public class Product {
    private final String name;
    private final Category category;
    private double price;
    private final boolean discount;
    private final LocalDate addingDate;

    public Product(String name, Category category, double price, boolean discount, LocalDate addingDate) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.discount = discount;
        this.addingDate = addingDate;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isDiscount() {
        return discount;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getAddingDate() {
        return addingDate;
    }

    // --- установим новую цену по скидке
    public boolean setDiscount(double discountPercent) {
        if(discount) {
            this.price = price * (1 - discountPercent/100);
            return true;
        } else {
            System.out.println("Скидка не разрешена, проверьте предварительную фильтрацию");
            return false;
        }
    }

    @Override
    public String toString() {
        return "\nProduct{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", discount=" + discount +
                ", addingDate=" + addingDate +
                '}';
    }
}
