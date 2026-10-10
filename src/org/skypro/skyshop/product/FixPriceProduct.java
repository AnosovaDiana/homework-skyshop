package org.skypro.skyshop.product;

public class FixPriceProduct extends Product {
    private static final double FIXED_PRICE = 99.0;

    public FixPriceProduct(String name) {
        super(name);
    }

    @Override
    public double getPrice() {
        return FIXED_PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}
