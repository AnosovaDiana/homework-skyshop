package org.skypro.skyshop;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;

public class Main {
    public static void main(String[] args) {
        Product milk = new SimpleProduct("Молоко", 80);
        Product laptop = new DiscountedProduct("Ноутбук", 80000, 10);
        Product book = new DiscountedProduct("Книга", 1500, 15);
        Product mug = new FixPriceProduct("Фирменная кружка");
        ProductBasket basket = new ProductBasket();
        System.out.println("Добавляем товары в корзину");
        basket.addProduct(milk);
        basket.addProduct(laptop);
        basket.addProduct(book);
        basket.addProduct(mug);
        System.out.println("В корзину добавлено 4 товара");
        System.out.println("\nПечать содержимого корзины");
        basket.printBasket();
        System.out.println("\nПроверка поиска товаров");
        boolean hasMilk = basket.hasProduct("Молоко");
        System.out.println("Есть ли 'Молоко' в корзине? Ответ: " + hasMilk);
        boolean hasCoffee = basket.hasProduct("Кофе");
        System.out.println("Есть ли 'Кофе' в корзине? Ответ: " + hasCoffee);
        System.out.println("\nОчистка корзины");
        basket.clearBasket();
        System.out.println("Корзина очищена.\n");
    }
}