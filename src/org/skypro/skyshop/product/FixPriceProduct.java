package org.skypro.skyshop.product;

public class FixPriceProduct extends Product {
    private static final double fixedPrice = 99.0;

    public FixPriceProduct(String name) {
        super(name);
    }

    @Override
    public double getPrice() {
        return fixedPrice;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}
