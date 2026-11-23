package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.*;

public class ProductBasket {
    private final Map<String, List<Product>> products = new HashMap<>();

    public void addProduct(Product product) {
        products.computeIfAbsent(product.getName(), k -> new LinkedList<>()).add(product);
    }

    public int getTotalPrice() {
        int totalSum = 0;
        for (List<Product> productList : products.values()) {
            for (Product product : productList) {
                totalSum += product.getPrice();
            }
        }
        return totalSum;
    }

    public void printProductList() {
        if (products.isEmpty()) {
            System.out.println("Корзина пуста");
            return;
        } else {
            System.out.println("Содержимое корзины: ");
            int specialCount = 0;
            for (List<Product> productList : products.values()) {
                for (Product product : productList) {
                    System.out.println(product);
                    if (product.isSpecial()) {
                        specialCount++;
                    }
                }
            }
            System.out.println("Итого: " + getTotalPrice());
            System.out.println("Специальных товаров: " + specialCount);
        }
    }

    public boolean hasProduct(String targetName) {
        return products.containsKey(targetName);
    }

    public void clearBasket() {
        products.clear();
    }

    public List<Product> removeProductByName(String name) {
        List<Product> removed = products.remove(name);
        if (removed == null) {
            return Collections.emptyList(); // (или new LinkedList<>())
        }
        return removed;
    }
        }





