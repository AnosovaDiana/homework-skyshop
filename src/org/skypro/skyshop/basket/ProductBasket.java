package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;

public class ProductBasket {
    private final Product[] products = new Product[5];
    private int count = 0;

    public int getSpecialProductsCount() {
        int specialCount = 0;
        for (int i = 0; i < count; i++) {
            if (products[i].isSpecial()) {
                specialCount++;
            }
        }
        return specialCount;
    }

    // Метод добавления продукта в корзину
    public void addProduct(Product product) {
        if (count < products.length) {
            products[count] = product;
            count++;
        } else {
            System.out.println("Невозможно добавить продукт");
        }

    }

    // Метод получения общей стоимости корзины
    public int getTotalPrice() {
        int totalPrice = 0;
        for (int i = 0; i < count; i++) {
            totalPrice += products[i].getPrice();
        }
        return totalPrice;
    }

    // Метод, который печатает содержимое корзины
    public void printBasket() {
        if (count == 0) {
            System.out.println("в корзине пусто");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println(products[i]);
        }

        System.out.println("Итого: " + getTotalPrice());
        System.out.println("Специальных товаров: " + getSpecialProductsCount());
    }



    // Метод, проверяющий продукт в корзине по имени
    public boolean hasProduct(String name) {
        for (int i = 0; i < count; i++) {
            if (products[i].getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // Метод очистки корзины
    public void clearBasket() {
        for (int i = 0; i < count; i++) {
            products[i] = null;
        }
        count = 0;
    }
}

