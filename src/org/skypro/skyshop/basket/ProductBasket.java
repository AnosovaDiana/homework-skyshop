package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;

import java.util.ArrayList;
import java.util.List;

public class ProductBasket {
    private final List<Product> products = new ArrayList<>();


    public int getSpecialProductsCount() {
        int specialCount = 0;
        for (Product product : products) {
            if (product.isSpecial()) {
                specialCount++;
            }
        }
        return specialCount;
    }

    // Метод добавления продукта в корзину
    public void addProduct(Product product) {
        products.add(product);
    }

    //метод удаления из корзины
    public List<Product> removeProductByName(String name) {
        List<Product> removedProducts = new ArrayList<>();
        java.util.Iterator<Product> iterator = products.iterator();

        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getName().equals(name)) {
                removedProducts.add(product);
                iterator.remove();
            }
        }
        return removedProducts;
    }


    // Метод получения общей стоимости корзины
    public int getTotalPrice() {
        int totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    // Метод, который печатает содержимое корзины
    public void printBasket() {
        if (products.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }
        for (Product product : products) {
            System.out.println(product);
        }

        System.out.println("Итого: " + getTotalPrice());
        System.out.println("Специальных товаров: " + getSpecialProductsCount());
    }


    // Метод, проверяющий продукт в корзине по имени
    public boolean hasProduct(String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // Метод очистки корзины
    public void clearBasket() {
        products.clear();
    }
}


