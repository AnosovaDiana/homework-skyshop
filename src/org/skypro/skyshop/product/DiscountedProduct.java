package org.skypro.skyshop.product;

public class DiscountedProduct extends Product {
    private double basePrice;
    private double discount;

    public DiscountedProduct(String name, double basePrice, double discount) {
        super(name);
        this.basePrice = basePrice;
        this.discount = discount;
    }

    @Override
    public double getPrice() {
        return basePrice * (1.0 - discount / 100.0);
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getDiscount() {
        return discount;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
    @Override
    public String toString() {
        return getName() + ": " + getPrice() + " (скидка " + discount + "%)";
    }
}
