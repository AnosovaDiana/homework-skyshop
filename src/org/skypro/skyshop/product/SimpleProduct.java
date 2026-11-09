package org.skypro.skyshop.product;

public class SimpleProduct extends Product {
    private double price;

    public SimpleProduct(String name, double price) {
        super(name);
        if (price <= 0) {
            throw new IllegalArgumentException("Цена продукта должна быть строго больше 0!");
        }
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return getName() + ": " + price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }
}
